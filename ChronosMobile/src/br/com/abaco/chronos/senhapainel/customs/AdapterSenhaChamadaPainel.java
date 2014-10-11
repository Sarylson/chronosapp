package br.com.abaco.chronos.senhapainel.customs;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import br.com.abaco.chronos.menubar.R;

/**
 * Adapter de Senha Chamadas no Painel
 * 
 * @author Jackson Silva
 */
public class AdapterSenhaChamadaPainel extends ArrayAdapter<SenhaChamadaPainelItem> {

	private class ViewSelecao {

		private TextView textDataHora;

		private TextView textSenha;

		private TextView textSituacao;

		public TextView getTextDataHora() {
			return textDataHora;
		}

		public void setTextDataHora( TextView textDataHora ) {
			this.textDataHora = textDataHora;
		}

		public TextView getTextSenha() {
			return textSenha;
		}

		public void setTextSenha( TextView textSenha ) {
			this.textSenha = textSenha;
		}

		public TextView getTextSituacao() {
			return textSituacao;
		}

		public void setTextSituacao( TextView textSituacao ) {
			this.textSituacao = textSituacao;
		}

		public TextView getIdRegistro() {
			return idRegistro;
		}

		public void setIdRegistro( TextView idRegistro ) {
			this.idRegistro = idRegistro;
		}

		private TextView idRegistro;
	}

	private Context context;

	public AdapterSenhaChamadaPainel( final Context context, final int resourceId, final List<SenhaChamadaPainelItem> listItems ) {
		super( context, resourceId, listItems );
		this.context = context;
	}

	public View getView( final int posicao, View viewConverter, final ViewGroup group ) {
		ViewSelecao selecao = null;

		final SenhaChamadaPainelItem itemCustom = getItem( posicao );

		final LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService( Activity.LAYOUT_INFLATER_SERVICE );
		if ( viewConverter == null ) {

			viewConverter = layoutInflater.inflate( R.layout.listview_custom_senhas_chamadas_painel, null );
			selecao = new ViewSelecao();
			selecao.setIdRegistro( (TextView) viewConverter.findViewById( R.id.idRegistro ) );
			selecao.setTextDataHora( (TextView) viewConverter.findViewById( R.id.txtSenhaPainelDataHora ) );
			selecao.setTextSenha( (TextView) viewConverter.findViewById( R.id.txtSenhaPainelSenha ) );
			selecao.setTextSituacao( (TextView) viewConverter.findViewById( R.id.txtSenhaPainelSituacao) );
			viewConverter.setTag( selecao );
		} else {
			selecao = (ViewSelecao) viewConverter.getTag();
		}
		selecao.getTextDataHora().setText( itemCustom.getDataHora() );
		selecao.getTextSenha().setText( itemCustom.getSenha() );
		selecao.getTextSituacao().setText( itemCustom.getSituacao());
		selecao.getIdRegistro().setText( String.valueOf( itemCustom.getIdRegistro() ) );

		return viewConverter;

	}
}
