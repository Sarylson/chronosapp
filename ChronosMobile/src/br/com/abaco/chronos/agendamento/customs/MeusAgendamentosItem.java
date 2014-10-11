package br.com.abaco.chronos.agendamento.customs;

public class MeusAgendamentosItem {

	private long idRegistro;

	private String senha;

	private String data;

	private String unidadeAtendimento;

	public long getIdRegistro() {
		return idRegistro;
	}

	public void setIdRegistro( long idRegistro ) {
		this.idRegistro = idRegistro;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha( String senha ) {
		this.senha = senha;
	}

	public String getData() {
		return data;
	}

	public void setData( String data ) {
		this.data = data;
	}

	public String getUnidadeAtendimento() {
		return unidadeAtendimento;
	}

	public void setUnidadeAtendimento( String unidadeAtendimento ) {
		this.unidadeAtendimento = unidadeAtendimento;
	}

}
