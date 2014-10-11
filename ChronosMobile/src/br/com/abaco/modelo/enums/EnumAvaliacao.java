package br.com.abaco.modelo.enums;

/**
 * Enum Avaliação
 * 
 * @author jackson
 */
public enum EnumAvaliacao {

	TIPO_AVALIACAO( 1L, "TIPO DE AVALIAÇÃO" ),

	ITEM_AVALIACAO( 2L, "ITEM DA AVALIAÇÃO" );

	public static EnumAvaliacao valueOf( final Long id ) {
		for ( final EnumAvaliacao tipo : values() ) {
			if ( tipo.id.equals( id ) ) {
				return tipo;
			}
		}
		return null;
	}

	private Long id;

	private String texto;

	EnumAvaliacao( final Long id, final String texto ) {
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
