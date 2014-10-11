package br.com.abaco.notification;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author jackson
 */
public class NotificacaoFragment extends Fragment {

	public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {

		final View rootView = inflater.inflate( br.com.abaco.chronos.menubar.R.layout.activity_notification, container, false );
		
//		final boolean alarmeDisparado = PendingIntent.getBroadcast( getActivity(), 0, new Intent("ALARME_DISPARADO"), PendingIntent.FLAG_NO_CREATE ) == null;
//		
//		if( alarmeDisparado ){
//			Log.i( "NOTIFICACAO","Novo Alarme" );
//			final Intent intent = new Intent("ALARME_DISPARADO");
//			final PendingIntent pendingIntent = PendingIntent.getBroadcast( getActivity(), 0, intent, 0 );
//			
//			final Calendar c = Calendar.getInstance();
//			c.setTimeInMillis( System.currentTimeMillis() );
//			
//			c.add( Calendar.SECOND, 3 );
//			
//			AlarmManager alarmManager = ( AlarmManager ) getActivity().getSystemService( Context.ALARM_SERVICE );
//			alarmManager.cancel( pendingIntent );
//			//setRepeating( AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), 5000, pendingIntent );//
//			alarmManager.set( AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent );
//		}else{
//			Log.i( "NOTIFICACAO","Alarme já ativo" );
//		}
		
		
		return rootView;
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();

//		final Intent intent = new Intent("ALARME_DISPARADO");
//		final PendingIntent pendingIntent = PendingIntent.getBroadcast( getActivity(), 0, intent, 0 );
//		
//		AlarmManager alarmManager = ( AlarmManager ) getActivity().getSystemService( Context.ALARM_SERVICE );
//		alarmManager.cancel( pendingIntent );
	}
}
