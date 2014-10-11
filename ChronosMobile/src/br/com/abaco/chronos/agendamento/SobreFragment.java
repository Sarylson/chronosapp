package br.com.abaco.chronos.agendamento;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SobreFragment extends Fragment {

	public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {

		View rootView = inflater.inflate( br.com.abaco.chronos.menubar.R.layout.activity_sobre, container, false );
		return rootView;
	}

}
