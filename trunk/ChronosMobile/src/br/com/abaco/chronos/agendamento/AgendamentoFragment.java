package br.com.abaco.chronos.agendamento;

import java.util.ArrayList;
import java.util.List;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import br.com.abaco.chronos.menubar.R;
import br.com.abaco.chronos.util.MensagemUtil;
import br.com.abaco.chronos.util.RestUtil;
import br.com.abaco.customs.AdpterListViewCustom;
import br.com.abaco.customs.ItemListCustom;
import br.com.abaco.modelo.gradehorario.GradeHorarioVO;
import br.com.abaco.modelo.gradeservico.GradeServicoVO;
import br.com.abaco.modelo.horario.HorarioVO;
import br.com.abaco.modelo.servico.ServicoVO;
import br.com.abaco.modelo.servicoatividade.ServicoAtividadeVO;
import br.com.abaco.modelo.unidadenegocio.UnidadeNegocioVO;
import br.com.abaco.modelo.unidadeorganizacional.UnidadeOrganizacionalVO;
import br.com.abaco.modelo.unidadeservicoatividade.UnidadeServicoAtividadeVO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * Agendamento
 * 
 * @author jackson
 */
@SuppressLint( "ValidFragment" )
public class AgendamentoFragment extends Fragment {

	private ListView listViewUnidadeOrganizacional;

	private String VAZIO = "";

	private View rootView = null;
	
	private AnimatorSet set;

	private Menu menu;
	
	private Gson jsonUtil = new Gson();

	final Gson gsonFormatDate = new GsonBuilder().setDateFormat( "dd/MM/yyyy HH:mm:ss" ).create();

	public AgendamentoFragment(final Menu menu){
		this.menu = menu;
		
	}
	public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {

		rootView = inflater.inflate( br.com.abaco.chronos.menubar.R.layout.activity_agendamento, container, false );

		listViewUnidadeOrganizacional = (ListView) rootView.findViewById( br.com.abaco.chronos.menubar.R.id.listViewUnidadeOrganizacional );
		new LoadingRealizarAgendamentoAsync().execute();
		
		return rootView;
	}

	@Override
	public void onDestroy() {

		getActivity().getIntent().removeExtra( getString( R.string.lb_requisicao_unidade_organizacional_selecionada_agendamento ) );
		getActivity().getIntent().removeExtra( getString( R.string.lb_requisicao_unidade_negocio_selecionada_agendamento ) );
		getActivity().getIntent().removeExtra( getString( R.string.lb_requisicao_servico_selecionado_agendamento ) );
		getActivity().getIntent().removeExtra( getString( R.string.lb_requisicao_grade_servico_selecionada_agendamento ) );
		getActivity().getIntent().removeExtra( getString( R.string.lb_requisicao_horario_selecionado_agendamento ) );
		
		super.onDestroy();
	}

	/**
	 * Responsável pelo carregamento do Agendamento
	 * 
	 * @author Jackson Silva
	 */
	public class LoadingRealizarAgendamentoAsync extends AsyncTask<Void, Void, String> {

		private ProgressDialog progressDialog = new ProgressDialog( getActivity() );

		
		@Override
		protected void onPreExecute() {
			progressDialog.setMessage( "Carregando..." );
			progressDialog.show();
		}

		@Override
		protected String doInBackground( Void... params ) {

			return VAZIO;
		}

