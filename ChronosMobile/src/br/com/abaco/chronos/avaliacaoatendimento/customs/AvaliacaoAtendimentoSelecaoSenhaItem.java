package br.com.abaco.chronos.avaliacaoatendimento.customs;

/**
 * Classe respons�vel pelo valores do listview customizado de avalia��o
 * 
 * @author Jackson Silva
 */
public class AvaliacaoAtendimentoSelecaoSenhaItem {

	private long idRegistro;

	private String senha;

	private String data;

	private String servico;

	private String avaliacao;

	public String getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao( String avaliacao ) {
		this.avaliacao = avaliacao;
	}

	public String getData() {
		return data;
	}

	public long getIdRegistro() {
		return idRegistro;
	}

	public String getSenha() {
		return senha;
	}

	public String getServico() {
		return servico;
	}

	public void setData( String data ) {
		this.data = data;
	}

	public void setIdRegistro( long idRegistro ) {
		this.idRegistro = idRegistro;
	}

	public void setSenha( String senha ) {
		this.senha = senha;
	}

	public void setServico( String servico ) {
		this.servico = servico;
	}

}
