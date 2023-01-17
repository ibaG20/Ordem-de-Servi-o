package com.gabi.os.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabi.os.domain.Cliente;
import com.gabi.os.domain.OS;
import com.gabi.os.domain.Tecnico;
import com.gabi.os.domain.enuns.Prioridade;
import com.gabi.os.domain.enuns.Status;
import com.gabi.os.repositories.ClienteRepository;
import com.gabi.os.repositories.OsRepository;
import com.gabi.os.repositories.TecnicoRepository;

@Service
public class DBService {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private OsRepository ordemServicoRepository;
	
	public void instanciaDB() {
		
		Tecnico t1 = new Tecnico(null, "Gabi", "659.099.400-58", "(88) 98888-8888");
		Tecnico t2 = new Tecnico(null, "Luci", "270.011.130-34", "(88) 98888-8888");
		Cliente c1 = new Cliente(null, "Jojo", "839.494.570-89", "(88) 91888-8888");
		OS os1 = new OS(null, Prioridade.ALTA, "Teste create OS", Status.ANDAMENTO, t1, c1);
		
		t1.getList().add(os1);
		c1.getList().add(os1);
		
		tecnicoRepository.saveAll(Arrays.asList(t1, t2));
		clienteRepository.saveAll(Arrays.asList(c1));
		ordemServicoRepository.saveAll(Arrays.asList(os1));
	}

}