		@Override
		protected void onPostExecute( String result ) {
			progressDialog.dismiss();
			
			MenuItem item = menu.findItem( R.id.action_home );
			item.setVisible(Boolean.FALSE );
			
			MenuItem itemBack = menu.findItem( R.id.idBackMenu );
			itemBack.setVisible(Boolean.TRUE );
			
			final Button confirmar = (Button) rootView.findViewById( R.id.btn_confirmar_agendamento );
			confirmar.setVisibility( View.INVISIBLE );
			
			final String jsonUnidadeOrganizacional = (String) getActivity().getIntent().getStringExtra( getString( R.string.lb_requisicao_unidade_organizacional_selecionada_agendamento ) );
			final UnidadeOrganizacionalVO unidadeOrganizacional = jsonUtil.fromJson( jsonUnidadeOrganizacional, new TypeToken<UnidadeOrganizacionalVO>() {
			}.getType() );

			final String jsonUnidadeNegocio = (String) getActivity().getIntent().getStringExtra( getString( R.string.lb_requisicao_unidade_negocio_selecionada_agendamento ) );
			final UnidadeNegocioVO unidadeNegocio = jsonUtil.fromJson( jsonUnidadeNegocio, new TypeToken<UnidadeNegocioVO>() {
			}.getType() );

			final String jsonServico = (String) getActivity().getIntent().getStringExtra( getString( R.string.lb_requisicao_servico_selecionado_agendamento ) );
			final ServicoVO servico = jsonUtil.fromJson( jsonServico, new TypeToken<ServicoVO>() {
			}.getType() );
			
			final String jsonGradeServico =  (String) getActivity().getIntent().getStringExtra( getString( R.string.lb_requisicao_grade_servico_selecionada_agendamento ) );
         final GradeServicoVO gradeServico = jsonUtil.fromJson( jsonGradeServico, new TypeToken<GradeServicoVO>() {}.getType() );
			
         
         final String jsonHorario = (String) getActivity().getIntent().getStringExtra( getString( R.string.lb_requisicao_horario_selecionado_agendamento ) );
         final HorarioVO horario =  jsonUtil.fromJson( jsonHorario, new TypeToken<HorarioVO>() {}.getType() );
         
         final boolean mostrarConfirmar = verificarTodasInformacoesValidas(jsonGradeServico,jsonServico,jsonUnidadeNegocio,jsonUnidadeOrganizacional,jsonHorario);
			if(mostrarConfirmar){
				confirmar.setVisibility( View.VISIBLE );
				
				ObjectAnimator mover = ObjectAnimator.ofFloat( confirmar,  "alpha", 0.40f, 1, 1);
				mover.setDuration( 1500 );

				set = new AnimatorSet();
				set.play( mover );
				set.start();
			}
         final List<ItemListCustom> listaItems = new ArrayList<ItemListCustom>();
			for ( final EnumAgendamento enumAgendamentoFor : EnumAgendamento.values() ) {
				ItemListCustom custom = null;
				// Seta o valor selecionado para unidade organizacional
				if ( ( unidadeOrganizacional != null && unidadeOrganizacional.getId() != null ) && enumAgendamentoFor.getId() == EnumAgendamento.UNIDADE_ORGANIZACIONAL.getId() ) {
					custom = new ItemListCustom( enumAgendamentoFor.getId(), R.drawable.expandir_selecionado, unidadeOrganizacional.getNome() );
					// getActivity().getIntent().removeExtra( "unidadeNegocioSelecionada" );
					listaItems.add( custom );
					continue;
				}
				if ( ( unidadeNegocio != null && unidadeNegocio.getId() != null ) && enumAgendamentoFor.getId() == EnumAgendamento.UNIDADE_NEGOCIO.getId() ) {
					custom = new ItemListCustom( enumAgendamentoFor.getId(), R.drawable.expandir_selecionado, unidadeNegocio.getNome() );
					listaItems.add( custom );
					continue;
				}
				if ( ( servico != null && servico.getId() != null ) && enumAgendamentoFor.getId() == EnumAgendamento.SERVICO.getId() ) {
					custom = new ItemListCustom( enumAgendamentoFor.getId(), R.drawable.expandir_selecionado, servico.getNome() );
					listaItems.add( custom );
					continue;
				}
				if ( ( gradeServico != null && gradeServico.getId() != null ) && enumAgendamentoFor.getId() == EnumAgendamento.DATA.getId() ) {
					custom = new ItemListCustom( enumAgendamentoFor.getId(), R.drawable.expandir_selecionado, gradeServico.getGradeHorario().getDataGradeHorarioFormatada() );
					listaItems.add( custom );
					continue;
				}
				if ( ( horario != null && horario.getId() != null ) && enumAgendamentoFor.getId() == EnumAgendamento.HORARIO.getId() ) {
					custom = new ItemListCustom( enumAgendamentoFor.getId(), R.drawable.expandir_selecionado, horario.getDataHoraGradeHorarioFormatada() );
					listaItems.add( custom );
					continue;
				}
				
				custom = new ItemListCustom( enumAgendamentoFor.getId(), R.drawable.expandir_back, getString( enumAgendamentoFor.getTitulo() ) );
				listaItems.add( custom );
			}
			final AdpterListViewCustom adapter = new AdpterListViewCustom( getActivity(), R.layout.listview_custom, listaItems );
			listViewUnidadeOrganizacional.setAdapter( adapter );
			listViewUnidadeOrganizacional.setOnItemClickListener( new OnItemClickListener() {

				@Override
				public void onItemClick( AdapterView<?> parent, View view, int position, long id ) {
					final ItemListCustom itemCustomList = (ItemListCustom) parent.getItemAtPosition( position );

					final int itemSelecionado = Integer.parseInt( String.valueOf( itemCustomList.getIdRegistro() ) );
					switch ( itemSelecionado ) {

						case 1: // Unidade Organizacional
							new LoadingSelecionarUnidadeOrganizacionalAsync().execute();
							break;
						case 2:// UnidadeNegocio
							if ( unidadeOrganizacional != null ) {
								new LoadingSelecionarUnidadeNegocioAsync().execute();
							} else {
								MensagemUtil.addMsg( getActivity(), getString( R.string.msg_agendamento_informe_unidade_organizacional ) );
							}
							break;
						case 3:// Serviços
							if ( unidadeNegocio != null ) {
								new LoadingSelecionarServicosAsync().execute();
							} else {
								MensagemUtil.addMsg( getActivity(), getString( R.string.msg_agendamento_informe_unidade_negocio ) );
							}
							break;
						case 4:// Datas
							 if ( servico != null ) {
							    new LoadingSelecionarDataDisponivelAsync().execute( );
							 } else {
							MensagemUtil.addMsg( getActivity(), getString( R.string.msg_agendamento_informe_servico ) );
							 }
							break;
						case 5:// Horários
							 if ( gradeServico != null ) {
							    new LoadingSelecionarHorarioDisponivelAsync().execute( );
							 } else {
									MensagemUtil.addMsg( getActivity(), getString( R.string.msg_agendamento_informe_horario ) );
							 }
							break;
					 }
				}
			} );
		}
	}

