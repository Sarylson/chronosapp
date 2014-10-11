package br.com.abaco.chronos.notificacao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import br.com.abaco.modelo.rest.usuario.usuarioexterno.UsuarioExternoVO;

public class NotificacaoVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Date dataNotificacao;

	private UsuarioExternoVO usuarioExterno;

	private String mensagem;

	private BigDecimal situacao;

	private String tituloMensagem;

	private String tituloNofiticacao;

	public EnumSituacaoNotificacao getSituacaoEnum() {
		return EnumSituacaoNotificacao.valueOf( this.situacao.longValue() );
	}

	public Date getDataNotificacao() {
		return dataNotificacao;
	}

	public Long getId() {
		return id;
	}

	public String getMensagem() {
		return mensagem;
	}

	public BigDecimal getSituacao() {
		return situacao;
	}

	public String getTituloMensagem() {
		return tituloMensagem;
	}

	public String getTituloNofiticacao() {
		return tituloNofiticacao;
	}

	public UsuarioExternoVO getUsuarioExterno() {
		return usuarioExterno;
	}

	public void setDataNotificacao( Date dataNotificacao ) {
		this.dataNotificacao = dataNotificacao;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public void setMensagem( String mensagem ) {
		this.mensagem = mensagem;
	}

	public void setSituacao( BigDecimal situacao ) {
		this.situacao = situacao;
	}

	public void setTituloMensagem( String tituloMensagem ) {
		this.tituloMensagem = tituloMensagem;
	}

	public void setTituloNofiticacao( String tituloNofiticacao ) {
		this.tituloNofiticacao = tituloNofiticacao;
	}

	public void setUsuarioExterno( UsuarioExternoVO usuarioExterno ) {
		this.usuarioExterno = usuarioExterno;
	}

}
