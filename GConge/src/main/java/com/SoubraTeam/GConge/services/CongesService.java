package com.SoubraTeam.GConge.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SoubraTeam.GConge.domain.Conges;
import com.SoubraTeam.GConge.domain.RelatCongeType;
import com.SoubraTeam.GConge.domain.TA_Conges;
import com.SoubraTeam.GConge.exceptions.TA_CongesCodeException;
import com.SoubraTeam.GConge.exceptions.TA_CongesNotFoundException;
import com.SoubraTeam.GConge.repositories.CongesRepository;
import com.SoubraTeam.GConge.repositories.RelatCongeTypeRepository;
import com.SoubraTeam.GConge.repositories.TA_CongesRepository;

@Service
public class CongesService {
	
	@Autowired
	private RelatCongeTypeRepository relatCongeTypeRepository;
	@Autowired
	private CongesRepository congesRepository ;
	@Autowired
	private TA_CongesRepository ta_CongesRepository;
	
	
	
	public Conges addConges(String TACongeCode, Conges conge) {
		
		try {
		
			RelatCongeType relatCongeType = relatCongeTypeRepository.findByTACongeCode(TACongeCode);
			conge.setRelatCongeType(relatCongeType);
			conge.setTACongeCode(TACongeCode);
			conge.setCodeSequence(TACongeCode+"-"+conge.getCode());
			return congesRepository.save(conge);
		
		}catch(Exception e){
			throw new TA_CongesNotFoundException("La famille de congés "+TACongeCode+" n'existe pas.");
		}
	}

	public Iterable<Conges> findRelatCongeTypeById(String code){
		TA_Conges ta_Conges = ta_CongesRepository.findBycode(code);
		if (ta_Conges == null) {
			throw new TA_CongesNotFoundException("La famille de congés "+code+" n'existe pas.");
		}
		return congesRepository.findByTACongeCode(code);
	}

	public Conges findByCodeSequence(String RelatCongesType_id,String Conges_id) {
		RelatCongeType relatCongesType = relatCongeTypeRepository.findByTACongeCode(RelatCongesType_id);
		if(relatCongesType == null) throw new TA_CongesNotFoundException("La famille de congés "+RelatCongesType_id+" n'existe pas.");
		
		Conges conge = congesRepository.findBycodeSequence(RelatCongesType_id+"-"+Conges_id);
		if(conge == null) throw new TA_CongesNotFoundException("Congé avec le code "+Conges_id+" n'existe pas.");
		
		if(!conge.getCode().equals(Conges_id)) throw new TA_CongesNotFoundException("Congé avec le code "+Conges_id+" n'est pas lié a la famille de congé "+RelatCongesType_id);
		
		return conge;
	}
	public Conges findByCode(String RelatCongesType_id,String Conges_id) {
		RelatCongeType relatCongesType = relatCongeTypeRepository.findByTACongeCode(RelatCongesType_id);
		if(relatCongesType == null) throw new TA_CongesNotFoundException("La famille de congés "+RelatCongesType_id+" n'existe pas.");
		
		Conges conge = congesRepository.findBycode(Conges_id);
		if(conge == null) throw new TA_CongesNotFoundException("Congé avec le code "+Conges_id+" n'existe pas.");
		
		if(!conge.getCode().equals(Conges_id)) throw new TA_CongesNotFoundException("Congé avec le code "+Conges_id+" n'est pas lié a la famille de congé "+RelatCongesType_id);
		
		return conge;
	}

	public Conges updateByCode(Conges updatedConge, String RelatCongesType_id, String Conge_id) {
		Conges conge = findByCode(RelatCongesType_id,Conge_id);
		conge = updatedConge;
		return congesRepository.save(conge);
			
	}

	public void deleteCongeByCode(String RelatCongesType_id, String Conge_id) {
		Conges conge = findByCode(RelatCongesType_id,Conge_id);
		//RelatCongeType relatCongesType = relatCongeTypeRepository.findByTACongeCode(RelatCongesType_id);
		//List<Conges> pts = relatCongesType.getConges();
		//pts.remove(conge);
		//relatCongeTypeRepository.save(relatCongesType);
		
		
		
		congesRepository.delete(conge);
	}
}
