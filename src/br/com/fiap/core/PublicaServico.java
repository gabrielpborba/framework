package br.com.fiap.core;

import javax.xml.ws.Endpoint;

import br.com.fiap.helper.Gerar;

public class PublicaServico {

	public static void main (String[] args ) {
		Gerar service = new Gerar();
		Endpoint endpoint  = Endpoint.publish("http://localhost:8080/gerar", service);
	}
}