	/**
	 * Classe responsável pela seleção da Unidade Organizacional
	 * 
	 * @author jackson
	 */
	public class LoadingSelecionarUnidadeOrganizacionalAsync extends AsyncTask<Void, Void, String> {

		private ProgressDialog progressDialog = new ProgressDialog( getActivity() );

		@Override
		protected void onPreExecute() {
			progressDialog.setMessage( "Carregando..." );
			progressDialog.show();
		}

		@Override
		protected String doInBackground( Void... params ) {

			getActivity().getIntent().removeExtra( getString( R.string.lb_requisicao_unidade_negocio_selecionada_agendamento ) );
			getActivity().getIntent().removeExtra( getString( R.string.lb_requisicao_servico_selecionado_agendamento ) );

			final String retorno = RestUtil.get( getString( R.string.url_rest_listar_unidades_organizacionais_ativas ) );
			return retorno;
		}

		@Override
		protected void onPostExecute( String result ) {
			progressDialog.dismiss();
			final Button confirmar = (Button) rootView.findViewById( R.id.btn_confirmar_agendamento );
			confirmar.setVisibility( View.INVISIBLE );

		   if ( result != null && result.equalsIgnoreCase( "FALHA" ) ) {
		   	MensagemUtil.addMsg( getActivity(), "Ocorreu um problema na comunicação com o servidor!\n tente novamente mais tarde." );
		   }else
			if ( result != null && !result.isEmpty() ) {

				final List<UnidadeOrganizacionalVO> unidadesOrganizacionaisAtivas = gsonFormatDate.fromJson( result, new TypeToken<List<UnidadeOrganizacionalVO>>() {
				}.getType() );

				final List<ItemListCustom> listaItems = new ArrayList<ItemListCustom>();
				for ( final UnidadeOrganizacionalVO unidadeOrganizacionalFor : unidadesOrganizacionaisAtivas ) {
					ItemListCustom custom = new ItemListCustom( unidadeOrganizacionalFor.getId(), R.drawable.expandir_back, unidadeOrganizacionalFor.getNome() );
					listaItems.add( custom );
				}

				final AdpterListViewCustom adapter = new AdpterListViewCustom( getActivity(), R.layout.listview_custom, listaItems );

				listViewUnidadeOrganizacional.setAdapter( adapter );

				listViewUnidadeOrganizacional.setOnItemClickListener( new OnItemClickListener() {

					@Override
					public void onItemClick( AdapterView<?> parent, View view, int position, long id ) {
						final ItemListCustom itemSelecionado = (ItemListCustom) parent.getItemAtPosition( position );
						if ( itemSelecionado != null ) {

							final UnidadeOrganizacionalVO unidadeOrganizacional = new UnidadeOrganizacionalVO();
							unidadeOrganizacional.setId( itemSelecionado.getIdRegistro() );
							unidadeOrganizacional.setNome( itemSelecionado.getTitulo() );

							getActivity().getIntent().putExtra( getString( R.string.lb_requisicao_unidade_organizacional_selecionada_agendamento ), jsonUtil.toJson( unidadeOrganizacional, new TypeToken<UnidadeOrganizacionalVO>() {
							}.getType() ) );
							new LoadingRealizarAgendamentoAsync().execute();
						}
					}
				} );

			}
		}
	}

