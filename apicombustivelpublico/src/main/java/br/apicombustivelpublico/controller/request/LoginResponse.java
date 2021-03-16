package br.apicombustivelpublico.controller.request;



public class LoginResponse {
	
    private SuccessFailure status;
    private String mensagem;

    
    
    public LoginResponse(SuccessFailure status, String message) {
		super();
		this.status = status;
		this.mensagem = message;
	}



	public enum SuccessFailure {
        SUCCESS, FAILURE
    }

		
	public SuccessFailure getStatus() {
		return status;
	}

	public void setStatus(SuccessFailure status) {
		this.status = status;
	}




	public String getMessage() {
		return mensagem;
	}




	public void setMessage(String message) {
		this.mensagem = message;
	}




	@Override
	public String toString() {
		return "LoginResponse [status=" + status + ", mensagem=" + mensagem + "]";
	}
	
	
}
