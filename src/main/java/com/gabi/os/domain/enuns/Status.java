package com.gabi.os.domain.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Status {
	
	ABERTO(0, "ABERTO"),
	ANDAMENTO(1, "ANDAMENTO"),
	ENCERRADO(2, "ENCERRADO");
	
	private Integer cod;
	private String descricao;
	
	public static Status toEnum(Integer cod) {
		
		//SE O COD FOR NULL ENT RETORNA NULL
		if(cod == null) {
			return null;
		}
		
		//PRA CADA X(PRIORIDADE), PEGANDO OS VALORES DA CLASSE PRIORIDADE
		//SE O EXISTIR O COD(0, 1, 2), RETORNA O COD
		for(Status x : Status.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		//SE FOR UM COD INVALIDO(!0, !1, !2), RETORNA ESSA STRING
		throw new IllegalArgumentException("Status inválido" + cod);
	}
}
