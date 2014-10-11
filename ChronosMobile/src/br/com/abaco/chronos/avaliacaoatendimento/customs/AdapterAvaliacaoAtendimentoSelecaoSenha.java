package br.com.abaco.chronos.avaliacaoatendimento.customs;

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
 * Adapter de Agendamento Cancelamento
 * 
 * @author Jackson Silva
 */
public class AdapterAvaliacaoAtendimentoSelecaoSenha extends ArrayAdapter<AvaliacaoAtendimentoSelecaoSenhaItem> {

	/**
	 * Classe de Seleção do Item de Avaliação de Atendimentos
	 * 
	 * @author Jackson Silva
	 */
	private class ViewSelecao {

		private TextView textData;

		private TextView textSenha;

		private TextView textServico;

		private TextView idRegistro;

		private TextView textAvaliacao;

		public TextView getTextAvaliacao() {
			return textAvaliacao;
		}

		public void setTextAvaliacao( TextView textAvaliacao ) {
			this.textAvaliacao = textAvaliacao;
		}

		public TextView getIdRegistro() {
			return idRegistro;
		}

		public TextView getTextData() {
			return textData;
		}

		public TextView getTextSenha() {
			return textSenha;
		}

		public void setIdRegistro( TextView idRegistro ) {
			this.idRegistro = idRegistro;
		}

		public TextView getTextServico() {
			return textServico;
		}

		public void setTextServico( TextView textServico ) {
			this.textServico = textServico;
		}

		public void setTextData( TextView textData ) {
			this.textData = textData;
		}

		public void setTextSenha( TextView textSenha ) {
			this.textSenha = textSenha;
		}

	}

	private Context context;

	public AdapterAvaliacaoAtendimentoSelecaoSenha( final Context context, final int resourceId, final List<AvaliacaoAtendimentoSelecaoSenhaItem> listItems ) {
		super( context, resourceId, listItems );
		this.context = context;
	}

	public View getView( final int posicao, View viewConverter, final ViewGroup group ) {
		ViewSelecao selecao = null;

		final AvaliacaoAtendimentoSelecaoSenhaItem itemCustom = getItem( posicao );

		final LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService( Activity.LAYOUT_INFLATER_SERVICE );
		if ( viewConverter == null ) {

			viewConverter = layoutInflater.inflate( R.layout.listview_selecionar_senha_avaliacao_atendimento, null );
			selecao = new ViewSelecao();
			selecao.setIdRegistro( (TextView) viewConverter.findViewById( R.id.idRegistro ) );
			selecao.setTextData( (TextView) viewConverter.findViewById( R.id.txtAvaliacaoAtendimentoData ) );
			selecao.setTextSenha( (TextView) viewConverter.findViewById( R.id.txtAvaliacaoAtendimentoSenha ) );
			selecao.setTextServico( (TextView) viewConverter.findViewById( R.id.txtAvaliacaoAtendimentoServico ) );
			selecao.setTextAvaliacao( (TextView) viewConverter.findViewById( R.id.txtAvaliacaoAtendimentoAvaliado ) );
			
			viewConverter.setTag( selecao );
		} else {
			selecao = (ViewSelecao) viewConverter.getTag();
		}
		selecao.getTextData().setText( itemCustom.getData() );
		selecao.getTextSenha().setText( itemCustom.getSenha() );
		selecao.getTextServico().setText( itemCustom.getServico() );
		selecao.getIdRegistro().setText( String.valueOf( itemCustom.getIdRegistro() ) );
		selecao.getTextAvaliacao().setText( itemCustom.getAvaliacao() );
		return viewConverter;

	}
}
