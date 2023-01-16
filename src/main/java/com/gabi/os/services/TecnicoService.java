package com.gabi.os.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabi.os.domain.Pessoa;
import com.gabi.os.domain.Tecnico;
import com.gabi.os.dtos.TecnicoDTO;
import com.gabi.os.repositories.PessoaRepository;
import com.gabi.os.repositories.TecnicoRepository;
import com.gabi.os.services.exceptions.DataIntegratyViolationException;
import com.gabi.os.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;

	public Tecnico findById(Integer id) {
		
		Optional<Tecnico> optional = tecnicoRepository.findById(id);
		return optional.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id:" + id + ", Tipo: " + Tecnico.class.getName()));
	}

	public List<Tecnico> findAll() {
		
		return tecnicoRepository.findAll();
	}
	
	public Tecnico create(TecnicoDTO tecnicoDTO) {
		
		if(findByCPF(tecnicoDTO) != null) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}
		
		return tecnicoRepository.save(new Tecnico(null, tecnicoDTO.getNome(), tecnicoDTO.getCpf(), tecnicoDTO.getTelefone()));
	}
	
	public Tecnico update(Integer id, TecnicoDTO tecnicoDTO) {
		
		Tecnico oldTecnico = findById(id);
		
		if(findByCPF(tecnicoDTO) != null && findByCPF(tecnicoDTO).getId() != id) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}
		
		oldTecnico.setNome(tecnicoDTO.getNome());
		oldTecnico.setCpf(tecnicoDTO.getCpf());
		oldTecnico.setTelefone(tecnicoDTO.getTelefone());
		
		return tecnicoRepository.save(oldTecnico);
	}
	
	public void delete(Integer id) {
		
		Tecnico tecnico = findById(id);
		
		if(tecnico.getList().size() > 0) {
			throw new DataIntegratyViolationException("Tecnico possui Ordem de Serviço. Não pode ser deletado!");			
		}
		
		tecnicoRepository.deleteById(id);
	}
	
	private Pessoa findByCPF(TecnicoDTO tecnicoDTO) {
		
		Pessoa pessoa = pessoaRepository.findByCpf(tecnicoDTO.getCpf());
		
		if(pessoa != null) {
			return pessoa;
		}
		return null;
	}
	
}
