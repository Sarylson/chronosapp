package br.com.abaco.chronos.util;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.widget.Toast;
import br.com.abaco.chronos.menubar.R;

/**
 * Mensagens do Sistema
 * 
 * @author jackson
 */
public class MensagemUtil {

	/**
	 * Mensagem de alerta rapido
	 * 
	 * @param activity
	 * @param msg
	 */
	public static void addMsg( final Context activity, final String msg ) {
		Toast.makeText( activity, msg, Toast.LENGTH_SHORT ).show();
	}

	/**
	 * Alert de Dialog Ok
	 * 
	 * @param activity
	 * @param titulo
	 * @param msg
	 * @param icone
	 */
	public static void addMsgOk( final Context activity, final String titulo, final String msg, final int icone ) {

		final AlertDialog.Builder alertDialog = new AlertDialog.Builder( activity );
		alertDialog.setTitle( titulo );
		alertDialog.setMessage( msg );
		alertDialog.setNeutralButton( R.string.lb_OK, null );
		alertDialog.setIcon( icone );
		
		alertDialog.show();
	}
	/**
	 * Alert de Confirmação
	 * 
	 * @param activity
	 * @param titulo
	 * @param msg
	 * @param icone
	 * @param listener 
	 */
	public static void addMsgConfirm( final Context activity, final String titulo, final String msg, final int icone, final OnClickListener listener ) {
		final AlertDialog.Builder alertDialog = new AlertDialog.Builder( activity );
		alertDialog.setTitle( titulo );
		alertDialog.setMessage( msg );
		alertDialog.setPositiveButton( R.string.lb_btn_confirmar, listener );
		alertDialog.setNegativeButton( R.string.lb_btn_cancelar, null );
		alertDialog.setIcon( icone );
		
		alertDialog.show();
	}
}

