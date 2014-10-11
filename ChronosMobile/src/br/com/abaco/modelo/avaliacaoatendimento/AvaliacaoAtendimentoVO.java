package br.com.abaco.modelo.avaliacaoatendimento;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import br.com.abaco.modelo.atendimento.AtendimentoVO;
import br.com.abaco.modelo.enums.EnumSimNao;
import br.com.abaco.modelo.itemavaliacao.ItemAvaliacaoVO;

public class AvaliacaoAtendimentoVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private ItemAvaliacaoVO itemAvaliacao;

	private AtendimentoVO atendimento;

	private Date dataAvaliacao;

	private Date dataRespostaOuvidoria;

	private String descricaoAvaliacao;

	private BigDecimal respostaOuvidoriaId;

	private String respostaAtendimento;

	public AtendimentoVO getAtendimento() {
		return atendimento;
	}

	public Date getDataAvaliacao() {
		return dataAvaliacao;
	}

	public Date getDataRespostaOuvidoria() {
		return dataRespostaOuvidoria;
	}

	public String getDescricaoAvaliacao() {
		return descricaoAvaliacao;
	}

	public Long getId() {
		return id;
	}

	public ItemAvaliacaoVO getItemAvaliacao() {
		return itemAvaliacao;
	}

	public String getRespostaAtendimento() {
		return respostaAtendimento;
	}

	public EnumSimNao getRespostaOuvidoria() {
		return EnumSimNao.valueOf( this.respostaOuvidoriaId.longValue() );
	}

	public BigDecimal getRespostaOuvidoriaId() {
		return respostaOuvidoriaId;
	}

	public void setAtendimento( AtendimentoVO atendimento ) {
		this.atendimento = atendimento;
	}

	public void setDataAvaliacao( Date dataAvaliacao ) {
		this.dataAvaliacao = dataAvaliacao;
	}

	public void setDataRespostaOuvidoria( Date dataRespostaOuvidoria ) {
		this.dataRespostaOuvidoria = dataRespostaOuvidoria;
	}

	public void setDescricaoAvaliacao( String descricaoAvaliacao ) {
		this.descricaoAvaliacao = descricaoAvaliacao;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public void setItemAvaliacao( ItemAvaliacaoVO itemAvaliacao ) {
		this.itemAvaliacao = itemAvaliacao;
	}

	public void setRespostaAtendimento( String respostaAtendimento ) {
		this.respostaAtendimento = respostaAtendimento;
	}

	public void setRespostaOuvidoriaId( BigDecimal respostaOuvidoriaId ) {
		this.respostaOuvidoriaId = respostaOuvidoriaId;
	}

}
