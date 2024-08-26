package com.kdigital.test3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdigital.test3.entity.FitnessEntity;

public interface FitnessRepository extends JpaRepository<FitnessEntity, Integer> {
	
}