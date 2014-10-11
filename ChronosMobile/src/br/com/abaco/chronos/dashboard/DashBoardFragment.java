package br.com.abaco.chronos.dashboard;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import br.com.abaco.chronos.menubar.R;

public class DashBoardFragment extends Fragment{


	public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {

		View rootView = inflater.inflate( R.layout.activity_dashboard, container, false );
		// TextView textView = (TextView) rootView.findViewById( R.id.section_label );
		// textView.setText( Integer.toString( getArguments().getInt( ARG_SECTION_NUMBER ) ) );
		return rootView;
	}

}
