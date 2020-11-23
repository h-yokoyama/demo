package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.controller.request.MaterialPageRequest;
import com.example.demo.controller.request.MaterialPageRequest.SearchItem;
import com.example.demo.domain.MaterialPagingRepository;
import com.example.demo.domain.MaterialRepository;
import com.example.demo.domain.entity.Material;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MaterialService {

	private final MaterialRepository materialRepository;
	private final MaterialPagingRepository materialPagingRepository;

	public List<Material> findAll() {
		return materialRepository.findAll();
	}

	public Material save(String name, String tag1, String tag2, String tag3, String makerName, String makerCharge,
			String makerContact, String remarks) {
		return materialRepository.save(Material.newMaterial(name, tag1, tag2, tag3, makerName, makerCharge, makerContact, remarks));
	}

	public Material update(Long id, String name, String tag1, String tag2, String tag3, String makerName,
			String makerCharge, String makerContact, String remarks) {
		return materialRepository
				.save(Material.newMaterial(id, name, tag1, tag2, tag3, makerName, makerCharge, makerContact, remarks));
	}

	public void delete(Long id) {
		materialRepository.findById(id).ifPresent(currency -> materialRepository.delete(currency));
	}

	public Page<Material> findAllPageable(MaterialPageRequest request) {
		Optional<Specification<Material>> specification = createSpecification(request);
		if (specification.isPresent()) {
			return materialPagingRepository.findAll(specification.get(),
					PageRequest.of(request.getPage(), request.getSize()));
		} else {
			return materialPagingRepository.findAll(PageRequest.of(request.getPage(), request.getSize()));
		}
	}

	/**
	 * 検索条件を作成する
	 * @param request
	 * @return
	 */
    private Optional<Specification<Material>> createSpecification(MaterialPageRequest request) {
		String value = request.getValue();
		SearchItem item = request.getSearchItem();
		if (!StringUtils.isEmpty(value) && item != null) {
			switch (request.getSearchItem()) {
			case MATERIAL:
				return Optional.of(Specification.where(this.materialNameLike(value)));
			case TAG:
				return Optional.of(Specification.where(this.materialTag1Like(value)).or(this.materialTag2Like(value))
						.or(this.materialTag3Like(value)));
			case MAKER:
				return Optional.of(Specification.where(this.materialMakerNameLike(value)));
			default:
			}
		}
		return Optional.empty();
	}

	@SuppressWarnings("serial")
	private Specification<Material> materialNameLike(String material) {
        return StringUtils.isEmpty(material) ? null : new Specification<Material>() {
            @Override
            public Predicate toPredicate(Root<Material> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
            	return criteriaBuilder.like(root.get("name"), "%" + material + "%");
            }
        };
    }
    
    @SuppressWarnings("serial")
	private Specification<Material> materialTag1Like(String tag) {
        return StringUtils.isEmpty(tag) ? null : new Specification<Material>() {
            @Override
            public Predicate toPredicate(Root<Material> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
            	return criteriaBuilder.like(root.get("tag1"), "%" + tag + "%");
            }
        };
    }
    
    @SuppressWarnings("serial")
	private Specification<Material> materialTag2Like(String tag) {
        return StringUtils.isEmpty(tag) ? null : new Specification<Material>() {
            @Override
            public Predicate toPredicate(Root<Material> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
            	return criteriaBuilder.like(root.get("tag2"), "%" + tag + "%");
            }
        };
    }
    
    @SuppressWarnings("serial")
	private Specification<Material> materialTag3Like(String tag) {
        return StringUtils.isEmpty(tag) ? null : new Specification<Material>() {
            @Override
            public Predicate toPredicate(Root<Material> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
            	return criteriaBuilder.like(root.get("tag3"), "%" + tag + "%");
            }
        };
    }
    
    @SuppressWarnings("serial")
	private Specification<Material> materialMakerNameLike(String makerName) {
        return StringUtils.isEmpty(makerName) ? null : new Specification<Material>() {
            @Override
            public Predicate toPredicate(Root<Material> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
            	return criteriaBuilder.like(root.get("maker_name"), "%" + makerName + "%");
            }
        };
    }
}