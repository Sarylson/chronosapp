package br.com.abaco.modelo.enums;

public enum EnumStatus {

	ATIVO( 1L, "ATIVO" ),

	INATIVO( 2L, "INATIVO" );

	public static EnumStatus valueOf( final Long id ) {
		for ( final EnumStatus tipo : values() ) {
			if ( tipo.id.equals( id ) ) {
				return tipo;
			}
		}
		return null;
	}

	private Long id;

	private String texto;

	EnumStatus( final Long id, final String texto ) {
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
