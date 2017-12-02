package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Util.ConnectionFactory;

public class AlunoDao {
		private Connection connection;


		public AlunoDao() throws SQLException{
			this.connection = ConnectionFactory.getConnectionFactory();
		}
		public void adiciona(Aluno aluno) throws SQLException{
			PreparedStatement stmt = this.connection.prepareStatement("insert into aluno(cpf)values(?)", Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, aluno.getCpf());


			/*PreparedStatement stmt = this.connection.prepareStatement("insert into aluno(cpf, nome, curso, sexo, imagem, email)values(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, aluno.getCpf());
			stmt.setString(2, aluno.getNome());
			stmt.setString(3, aluno.getCurso());
			stmt.setString(4, aluno.getSexo());
			stmt.setString(5, aluno.getImagem());
			stmt.setString(6, aluno.getEmail());
             */

			stmt.execute();
			ResultSet rs = stmt.getGeneratedKeys();
		    rs.next();
		    aluno.setId(rs.getInt(1));


			stmt.close();
		}
		public void edita(Aluno aluno) throws SQLException{
			PreparedStatement stmt = this.connection.prepareStatement("update aluno SET cpf=?, nome=?, cursoe=?, sexoe=?, imageme=?, email=? where id = ?");

			stmt.setString(1, aluno.getCpf());
			stmt.setString(2, aluno.getNome());
			stmt.setString(3, aluno.getCurso());
			stmt.setString(4, aluno.getSexo());
			stmt.setString(5, aluno.getImagem());
			stmt.setString(6, aluno.getEmail());
			stmt.setInt(7, aluno.getId());
			//System.out.println(stmt.toString());
			stmt.execute();
			stmt.close();
		}

}

