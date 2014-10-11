package br.com.abaco.modelo.guiche;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import br.com.abaco.modelo.unidadenegocio.UnidadeNegocioVO;

public class GuicheVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private UnidadeNegocioVO unidadeNegocio;

	private Date dataAtualizacao;

	private Date dataAtualizacaoInatividade;

	private Date dataUtilizacao;

	private BigDecimal status;

	private BigDecimal numeroMatricula;

	private String numeroGuiche;

	private BigDecimal servicoAtendimentoTemporario;

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public Date getDataAtualizacaoInatividade() {
		return dataAtualizacaoInatividade;
	}

	public Date getDataUtilizacao() {
		return dataUtilizacao;
	}

	public Long getId() {
		return id;
	}

	public String getNumeroGuiche() {
		return numeroGuiche;
	}

	public BigDecimal getNumeroMatricula() {
		return numeroMatricula;
	}

	public BigDecimal getServicoAtendimentoTemporario() {
		return servicoAtendimentoTemporario;
	}

	public BigDecimal getStatus() {
		return status;
	}

	public UnidadeNegocioVO getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setDataAtualizacao( Date dataAtualizacao ) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public void setDataAtualizacaoInatividade( Date dataAtualizacaoInatividade ) {
		this.dataAtualizacaoInatividade = dataAtualizacaoInatividade;
	}

	public void setDataUtilizacao( Date dataUtilizacao ) {
		this.dataUtilizacao = dataUtilizacao;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public void setNumeroGuiche( String numeroGuiche ) {
		this.numeroGuiche = numeroGuiche;
	}

	public void setNumeroMatricula( BigDecimal numeroMatricula ) {
		this.numeroMatricula = numeroMatricula;
	}

	public void setServicoAtendimentoTemporario( BigDecimal servicoAtendimentoTemporario ) {
		this.servicoAtendimentoTemporario = servicoAtendimentoTemporario;
	}

	public void setStatus( BigDecimal status ) {
		this.status = status;
	}

	public void setUnidadeNegocio( UnidadeNegocioVO unidadeNegocio ) {
		this.unidadeNegocio = unidadeNegocio;
	}

}
