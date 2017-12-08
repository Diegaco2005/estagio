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
import java.util.ArrayList;
import java.util.List;

public class AlunoDao {

    private Connection connection;

    public AlunoDao() throws SQLException {
        this.connection = ConnectionFactory.getConnectionFactory();
    }

    public void adiciona(Aluno aluno) throws SQLException {
        
        System.out.println("CPF " + aluno.getCpf());
        System.out.println("\nNome " + aluno.getNome());
        System.out.println("\nCurso " + aluno.getCurso());
        System.out.println("\nImagem " + aluno.getImagem());
        System.out.println("\nEmail " + aluno.getEmail());

        PreparedStatement stmt = this.connection.prepareStatement("insert into aluno(cpf, nome, curso, sexo, imagem, email)values(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, aluno.getCpf());
        stmt.setString(2, aluno.getNome());
        stmt.setString(3, aluno.getCurso());
        stmt.setString(4, aluno.getSexo());
        stmt.setString(5, aluno.getImagem());
        stmt.setString(6, aluno.getEmail());

        stmt.execute();
        ResultSet rs = stmt.getGeneratedKeys();
        rs.next();
        aluno.setId(rs.getInt(1));

        stmt.close();
    }
    public void edita(Aluno aluno) throws SQLException {
        System.out.println(aluno.getNome()+" - "+ aluno.getId());
        PreparedStatement stmt = this.connection.prepareStatement("update aluno SET cpf=?, nome=?, curso=?, sexo=?, imagem=?, email=? where id = ?");

        stmt.setString(1, aluno.getCpf());
        stmt.setString(2, aluno.getNome());
        stmt.setString(3, aluno.getCurso());
        stmt.setString(4, aluno.getSexo());
        stmt.setString(5, aluno.getImagem());
        stmt.setString(6, aluno.getEmail());
        stmt.setInt(7, aluno.getId());
        System.out.println(stmt.toString());
        stmt.execute();
        stmt.close();
    }
    public ObservableList<Aluno> getAll() throws SQLException{
        ObservableList<Aluno> alunos = FXCollections.observableArrayList();
        PreparedStatement stmt = this.connection.prepareStatement("select id, cpf, nome, curso, sexo, imagem, email FROM aluno");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Aluno alunoTmp = new Aluno();
            alunoTmp.setId(rs.getInt("id"));
            alunoTmp.setNome(rs.getString("nome"));
            alunoTmp.setCpf(rs.getString("cpf"));
            alunoTmp.setCurso(rs.getString("curso"));
            alunoTmp.setSexo(rs.getString("sexo"));
            alunoTmp.setImagem(rs.getString("imagem"));
            alunoTmp.setEmail(rs.getString("email"));
            alunos.add(alunoTmp);
       }
       rs.close();
       return alunos;
    }

}
