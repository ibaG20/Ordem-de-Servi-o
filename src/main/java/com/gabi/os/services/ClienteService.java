package com.gabi.os.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabi.os.domain.Cliente;
import com.gabi.os.domain.Pessoa;
import com.gabi.os.dtos.ClienteDTO;
import com.gabi.os.repositories.ClienteRepository;
import com.gabi.os.repositories.PessoaRepository;
import com.gabi.os.services.exceptions.DataIntegratyViolationException;
import com.gabi.os.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	public ClienteRepository clienteRepository;
	@Autowired
	private PessoaRepository pessoaRepository;

	public Cliente findById(Integer id) {

		Optional<Cliente> optional = clienteRepository.findById(id);
		return optional.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id:" + id + ", Tipo: " + Cliente.class.getName()));
	}

	public List<Cliente> findAll() {

		return clienteRepository.findAll();
	}

	public Cliente create(ClienteDTO clienteDTO) {
		
		if(findByCPF(clienteDTO) != null) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}

		return clienteRepository
				.save(new Cliente(null, clienteDTO.getNome(), clienteDTO.getCpf(), clienteDTO.getTelefone()));
	}
	
	public Cliente update(Integer id, ClienteDTO clienteDTO) {
		
		Cliente oldCliente = findById(id);
		
		if(findByCPF(clienteDTO) != null && findByCPF(clienteDTO).getId() != id) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}
		
		oldCliente.setNome(clienteDTO.getNome());
		oldCliente.setCpf(clienteDTO.getCpf());
		oldCliente.setTelefone(clienteDTO.getTelefone());
		
		return clienteRepository.save(oldCliente);
	}

	private Pessoa findByCPF(ClienteDTO clienteDTO) {

		Pessoa pessoa = pessoaRepository.findByCpf(clienteDTO.getCpf());

		if (pessoa != null) {
			return pessoa;
		}
		return null;
	}

	public void delete(Integer id) {
		
		Cliente cliente = findById(id);
		
		if(cliente.getList().size() > 0) {
			throw new DataIntegratyViolationException("Tecnico possui Ordem de Serviço. Não pode ser deletado!");			
		}
		
		clienteRepository.deleteById(id);
	}

}
