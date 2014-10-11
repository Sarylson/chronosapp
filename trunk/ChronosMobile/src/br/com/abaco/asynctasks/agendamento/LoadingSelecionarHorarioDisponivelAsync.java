package br.com.abaco.asynctasks.agendamento;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import br.com.abaco.chronos.agendamento.AgendamentoConfirmadoFragment;
import br.com.abaco.chronos.menubar.R;
import br.com.abaco.chronos.util.MensagemUtil;
import br.com.abaco.chronos.util.RestUtil;
import br.com.abaco.modelo.agendamento.AgendamentoVO;
import br.com.abaco.modelo.gradeservico.GradeServicoVO;
import br.com.abaco.modelo.horario.HorarioVO;
import br.com.abaco.modelo.rest.usuario.UsuarioVO;
import br.com.abaco.modelo.senha.SenhaVO;
import br.com.abaco.modelo.servico.ServicoVO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * Classe responsável pela finalização do Agendamento Eletrônico
 * 
 * @author Jackson Silva
 */
public class LoadingSelecionarHorarioDisponivelAsync extends AsyncTask<Void, Void, String> {

	private Activity activity;

	private ProgressDialog progressDialog;

	final FragmentManager fragmentManager;
	
	private Gson jsonUtil = new GsonBuilder().setDateFormat( "dd/MM/yyyy HH:mm:ss" ).create();

	public LoadingSelecionarHorarioDisponivelAsync( final Activity activity, final FragmentManager fragmentManager ) {
		super();
		this.activity = activity;
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

		// Carregar todos os objetos necessários

		final String jsonServico = (String) activity.getIntent().getStringExtra( activity.getString( R.string.lb_requisicao_servico_selecionado_agendamento ) );
		final ServicoVO servico = jsonUtil.fromJson( jsonServico, new TypeToken<ServicoVO>() {
		}.getType() );

		final String jsonGradeServico = (String) activity.getIntent().getStringExtra( activity.getString( R.string.lb_requisicao_grade_servico_selecionada_agendamento ) );
		final GradeServicoVO gradeServico = jsonUtil.fromJson( jsonGradeServico, new TypeToken<GradeServicoVO>() {
		}.getType() );

		final String jsonHorario = (String) activity.getIntent().getStringExtra( activity.getString( R.string.lb_requisicao_horario_selecionado_agendamento ) );
		final HorarioVO horario = jsonUtil.fromJson( jsonHorario, new TypeToken<HorarioVO>() {
		}.getType() );

		horario.setGradeHorario( gradeServico.getGradeHorario() );

		final SharedPreferences prefs = activity.getSharedPreferences( "MyprefChronos", 0 );
		final String jsonUsuario = prefs.getString( "usuario", null );

		final UsuarioVO usuario = jsonUtil.fromJson( jsonUsuario, new TypeToken<UsuarioVO>() {
		}.getType() );

		final AgendamentoVO agendamento = new AgendamentoVO();

		agendamento.setUsuario( usuario );
		agendamento.setGradeServico( gradeServico );
		agendamento.setHorario( horario );
		agendamento.setServico( servico );

		final String json = jsonUtil.toJson( agendamento, new TypeToken<AgendamentoVO>() {
		}.getType() );
		if ( json == null ) {
			return null;
		}
		final String retorno = RestUtil.post( activity.getString( R.string.url_rest_realizar_agendamento ), json );
		return retorno;
	}

	@Override
	protected void onPostExecute( String result ) {
		progressDialog.dismiss();

		//MensagemUtil.addMsg( activity, "tESTADO COM SUCESO!" );
	   if ( result != null && result.equalsIgnoreCase( "FALHA" ) ) {
	   	MensagemUtil.addMsg( activity, "Ocorreu um problema na comunicação com o servidor!\n tente novamente mais tarde." );
	   }else
		
		if ( result != null && !result.isEmpty() ) {
			final SenhaVO senha = jsonUtil.fromJson( result, new TypeToken<SenhaVO>() {
			}.getType() );

			activity.getIntent().putExtra( activity.getString( R.string.lb_requisicao_senha_confirmada_agendamento ), jsonUtil.toJson( senha, new TypeToken<SenhaVO>() {
			}.getType() ) );

			fragmentManager.beginTransaction().replace( R.id.container, new AgendamentoConfirmadoFragment() ).commit();
			
		}
	}
}
