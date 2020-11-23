package com.example.demo.filter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.excpetion.BadCredentialsException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SigningKeyResolverAdapter;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Component
public class LoginFilter extends OncePerRequestFilter {

	@Autowired
	ObjectMapper objectMapper;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			SecurityContextHolder.getContext()
			.setAuthentication(new PreAuthenticatedAuthenticationToken(auth(request), null));			
			filterChain.doFilter(request, response);
		} catch (BadCredentialsException e) {// トークンエラー
			// ExceptionHandlerControllerAdvice でハンドリングできないためここでレスポンスを生成する
			ResponseEntity<String> errorResponse = new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
			response.setContentType("application/json;charset=UTF-8"); 
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.getWriter().write(convertObjectToJson(errorResponse));
		}
	}

	// ログインユーザ情報を取得
	private String auth(HttpServletRequest request) throws BadCredentialsException {
		// リクエストヘッダからJWTを取り出す
		String token = getToken(request);
		try {
			// JWTを検証、クレーム取得
			// 検証に失敗したら、例外がスローされる。
			Jws<Claims> claim = Jwts.parser().setSigningKeyResolver(new GoogleSigningKeyResolver()) // 独自のリゾルバが必要
					.parseClaimsJws(token);
			// クレームのボディ部分からuidを取得
			String uid = (String) claim.getBody().get("user_id");
			return uid;
		} catch (Exception e) {
			throw new BadCredentialsException("トークンが無効です");
		}
	}

	// リクエストヘッダからトークンを取得します。
	private String getToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token == null || !token.startsWith("Bearer ")) {
			return null;
		}
		return token.substring("Bearer ".length());
	}

	/**
	 * 署名に利用する公開鍵を返却します。
	 */
	public class GoogleSigningKeyResolver extends SigningKeyResolverAdapter {

		@SuppressWarnings("rawtypes")
		@Override
		public Key resolveSigningKey(JwsHeader jwsHeader, Claims claims) {

			try {
				Map<String, Object> map = getJwks();

				if (map.isEmpty()) {
					return null;
				}

				String keyValue = (String) map.get(jwsHeader.getKeyId());

				if (keyValue == null) {
					return null;
				}
				// 開始（BEGIN）と終了（END）のラベルを除去する。
				keyValue = keyValue.replaceAll("-----BEGIN CERTIFICATE-----\n", "")
						.replaceAll("-----END CERTIFICATE-----\n", "");

				InputStream in = new ByteArrayInputStream(Base64.decodeBase64(keyValue.getBytes("UTF-8")));
				X509Certificate certificate = (X509Certificate) CertificateFactory.getInstance("X.509")
						.generateCertificate(in);
				return certificate.getPublicKey();

			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		/**
		 * GoogleからJWKSを取得します。
		 * 
		 * @return JWKS
		 */
		private Map<String, Object> getJwks() {
			OkHttpClient client = new OkHttpClient();
			Request request = new Request.Builder()
					.url("https://www.googleapis.com/robot/v1/metadata/x509/securetoken@system.gserviceaccount.com")
					.build();
			try (Response response = client.newCall(request).execute()) {
				return objectMapper.readValue(response.body().string(), new TypeReference<Map<String, Object>>() {
				});
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	private String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}
