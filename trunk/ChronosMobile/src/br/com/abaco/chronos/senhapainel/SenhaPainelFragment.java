package br.com.abaco.chronos.senhapainel;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import br.com.abaco.chronos.menubar.R;
import br.com.abaco.chronos.senhapainel.asynctasks.LoadingSenhasChamadasPainel;

/**
 * Senhas Painel
 * 
 * @author jackson
 */
@SuppressLint( "ValidFragment" )
public class SenhaPainelFragment extends Fragment {

	private View view = null;

	private Menu menu;

	public SenhaPainelFragment( final Menu menu ) {
		this.menu = menu;
	}
	public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {

		view = inflater.inflate( br.com.abaco.chronos.menubar.R.layout.activity_painel_senhas, container, false );

		new LoadingSenhasChamadasPainel( getActivity(), view, menu ).execute();

		return view;
	}
	public void onDestroyView() {

		MenuItem itemBack = menu.findItem( R.id.idBackMenu );
		if ( itemBack != null ) {
			itemBack.setVisible( Boolean.FALSE );
		}
		MenuItem item = menu.findItem( R.id.action_home );
		if ( item != null ) {
			item.setVisible( Boolean.TRUE );
		}
		super.onDestroyView();
	}
}
