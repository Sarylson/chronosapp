package br.com.abaco.chronos.configuracao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import br.com.abaco.chronos.menubar.R;

public class ConfiguracaoFragment extends Fragment {

	public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
	
		View rootView = inflater.inflate( R.layout.activity_configuracao, container, false );
		//TextView textView = (TextView) rootView.findViewById( R.id.section_label );
		//textView.setText( Integer.toString( getArguments().getInt( ARG_SECTION_NUMBER ) ) );
		

		CheckBox checkboxHabilitarNotificao = (CheckBox) rootView.findViewById( R.id.idCheckboxHabilitarNotificacao );

		CheckBox checkboxNotificacaoComSom = (CheckBox) rootView.findViewById( R.id.idCheckboxNotificacaoComSom );
		CheckBox checkboxNotificacaoComVibracao = (CheckBox) rootView.findViewById( R.id.idCheckboxNotificacaoComVibracao );
		CheckBox checkboxNotificacaoACadaXTempo = (CheckBox) rootView.findViewById( R.id.idCheckboxNotificacaoAcadaXTempo );
		
		final String isExisteConfiguracao = getActivity().getSharedPreferences( "MyprefChronos", 0 ).getString( "configuracao", " " );

		if ( isExisteConfiguracao != null ) {
			final Gson gson = new GsonBuilder().setDateFormat( "dd/MM/yyyy HH:mm:ss" ).create();
			final Configuracao configuracao = gson.fromJson( isExisteConfiguracao, new TypeToken<Configuracao>() {
			}.getType() );

			if ( configuracao != null ) {
				if ( checkboxHabilitarNotificao != null ) {
					checkboxHabilitarNotificao.setChecked( configuracao.getHabilitarNotificacao() );
				}
				if ( checkboxNotificacaoComSom != null ) {
					checkboxNotificacaoComSom.setChecked( configuracao.getNotificacaoComSom() );
				}
				if ( checkboxNotificacaoComVibracao != null ) {
					checkboxNotificacaoComVibracao.setChecked( configuracao.getNotificacaoComVibracao() );
				}
				if ( checkboxNotificacaoACadaXTempo != null ) {
					checkboxNotificacaoACadaXTempo.setChecked( configuracao.getNotificacaoACadaXTempo() );
				}
			}
		}
		
		return rootView;
	}
	

}
