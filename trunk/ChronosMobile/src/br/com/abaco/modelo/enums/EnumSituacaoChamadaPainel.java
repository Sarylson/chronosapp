package br.com.abaco.modelo.enums;

/**
 * Enum de Senhas Chamadas Painel
 * 
 * @author jackson
 */
public enum EnumSituacaoChamadaPainel {

	ATENDENDO( 1L, "ATENDENDO" ),

	ATENDIDA( 2L, "ATENDIDA" ),

	EM_CANCELAMENTO( 3L, "EM CANCELAMENTO" ),

	AUSENTE( 4L, "AUSENTE" ),

	CANCELADA( 5L, "CANCELADA" );

	public static EnumSituacaoChamadaPainel valueOf( final Long id ) {
		for ( final EnumSituacaoChamadaPainel tipo : values() ) {
			if ( tipo.id.equals( id ) ) {
				return tipo;
			}
		}
		return null;
	}

	private Long id;

	private String texto;

	EnumSituacaoChamadaPainel( final Long id, final String texto ) {
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