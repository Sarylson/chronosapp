package br.com.abaco.chronos.senhapainel.customs;

public class SenhaChamadaPainelItem {

	private long idRegistro;

	private String senha;

	private String situacao;

	private String dataHora;

	public String getDataHora() {
		return dataHora;
	}

	public long getIdRegistro() {
		return idRegistro;
	}

	public String getSenha() {
		return senha;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setDataHora( String dataHora ) {
		this.dataHora = dataHora;
	}

	public void setIdRegistro( long idRegistro ) {
		this.idRegistro = idRegistro;
	}

	public void setSenha( String senha ) {
		this.senha = senha;
	}

	public void setSituacao( String situacao ) {
		this.situacao = situacao;
	}
}
