package br.com.rbsystems.cobranca.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Local {
	
	@Id
	private Long id;
	
	private String local;

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

}
