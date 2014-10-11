package br.com.abaco.chronos.avaliacaoatendimento;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import br.com.abaco.chronos.avaliacaoatendimento.asynctasks.LoadingAvaliarAtendimentoSelecaoSenha;
import br.com.abaco.chronos.menubar.R;

/**
 * Fragment Responsável pela exibição das Senhas atendidas para Avaliação
 * 
 * @author Jackson Silva
 */
@SuppressLint( "ValidFragment" )
public class AvaliarAtendimentoSelecaoSenhaFragment extends Fragment {

	private View rootView = null;

	private Menu menu;

	private FragmentManager manager;

	public AvaliarAtendimentoSelecaoSenhaFragment( final Menu menu, final FragmentManager manager ) {
		this.menu = menu;
		this.manager = manager;
	}

	public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {


		getActivity().getIntent().removeExtra( getString( R.string.lb_requisicao_avaliacao_atendimento_tipo_avaliacao ) );
		getActivity().getIntent().removeExtra( getString( R.string.lb_requisicao_avaliacao_atendimento_item_avaliacao ) );
		
		
		rootView = inflater.inflate( br.com.abaco.chronos.menubar.R.layout.activity_selecionar_senha_avaliacao_atendimento, container, false );

		new LoadingAvaliarAtendimentoSelecaoSenha( getActivity(), rootView, menu , manager).execute();

		return rootView;
	}

}
