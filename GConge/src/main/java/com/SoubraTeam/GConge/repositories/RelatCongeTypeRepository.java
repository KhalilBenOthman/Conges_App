package com.SoubraTeam.GConge.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.SoubraTeam.GConge.domain.RelatCongeType;
@Repository
public interface RelatCongeTypeRepository extends CrudRepository<RelatCongeType, Long>{
	
	RelatCongeType findByTACongeCode(String code);

}
