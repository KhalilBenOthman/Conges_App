package com.SoubraTeam.GConge.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.SoubraTeam.GConge.domain.Conges;

public interface CongesRepository extends CrudRepository<Conges, Long>{
	
	List<Conges> findByTACongeCode(String id);
	Conges findBycodeSequence(String sequence);
	Conges findBycode(String code);

}
