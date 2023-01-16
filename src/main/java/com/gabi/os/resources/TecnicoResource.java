package com.gabi.os.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gabi.os.domain.Tecnico;
import com.gabi.os.dtos.TecnicoDTO;
import com.gabi.os.services.TecnicoService;

@RestController
@RequestMapping(value="/tecnicos")
public class TecnicoResource {
	
	@Autowired
	private TecnicoService tecnicoService;
	
	//--------------------------------------------------------------------GET
	
	@GetMapping(value="/{id}")
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id){
		
//		Tecnico tecnico = tecnicoService.findById(id);
//		TecnicoDTO tecnicoDTO = new TecnicoDTO(tecnico);
		
		TecnicoDTO tecnicoDTO = new TecnicoDTO(tecnicoService.findById(id));
	
		return ResponseEntity.ok().body(tecnicoDTO);
	}
	
	@GetMapping
	public ResponseEntity<List<TecnicoDTO>> findAll(){
		
		List<TecnicoDTO> listDTO = tecnicoService.findAll()
				.stream()
				.map(obj -> new TecnicoDTO(obj))
				.collect(Collectors.toList());
		return ResponseEntity.ok(listDTO);
		
//      QUE É IGUAL A:
		
//		List<Tecnico> list = tecnicoService.findAll();
//		List<TecnicoDTO> listDTO = new ArrayList<>();
//		
//		//FORMAS DE TRANSFORMAR DA ENTITY PRO DTO
// 		for(Tecnico obj : list) {
//			listDTO.add(new TecnicoDTO(obj));
//		}
//		
//		//OU
//		list.forEach(obj -> listDTO.add(new TecnicoDTO(obj)));
	}
	
	//--------------------------------------------------------------------POST
	
	@PostMapping
	public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO tecnicoDTO){
		
		Tecnico tecnico = tecnicoService.create(tecnicoDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(tecnico.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	//--------------------------------------------------------------------PUT
	
	@PutMapping(value="/{id}")
	public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id, @Valid @RequestBody TecnicoDTO tecnicoDTO){
		
		TecnicoDTO newTecnico = new TecnicoDTO(tecnicoService.update(id, tecnicoDTO));
		return ResponseEntity.ok().body(newTecnico);
		
	}
	
	//--------------------------------------------------------------------DELETE
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		
		tecnicoService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
