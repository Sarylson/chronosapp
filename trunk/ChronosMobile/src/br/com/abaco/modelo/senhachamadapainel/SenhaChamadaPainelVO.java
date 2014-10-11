package br.com.abaco.modelo.senhachamadapainel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import br.com.abaco.modelo.agendamento.AgendamentoVO;
import br.com.abaco.modelo.unidadenegocio.UnidadeNegocioVO;

public class SenhaChamadaPainelVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private UnidadeNegocioVO UnidadeNegocioVO;

	private AgendamentoVO agendamentoVO;

	private Date dataHoraChamada;

	private BigDecimal senhaPainel;

	private String nomeUnidadeNegocio;

	private String numeroSenha;
	
	private String dataHoraChamadaFormatada;

	
	@Override
	public boolean equals( Object obj ) {
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		SenhaChamadaPainelVO other = (SenhaChamadaPainelVO) obj;
		if ( UnidadeNegocioVO == null ) {
			if ( other.UnidadeNegocioVO != null )
				return false;
		} else if ( !UnidadeNegocioVO.equals( other.UnidadeNegocioVO ) )
			return false;
		if ( agendamentoVO == null ) {
			if ( other.agendamentoVO != null )
				return false;
		} else if ( !agendamentoVO.equals( other.agendamentoVO ) )
			return false;
		if ( dataHoraChamada == null ) {
			if ( other.dataHoraChamada != null )
				return false;
		} else if ( !dataHoraChamada.equals( other.dataHoraChamada ) )
			return false;
		if ( id == null ) {
			if ( other.id != null )
				return false;
		} else if ( !id.equals( other.id ) )
			return false;
		if ( nomeUnidadeNegocio == null ) {
			if ( other.nomeUnidadeNegocio != null )
				return false;
		} else if ( !nomeUnidadeNegocio.equals( other.nomeUnidadeNegocio ) )
			return false;
		if ( numeroSenha == null ) {
			if ( other.numeroSenha != null )
				return false;
		} else if ( !numeroSenha.equals( other.numeroSenha ) )
			return false;
		if ( senhaPainel == null ) {
			if ( other.senhaPainel != null )
				return false;
		} else if ( !senhaPainel.equals( other.senhaPainel ) )
			return false;
		return true;
	}

	
	public AgendamentoVO getAgendamentoVO() {
		return agendamentoVO;
	}

	public Date getDataHoraChamada() {
		return dataHoraChamada;
	}

	public String getDataHoraChamadaFormatada() {
		
	  if( dataHoraChamada != null){
				return new SimpleDateFormat("dd/MM/yyyy HH:mm",new Locale( "pt","BR" )).format( dataHoraChamada );
			}
		return dataHoraChamadaFormatada;
	}

	public Long getId() {
		return id;
	}

	public String getNomeUnidadeNegocio() {
		return nomeUnidadeNegocio;
	}

	public String getNumeroSenha() {
		return numeroSenha;
	}

	public BigDecimal getSenhaPainel() {
		return senhaPainel;
	}

	public UnidadeNegocioVO getUnidadeNegocioVO() {
		return UnidadeNegocioVO;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( UnidadeNegocioVO == null ) ? 0 : UnidadeNegocioVO.hashCode() );
		result = prime * result + ( ( agendamentoVO == null ) ? 0 : agendamentoVO.hashCode() );
		result = prime * result + ( ( dataHoraChamada == null ) ? 0 : dataHoraChamada.hashCode() );
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
		result = prime * result + ( ( nomeUnidadeNegocio == null ) ? 0 : nomeUnidadeNegocio.hashCode() );
		result = prime * result + ( ( numeroSenha == null ) ? 0 : numeroSenha.hashCode() );
		result = prime * result + ( ( senhaPainel == null ) ? 0 : senhaPainel.hashCode() );
		return result;
	}

	public void setAgendamentoVO( AgendamentoVO agendamentoVO ) {
		this.agendamentoVO = agendamentoVO;
	}

	public void setDataHoraChamada( Date dataHoraChamada ) {
		this.dataHoraChamada = dataHoraChamada;
	}

	public void setDataHoraChamadaFormatada( String dataHoraChamadaFormatada ) {
		this.dataHoraChamadaFormatada = dataHoraChamadaFormatada;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public void setNomeUnidadeNegocio( String nomeUnidadeNegocio ) {
		this.nomeUnidadeNegocio = nomeUnidadeNegocio;
	}

	public void setNumeroSenha( String numeroSenha ) {
		this.numeroSenha = numeroSenha;
	}

	public void setSenhaPainel( BigDecimal senhaPainel ) {
		this.senhaPainel = senhaPainel;
	}

	public void setUnidadeNegocioVO( UnidadeNegocioVO unidadeNegocioVO ) {
		UnidadeNegocioVO = unidadeNegocioVO;
	}

}
