package com.gabi.os.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import com.gabi.os.domain.Tecnico;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TecnicoDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message = "O campo NOME é requerido!")
	private String nome;
	
	@CPF //DAVA PRA COLOCAR A ANOTAÇÃO UNIQUE PRA NÃO DEIXAR COLOCAR CPFs IGUAIS, MAS FIZEMOS DIFERENTE
	@NotEmpty(message = "O campo CPF é requerido!")
	private String cpf;
	
	@NotEmpty(message = "O campo TELEFONE é requerido!")
	private String telefone;
	
	public TecnicoDTO() {
		super();
	}

	public TecnicoDTO(Tecnico tecnico) {
		super();
		this.id = tecnico.getId();
		this.nome = tecnico.getNome();
		this.cpf = tecnico.getCpf();
		this.telefone = tecnico.getTelefone();
	}

}
