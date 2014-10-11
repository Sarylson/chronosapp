package br.com.abaco.chronos.menubar.rotas;

import java.util.Locale;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import br.com.abaco.chronos.menubar.R;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

/**
 * Classe responsável por traçar uma rota até a unidade de atendimento.
 * 
 * @author Jackson Silva
 */
public class RotaAsyncTask extends AsyncTask<Double, Void, Void> {

	private ProgressDialog dialog;

	private GoogleMap mapView;

	private Context context;

	private Route rota;

	public RotaAsyncTask( Context ctx, GoogleMap mapa ) {
		mapView = mapa;
		context = ctx;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		dialog = ProgressDialog.show( context, "Aguarde", "Traçando a rota até a Unidade de Atendimento." );
	}

	@Override
	protected Void doInBackground( Double... params ) {

		rota = directions( new LatLng( params[0], params[1] ), new LatLng( params[2], params[3] ) );
		return null;
	}

	@Override
	protected void onPostExecute( Void result ) {
		super.onPostExecute( result );
		PolylineOptions options = new PolylineOptions().width( 5 ).color( Color.rgb( 50,153,204 ) ).visible( true );

		final LatLng latFinal = rota.getPoints().get( rota.getPoints().size() -1 );
		for ( LatLng latlng : rota.getPoints() ) {
			options.add( latlng );
		}

		mapView.addPolyline( options );
		
		MarkerOptions optionsMarkers = new MarkerOptions().title( "Unidade de Atendimento" ).snippet( "TCE - Tribunal de Contas | MT" ).position( latFinal );
		BitmapDescriptor icon = BitmapDescriptorFactory.fromResource( R.drawable.localizacao );
		optionsMarkers.icon( icon );
		mapView.addMarker( optionsMarkers );
		
		dialog.dismiss();
	}

	private Route directions( final LatLng start, final LatLng dest ) {

		// Formatando a URL com a latitude e longitude
		// de origem e destino.
		String urlRota = String.format( Locale.US, "http://maps.googleapis.com/maps/api/" + "directions/json?origin=%f,%f&" + "destination=%f,%f&" + "sensor=true&mode=driving", start.latitude, start.longitude, dest.latitude, dest.longitude );

		GoogleParser parser;
		parser = new GoogleParser( urlRota );
		return parser.parse();
	}
}