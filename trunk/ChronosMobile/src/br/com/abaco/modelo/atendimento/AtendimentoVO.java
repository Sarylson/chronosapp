package br.com.abaco.modelo.atendimento;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import br.com.abaco.modelo.agendamento.AgendamentoVO;
import br.com.abaco.modelo.enums.EnumSimNao;
import br.com.abaco.modelo.enums.EnumStatus;
import br.com.abaco.modelo.guiche.GuicheVO;
import br.com.abaco.modelo.motivocancelamento.MotivoCancelamentoAtendimentoVO;
import br.com.abaco.modelo.unidadenegocio.UnidadeNegocioVO;

public class AtendimentoVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private UnidadeNegocioVO unidadeNegocio;

	private GuicheVO guiche;

	private AgendamentoVO agendamento;

	private MotivoCancelamentoAtendimentoVO motivoCancelamentoAtendimento;

	private Date dataFim;

	private Date dataInicio;

	private String descricaoJustificativaCancelamento;

	private BigDecimal avaliadoAtendimentoId;

	private BigDecimal numeroMatricula;

	private BigDecimal notaAtendimento;

	private String observacaoAtendimento;

	private BigDecimal quantidadeProcesso;

	private BigDecimal situacaoAtendimentoId;

	private BigDecimal tempoAtendimento;

	private String senha;
	
	private String dataFormatada;
	
	
	public String getDataFormatada() {
		if ( dataInicio != null ) {
			dataFormatada =  new SimpleDateFormat( "dd/MM/yyyy" , new Locale( "pt","BR" )).format( dataInicio );
		}
		return dataFormatada;
	}
	
	public String getSenha() {
		return senha;
	}

	
	public void setSenha( String senha ) {
		this.senha = senha;
	}

	public AgendamentoVO getAgendamento() {
		return agendamento;
	}

	public EnumSimNao getAvaliadoAtendimento() {
		return EnumSimNao.valueOf( this.avaliadoAtendimentoId.longValue() );
	}

	public BigDecimal getAvaliadoAtendimentoId() {
		return avaliadoAtendimentoId;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public String getDescricaoJustificativaCancelamento() {
		return descricaoJustificativaCancelamento;
	}

	public GuicheVO getGuiche() {
		return guiche;
	}

	public Long getId() {
		return id;
	}

	public MotivoCancelamentoAtendimentoVO getMotivoCancelamentoAtendimento() {
		return motivoCancelamentoAtendimento;
	}

	public BigDecimal getNotaAtendimento() {
		return notaAtendimento;
	}

	public BigDecimal getNumeroMatricula() {
		return numeroMatricula;
	}

	public String getObservacaoAtendimento() {
		return observacaoAtendimento;
	}

	public BigDecimal getQuantidadeProcesso() {
		return quantidadeProcesso;
	}

	public EnumStatus getSituacaoAtendimento() {
		return EnumStatus.valueOf( this.situacaoAtendimentoId.longValue() );
	}

	public BigDecimal getSituacaoAtendimentoId() {
		return situacaoAtendimentoId;
	}

	public BigDecimal getTempoAtendimento() {
		return tempoAtendimento;
	}

	public UnidadeNegocioVO getUnidadeNegocio() {
		return unidadeNegocio;
	}

	public void setAgendamento( AgendamentoVO agendamento ) {
		this.agendamento = agendamento;
	}

	public void setAvaliadoAtendimentoId( BigDecimal avaliadoAtendimentoId ) {
		this.avaliadoAtendimentoId = avaliadoAtendimentoId;
	}

	public void setDataFim( Date dataFim ) {
		this.dataFim = dataFim;
	}

	public void setDataInicio( Date dataInicio ) {
		this.dataInicio = dataInicio;
	}

	public void setDescricaoJustificativaCancelamento( String descricaoJustificativaCancelamento ) {
		this.descricaoJustificativaCancelamento = descricaoJustificativaCancelamento;
	}

	public void setGuiche( GuicheVO guiche ) {
		this.guiche = guiche;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public void setMotivoCancelamentoAtendimento( MotivoCancelamentoAtendimentoVO motivoCancelamentoAtendimento ) {
		this.motivoCancelamentoAtendimento = motivoCancelamentoAtendimento;
	}

	public void setNotaAtendimento( BigDecimal notaAtendimento ) {
		this.notaAtendimento = notaAtendimento;
	}

	public void setNumeroMatricula( BigDecimal numeroMatricula ) {
		this.numeroMatricula = numeroMatricula;
	}

	public void setObservacaoAtendimento( String observacaoAtendimento ) {
		this.observacaoAtendimento = observacaoAtendimento;
	}

	public void setQuantidadeProcesso( BigDecimal quantidadeProcesso ) {
		this.quantidadeProcesso = quantidadeProcesso;
	}

	public void setSituacaoAtendimentoId( BigDecimal situacaoAtendimentoId ) {
		this.situacaoAtendimentoId = situacaoAtendimentoId;
	}

	public void setTempoAtendimento( BigDecimal tempoAtendimento ) {
		this.tempoAtendimento = tempoAtendimento;
	}

	public void setUnidadeNegocio( UnidadeNegocioVO unidadeNegocio ) {
		this.unidadeNegocio = unidadeNegocio;
	}

}