package br.com.abaco.chronos.painelsenhas;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import br.com.abaco.chronos.menubar.R;
import br.com.abaco.chronos.util.MensagemUtil;

@SuppressLint( "ValidFragment" )
public class PainelSenhasFragment extends Fragment {

	private Menu menu;

	public PainelSenhasFragment( final Menu menu ) {
		this.menu = menu;
	}

	public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {

		View rootView = inflater.inflate( br.com.abaco.chronos.menubar.R.layout.activity_painel_senhas, container, false );
		
		MenuItem itemBack = menu.findItem( R.id.idBackMenu );
		if ( itemBack != null ) {
			itemBack.setVisible( Boolean.FALSE );
		}
		MenuItem item = menu.findItem( R.id.action_home );
		if ( item != null ) {
			item.setVisible( Boolean.TRUE );
		}
		
		return rootView;
	}

	public void alterarDados( final View view ) {

		MensagemUtil.addMsg( getActivity(), " CLicou sem problemas!!!" );
	}

	@Override
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
