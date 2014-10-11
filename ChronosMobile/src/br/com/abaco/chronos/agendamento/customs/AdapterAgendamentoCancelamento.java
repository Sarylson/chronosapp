package br.com.abaco.chronos.agendamento.customs;

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
public class AdapterAgendamentoCancelamento extends ArrayAdapter<AgendamentoCancelamentoItem> {

	/**
	 * Classe de Seleção do Item
	 * 
	 * @author jackson
	 *
	 */
	private class ViewSelecao {

		private TextView textData;

		private TextView textSenha;

		private TextView textUnidadeAtendimento;

		private TextView idRegistro;

		public TextView getIdRegistro() {
			return idRegistro;
		}

		public TextView getTextData() {
			return textData;
		}

		public TextView getTextSenha() {
			return textSenha;
		}

		public TextView getTextUnidadeAtendimento() {
			return textUnidadeAtendimento;
		}

		public void setIdRegistro( TextView idRegistro ) {
			this.idRegistro = idRegistro;
		}

		public void setTextData( TextView textData ) {
			this.textData = textData;
		}

		public void setTextSenha( TextView textSenha ) {
			this.textSenha = textSenha;
		}

		public void setTextUnidadeAtendimento( TextView textUnidadeAtendimento ) {
			this.textUnidadeAtendimento = textUnidadeAtendimento;
		}

	}

	private Context context;

	public AdapterAgendamentoCancelamento( final Context context, final int resourceId, final List<AgendamentoCancelamentoItem> listItems ) {
		super( context, resourceId, listItems );
		this.context = context;
	}
	
	public View getView( final int posicao, View viewConverter, final ViewGroup group ) {
		ViewSelecao selecao = null;

		final AgendamentoCancelamentoItem itemCustom = getItem( posicao );

		final LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService( Activity.LAYOUT_INFLATER_SERVICE );
		if ( viewConverter == null ) {

			viewConverter = layoutInflater.inflate( R.layout.listview_custom_cancelamento_agendamento, null );
			selecao = new ViewSelecao();
			selecao.setIdRegistro( (TextView) viewConverter.findViewById( R.id.idRegistro ) );
			selecao.setTextData( (TextView) viewConverter.findViewById( R.id.txtCancelamentoAgendamentoDataValor ) );
			selecao.setTextSenha( (TextView) viewConverter.findViewById( R.id.txtCancelamentoAgendamentoSenhaValor ) );
			selecao.setTextUnidadeAtendimento( (TextView) viewConverter.findViewById( R.id.txtCancelamentoAgendamentoUnidadeAtendimentoValor ) );
			viewConverter.setTag( selecao );
		} else {
			selecao = (ViewSelecao) viewConverter.getTag();
		}
		selecao.getTextData().setText( itemCustom.getData() );
		selecao.getTextSenha().setText( itemCustom.getSenha() );
		selecao.getTextUnidadeAtendimento().setText( itemCustom.getUnidadeAtendimento() );
		selecao.getIdRegistro().setText( String.valueOf( itemCustom.getIdRegistro() ) );
		
		return viewConverter;

	}
}
