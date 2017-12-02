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

public class EstagioDao {
		private Connection connection;

		public EstagioDao() throws SQLException{
			this.connection = ConnectionFactory.getConnectionFactory();
		}

		public void adiciona(Estagio estagio) throws SQLException{
			PreparedStatement stmt = this.connection.prepareStatement("insert into estagio(aluno, empresa, data_inicio, data_fim)values(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, estagio.getAluno().getId());
			stmt.setInt(2, estagio.getEmpresa().getId());
			stmt.setString(3, estagio.getDataInicio().toString());
			stmt.setString(4, estagio.getDataFinal().toString());

			stmt.execute();

			ResultSet rs = stmt.getGeneratedKeys();
		    rs.next();
		    estagio.setId(rs.getInt(1));


			stmt.close();
		}
		public void edita(Estagio estagio) throws SQLException{
			PreparedStatement stmt = this.connection.prepareStatement("update estagio SET aluno=?, empresa=?, data_inicio=?, data_fim=? where id = ?");

			stmt.setInt(1, estagio.getAluno().getId());
			stmt.setInt(2, estagio.getEmpresa().getId());
			stmt.setString(3, estagio.getDataInicio().toString());
			stmt.setString(4, estagio.getDataFinal().toString());
			stmt.setInt(6, estagio.getId());

			//System.out.println(stmt.toString());
			stmt.execute();
			stmt.close();
		}

}

