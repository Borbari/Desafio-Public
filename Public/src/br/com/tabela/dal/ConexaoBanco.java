package br.com.tabela.dal;

import java.sql.*;

/**
 *
 * @author Ricardo Borba de Oliveira
 */
public class ConexaoBanco {
    //conexao com o banco de dados mysql
    public static Connection conector() {
        java.sql.Connection conexao = null;
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/dbdesafio";
        String user = "root";
        String password = "";
        
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url,user,password);
            return conexao;
        } catch (Exception e) {
            System.out.println("Erro ao conectar Banco de Dados!");
            return null;
        }
    }
}