	/**
	 * Classe responsável pela selecção do Unidade de Atendimento
	 * 
	 * @author jackson
	 */
	public class LoadingSelecionarUnidadeNegocioAsync extends AsyncTask<Void, Void, String> {

		private ProgressDialog progressDialog = new ProgressDialog( getActivity() );

		@Override
		protected void onPreExecute() {
			progressDialog.setMessage( "Carregando..." );
			progressDialog.show();
		}

		@Override
		protected String doInBackground( Void... params ) {

			getActivity().getIntent().removeExtra( getString( R.string.lb_requisicao_servico_selecionado_agendamento ) );

			final String json = (String) getActivity().getIntent().getStringExtra( getString( R.string.lb_requisicao_unidade_organizacional_selecionada_agendamento ) );
			if ( json == null ) {
				return null;
			}
			final String retorno = RestUtil.post( getString( R.string.url_rest_listar_unidades_negocio_por_unidade_organizacional ), json );
			return retorno;
		}

		@Override
		protected void onPostExecute( String result ) {
			progressDialog.dismiss();
			final Button confirmar = (Button) rootView.findViewById( R.id.btn_confirmar_agendamento );
			confirmar.setVisibility( View.INVISIBLE );
			
		   if ( result != null && result.equalsIgnoreCase( "FALHA" ) ) {
		   	MensagemUtil.addMsg( getActivity(), "Ocorreu um problema na comunicação com o servidor!\n tente novamente mais tarde." );
		   }else
			if ( result != null && !result.isEmpty() ) {

				final List<UnidadeNegocioVO> unidadeNegocios = gsonFormatDate.fromJson( result, new TypeToken<List<UnidadeNegocioVO>>() {
				}.getType() );

				final List<ItemListCustom> listaItems = new ArrayList<ItemListCustom>();
				for ( final UnidadeNegocioVO unidadeNegocioFor : unidadeNegocios ) {
					ItemListCustom custom = new ItemListCustom( unidadeNegocioFor.getId(), R.drawable.selecione_black, unidadeNegocioFor.getNome() );
					listaItems.add( custom );
				}

				final AdpterListViewCustom adapter = new AdpterListViewCustom( getActivity(), R.layout.listview_custom, listaItems );

				listViewUnidadeOrganizacional.setAdapter( adapter );

				listViewUnidadeOrganizacional.setOnItemClickListener( new OnItemClickListener() {

					@Override
					public void onItemClick( AdapterView<?> parent, View view, int position, long id ) {
						final ItemListCustom itemSelecionado = (ItemListCustom) parent.getItemAtPosition( position );
						if ( itemSelecionado != null ) {

							final UnidadeNegocioVO unidadeNegocio = new UnidadeNegocioVO();
							unidadeNegocio.setId( itemSelecionado.getIdRegistro() );
							unidadeNegocio.setNome( itemSelecionado.getTitulo() );

							getActivity().getIntent().putExtra( getString( R.string.lb_requisicao_unidade_negocio_selecionada_agendamento ), jsonUtil.toJson( unidadeNegocio, new TypeToken<UnidadeNegocioVO>() {
							}.getType() ) );
							new LoadingRealizarAgendamentoAsync().execute();
						}
					}
				} );

			}
		}
	}

