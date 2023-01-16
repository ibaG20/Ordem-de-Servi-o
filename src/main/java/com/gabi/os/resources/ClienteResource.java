package com.gabi.os.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gabi.os.domain.Cliente;
import com.gabi.os.dtos.ClienteDTO;
import com.gabi.os.services.ClienteService;

@CrossOrigin("*") 
@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	@Autowired
	ClienteService clienteService;
	
	//--------------------------------------------------------------------GET
	
	@GetMapping(value= "/{id}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id){
		
		ClienteDTO clienteDTO = new ClienteDTO(clienteService.findById(id));
		return ResponseEntity.ok().body(clienteDTO);
	}
	
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll(){
		
		List<ClienteDTO> listDTO = clienteService.findAll()
				.stream()
				.map(obj -> new ClienteDTO(obj))
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(listDTO);
	}
	
	//--------------------------------------------------------------------POST
	
	@PostMapping
	public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO clienteDTO){
		
		Cliente cliente = clienteService.create(clienteDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(cliente.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	//--------------------------------------------------------------------PUT
	
	@PutMapping("/{id}")
	public ResponseEntity<ClienteDTO> update (@PathVariable Integer id, @Valid @RequestBody ClienteDTO clienteDTO){
		
		ClienteDTO newCliente = new ClienteDTO(clienteService.update(id, clienteDTO));
		return ResponseEntity.ok().body(newCliente);
	}
	
	//--------------------------------------------------------------------DELETE
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete (@PathVariable Integer id){
		
		clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}
	

}
