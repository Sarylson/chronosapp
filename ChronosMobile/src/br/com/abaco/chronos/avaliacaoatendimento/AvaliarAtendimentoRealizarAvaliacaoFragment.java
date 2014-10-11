package br.com.abaco.chronos.avaliacaoatendimento;

import br.com.abaco.chronos.avaliacaoatendimento.asynctasks.LoadingAvaliarAtendimentoRealizarAvaliacao;
import br.com.abaco.chronos.menubar.R;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

/**
 * Representa o Fragment da realização dos Atendimentos.
 * 
 * @author Jackson Silva
 */
@SuppressLint( "ValidFragment" )
public class AvaliarAtendimentoRealizarAvaliacaoFragment extends Fragment {

	private View rootView = null;

	private Menu menu;

	private FragmentManager manager;

	public AvaliarAtendimentoRealizarAvaliacaoFragment( final Menu menu, final FragmentManager manager ) {
		this.menu = menu;
		this.manager = manager;
	}

	public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {

		rootView = inflater.inflate( br.com.abaco.chronos.menubar.R.layout.activity_realizar_avaliacao_atendimento, container, false );

		new LoadingAvaliarAtendimentoRealizarAvaliacao( getActivity(), rootView, menu, manager ).execute();

		return rootView;
	}
	
	@Override
	public void onDestroyView() {

		super.onDestroyView();
	}
}
