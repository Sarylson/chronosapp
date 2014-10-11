package br.com.abaco.chronos.notificacao;

/**
 * Enum Situação das Notificações
 * 
 * @author Jackson Silva
 */
public enum EnumSituacaoNotificacao {

	MENSAGEM_LIDA( 1L, "Mensagem Lida" ),

	MENSAGEM_NAO_LIDA( 2L, "Mensagem Não Lida" );

	public static EnumSituacaoNotificacao valueOf( final Long id ) {
		for ( final EnumSituacaoNotificacao tipo : values() ) {
			if ( tipo.id.equals( id ) ) {
				return tipo;
			}
		}
		return null;
	}

	private Long id;

	private String texto;

	EnumSituacaoNotificacao( final Long id, final String texto ) {
		this.id = id;
		this.texto = texto;
	}

	public Long getId() {
		return id;
	}

	public String getTexto() {
		return texto;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public void setTexto( String texto ) {
		this.texto = texto;
	}

}
