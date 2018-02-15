package mz.ciuem.sipma.entity;

import javax.persistence.Entity;
import javax.persistence.Table;


public class User_Session {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String user_name;
	
	private long id_orgao;
	
	
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public long getId_orgao() {
		return id_orgao;
	}
	public void setId_orgao(long id_orgao) {
		this.id_orgao = id_orgao;
	}


}
