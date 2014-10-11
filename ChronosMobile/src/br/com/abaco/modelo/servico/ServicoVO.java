package br.com.abaco.modelo.servico;

import java.io.Serializable;

public class ServicoVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String observacao;

	private String descricao;

	private Integer status;

	private String nome;

	private String sigla;

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		ServicoVO other = (ServicoVO) obj;
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
		if ( observacao == null ) {
			if ( other.observacao != null )
				return false;
		} else if ( !observacao.equals( other.observacao ) )
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

	public String getNome() {
		return nome;
	}

	public String getObservacao() {
		return observacao;
	}

	public String getSigla() {
		return sigla;
	}

	public Integer getStatus() {
		return status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( descricao == null ) ? 0 : descricao.hashCode() );
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
		result = prime * result + ( ( nome == null ) ? 0 : nome.hashCode() );
		result = prime * result + ( ( observacao == null ) ? 0 : observacao.hashCode() );
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

	public void setNome( String nome ) {
		this.nome = nome;
	}

	public void setObservacao( String observacao ) {
		this.observacao = observacao;
	}

	public void setSigla( String sigla ) {
		this.sigla = sigla;
	}

	public void setStatus( Integer status ) {
		this.status = status;
	}

}