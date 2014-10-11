package br.com.abaco.chronos.custom.listview;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Adptador customizado para o ListView Dashboard
 * 
 * @author jackson
 */
public class AdpterListViewCustom extends ArrayAdapter<ItemMenu> {

	private Context context;

	public AdpterListViewCustom( final Context context, final int resourceId, final List<ItemMenu> listItems ) {
		super( context, resourceId, listItems );
		this.context = context;
	}

	private class ViewSelecao {

		private ImageView imgTitulo;

		private TextView textTitulo;

		public ImageView getImgTitulo() {
			return imgTitulo;
		}

		public void setImgTitulo( ImageView imgTitulo ) {
			this.imgTitulo = imgTitulo;
		}

		public TextView getTextTitulo() {
			return textTitulo;
		}

		public void setTextTitulo( TextView textTitulo ) {
			this.textTitulo = textTitulo;
		}
	}

	public View getView( final int posicao, View viewConverter, final ViewGroup group ) {
		ViewSelecao selecao = null;

		final ItemMenu itemDash = getItem( posicao );

		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService( Activity.LAYOUT_INFLATER_SERVICE );
		if ( viewConverter == null ) {

			viewConverter = layoutInflater.inflate( br.com.abaco.chronos.menubar.R.layout.listview_menu_custom, null );
			selecao = new ViewSelecao();
			selecao.setImgTitulo( (ImageView) viewConverter.findViewById( br.com.abaco.chronos.menubar.R.id.imgTitulo ) );
			selecao.setTextTitulo( (TextView) viewConverter.findViewById( br.com.abaco.chronos.menubar.R.id.txtTitulo ) );
			viewConverter.setTag( selecao );
		} else {
			selecao = (ViewSelecao) viewConverter.getTag();
		}
		selecao.getImgTitulo().setImageResource( itemDash.getIdImg() );
		selecao.getTextTitulo().setText( itemDash.getTitulo() );
		return viewConverter;

	}
	
}
