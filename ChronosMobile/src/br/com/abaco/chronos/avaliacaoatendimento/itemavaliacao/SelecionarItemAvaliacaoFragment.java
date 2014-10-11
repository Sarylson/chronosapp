package br.com.abaco.chronos.avaliacaoatendimento.itemavaliacao;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import br.com.abaco.chronos.avaliacaoatendimento.itemavaliacao.asynctasks.LoadingSelecionarItemAvaliacao;


/**
 * Selecionar Item de Avaliação
 * 
 * @author Jackson Silva
 *
 */
@SuppressLint( "ValidFragment" )
public class SelecionarItemAvaliacaoFragment  extends Fragment {

	private View rootView = null;

	private Menu menu;

	private FragmentManager manager;

	public SelecionarItemAvaliacaoFragment( final Menu menu, final FragmentManager manager ) {
		this.menu = menu;
		this.manager = manager;
	}

	public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {

		rootView = inflater.inflate( br.com.abaco.chronos.menubar.R.layout.activity_selecionar_item_avaliacao_atendimento, container, false );

		new LoadingSelecionarItemAvaliacao( getActivity(), rootView, menu, manager ).execute();

		return rootView;
	}
}