package br.com.abaco.modelo.senha;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.abaco.modelo.agendamento.AgendamentoVO;

public class SenhaVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private AgendamentoVO agendamento;

	private BigDecimal codigoNovaSenha;

	private BigDecimal status;

	private BigDecimal tipoSenha;

	private String numeroSenha;

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		SenhaVO other = (SenhaVO) obj;
		if ( agendamento == null ) {
			if ( other.agendamento != null )
				return false;
		} else if ( !agendamento.equals( other.agendamento ) )
			return false;
		if ( codigoNovaSenha == null ) {
			if ( other.codigoNovaSenha != null )
				return false;
		} else if ( !codigoNovaSenha.equals( other.codigoNovaSenha ) )
			return false;
		if ( id == null ) {
			if ( other.id != null )
				return false;
		} else if ( !id.equals( other.id ) )
			return false;
		if ( numeroSenha == null ) {
			if ( other.numeroSenha != null )
				return false;
		} else if ( !numeroSenha.equals( other.numeroSenha ) )
			return false;
		if ( status == null ) {
			if ( other.status != null )
				return false;
		} else if ( !status.equals( other.status ) )
			return false;
		if ( tipoSenha == null ) {
			if ( other.tipoSenha != null )
				return false;
		} else if ( !tipoSenha.equals( other.tipoSenha ) )
			return false;
		return true;
	}

	public AgendamentoVO getAgendamento() {
		return agendamento;
	}

	public BigDecimal getCodigoNovaSenha() {
		return codigoNovaSenha;
	}

	public Long getId() {
		return id;
	}

	public String getNumeroSenha() {
		return numeroSenha;
	}

	public BigDecimal getStatus() {
		return status;
	}

	public BigDecimal getTipoSenha() {
		return tipoSenha;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( agendamento == null ) ? 0 : agendamento.hashCode() );
		result = prime * result + ( ( codigoNovaSenha == null ) ? 0 : codigoNovaSenha.hashCode() );
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
		result = prime * result + ( ( numeroSenha == null ) ? 0 : numeroSenha.hashCode() );
		result = prime * result + ( ( status == null ) ? 0 : status.hashCode() );
		result = prime * result + ( ( tipoSenha == null ) ? 0 : tipoSenha.hashCode() );
		return result;
	}

	public void setAgendamento( AgendamentoVO agendamento ) {
		this.agendamento = agendamento;
	}

	public void setCodigoNovaSenha( BigDecimal codigoNovaSenha ) {
		this.codigoNovaSenha = codigoNovaSenha;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public void setNumeroSenha( String numeroSenha ) {
		this.numeroSenha = numeroSenha;
	}

	public void setStatus( BigDecimal status ) {
		this.status = status;
	}

	public void setTipoSenha( BigDecimal tipoSenha ) {
		this.tipoSenha = tipoSenha;
	}

}
