package br.com.abaco.modelo.unidadenegocio;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.abaco.modelo.unidadeorganizacional.UnidadeOrganizacionalVO;

public class UnidadeNegocioVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private UnidadeOrganizacionalVO unidadeOrganizacional;

	private String sigla;

	private String descricao;

	private String nome;

	private BigDecimal status;

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		UnidadeNegocioVO other = (UnidadeNegocioVO) obj;
		if ( descricao == null ) {
			if ( other.descricao != null )
				return false;
		} else if ( !descricao.equals( other.descricao ) )
			return false;
		if ( id == null ) {
			if ( other.id != null )
				return false;
		} else if ( !id.equals( other.id ) )
			return false;
		if ( nome == null ) {
			if ( other.nome != null )
				return false;
		} else if ( !nome.equals( other.nome ) )
			return false;
		if ( sigla == null ) {
			if ( other.sigla != null )
				return false;
		} else if ( !sigla.equals( other.sigla ) )
			return false;
		if ( status == null ) {
			if ( other.status != null )
				return false;
		} else if ( !status.equals( other.status ) )
			return false;
		if ( unidadeOrganizacional == null ) {
			if ( other.unidadeOrganizacional != null )
				return false;
		} else if ( !unidadeOrganizacional.equals( other.unidadeOrganizacional ) )
			return false;
		return true;
	}

	public String getDescricao() {
		return descricao;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getSigla() {
		return sigla;
	}

	public BigDecimal getStatus() {
		return status;
	}

	public UnidadeOrganizacionalVO getUnidadeOrganizacional() {
		return unidadeOrganizacional;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( descricao == null ) ? 0 : descricao.hashCode() );
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
		result = prime * result + ( ( nome == null ) ? 0 : nome.hashCode() );
		result = prime * result + ( ( sigla == null ) ? 0 : sigla.hashCode() );
		result = prime * result + ( ( status == null ) ? 0 : status.hashCode() );
		result = prime * result + ( ( unidadeOrganizacional == null ) ? 0 : unidadeOrganizacional.hashCode() );
		return result;
	}

	public void setDescricao( String descricao ) {
		this.descricao = descricao;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public void setNome( String nome ) {
		this.nome = nome;
	}

	public void setSigla( String sigla ) {
		this.sigla = sigla;
	}

	public void setStatus( BigDecimal status ) {
		this.status = status;
	}

	public void setUnidadeOrganizacional( UnidadeOrganizacionalVO unidadeOrganizacional ) {
		this.unidadeOrganizacional = unidadeOrganizacional;
	}

}