	/**
	 * Classe responsável pela selecção do Servico
	 * 
	 * @author jackson
	 */
	public class LoadingSelecionarServicosAsync extends AsyncTask<Void, Void, String> {

		private ProgressDialog progressDialog = new ProgressDialog( getActivity() );

		@Override
		protected void onPreExecute() {
			progressDialog.setMessage( "Carregando..." );
			progressDialog.show();
		}

		@Override
		protected String doInBackground( Void... params ) {

			getActivity().getIntent().removeExtra( getString( R.string.lb_requisicao_grade_servico_selecionada_agendamento ) );
			
			final String json = (String) getActivity().getIntent().getStringExtra( getString( R.string.lb_requisicao_unidade_negocio_selecionada_agendamento ) );
			if ( json == null ) {
				return null;
			}
			final String retorno = RestUtil.post( getString( R.string.url_rest_listar_servicos_por_unidade_negocio ), json );
			return retorno;
		}

		@Override
		protected void onPostExecute( String result ) {
			progressDialog.dismiss();
			final Button confirmar = (Button) rootView.findViewById( R.id.btn_confirmar_agendamento );
			confirmar.setVisibility( View.INVISIBLE );
		   if ( result != null && result.equalsIgnoreCase( "FALHA" ) ) {
		   	MensagemUtil.addMsg( getActivity(), "Ocorreu um problema na comunicação com o servidor!\n tente novamente mais tarde." );
		   }else
			if ( result != null && !result.isEmpty() ) {

				final List<ServicoVO> servicos = gsonFormatDate.fromJson( result, new TypeToken<List<ServicoVO>>() {
				}.getType() );

				final List<ItemListCustom> listaItems = new ArrayList<ItemListCustom>();
				for ( final ServicoVO servicoFor : servicos ) {
					ItemListCustom custom = new ItemListCustom( servicoFor.getId(), R.drawable.selecione_black, servicoFor.getNome() );
					listaItems.add( custom );
				}

				final AdpterListViewCustom adapter = new AdpterListViewCustom( getActivity(), R.layout.listview_custom, listaItems );

				listViewUnidadeOrganizacional.setAdapter( adapter );

				listViewUnidadeOrganizacional.setOnItemClickListener( new OnItemClickListener() {

					@Override
					public void onItemClick( AdapterView<?> parent, View view, int position, long id ) {
						final ItemListCustom itemSelecionado = (ItemListCustom) parent.getItemAtPosition( position );
						if ( itemSelecionado != null ) {

							final ServicoVO servico = new ServicoVO();
							servico.setId( itemSelecionado.getIdRegistro() );
							servico.setNome( itemSelecionado.getTitulo() );
							getActivity().getIntent().putExtra( getString( R.string.lb_requisicao_servico_selecionado_agendamento ), jsonUtil.toJson( servico, new TypeToken<ServicoVO>() {
							}.getType() ) );
							new LoadingRealizarAgendamentoAsync().execute();
						}
					}
				} );

			}
		}
	}

