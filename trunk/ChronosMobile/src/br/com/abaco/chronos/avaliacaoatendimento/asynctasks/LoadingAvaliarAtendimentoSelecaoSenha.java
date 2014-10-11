package br.com.abaco.chronos.avaliacaoatendimento.asynctasks;



import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import br.com.abaco.chronos.avaliacaoatendimento.AvaliarAtendimentoRealizarAvaliacaoFragment;
import br.com.abaco.chronos.avaliacaoatendimento.customs.AdapterAvaliacaoAtendimentoSelecaoSenha;
import br.com.abaco.chronos.avaliacaoatendimento.customs.AvaliacaoAtendimentoSelecaoSenhaItem;
import br.com.abaco.chronos.menubar.R;
import br.com.abaco.chronos.util.MensagemUtil;
import br.com.abaco.chronos.util.RestUtil;
import br.com.abaco.modelo.atendimento.AtendimentoVO;
import br.com.abaco.modelo.rest.usuario.UsuarioVO;
import br.com.abaco.modelo.rest.usuario.usuarioexterno.UsuarioExternoVO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * Loading solicita avaliação de atendimentos
 * 
 * @author Jackson Silva
 *
 */
public class LoadingAvaliarAtendimentoSelecaoSenha extends AsyncTask<Void, Void, String> {

	private ProgressDialog progressDialog;

	private Gson jsonUtil = new GsonBuilder().setDateFormat( "dd/MM/yyyy HH:mm:ss" ).create();

	private Activity activity;

	private View viewRoot;
	
	private Menu menu;

	private FragmentManager fragmentManager;

	public LoadingAvaliarAtendimentoSelecaoSenha( final Activity activity, final View view , final Menu menu , final FragmentManager fragmentManager) {
		super();
		
		this.menu = menu;
		this.activity = activity;
		this.viewRoot = view;
		this.fragmentManager = fragmentManager;
		progressDialog = new ProgressDialog( activity );
	}

	@Override
	protected void onPreExecute() {
		progressDialog.setMessage( "Carregando..." );
		progressDialog.show();
	}

	@Override
	protected String doInBackground( Void... params ) {

		final String usuarioLogado = this.activity.getSharedPreferences( "MyprefChronos", 0 ).getString( "usuario", " " );

		final UsuarioVO usuario = jsonUtil.fromJson( usuarioLogado, new TypeToken<UsuarioVO>() {}.getType() );

		final UsuarioExternoVO usuarioExterno = usuario.getUsuarioExterno();

		final String json = jsonUtil.toJson( usuarioExterno, new TypeToken<UsuarioExternoVO>() {}.getType() );

		final String retorno = RestUtil.post( activity.getString( R.string.url_rest_listar_atendimentos_para_avaliacao ), json );
		
		return retorno;
	}

	@Override
	protected void onPostExecute( String result ) {
		progressDialog.dismiss();

		if( (result != null && result.equals( "[]" ) ) || result.equals( "" ) ){
			
			Log.i("DEBVIUDDD", "ENTROU AQUI");
			
			final LinearLayout linearLayoutAvaliacaoAtendimento = (LinearLayout) viewRoot.findViewById( R.id.idLinearLayoutAvaliacaoAtendimentoSemRegistros );
			linearLayoutAvaliacaoAtendimento.setVisibility( View.VISIBLE );
			
			
			final LinearLayout linearLayoutAvaliacaoAtendimentoComRegistros = (LinearLayout) viewRoot.findViewById( R.id.idLinearLayoutAvaliacaoAtendimentoComRegistros );
			linearLayoutAvaliacaoAtendimentoComRegistros.setVisibility( View.INVISIBLE );
			
			
		}else  if ( result != null && result.equalsIgnoreCase( "FALHA" ) ) {
		   	MensagemUtil.addMsg( activity, "Ocorreu um problema na comunicação com o servidor! tente novamente mais tarde." );
		}else if ( result != null && !result.isEmpty() ) {
			Log.i("DEBVIUDDD", result);
			final List<AtendimentoVO> atendimentos = jsonUtil.fromJson( result, new TypeToken<List<AtendimentoVO>>() {}.getType() );

			final List<AvaliacaoAtendimentoSelecaoSenhaItem> listaItems = new ArrayList<AvaliacaoAtendimentoSelecaoSenhaItem>();
			for ( final AtendimentoVO atendimentoFor : atendimentos ) {

				AvaliacaoAtendimentoSelecaoSenhaItem selecaoAtendimentoItem = new AvaliacaoAtendimentoSelecaoSenhaItem();
				selecaoAtendimentoItem.setIdRegistro( atendimentoFor.getId() );
				selecaoAtendimentoItem.setData( atendimentoFor.getDataFormatada() );
				selecaoAtendimentoItem.setSenha( atendimentoFor.getSenha() );
				selecaoAtendimentoItem.setServico( atendimentoFor.getAgendamento().getServico().getNome() );
				selecaoAtendimentoItem.setAvaliacao(  atendimentoFor.getAvaliadoAtendimento().getTexto() );
				
				listaItems.add( selecaoAtendimentoItem );
			}

			final AdapterAvaliacaoAtendimentoSelecaoSenha adapter = new AdapterAvaliacaoAtendimentoSelecaoSenha( this.activity, R.layout.listview_selecionar_senha_avaliacao_atendimento, listaItems );

			final ListView listViewSelecionarSenhaAtendimento = (ListView) viewRoot.findViewById( br.com.abaco.chronos.menubar.R.id.listViewSelecionarSenhaAvaliacaoAtendimento );

			listViewSelecionarSenhaAtendimento.setAdapter( adapter );
			
			listViewSelecionarSenhaAtendimento.setOnItemClickListener( new OnItemClickListener() {

				@Override
				public void onItemClick( AdapterView<?> parent, View view, int position, long id ) {
					final AvaliacaoAtendimentoSelecaoSenhaItem avaliacaoAtendimentoSelecaoSenhaItem = (AvaliacaoAtendimentoSelecaoSenhaItem) parent.getItemAtPosition( position );
					
					final AtendimentoVO atendimento  = new AtendimentoVO();
					atendimento.setId( avaliacaoAtendimentoSelecaoSenhaItem.getIdRegistro() );
					
					final String jsonAtendimento = jsonUtil.toJson( atendimento, new TypeToken<AtendimentoVO>() {}.getType() );
					//Seta um novo fragmento na tela
					activity.getIntent().putExtra( activity.getString( R.string.lb_requisicao_listar_senhas_avaliacoes ), jsonAtendimento );
					
					final AvaliarAtendimentoRealizarAvaliacaoFragment atendimentoRealizarAvaliacaoFragment = new AvaliarAtendimentoRealizarAvaliacaoFragment(menu,fragmentManager);
					fragmentManager.beginTransaction().replace( R.id.container, atendimentoRealizarAvaliacaoFragment ).addToBackStack( atendimentoRealizarAvaliacaoFragment.getClass().getName() ).commit();
				}
			} );

		}
	}
}
