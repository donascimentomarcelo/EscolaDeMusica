package escola.musica.util;

public class GeradorSenhaAleatoria {

	private static String caracteresSenha = "ABCDEFGHIJKLMNOPQRSTUWVXYZ0123456789";

	public static String gerarSenhaAleatoria(int tamanhoSenha) {
		StringBuilder builder = new StringBuilder();
		
		for(int i = 0; i < tamanhoSenha; i ++)
		{
			builder.append(caracteresSenha.charAt((int) (Math.random() * 36)));
			//gera uma senha aleatoria
		}
		
		return builder.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(gerarSenhaAleatoria(70));
	}
}
