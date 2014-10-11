package br.com.abaco.customs;


import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.abaco.chronos.menubar.R;

/**
 * Adptador customizado para o ListView Dashboard
 * 
 * @author jackson
 */
public class AdpterListViewCustom extends ArrayAdapter<ItemListCustom> {

	private Context context;

	public AdpterListViewCustom( final Context context, final int resourceId, final List<ItemListCustom> listItems ) {
		super( context, resourceId, listItems );
		this.context = context;
	}

	private class ViewSelecao {

		private ImageView imgTitulo;

		private TextView textRegistro;
		
		private TextView idRegistro;
		
		public TextView getIdRegistro(){
			return idRegistro;
		}
		
		public void setIdRegistro( TextView textRegistro ) {
			this.idRegistro = textRegistro;
		}
		
		
		public ImageView getImgTitulo() {
			return imgTitulo;
		}

		public void setImgTitulo( ImageView imgTitulo ) {
			this.imgTitulo = imgTitulo;
		}

		public TextView getTextRegistro() {
			return textRegistro;
		}

		public void setTextRegistro( TextView textTitulo ) {
			this.textRegistro = textTitulo;
		}
	}

	public View getView( final int posicao, View viewConverter, final ViewGroup group ) {
		ViewSelecao selecao = null;

		final ItemListCustom itemCustom = getItem( posicao );

		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService( Activity.LAYOUT_INFLATER_SERVICE );
		if ( viewConverter == null ) {

			viewConverter = layoutInflater.inflate( R.layout.listview_custom, null );
			selecao = new ViewSelecao();
			selecao.setIdRegistro( (TextView) viewConverter.findViewById( R.id.idRegistro ) );
			selecao.setImgTitulo( (ImageView) viewConverter.findViewById(R.id.imgTituloRegistro) );
			selecao.setTextRegistro( (TextView) viewConverter.findViewById( R.id.txtRegistro ) );
			viewConverter.setTag( selecao );
		} else {
			selecao = (ViewSelecao) viewConverter.getTag();
		}
		selecao.getImgTitulo().setImageResource( itemCustom.getIdImg() );
		selecao.getTextRegistro().setText( itemCustom.getTitulo() );
		selecao.getIdRegistro().setText( String.valueOf( itemCustom.getIdRegistro() ) );
		return viewConverter;

	}
}

