package br.com.abaco.chronos.agendamento;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import br.com.abaco.chronos.menubar.R;
import br.com.abaco.modelo.senha.SenhaVO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * Agendamento Confirmado
 * 
 * @author jackson
 */
public class AgendamentoConfirmadoFragment extends Fragment {

	private View rootView = null;

	private TextView textViewNumeroDocumento;

	private TextView textViewUnidadeAtendimento;

	private TextView textViewUnidadeServico;

	private TextView textViewUnidadeSenha;


	private TextView textViewDataHoraAgendamento;

	private AnimatorSet set;

	public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {

		rootView = inflater.inflate( br.com.abaco.chronos.menubar.R.layout.activity_agendamento_confirmado, container, false );
		textViewNumeroDocumento = (TextView) rootView.findViewById( R.id.textViewDocumentoCPF );
		textViewUnidadeAtendimento = (TextView) rootView.findViewById( R.id.textViewUnidadeAtendimento );
		textViewUnidadeServico = (TextView) rootView.findViewById( R.id.textViewServico );
		textViewUnidadeSenha = (TextView) rootView.findViewById( R.id.textViewSenha );
		textViewDataHoraAgendamento = (TextView) rootView.findViewById( R.id.viewDataHoraAgendamento );

		final String jsonSenha = getActivity().getIntent().getStringExtra( getString( R.string.lb_requisicao_senha_confirmada_agendamento ) );
		final Gson gson = new GsonBuilder().setDateFormat( "dd/MM/yyyy HH:mm:ss" ).create();
		final SenhaVO senha = gson.fromJson( jsonSenha, new TypeToken<SenhaVO>() {
		}.getType() );

		if ( senha != null ) {

			textViewNumeroDocumento.setText( senha.getAgendamento().getUsuarioExterno().getNumeroDocumento() );
			textViewUnidadeAtendimento.setText( senha.getAgendamento().getUnidadeNegocio().getNome() );
			textViewUnidadeServico.setText( senha.getAgendamento().getServico().getNome() );
			textViewUnidadeSenha.setText( senha.getNumeroSenha() );
			textViewDataHoraAgendamento.setText( senha.getAgendamento().getDataAgendamentoFormatada() +" "+senha.getAgendamento().getHoraAgendamentoFormatada());

		}


		ObjectAnimator mover2 = ObjectAnimator.ofFloat( textViewUnidadeAtendimento, "translationX", -500f, 0f );
		mover2.setDuration( 500 );
		
		ObjectAnimator mover3 = ObjectAnimator.ofFloat( textViewUnidadeServico, "translationX", -500f, 0f );
		mover3.setDuration( 500 );
		
		ObjectAnimator mover4 = ObjectAnimator.ofFloat( textViewUnidadeSenha, "translationX", -500f, 0f );
		mover4.setDuration( 500 );
		
		ObjectAnimator mover5 = ObjectAnimator.ofFloat( textViewDataHoraAgendamento, "translationX", -500f, 0f );
		mover5.setDuration( 500 );
		
		set = new AnimatorSet();
		set.play( mover2 ).after( mover3 ).after( mover4 ).after( mover5 );
		set.start();

		// listViewUnidadeOrganizacional = (ListView) rootView.findViewById( br.com.abaco.chronos.menubar.R.id.listViewUnidadeOrganizacional );
		// new LoadingRealizarAgendamentoAsync().execute();

		return rootView;
	}

}