package br.com.caelum.jdbc.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.caelum.jdbc.ConnectionFactory;
import br.com.caelum.jdbc.modelo.Contato;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;

public class ContatoDao {

	// a conexao com o banco de dados
	private Connection connection;

	public ContatoDao() throws ClassNotFoundException {
		this.connection = (Connection) new ConnectionFactory().getConnection();
	}

	public void adiciona(Contato contato) {
		String sql = "insert into contatos (nome, email, endereco, dataNascimento) values (?,?,?,?)";

		try {
			// prepared statement para insercao
			PreparedStatement stmt = connection.prepareStatement(sql);

			// seta os valores
			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getEmail());
			stmt.setString(3, contato.getEndereco());
			stmt.setDate(4, new Date(contato.getDataNascimento()
					.getTimeInMillis()));

			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Contato> getLista() {
		try {
			List<Contato> contatos = new ArrayList<Contato>();
			PreparedStatement stmt = this.connection
					.prepareStatement("select * from contatos");
			ResultSet rs = (ResultSet) stmt.executeQuery();

			while (rs.next()) {
				// criando o objeto contato
				Contato contato = new Contato();
				contato.setId(rs.getLong("id"));
				contato.setNome(rs.getString("nome"));
				contato.setEmail(rs.getString("email"));
				contato.setEndereco(rs.getString("endereco"));

				// montando a data através do Calendar
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataNascimento"));
				contato.setDataNascimento(data);

				// adicionando o objeto à lista
				contatos.add(contato);
			}

			rs.close();
			stmt.close();
			return contatos;
		} catch (SQLException e) {
			throw new DAOException();
		}
	}

	public List<Contato> getListaDeContatosComecandoComC() {
		try {
			List<Contato> contatos = new ArrayList<Contato>();
			PreparedStatement stmt = this.connection
					.prepareStatement("SELECT * FROM contatos "
							+ "WHERE nome LIKE 'c%'");
			ResultSet rs = (ResultSet) stmt.executeQuery();

			while (rs.next()) {
				// criando o objeto contato
				Contato contato = new Contato();
				contato.setId(rs.getLong("id"));
				contato.setNome(rs.getString("nome"));
				contato.setEmail(rs.getString("email"));
				contato.setEndereco(rs.getString("endereco"));

				// montando a data através do Calendar
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataNascimento"));
				contato.setDataNascimento(data);

				// adicionando o objeto à lista
				contatos.add(contato);
			}
			System.out.println("Não foi encontrado nenhum registro");

			rs.close();
			stmt.close();
			return contatos;
		} catch (SQLException e) {
			throw new DAOException();
		}
	}

	// Metodo de Pesquisa getLista recebendo parametro ID
	public List<Contato> getLista(int id) {
		try {
			PreparedStatement stmt = this.connection
					.prepareStatement("SELECT * FROM CONTATOS WHERE ID =?");

			stmt.setInt(1, id);
			ResultSet rs = (ResultSet) stmt.executeQuery();

			List<Contato> contatos = new ArrayList<Contato>();

			while (rs.next()) {
				// Criando um objeto tipo Contato
				Contato contato = new Contato();
				contato.setNome(rs.getString("nome"));
				contato.setEmail(rs.getString("email"));
				contato.setEndereco(rs.getString("endereco"));

				// montando a data através do Calendar
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataNascimento"));
				contato.setDataNascimento(data);

				// Adicionando Valores a lista
				contatos.add(contato);
			}

			rs.close();
			stmt.close();
			return contatos;
		} catch (SQLException e) {
			throw new DAOException();
		}
		
	}
	
	public void altera(Contato contato){
		String sql = "update contatos set nome=?, email=?, endereco=?, dataNascimento=? where id=?";
		
		try{
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, contato.getNome());
			stmt.setString(1, contato.getEmail());
			stmt.setString(1, contato.getEndereco());
			stmt.setDate(4, new Date(contato.getDataNascimento().getTimeInMillis()));
			stmt.setLong(5, contato.getId());
			stmt.execute();
			stmt.close();
		}catch (SQLException e){
			throw new DAOException();
		}
	}
	
	public void remove(Contato contato){
		try{
			PreparedStatement stmt = connection.prepareStatement("delete from contatos where id=?");
			stmt.setLong(1, contato.getId());
			stmt.execute();
			stmt.close();
		}catch (SQLException e){
			throw new DAOException();
		}
	}
}
