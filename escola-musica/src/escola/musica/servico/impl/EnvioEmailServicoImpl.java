package escola.musica.servico.impl;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import escola.musica.modelo.UsuarioProfessor;
import escola.musica.servico.EnvioEmailServico;

@Service
public class EnvioEmailServicoImpl implements EnvioEmailServico{

	@Override
	//@Scheduled(fixedDelay = 10000000)
	public void enviarEmail(String assunto, String texto, List<File> anexos, String... destinatarios) {
		
		try {
			
			JavaMailSenderImpl mailSender= new JavaMailSenderImpl();
			mailSender.setHost("smtp.gmail.com");
			mailSender.setPort(587);
			mailSender.setProtocol("smtp");
			mailSender.setUsername("marcelo.cyborgs@gmail.com");
			mailSender.setPassword("ch1ch4r1t0");
			mailSender.setDefaultEncoding("utf-8");
			
			Properties properties = new Properties();
			properties.setProperty("username", "marcelo.cyborgs@gmail.com");
			properties.setProperty("password", "ch1ch4r1t0");
			properties.setProperty("mail.smtp.auth", "true");
			properties.setProperty("mail.smtp.starttls.enable", "true");
			properties.setProperty("mail.transport.protocol", "smtp");
			mailSender.setJavaMailProperties(properties);
			
			MimeMessage msg = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg, false);
			helper.setFrom("marcelo.cyborgs@gmail.com");
			helper.setSubject(assunto);
			helper.setText(texto, true);
			//helper.addTo("marcelojunin2010@hotmail.com");
			
			for (String destinatario : destinatarios) {
				//percorre a lista de destinatarios para recuperar o email destinatario
				helper.addTo(destinatario);
			}
			
			mailSender.send(msg);
			
			System.out.println("Enviando..");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
	}

	@Override
	@Async
	public void enviarEmailCadastroUsuarioProfessor(
			UsuarioProfessor usuarioProfessor, String senha) {
		String assunto = "Cadastro de Usuário";
		String texto = pegarHtmalEmail("resources/mail_professor.html");
		
		//substitui lá no html com os valores de nome, email e senha.
		texto = texto.replace("{professor.nome}", usuarioProfessor.getNome());
		texto = texto.replace("{professor.login}", usuarioProfessor.getLogin());
		texto = texto.replace("{professor.senha}", senha);
		
		enviarEmail(assunto, texto, null, usuarioProfessor.getEmail());
	}

	private String pegarHtmalEmail(String url) {
		// dá os imports do java.io -> java.io input stream ou alguma coisa de array que aparece nas opções.
		InputStream is = getClass().getResourceAsStream(url);
		BufferedInputStream bis = new BufferedInputStream(is);
		ByteArrayOutputStream buf = new ByteArrayOutputStream();
		int result;
		try {
			result = bis.read();
			while (result != -1) {
				byte b = (byte) result;
				buf.write(b);
				result = bis.read();
			}
			return buf.toString("UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

}
