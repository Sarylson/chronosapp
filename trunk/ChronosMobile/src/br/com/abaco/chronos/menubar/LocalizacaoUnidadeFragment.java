package br.com.abaco.chronos.menubar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.LocationManager;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;
import br.com.abaco.chronos.menubar.rotas.RotaAsyncTask;
import br.com.abaco.chronos.util.MensagemUtil;
import br.com.abaco.modelo.rest.usuario.UsuarioVO;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * @author jackson
 */
@SuppressLint( "ValidFragment" )
@SuppressWarnings( "unused" )
public class LocalizacaoUnidadeFragment extends SupportMapFragment {

	private Menu menu;

	private FragmentManager fragmentManager;

	public LocalizacaoUnidadeFragment( final Menu menu, final FragmentManager fragmentManager ) {
		this.menu = menu;
		this.fragmentManager = fragmentManager;
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	public void centralizaNo( final LatLng local ) {
		final GoogleMap map = getMap();
		CameraUpdate update = CameraUpdateFactory.newLatLngZoom( local, 15 );

		final String isUsuarioLogado = getActivity().getSharedPreferences( "MyprefChronos", 0 ).getString( "usuario", " " );
      String nomeUsuario = "";
		if ( isUsuarioLogado != null ) {
			final Gson gson = new GsonBuilder().setDateFormat( "dd/MM/yyyy HH:mm:ss" ).create();
			final UsuarioVO usuario = gson.fromJson( isUsuarioLogado, new TypeToken<UsuarioVO>() {}.getType() );
			
			nomeUsuario = usuario.getUsuarioExterno().getNome();
		}
		
		
		MarkerOptions options = new MarkerOptions().title( "ChronosApp" ).snippet( nomeUsuario ).position( local );
		BitmapDescriptor icon = BitmapDescriptorFactory.fromResource( R.drawable.localizacao );
		options.icon( icon );
		map.addMarker( options );
		map.animateCamera( update );

		new RotaAsyncTask( getActivity(), map ).execute(
		// Latitude, Logintude de Origem
				local.latitude, local.longitude,
				// Latitude, Longitude de Destino
				-15.569836, -56.072124 );
	}

	@Override
	public void onDestroyView() {

		fragmentManager = null;

		if ( menu != null ) {

			MenuItem itemBack = menu.findItem( R.id.idBackMenu );
			if ( itemBack != null ) {
				itemBack.setVisible( Boolean.FALSE );
			}
			MenuItem item = menu.findItem( R.id.action_home );
			if ( item != null ) {
				item.setVisible( Boolean.TRUE );
			}
		}
		super.onDestroyView();
	}
}
