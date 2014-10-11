package br.com.abaco.modelo.enums;



/**
 * @author jackson
 *
 */
public enum EnumTipoItemAvaliacao {

	SUGESTAO( 1L, "SUGESTÃO" ),

	RECLAMACAO( 2L, "RECLAMAÇÃO" ),
	
	ELOGIO(3L, "ELOGIO");

	public static EnumTipoItemAvaliacao valueOf( final Long id ) {
		for ( final EnumTipoItemAvaliacao tipo : values() ) {
			if ( tipo.id.equals( id ) ) {
				return tipo;
			}
		}
		return null;
	}

	private Long id;

	private String texto;

	EnumTipoItemAvaliacao( final Long id, final String texto ) {
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

