package com.gabi.os.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabi.os.domain.Cliente;
import com.gabi.os.domain.OS;
import com.gabi.os.domain.Tecnico;
import com.gabi.os.dtos.OsDTO;
import com.gabi.os.repositories.OsRepository;
import com.gabi.os.services.exceptions.ObjectNotFoundException;

@Service
public class OsService {
	
	@Autowired
	private OsRepository osRepository;
	
	@Autowired
	private TecnicoService tecnicoService;
	
	@Autowired
	private ClienteService clienteService;
	
	public OS findById(Integer id) {
		
		Optional<OS> optional = osRepository.findById(id);
		return optional.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + OS.class.getName()));
	} 
	
	public List<OS> findAll(){
		return osRepository.findAll();
	}

	public OS create(@Valid OsDTO osDTO) {
		
		return fromDTO(osDTO);
	}
	
	public OS update(@Valid OsDTO osDTO) {
		
		findById(osDTO.getId());
		return fromDTO(osDTO);
	}
	
	private OS fromDTO(OsDTO os) {
		
		OS newOs = new OS();
		
		newOs.setId(os.getId());
		newOs.setObservacoes(os.getObservacoes());
		newOs.setPrioridade(os.getPrioridade());
		newOs.setStatus(os.getStatus());
		
		Tecnico tecnico = tecnicoService.findById(os.getTecnico());
		Cliente cliente = clienteService.findById(os.getCliente());
		
		newOs.setTecnico(tecnico);
		newOs.setCliente(cliente);
		
		if(newOs.getStatus().getCod().equals(2)) {
			newOs.setDataFechamento(LocalDateTime.now());
		}
		
		return osRepository.save(newOs);
		
	}


}
