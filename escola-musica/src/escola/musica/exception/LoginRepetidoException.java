package escola.musica.exception;

public class LoginRepetidoException extends RuntimeException{
// troca de extends exception para extends runtimeException
	private static final long serialVersionUID = 1634515818407854026L;

   //ao criar essa classe, ctrl + 3 ->construtor super class
	
	
	public LoginRepetidoException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoginRepetidoException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

}
