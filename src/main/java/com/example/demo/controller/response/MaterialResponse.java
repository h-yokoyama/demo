package com.example.demo.controller.response;

import java.util.List;

import com.example.demo.domain.entity.Material;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MaterialResponse {
	
	private List<Material> materials;
}
