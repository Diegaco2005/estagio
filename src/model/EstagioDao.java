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
import Util.DateUtil;
import javafx.scene.chart.PieChart;

public class EstagioDao {

    private Connection connection;

    public EstagioDao() throws SQLException {
        this.connection = ConnectionFactory.getConnectionFactory();
    }

    public void adiciona(Estagio estagio) throws SQLException {
        PreparedStatement stmt = this.connection.prepareStatement("insert into estagio(aluno, empresa, data_inicio, data_fim)values(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        stmt.setInt(1, estagio.getAluno().getId());
        stmt.setInt(2, estagio.getEmpresa().getId());
        stmt.setString(3, estagio.getDataInicio().toString());
        if (estagio.getDataFinal() != null) {
            stmt.setString(4, estagio.getDataFinal().toString());
        } else {
            stmt.setString(4, "1000-01-01");
        }
        stmt.execute();

        stmt.close();
    }

    public void edita(Estagio estagio) throws SQLException {
        PreparedStatement stmt = this.connection.prepareStatement("update estagio SET aluno=?, empresa=?, data_inicio=?, data_fim=? where aluno=? and empresa=?");

        stmt.setInt(1, estagio.getAluno().getId());
        stmt.setInt(2, estagio.getEmpresa().getId());
        stmt.setString(3, estagio.getDataInicio().toString());
        if (estagio.getDataFinal() != null) {
            stmt.setString(4, estagio.getDataFinal().toString());
        } else {
            stmt.setString(4, "1000-01-01");
        }
        stmt.setInt(5, estagio.getAluno().getId());
        stmt.setInt(6, estagio.getEmpresa().getId());

        //System.out.println(stmt.toString());
        stmt.execute();
        stmt.close();
    }

    public void deleta(Estagio estagio) throws SQLException {
        PreparedStatement stmt = this.connection.prepareStatement("delete from estagio where aluno=? and empresa=?");
        stmt.setInt(1, estagio.getAluno().getId());
        stmt.setInt(2, estagio.getEmpresa().getId());

        //System.out.println(stmt.toString());
        stmt.execute();
        stmt.close();

    }

    public ObservableList<Estagio> getAll() throws SQLException {
        ObservableList<Estagio> estagios = FXCollections.observableArrayList();
        PreparedStatement stmt = this.connection.prepareStatement("SELECT data_inicio, data_fim, aluno.id,aluno.nome, empresa.nome, empresa.id FROM estagio INNER JOIN empresa ON (empresa.id = estagio.empresa) INNER JOIN aluno ON (aluno.id = estagio.aluno)");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Estagio estagioTmp = new Estagio();

            Aluno alunoTmp = new Aluno();
            alunoTmp.setNome(rs.getString("aluno.nome"));
            alunoTmp.setId(rs.getInt("aluno.id"));
            estagioTmp.setNomeAluno(rs.getString("aluno.nome"));

            Empresa empresaTmp = new Empresa();
            empresaTmp.setNome(rs.getString("empresa.nome"));
            empresaTmp.setId(rs.getInt("empresa.id"));
            estagioTmp.setNomeEmpresa(rs.getString("empresa.nome"));

            estagioTmp.setAluno(alunoTmp);
            estagioTmp.setEmpresa(empresaTmp);
            estagioTmp.setDataInicio(DateUtil.parseToSql(rs.getString("data_inicio")));
            estagioTmp.setDataFinal(DateUtil.parseToSql(rs.getString("data_fim")));

            estagios.add(estagioTmp);
        }
        rs.close();
        return estagios;
    }

    public ObservableList<PieChart.Data> getGrafico(String curso) throws SQLException {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        PreparedStatement stmt = this.connection.prepareStatement("SELECT count(*) AS quant FROM ads_estagio.estagio INNER JOIN aluno on (aluno.id = estagio.aluno) WHERE data_fim = '1000-01-01' AND aluno.curso=?");
        stmt.setString(1, curso);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            pieChartData.add(new PieChart.Data("Estagio em andamento", rs.getInt("quant")));
        }else{
            pieChartData.add(new PieChart.Data("Estagio em andamento", 0));
        }
        stmt = this.connection.prepareStatement("SELECT count(*) AS quant FROM ads_estagio.estagio INNER JOIN aluno on (aluno.id = estagio.aluno) WHERE data_fim != '1000-01-01' AND aluno.curso=?");
        stmt.setString(1, curso);
        rs = stmt.executeQuery();
        if (rs.next()) {
            pieChartData.add(new PieChart.Data("Estagio finalizado", rs.getInt("quant")));
        }else{
            pieChartData.add(new PieChart.Data("Estagio finalizado", 0));
        }
        rs.close();
        rs.close();
                       
        return pieChartData;
    }

}
