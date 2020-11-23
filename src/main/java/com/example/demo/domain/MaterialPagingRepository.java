package com.example.demo.domain;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entity.Material;

@Repository
public interface MaterialPagingRepository extends PagingAndSortingRepository<Material, Long>, JpaSpecificationExecutor<Material> {
}