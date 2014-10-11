package br.com.abaco.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import br.com.abaco.chronos.notificacao.LoadingVerificarTempoParaAtendimento;

/**
 * Broadcast Receiver Geral
 * 
 * @author Jackson Silva
 */
public class BroadcastReceiverGeral extends BroadcastReceiver {

	@Override
	public void onReceive( Context context, Intent intent ) {
		
	   new LoadingVerificarTempoParaAtendimento(context,intent).execute( );
		
	}

}
