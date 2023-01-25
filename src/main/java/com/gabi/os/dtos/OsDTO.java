package com.gabi.os.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gabi.os.domain.OS;
import com.gabi.os.domain.enuns.Prioridade;
import com.gabi.os.domain.enuns.Status;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OsDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataAbertura;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	
	private LocalDateTime dataFechamento;
	private Integer prioridade;
	private Integer status;
	
	@NotEmpty(message = "O campo OBSERVAÇÕES é requerido!")
	private String observacoes;
	
	private Integer tecnico;
	private Integer cliente;
	
	public OsDTO(OS os) {
		super();
		this.id = os.getId();
		this.dataAbertura = os.getDataAbertura();
		this.dataFechamento = os.getDataFechamento();
		this.prioridade = os.getPrioridade().getCod();
		this.status = os.getStatus().getCod();
		this.observacoes = os.getObservacoes();
		this.tecnico = os.getTecnico().getId();
		this.cliente = os.getCliente().getId();
	}
	
	public Status getStatus() {
		return Status.toEnum(this.status);
	}
	
	public Prioridade getPrioridade() {
		return Prioridade.toEnum(this.prioridade);
	}

	
}
