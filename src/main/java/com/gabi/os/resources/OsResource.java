package com.gabi.os.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gabi.os.dtos.OsDTO;
import com.gabi.os.services.OsService;

@RestController
@RequestMapping(value = "/os")
public class OsResource {
	
	@Autowired
	private OsService osService;
	
	//--------------------------------------------------------------------GET

	@GetMapping("/{id}")
	public ResponseEntity<OsDTO> findById(@PathVariable Integer id){
		
		OsDTO osDTO = new OsDTO(osService.findById(id));
		return ResponseEntity.ok().body(osDTO);
	}
	
	@GetMapping
	public ResponseEntity<List<OsDTO>> findAll(){
		
		List<OsDTO> list = osService.findAll()
				.stream()
				.map(obj -> new OsDTO(obj))
				.collect(Collectors.toList());
		
		return ResponseEntity.ok().body(list);
	}
	
	//--------------------------------------------------------------------GET
	
	@PostMapping
	public ResponseEntity<OsDTO> create(@Valid @RequestBody OsDTO osDTO){
		
		osDTO = new OsDTO(osService.create(osDTO));
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(osDTO.getId())
				.toUri();
		
		return ResponseEntity.created(uri).build();
	}
}








