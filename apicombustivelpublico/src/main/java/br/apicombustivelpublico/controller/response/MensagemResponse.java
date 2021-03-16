package br.apicombustivelpublico.controller.response;

public class MensagemResponse {
	private String mensagem;

	public MensagemResponse(String message) {
	    this.mensagem = message;
	  }

	public String getMessage() {
		return mensagem;
	}

	public void setMessage(String message) {
		this.mensagem = message;
	}
}
