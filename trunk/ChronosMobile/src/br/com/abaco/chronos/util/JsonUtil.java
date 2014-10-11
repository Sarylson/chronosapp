package br.com.abaco.chronos.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * Alterar aqui depois
 * 
 * @author jackson
 * @param <T>
 */
public class JsonUtil<T> {

	private Gson gson = null;

	public JsonUtil() {
		this.gson =  new GsonBuilder().setDateFormat( "dd/MM/yyyy HH:mm:ss" ).create();
	}

	public T converterJsonParaObjeto( final String json ) {

		return gson.fromJson( json, new TypeToken<T>() {
		}.getType() );
	}

	public String converterObjetoParaJson( final T objeto ) {
		return gson.toJson( objeto );
	}
}
