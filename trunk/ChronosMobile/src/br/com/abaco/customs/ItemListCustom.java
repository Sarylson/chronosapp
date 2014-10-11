package br.com.abaco.customs;

/**
 * Representa o item do Dashboard List View's Custom
 * 
 * @author jackson
 */
public class ItemListCustom {

	private int idImg;

	private long idRegistro;

	private String titulo;

	public ItemListCustom( final long idRegistro, final int idImg, final String titulo ) {
		this.idImg = idImg;
		this.titulo = titulo;
		this.idRegistro = idRegistro;

	}

	public int getIdImg() {
		return idImg;
	}

	public long getIdRegistro() {
		return idRegistro;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setIdImg( int idImg ) {
		this.idImg = idImg;
	}

	public void setIdRegistro( long idRegistro ) {
		this.idRegistro = idRegistro;
	}

	public void setTitulo( String titulo ) {
		this.titulo = titulo;
	}
}
