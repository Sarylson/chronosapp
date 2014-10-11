package br.com.abaco.chronos.avaliacaoatendimento.asynctasks;


import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import br.com.abaco.chronos.avaliacaoatendimento.AvaliarAtendimentoRealizarAvaliacaoFragment;
import br.com.abaco.chronos.avaliacaoatendimento.customs.AdapterAvaliacaoAtendimentoSelecaoSenha;
import br.com.abaco.chronos.avaliacaoatendimento.customs.AvaliacaoAtendimentoSelecaoSenhaItem;
import br.com.abaco.chronos.menubar.R;
import br.com.abaco.chronos.util.MensagemUtil;
import br.com.abaco.chronos.util.RestUtil;
import br.com.abaco.modelo.atendimento.AtendimentoVO;
import br.com.abaco.modelo.avaliacaoatendimento.AvaliacaoAtendimentoVO;
import br.com.abaco.modelo.itemavaliacao.ItemAvaliacaoVO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * Loading solicita avaliação de atendimentos
 * 
 * @author Jackson Silva
 *
 */
public class LoadingAvaliarAtendimento extends AsyncTask<Void, Void, String> {

	private ProgressDialog progressDialog;

	private Gson jsonUtil = new GsonBuilder().setDateFormat( "dd/MM/yyyy HH:mm:ss" ).create();

	private Activity activity;

	private Menu menu;

	private FragmentManager fragmentManager;

	private View view;

	public LoadingAvaliarAtendimento( final Activity activity, final Menu menu , final FragmentManager fragmentManager,final View view) {
		super();
		
		this.menu = menu;
		this.activity = activity;
		this.fragmentManager = fragmentManager;
		this.view = view;
		progressDialog = new ProgressDialog( activity );
	}

	@Override
	protected void onPreExecute() {
		progressDialog.setMessage( "Carregando..." );
		progressDialog.show();
	}

	@Override
	protected String doInBackground( Void... params ) {

	//	final String tipoAvaliacao = activity.getIntent().getStringExtra(activity.getString( R.string.lb_requisicao_avaliacao_atendimento_tipo_avaliacao) );
		final String jsonTipoItemAvaliacao =  activity.getIntent().getStringExtra( activity.getString( R.string.lb_requisicao_avaliacao_atendimento_item_avaliacao) );
		
		final ItemAvaliacaoVO itemAvalicao = jsonUtil.fromJson( jsonTipoItemAvaliacao, new TypeToken<ItemAvaliacaoVO>() {}.getType() );
		
		final EditText descricaoAvaliacao = (EditText)activity.findViewById( R.id.txtDescricaoAvaliacao );
		
		final String jsonAtendimento =  activity.getIntent().getStringExtra( activity.getString( R.string.lb_requisicao_listar_senhas_avaliacoes) );
		
		final AtendimentoVO atendimento = jsonUtil.fromJson( jsonAtendimento, new TypeToken<AtendimentoVO>() {}.getType() );
		
		final AvaliacaoAtendimentoVO avaliacaoAtendimentoVO = new AvaliacaoAtendimentoVO();
		avaliacaoAtendimentoVO.setAtendimento( atendimento );
		avaliacaoAtendimentoVO.setItemAvaliacao( itemAvalicao );
		avaliacaoAtendimentoVO.setDescricaoAvaliacao( descricaoAvaliacao.getEditableText().toString() );
		
		final String json = jsonUtil.toJson( avaliacaoAtendimentoVO , new TypeToken<AvaliacaoAtendimentoVO>() {}.getType() );

		final String retorno = RestUtil.post( activity.getString( R.string.url_rest_avaliar_atendimento ), json );
		
		return retorno;
	}

	@Override
	protected void onPostExecute( String result ) {
		   progressDialog.dismiss();
			
		   if( result != null && !result.isEmpty() ){
			   MensagemUtil.addMsg( activity, "Avaliação realizada com sucesso!" );
		   
				new LoadingAvaliarAtendimentoSelecaoSenha( activity, view, menu , fragmentManager).execute();
		   }
			
			//LoadingAvaliarAtendimentoSelecaoSenha
	}
}

