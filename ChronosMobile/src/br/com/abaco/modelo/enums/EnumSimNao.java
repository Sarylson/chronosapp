package br.com.abaco.modelo.enums;

/**
 * @author jackson
 */
public enum EnumSimNao {

	SIM( 1L, "SIM" ),

	NAO( 2L, "NÃO" );

	public static EnumSimNao valueOf( final Long id ) {
		for ( final EnumSimNao tipo : values() ) {
			if ( tipo.id.equals( id ) ) {
				return tipo;
			}
		}
		return null;
	}

	private Long id;

	private String texto;

	EnumSimNao( final Long id, final String texto ) {
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
