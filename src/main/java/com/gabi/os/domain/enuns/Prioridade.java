package com.gabi.os.domain.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Prioridade {
	
	BAIXA(0, "BAIXA"),
	MEDIA(1, "MEDIA"),
	ALTA(2, "ALTA");
	
	private Integer cod;
	private String descricao;
	
	public static Prioridade toEnum(Integer cod) {
		
		//SE O COD FOR NULL ENT RETORNA NULL
		if(cod == null) {
			return null;
		}
		
		//PRA CADA X(PRIORIDADE), PEGANDO OS VALORES DA CLASSE PRIORIDADE
		//SE O EXISTIR O COD(0, 1, 2), RETORNA O COD
		for(Prioridade x : Prioridade.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		//SE FOR UM COD INVALIDO(!0, !1, !2), RETORNA ESSA STRING
		throw new IllegalArgumentException("Prioridade inválida" + cod);
	} 

}
