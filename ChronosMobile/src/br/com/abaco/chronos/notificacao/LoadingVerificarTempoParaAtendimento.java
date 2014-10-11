package br.com.abaco.chronos.notificacao;



import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;
import br.com.abaco.chronos.menubar.HomeActivity;
import br.com.abaco.chronos.menubar.R;
import br.com.abaco.chronos.util.MensagemUtil;
import br.com.abaco.chronos.util.RestUtil;
import br.com.abaco.modelo.rest.usuario.UsuarioVO;
import br.com.abaco.modelo.rest.usuario.usuarioexterno.UsuarioExternoVO;
import br.com.abaco.modelo.senhapainel.SenhaPainelVO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * LoadingVerificarTempoParaAtendimento
 * 
 * @author Jackson Silva
 *
 */
public class LoadingVerificarTempoParaAtendimento extends AsyncTask<Void, Void, String> {

	private Gson jsonUtil = new GsonBuilder().setDateFormat( "dd/MM/yyyy HH:mm:ss" ).create();

	private Context activity;


	public LoadingVerificarTempoParaAtendimento( final Context activity ) {
		super();
		this.activity = activity;
	}

	public LoadingVerificarTempoParaAtendimento( final Context activity, Intent i ) {
		super();
		this.activity = activity;
	}

	@Override
	protected void onPreExecute() {
	}

	@Override
	protected String doInBackground( Void... params ) {

		final String usuarioLogado = this.activity.getSharedPreferences( "MyprefChronos", 0 ).getString( "usuario", " " );

		final UsuarioVO usuario = jsonUtil.fromJson( usuarioLogado, new TypeToken<UsuarioVO>() {}.getType() );

		final UsuarioExternoVO usuarioExterno = usuario.getUsuarioExterno();

		final String json = jsonUtil.toJson( usuarioExterno, new TypeToken<UsuarioExternoVO>() {}.getType() );

		final String retorno = RestUtil.post( activity.getString( R.string.url_rest_painel_senhas ), json );
		return retorno;
	}

	@Override
	protected void onPostExecute( String result ) {
		
		if(result != null && result.equalsIgnoreCase( "FALHA" )){
			 MensagemUtil.addMsg( this.activity, "Problemas na comunicação com o servidor." );
		}else if ( result != null && !result.isEmpty() ) {

			if( result.contains( "Error:" )){
			    MensagemUtil.addMsgOk( this.activity, "Informação", result, R.drawable.info_dark );
			}else{
			
				final SenhaPainelVO senhaPainel = jsonUtil.fromJson( result, new TypeToken<SenhaPainelVO>() {}.getType() );
				
				final NotificationManager notificationManager = (NotificationManager) activity.getSystemService( Context.NOTIFICATION_SERVICE );
				
				final Intent i = new Intent( activity, HomeActivity.class );
				i.putExtra( "jacksonsilva", "teste" );
				
				final PendingIntent pendingIntent = PendingIntent.getActivity( activity, 0, i, Intent.FLAG_ACTIVITY_NEW_TASK );
				
				final NotificationCompat.Builder builder = new NotificationCompat.Builder( activity );
				builder.setTicker( "Notificação do Painel" );
				builder.setContentTitle( "Chronos - Notificação do Painel" );
				builder.setContentText( senhaPainel.getTempoParaAtendimento() );
				builder.setSmallIcon( R.drawable.ic_launcher );
				builder.setLargeIcon( BitmapFactory.decodeResource( activity.getResources(), R.drawable.ic_launcher ) );
				builder.setContentIntent( pendingIntent );

				final NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
				inboxStyle.addLine( senhaPainel.getTempoParaAtendimento() );
				builder.setStyle( inboxStyle );

				final Notification notification = builder.build();
				
				notification.vibrate = new long[] { 150, 350, 150, 500 };
				notification.flags = Notification.FLAG_AUTO_CANCEL;

				notificationManager.notify( R.drawable.ic_launcher, notification );

				final Uri som = RingtoneManager.getDefaultUri( RingtoneManager.TYPE_NOTIFICATION );
				final Ringtone toque = RingtoneManager.getRingtone( activity, som );
				toque.play();
			}
		}
	}

	
}
