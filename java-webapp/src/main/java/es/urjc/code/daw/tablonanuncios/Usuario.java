package es.urjc.code.daw.tablonanuncios;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Usuario {

	private String nombre = "";
	private int numAnuncios;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void incAnuncios() {
		this.numAnuncios++;
	}

	public int getNumAnuncios() {
		return numAnuncios;
	}

	public void setNumAnuncios(int numAnuncios) {
		this.numAnuncios = numAnuncios;
	}

}