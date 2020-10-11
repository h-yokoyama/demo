package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.domain.MaterialRepository;
import com.example.demo.domain.entity.Material;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MaterialService {

	private final MaterialRepository materialRepository;

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
}