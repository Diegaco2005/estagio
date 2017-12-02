package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Util.ConnectionFactory;

public class AlunoDao {
		private Connection connection;


		public AlunoDao() throws SQLException{
			this.connection = ConnectionFactory.getConnectionFactory();
		}
		/*public void adiciona(Aluno aluno) throws SQLException{
			PreparedStatement stmt = this.connection.prepareStatement("insert into aluno(placa, marca, modelo, cor)values(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, aluno.getPlaca());
			stmt.setString(2, aluno.getMarca());
			stmt.setString(3, aluno.getModelo());
			stmt.setString(4, aluno.getCor());

			stmt.execute();
			ResultSet rs = stmt.getGeneratedKeys();
		    rs.next();
		    aluno.setIdVeiculo(rs.getInt(1));


			stmt.close();
		}
		public ObservableList<Veiculo> selectAll() throws SQLException{
			PreparedStatement stmt = this.connection.prepareStatement("select id_veiculo, placa, modelo, marca, cor FROM veiculo");
			ResultSet rs = stmt.executeQuery();
			ObservableList<Veiculo> veiculoData = FXCollections.observableArrayList();
		       while (rs.next()) {
		    	   Veiculo veiculo = new Veiculo(rs.getInt("id_veiculo"));
		    	   veiculo.setPlaca(rs.getString("placa"));
		    	   veiculo.setMarca(rs.getString("modelo"));
		    	   veiculo.setModelo(rs.getString("marca"));
		    	   veiculo.setCor(rs.getString("cor"));
		    	   //System.out.println(veiculo.getPlaca());
		    	   veiculoData.add(veiculo);
		       }
		       rs.close();
		       return veiculoData;
		}*/
}

