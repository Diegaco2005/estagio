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

public class EmpresaDao {

    private Connection connection;

    public EmpresaDao() throws SQLException {
        this.connection = ConnectionFactory.getConnectionFactory();
    }

    public void adiciona(Empresa empresa) throws SQLException {
        PreparedStatement stmt = this.connection.prepareStatement("insert into empresa(nome, logo, cidade, pais, endereco)values(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, empresa.getNome());
        stmt.setString(2, empresa.getLogo());
        stmt.setString(3, empresa.getCidade());
        stmt.setString(4, empresa.getPais());
        stmt.setString(5, empresa.getEndereco());
        stmt.execute();

        ResultSet rs = stmt.getGeneratedKeys();
        rs.next();
        empresa.setId(rs.getInt(1));

        stmt.close();
    }

    public void edita(Empresa empresa) throws SQLException {
        PreparedStatement stmt = this.connection.prepareStatement("update empresa SET nome=?, logo=?, cidade=?, pais=?, endereco=? where id = ?");

        stmt.setString(1, empresa.getNome());
        stmt.setString(2, empresa.getLogo());
        stmt.setString(3, empresa.getCidade());
        stmt.setString(4, empresa.getPais());
        stmt.setString(5, empresa.getEndereco());
        stmt.setInt(6, empresa.getId());

        //System.out.println(stmt.toString());
        stmt.execute();
        stmt.close();
    }
    public ObservableList<Empresa> getAll() throws SQLException{
        ObservableList<Empresa> empresas = FXCollections.observableArrayList();
        PreparedStatement stmt = this.connection.prepareStatement("select id, nome, logo, cidade, pais, endereco FROM empresa");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Empresa empresaTmp = new Empresa();
            empresaTmp.setId(rs.getInt("id"));
            empresaTmp.setNome(rs.getString("nome"));
            empresaTmp.setLogo(rs.getString("logo"));
            empresaTmp.setCidade(rs.getString("cidade"));
            empresaTmp.setPais(rs.getString("pais"));
            empresaTmp.setEndereco(rs.getString("endereco"));
            empresas.add(empresaTmp);
       }
       rs.close();
       return empresas;
    }

}
