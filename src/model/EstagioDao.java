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
            
            if(estagio.getDataFinal() != null)
                stmt.setString(4, estagio.getDataFinal().toString());
            else
                stmt.setString(4, "1000-01-01");
            stmt.execute();

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
        public ObservableList<Estagio> getAll() throws SQLException{
        ObservableList<Estagio> estagios = FXCollections.observableArrayList();
        PreparedStatement stmt = this.connection.prepareStatement("SELECT data_inicio, data_fim, aluno.nome, empresa.nome FROM estagio INNER JOIN empresa ON (empresa.id = estagio.empresa) INNER JOIN aluno ON (aluno.id = estagio.aluno)");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Estagio estagioTmp = new Estagio();
            Aluno alunoTmp = new Aluno();
            alunoTmp.setNome(rs.getString("aluno.nome"));
            estagioTmp.setNomeAluno(rs.getString("aluno.nome"));
            
            Empresa empresaTmp = new Empresa();
            empresaTmp.setNome(rs.getString("empresa.nome"));
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

}

