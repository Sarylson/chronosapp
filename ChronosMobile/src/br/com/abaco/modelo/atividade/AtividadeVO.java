package br.com.abaco.modelo.atividade;

import java.io.Serializable;

import java.math.BigDecimal;

public class AtividadeVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String descricao;

	private BigDecimal status;

	private String nome;

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		AtividadeVO other = (AtividadeVO) obj;
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

	public String getNome() {
		return nome;
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
		result = prime * result + ( ( nome == null ) ? 0 : nome.hashCode() );
		result = prime * result + ( ( status == null ) ? 0 : status.hashCode() );
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

	public void setStatus( BigDecimal status ) {
		this.status = status;
	}

}
