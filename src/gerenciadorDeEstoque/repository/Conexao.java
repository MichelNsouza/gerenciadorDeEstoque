package gerenciadorDeEstoque.repository;

import java.sql.*;


public class Conexao {

    private static Connection conexao = null;

    public static Connection obterConexao() {
        if (conexao != null) {
            return conexao;
        } else {
            String servidor = "jdbc:mysql://localhost:3306/vendas";
            String usuario = "workbench";
            String senha = "PASSWORD";
            String driver = "com.mysql.cj.jdbc.Driver";

            try {
                Class.forName(driver);
                conexao = DriverManager.getConnection(servidor, usuario, senha);
                System.out.println("Conexão estabelecida com sucesso.");
            } catch (ClassNotFoundException | SQLException excecao) {
                System.out.println("Não foi possível conectar ao banco: " + excecao.getMessage());
            }
            return conexao;
        }
    }
}

