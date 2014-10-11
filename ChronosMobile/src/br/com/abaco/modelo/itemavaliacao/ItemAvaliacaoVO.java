package br.com.abaco.modelo.itemavaliacao;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.abaco.modelo.enums.EnumStatus;
import br.com.abaco.modelo.enums.EnumTipoItemAvaliacao;

public class ItemAvaliacaoVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private BigDecimal statusId;

	private String nomeItemAvaliacao;

	private BigDecimal tipoItemAvaliacaoId;

	public Long getId() {
		return id;
	}

	public String getNomeItemAvaliacao() {
		return nomeItemAvaliacao;
	}

	public EnumStatus getStatus() {
		return EnumStatus.valueOf( this.statusId.longValue() );
	}

	public BigDecimal getStatusId() {
		return statusId;
	}

	public EnumTipoItemAvaliacao getTipoItemAvaliacao() {
		return EnumTipoItemAvaliacao.valueOf( this.tipoItemAvaliacaoId.longValue() );
	}

	public BigDecimal getTipoItemAvaliacaoId() {
		return tipoItemAvaliacaoId;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public void setNomeItemAvaliacao( String nomeItemAvaliacao ) {
		this.nomeItemAvaliacao = nomeItemAvaliacao;
	}

	public void setStatusId( BigDecimal statusId ) {
		this.statusId = statusId;
	}

	public void setTipoItemAvaliacaoId( BigDecimal tipoItemAvaliacaoId ) {
		this.tipoItemAvaliacaoId = tipoItemAvaliacaoId;
	}

}