	/**
	 * Responsável pela seleção de uma data disponivel para agendamento
	 * 
	 * @author Jackson
	 */
	public class LoadingSelecionarDataDisponivelAsync extends AsyncTask<Void, Void, String> {

		private ProgressDialog progressDialog = new ProgressDialog( getActivity() );

		@Override
		protected void onPreExecute() {
			progressDialog.setMessage( "Carregando..." );
			progressDialog.show();
		}

		@Override
		protected String doInBackground( Void... params ) {

			getActivity().getIntent().removeExtra( getString( R.string.lb_requisicao_horario_selecionado_agendamento ) );
			
			final String jsonServico = (String) getActivity().getIntent().getStringExtra( getString( R.string.lb_requisicao_servico_selecionado_agendamento ) );
			final String jsonUnidadeNegocio = (String) getActivity().getIntent().getStringExtra( getString( R.string.lb_requisicao_unidade_negocio_selecionada_agendamento ) );
			
			final ServicoVO servico = jsonUtil.fromJson( jsonServico, new TypeToken<ServicoVO>() {}.getType() );
			final UnidadeNegocioVO unidadeNegocio = jsonUtil.fromJson( jsonUnidadeNegocio, new TypeToken<UnidadeNegocioVO>() {}.getType() );
			
			final ServicoAtividadeVO servicoAtividade = new ServicoAtividadeVO();
			servicoAtividade.setServico( servico );
			
			final UnidadeServicoAtividadeVO unidadeServicoAtividade = new UnidadeServicoAtividadeVO();
			unidadeServicoAtividade.setServicoAtividade( servicoAtividade );
			unidadeServicoAtividade.setUnidadeNegocio( unidadeNegocio );
			
			final String jsonUnidadeServicoAtividade  = jsonUtil.toJson( unidadeServicoAtividade,  new TypeToken<UnidadeServicoAtividadeVO>() {}.getType() );
			
			if ( jsonUnidadeServicoAtividade == null ) {
				return null;
			}
			final String retorno = RestUtil.post( getString( R.string.url_rest_listar_datas_disponiveis_agendamento ), jsonUnidadeServicoAtividade );
			return retorno;
		}

		@Override
		protected void onPostExecute( String result ) {
			progressDialog.dismiss();
			final Button confirmar = (Button) rootView.findViewById( R.id.btn_confirmar_agendamento );
			confirmar.setVisibility( View.INVISIBLE );
		   if ( result != null && result.equalsIgnoreCase( "FALHA" ) ) {
		   	MensagemUtil.addMsg( getActivity(), "Ocorreu um problema na comunicação com o servidor!\n tente novamente mais tarde." );
		   }else
			if ( result != null && !result.isEmpty() ) {

				final List<GradeServicoVO> gradeServicos = gsonFormatDate.fromJson( result, new TypeToken<List<GradeServicoVO>>() {
				}.getType() );

				final List<ItemListCustom> listaItems = new ArrayList<ItemListCustom>();
				for ( final GradeServicoVO gradeServicoFor : gradeServicos ) {

					ItemListCustom custom = new ItemListCustom( gradeServicoFor.getId(), R.drawable.selecione_black, gradeServicoFor.getGradeHorario().getDataGradeHorarioFormatada() );
					listaItems.add( custom );
				}

				final AdpterListViewCustom adapter = new AdpterListViewCustom( getActivity(), R.layout.listview_custom, listaItems );

				listViewUnidadeOrganizacional.setAdapter( adapter );

				listViewUnidadeOrganizacional.setOnItemClickListener( new OnItemClickListener() {

					@Override
					public void onItemClick( AdapterView<?> parent, View view, int position, long id ) {
						final ItemListCustom itemSelecionado = (ItemListCustom) parent.getItemAtPosition( position );
						if ( itemSelecionado != null ) {

							final GradeServicoVO gradeServico = new GradeServicoVO();
							gradeServico.setId( itemSelecionado.getIdRegistro() );
							
							final GradeHorarioVO gradeHorario = new GradeHorarioVO();
							gradeHorario.setDataGradeHorarioFormatada( itemSelecionado.getTitulo()  );
							gradeServico.setGradeHorario( gradeHorario );
							
							getActivity().getIntent().putExtra( getString( R.string.lb_requisicao_grade_servico_selecionada_agendamento ), jsonUtil.toJson( gradeServico, new TypeToken<GradeServicoVO>() {
							}.getType() ) );
							new LoadingRealizarAgendamentoAsync().execute();
						}
					}
				} );

			}
		}
	}
	
