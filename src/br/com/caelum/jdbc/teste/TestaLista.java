package br.com.caelum.jdbc.teste;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import br.com.caelum.jdbc.dao.ContatoDao;
import br.com.caelum.jdbc.modelo.Contato;

/**
 * @author François Jr
 *
 */
public class TestaLista {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		ContatoDao dao = new ContatoDao();

	
		List<Contato> contatos = dao.getLista(2);
		
		for(Contato contato : contatos){
			 System.out.println("Nome: " + contato.getNome());
			 System.out.println("Email: " + contato.getEmail());
			 System.out.println("Endereço: " + contato.getEndereco());
			 String dataFormatada = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(contato.getDataNascimento().getTime());
			 System.out.println("Data de Nascimento: " + dataFormatada + "\n");
		}
	
		
	}

}
