package br.com.abaco.chronos.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

public class RestUtil {

	// public static String getConteudo( final String url, final String parametros ) {
	//
	// // StrictMode.setThreadPolicy( new StrictMode.ThreadPolicy.Builder().permitAll().build() );
	// String response = null;
	// try {
	// final HttpParams httpParams = new BasicHttpParams();
	// HttpConnectionParams.setConnectionTimeout( httpParams, 30000 );
	// final HttpClient client = new DefaultHttpClient( httpParams );
	//
	// String urlNova = "";
	// if( parametros != null && !parametros.isEmpty()){
	// urlNova = url + URLEncoder.encode( parametros, "UTF-8" );
	// }else{
	// urlNova = url;
	// }
	// final HttpGet get = new HttpGet( urlNova );
	//
	// //final ResponseHandler<String> responseHandler = new BasicResponseHandler();
	// HttpResponse resp = client.execute( get );
	// response = EntityUtils.toString( resp.getEntity() );
	// } catch ( final Throwable e ) {
	//
	// return "FALHA";
	// }
	// return response;
	// }

	public static String post( final String url, final String parametros ) {
		String response = null;
		try {
			final HttpParams httpParams = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout( httpParams, 30000 );
			final DefaultHttpClient httpClient = new DefaultHttpClient( httpParams );

			final HttpPost post = new HttpPost( url );
			post.setEntity( new StringEntity( parametros ,"utf-8") );

			post.setHeader( "Content-Type", "application/json; charset=utf-8" );
			post.setHeader( "Accept", "application/json" );

			
			final HttpResponse retorno = httpClient.execute( post );
			
			response = EntityUtils.toString( retorno.getEntity() , "utf-8" );

		} catch ( final Exception e ) {
			e.printStackTrace();
			return "FALHA";
		}
		return response;
	}

	public static String get( final String url ) {
		String response = null;
		try {
			final HttpParams httpParams = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout( httpParams, 30000 );
			final HttpClient client = new DefaultHttpClient( httpParams );

			final HttpGet get = new HttpGet( url );

			final HttpResponse resp = client.execute( get );
			response = EntityUtils.toString( resp.getEntity() );
		} catch ( final Throwable e ) {

			return "FALHA";
		}
		return response;
	}

	// public static String post( final String url, final String json ) {
	// try {
	// final DefaultHttpClient httpClient = new DefaultHttpClient();
	//
	// final HttpPost post = new HttpPost( url );
	// post.setEntity( new StringEntity( json ) );
	//
	// post.setHeader( "Content-Type", "application/json" );
	// post.setHeader( "Accept", "application/json" );
	//
	// final HttpResponse response = httpClient.execute( post );
	//
	// final String jsonResposta = EntityUtils.toString( response.getEntity() );
	//
	// return jsonResposta;
	//
	// } catch ( final Exception e ) {
	// e.printStackTrace();
	// }
	// return "";
	// }
	// public static String convertStreamToString( InputStream is ) throws IOException {
	// if ( is != null ) {
	// StringBuilder sb = new StringBuilder();
	// String line;
	// try {
	// BufferedReader reader = new BufferedReader( new InputStreamReader( is, "UTF-8" ) );
	// while ( ( line = reader.readLine() ) != null ) {
	// sb.append( line ).append( "\n" );
	// }
	// } finally {
	// is.close();
	// }
	// return sb.toString();
	// } else {
	// return "";
	// }
	// }
}