	/**
	 * Responsável pela seleção do horário
	 * 
	 * @author jackson
	 *
	 */
	public class LoadingSelecionarHorarioDisponivelAsync extends AsyncTask<Void, Void, String> {

		private ProgressDialog progressDialog = new ProgressDialog( getActivity() );

		@Override
		protected void onPreExecute() {
			progressDialog.setMessage( "Carregando..." );
			progressDialog.show();
		}

		@Override
		protected String doInBackground( Void... params ) {

			final String json = (String) getActivity().getIntent().getStringExtra( getString( R.string.lb_requisicao_grade_servico_selecionada_agendamento ) );
			
			if ( json == null ) {
				return null;
			}
			final String retorno = RestUtil.post( getString( R.string.url_rest_listar_horarios_disponiveis_agendamento ), json );
			return retorno;
		}

		@Override
		protected void onPostExecute( String result ) {
			progressDialog.dismiss();
			final Button confirmar = (Button) rootView.findViewById( R.id.btn_confirmar_agendamento );
			confirmar.setVisibility( View.INVISIBLE );
			
		   if ( result != null && result.equalsIgnoreCase( "FALHA" ) ) {
		   	MensagemUtil.addMsg( getActivity(), "Ocorreu um problema na comunicação com o servidor!\n tente novamente mais tarde." );
		   }else
			if ( result != null && !result.isEmpty() ) {

				final List<HorarioVO> horarios = gsonFormatDate.fromJson( result, new TypeToken<List<HorarioVO>>() {}.getType() );

				final List<ItemListCustom> listaItems = new ArrayList<ItemListCustom>();
				for ( final HorarioVO horarioFor : horarios ) {

					ItemListCustom custom = new ItemListCustom(1L, R.drawable.selecione_black, horarioFor.getDataHoraGradeHorarioFormatada() );
					listaItems.add( custom );
				}

				final AdpterListViewCustom adapter = new AdpterListViewCustom( getActivity(), R.layout.listview_custom, listaItems );

				listViewUnidadeOrganizacional.setAdapter( adapter );

				listViewUnidadeOrganizacional.setOnItemClickListener( new OnItemClickListener() {

					@Override
					public void onItemClick( AdapterView<?> parent, View view, int position, long id ) {
						final ItemListCustom itemSelecionado = (ItemListCustom) parent.getItemAtPosition( position );
						if ( itemSelecionado != null ) {

							final HorarioVO horario = new HorarioVO();
							horario.setId( itemSelecionado.getIdRegistro() );
							horario.setDataHoraGradeHorarioFormatada( itemSelecionado.getTitulo() );
							
							//Colocar o agendamento Aqui
							
							getActivity().getIntent().putExtra( getString( R.string.lb_requisicao_horario_selecionado_agendamento ), jsonUtil.toJson( horario, new TypeToken<HorarioVO>() {}.getType() ) );
							new LoadingRealizarAgendamentoAsync().execute();
						}
					}
				} );

			}
		}
	}
	public boolean verificarTodasInformacoesValidas(final String...strs){
		
		if( strs == null){
			return false;
		}
		for( final String arg : strs ){
			if( arg == null || arg.isEmpty() ){
				return false;
			}
		}
			
		return true;
	}
	
	@Override
	public void onDestroyView() {

		MenuItem itemBack = menu.findItem( R.id.idBackMenu );
		if ( itemBack != null ) {
			itemBack.setVisible( Boolean.FALSE );
		}
		MenuItem item = menu.findItem( R.id.action_home );
		if ( item != null ) {
			item.setVisible( Boolean.TRUE );
		}
		
		super.onDestroyView();
	}
}