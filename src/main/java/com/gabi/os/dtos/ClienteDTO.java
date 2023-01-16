package com.gabi.os.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import com.gabi.os.domain.Cliente;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClienteDTO implements Serializable{
	
//	OBS: PENSE EM SERIALIZAR UMA CLASSE QUANDO VC QUER PERSISTIR ELA
//	     EM ALGUM ARQUIVO, COMO O XML
//	SUA PRINCIPAL FINALIDADE É SALVAR O ESTADO DE UM OBJ E RECRIA-LO QUANDO NECESSÁRIO
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotEmpty(message = "O campo NOME é requerido!")
	private String nome;
	
	@CPF
	@NotEmpty(message = "O campo CPF é requerido!")
	private String cpf;
	
	@NotEmpty(message = "O campo TELEFONE é requerido!")
	private String telefone;
	

	public ClienteDTO(Cliente cliente) {
		super();
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.cpf = cliente.getCpf();
		this.telefone = cliente.getTelefone();
	}
	
	

}
