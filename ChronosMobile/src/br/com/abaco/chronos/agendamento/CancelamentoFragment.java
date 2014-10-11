package br.com.abaco.chronos.agendamento;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import br.com.abaco.chronos.util.MensagemUtil;


public class CancelamentoFragment extends Fragment {

	public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
	
		View rootView = inflater.inflate( br.com.abaco.chronos.menubar.R.layout.activity_cancelamento, container, false );
		return rootView;
	}
	
	public void alterarDados( final View view ){
		
		MensagemUtil.addMsg( getActivity()," CLicou sem problemas!!!" );
	}
}
