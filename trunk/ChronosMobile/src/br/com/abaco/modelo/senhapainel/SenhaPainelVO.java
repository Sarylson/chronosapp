package br.com.abaco.modelo.senhapainel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import br.com.abaco.modelo.agendamento.AgendamentoVO;
import br.com.abaco.modelo.guiche.GuicheVO;
import br.com.abaco.modelo.senha.SenhaVO;
import br.com.abaco.modelo.senhachamadapainel.SenhaChamadaPainelVO;

public class SenhaPainelVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private GuicheVO guicheVO;

	private AgendamentoVO agendamento;

	private Date dataHoraApresentacao;

	private BigDecimal chamadaPainel;

	private BigDecimal numeroControleChamada;

	private List<SenhaChamadaPainelVO> senhaChamadaPainel;

	private String tempoParaAtendimento;

	private SenhaVO senha;

	public AgendamentoVO getAgendamento() {
		return agendamento;
	}

	public BigDecimal getChamadaPainel() {
		return chamadaPainel;
	}

	public Date getDataHoraApresentacao() {
		return dataHoraApresentacao;
	}

	public GuicheVO getGuicheVO() {
		return guicheVO;
	}

	public Long getId() {
		return id;
	}

	public BigDecimal getNumeroControleChamada() {
		return numeroControleChamada;
	}

	public SenhaVO getSenha() {
		return senha;
	}

	public List<SenhaChamadaPainelVO> getSenhaChamadaPainel() {
		return senhaChamadaPainel;
	}

	public String getTempoParaAtendimento() {
		return tempoParaAtendimento;
	}

	public void setAgendamento( AgendamentoVO agendamento ) {
		this.agendamento = agendamento;
	}

	public void setChamadaPainel( BigDecimal chamadaPainel ) {
		this.chamadaPainel = chamadaPainel;
	}

	public void setDataHoraApresentacao( Date dataHoraApresentacao ) {
		this.dataHoraApresentacao = dataHoraApresentacao;
	}

	public void setGuicheVO( GuicheVO guicheVO ) {
		this.guicheVO = guicheVO;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public void setNumeroControleChamada( BigDecimal numeroControleChamada ) {
		this.numeroControleChamada = numeroControleChamada;
	}

	public void setSenha( SenhaVO senha ) {
		this.senha = senha;
	}

	public void setSenhaChamadaPainel( List<SenhaChamadaPainelVO> senhaChamadaPainel ) {
		this.senhaChamadaPainel = senhaChamadaPainel;
	}

	public void setTempoParaAtendimento( String tempoParaAtendimento ) {
		this.tempoParaAtendimento = tempoParaAtendimento;
	}

}
