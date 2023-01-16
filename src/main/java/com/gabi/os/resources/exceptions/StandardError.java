package com.gabi.os.resources.exceptions;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

//@AllArgsConstructor
@Getter
@Setter
public class StandardError implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long timestamp;
	private Integer status;
	private String error;
	
	public StandardError() {
		super();
	}

	public StandardError(Long timestamp, Integer status, String error) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
	}
	
	
}
