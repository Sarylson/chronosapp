package br.com.abaco.chronos.avaliacaoatendimento.tipoavaliacao;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import br.com.abaco.chronos.avaliacaoatendimento.tipoavaliacao.asynctasks.LoadingSelecionarTipoAvaliacao;

/**
 * Responsável pelos listagem dos tipos de itens
 * 
 * @author Jackson Silva
 */
@SuppressLint( "ValidFragment" )
public class SelecionarTipoAvaliacaoFragment extends Fragment {

	private View rootView = null;

	private Menu menu;

	private FragmentManager manager;

	public SelecionarTipoAvaliacaoFragment( final Menu menu, final FragmentManager manager ) {
		this.menu = menu;
		this.manager = manager;
	}

	public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {

		rootView = inflater.inflate( br.com.abaco.chronos.menubar.R.layout.activity_selecionar_tipo_avaliacao_atendimento, container, false );

		new LoadingSelecionarTipoAvaliacao( getActivity(), rootView, menu, manager ).execute();

		return rootView;
	}

}
