package br.com.abaco.chronos.configuracao;

import java.io.Serializable;

/**
 * Classe de Configuração Chronos
 * 
 * @author Jackson Silva
 */
public class Configuracao implements Serializable {

	private static final long serialVersionUID = 1L;

	private Boolean habilitarNotificacao;

	private Boolean notificacaoComSom;

	private Boolean notificacaoComVibracao;

	private Boolean notificacaoACadaXTempo;

	public Boolean getHabilitarNotificacao() {
		return habilitarNotificacao;
	}

	public Boolean getNotificacaoACadaXTempo() {
		return notificacaoACadaXTempo;
	}

	public Boolean getNotificacaoComSom() {
		return notificacaoComSom;
	}

	public Boolean getNotificacaoComVibracao() {
		return notificacaoComVibracao;
	}

	public void setHabilitarNotificacao( Boolean habilitarNotificacao ) {
		this.habilitarNotificacao = habilitarNotificacao;
	}

	public void setNotificacaoACadaXTempo( Boolean notificacaoACadaXTempo ) {
		this.notificacaoACadaXTempo = notificacaoACadaXTempo;
	}

	public void setNotificacaoComSom( Boolean notificacaoComSom ) {
		this.notificacaoComSom = notificacaoComSom;
	}

	public void setNotificacaoComVibracao( Boolean notificacaoComVibracao ) {
		this.notificacaoComVibracao = notificacaoComVibracao;
	}
}
