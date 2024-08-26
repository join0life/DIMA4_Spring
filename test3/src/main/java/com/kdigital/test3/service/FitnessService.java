package com.kdigital.test3.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;

import com.kdigital.test3.dto.FitnessDTO;
import com.kdigital.test3.entity.FitnessEntity;
import com.kdigital.test3.repository.FitnessRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class FitnessService {
	
	final FitnessRepository repository;
	
	public void insert(FitnessDTO fitness) {
		FitnessEntity fitnessEntity = FitnessEntity.toEntity(fitness);
		repository.save(fitnessEntity);
	}
	
	public List<FitnessDTO> selectAll(){
		List<FitnessDTO> list = new ArrayList<>();
		List<FitnessEntity> entityList = repository.findAll();
		
		entityList.forEach((entity) -> list.add(FitnessDTO.toDTO(entity)));
		
		return list;
	}
	
	public void deleteOne(Integer seq) {
		repository.deleteById(seq);
	}
	
	public FitnessDTO selectOne(Integer seq) {
		Optional<FitnessEntity> entity = repository.findById(seq);
		if(entity.isPresent()) {
			FitnessEntity fitnessEntity = entity.get();
			return FitnessDTO.toDTO(fitnessEntity);
		}
		
		return null;
	}
	
	@Transactional
	public void updateOne(FitnessDTO fitness) {
		Optional<FitnessEntity> entity = repository.findById(fitness.getSeq());
		if(entity.isPresent()) {
			FitnessEntity fitnessEntity = entity.get();
			
			fitnessEntity.setGender(fitness.isGender());
			fitnessEntity.setHeight(fitness.getHeight());
			fitnessEntity.setWeight(fitness.getWeight());
			fitnessEntity.setStdWeight(fitness.getStdWeight());
			fitnessEntity.setBmi(fitness.getBmi());
			fitnessEntity.setBmiResult(fitness.getBmiResult());
		}
	}
}
