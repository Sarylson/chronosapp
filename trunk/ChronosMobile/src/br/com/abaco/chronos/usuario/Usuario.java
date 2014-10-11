package br.com.abaco.chronos.usuario;

import java.io.Serializable;


public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nomeUsuario;

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario( String nomeUsuario ) {
		this.nomeUsuario = nomeUsuario;
	}

	private String login;

	private String senha;

	public String getLogin() {
		return login;
	}

	public void setLogin( String login ) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha( String senha ) {
		this.senha = senha;
	}
}

