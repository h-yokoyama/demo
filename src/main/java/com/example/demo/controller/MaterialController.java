package com.example.demo.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.request.MaterialAddRequest;
import com.example.demo.controller.request.MaterialUpdateRequest;
import com.example.demo.controller.request.PageRequest;
import com.example.demo.controller.response.MaterialResponse;
import com.example.demo.domain.entity.Material;
import com.example.demo.service.MaterialService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MaterialController {

	private static final String URL_MATERIAL = "/material/";
	private static final String URL_FIND_ALL = URL_MATERIAL + "findAll";
	private static final String URL_UPDATE = URL_MATERIAL + "update";
	private static final String URL_SAVE = URL_MATERIAL + "save";
	private static final String URL_DELETE = URL_MATERIAL + "delete";
	private static final String URL_FIND_ALL_PAGEABLE = URL_MATERIAL + "findAllPageable";

	private final MaterialService materialService;

	@GetMapping(URL_FIND_ALL)
	public ResponseEntity<MaterialResponse> findAll() {
		List<Material> materials = materialService.findAll();
		MaterialResponse materialResponse = MaterialResponse.builder().materials(materials).build();
		return new ResponseEntity<>(materialResponse, HttpStatus.OK);
	}
	
	@PostMapping(URL_FIND_ALL_PAGEABLE)
	public ResponseEntity<MaterialResponse> findAllPageable(@RequestBody PageRequest request) {
		Page<Material> materials = materialService.findAllPageable(request.getPage(), request.getSize());
		MaterialResponse materialResponse = MaterialResponse.builder().
				materials(materials.getContent()).
				totalElements(materials.getTotalElements()).
				totalPages(materials.getTotalPages()).
				page(materials.getNumber()).
				build();
		return new ResponseEntity<>(materialResponse, HttpStatus.OK);
	}

	@PostMapping(URL_SAVE)
	public ResponseEntity<HttpStatus> save(@RequestBody MaterialAddRequest request) {
		materialService.save(request.getName(), request.getTag1(), request.getTag2(), request.getTag3(),
				request.getMakerName(), request.getMakerCharge(), request.getMakerContact(), request.getRemarks());
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PostMapping(URL_UPDATE)
    public ResponseEntity<HttpStatus> update(@RequestBody MaterialUpdateRequest request) {
        materialService.update(request.getId(),request.getName(), request.getTag1(), request.getTag2(), request.getTag3(),
				request.getMakerName(), request.getMakerCharge(), request.getMakerContact(), request.getRemarks());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

	@GetMapping(URL_DELETE + "/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
		materialService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
