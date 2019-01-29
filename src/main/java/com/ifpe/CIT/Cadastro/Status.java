package com.ifpe.CIT.Cadastro;

public enum Status {

	ATIVO ("ATIVO"),
	INATIVO ("INATIVO");

	String text;

	Status(String t) {
		this.text = t;
	}

	public String getText() {
		return this.text;
	}
}

