package br.com.abaco.chronos.custom.listview;

/**
 * @author jackson
 */
public class ItemMenu {

	private int idImg;

	private String titulo;

	public ItemMenu( final int idImg, final String titulo ) {
		this.idImg = idImg;
		this.titulo = titulo;

	}

	public int getIdImg() {
		return idImg;
	}

	public void setIdImg( int idImg ) {
		this.idImg = idImg;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo( String titulo ) {
		this.titulo = titulo;
	}
}
