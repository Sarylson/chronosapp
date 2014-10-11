package br.com.abaco.modelo.unidadeorganizacional;

import java.io.Serializable;
import java.math.BigDecimal;


import br.com.abaco.modelo.municipio.MunicipioVO;


public class UnidadeOrganizacionalVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private MunicipioVO municipio;

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
		UnidadeOrganizacionalVO other = (UnidadeOrganizacionalVO) obj;
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
		if ( municipio == null ) {
			if ( other.municipio != null )
				return false;
		} else if ( !municipio.equals( other.municipio ) )
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
		return true;
	}

	public String getDescricao() {
		return descricao;
	}

	public Long getId() {
		return id;
	}

	public MunicipioVO getMunicipio() {
		return municipio;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( descricao == null ) ? 0 : descricao.hashCode() );
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
		result = prime * result + ( ( municipio == null ) ? 0 : municipio.hashCode() );
		result = prime * result + ( ( nome == null ) ? 0 : nome.hashCode() );
		result = prime * result + ( ( sigla == null ) ? 0 : sigla.hashCode() );
		result = prime * result + ( ( status == null ) ? 0 : status.hashCode() );
		return result;
	}

	public void setDescricao( String descricao ) {
		this.descricao = descricao;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public void setMunicipio( MunicipioVO municipio ) {
		this.municipio = municipio;
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
}

