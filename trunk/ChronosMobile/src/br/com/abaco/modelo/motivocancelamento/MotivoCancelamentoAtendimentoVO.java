package br.com.abaco.modelo.motivocancelamento;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.abaco.modelo.enums.EnumSimNao;
import br.com.abaco.modelo.enums.EnumStatus;

public class MotivoCancelamentoAtendimentoVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long codgMotivoCancSeqc;

	private String descricaoMotivoCancelamento;

	private BigDecimal flagObrigatorioJustId;

	private BigDecimal statusMotivoCancelamentoId;

	public Long getCodgMotivoCancSeqc() {
		return codgMotivoCancSeqc;
	}

	public String getDescricaoMotivoCancelamento() {
		return descricaoMotivoCancelamento;
	}

	public BigDecimal getFlagObrigatorioJustId() {
		return flagObrigatorioJustId;
	}

	public EnumSimNao getFlaObrigatorioJust() {
		return EnumSimNao.valueOf( this.flagObrigatorioJustId.longValue() );
	}

	public EnumStatus getStatusMotivoCancelamento() {
		return EnumStatus.valueOf( this.statusMotivoCancelamentoId.longValue() );
	}

	public BigDecimal getStatusMotivoCancelamentoId() {
		return statusMotivoCancelamentoId;
	}

	public void setCodgMotivoCancSeqc( Long codgMotivoCancSeqc ) {
		this.codgMotivoCancSeqc = codgMotivoCancSeqc;
	}

	public void setDescricaoMotivoCancelamento( String descricaoMotivoCancelamento ) {
		this.descricaoMotivoCancelamento = descricaoMotivoCancelamento;
	}

	public void setFlagObrigatorioJustId( BigDecimal flagObrigatorioJustId ) {
		this.flagObrigatorioJustId = flagObrigatorioJustId;
	}

	public void setStatusMotivoCancelamentoId( BigDecimal statusMotivoCancelamentoId ) {
		this.statusMotivoCancelamentoId = statusMotivoCancelamentoId;
	}

}
