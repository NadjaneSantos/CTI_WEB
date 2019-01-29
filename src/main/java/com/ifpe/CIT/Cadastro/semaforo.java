package com.ifpe.CIT.Cadastro;

import java.math.BigInteger;

@Entity
public class semaforo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger id;
		
	@Enumerated(Status.STRING)
	private Status status;
	
	public semaforo (BigInteger id, Status status) {
	super();
		this.id = id;
		this.status = status;

	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
