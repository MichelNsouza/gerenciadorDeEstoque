package gerenciadorDeEstoque.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ClientesController {

    public ClientesController() {}

    public void gravarCliente(Connection conexao, String nome, String email, String telefone) throws SQLException {
        Statement declaracao = conexao.createStatement();

        String querySQL = "INSERT INTO vendas.cliente(Nome, Email, Telefone) VALUES('" + nome + "', '" + email + "', '" + telefone + "')";

        declaracao.executeUpdate(querySQL);
    }

    public void apagarCliente(Connection conexao, int id) throws SQLException {
        Statement declaracao = conexao.createStatement();

        String querySQL = "DELETE FROM vendas.cliente WHERE Id = " + id;

        declaracao.executeUpdate(querySQL);
    }

    public void atualizarCliente(Connection conexao, int id, String nome, String email, String telefone) throws SQLException {
        Statement declaracao = conexao.createStatement();

        String querySQL = "UPDATE vendas.cliente SET Nome = '" + nome + "', Email = '" + email + "', Telefone = '" + telefone + "' WHERE Id = " + id;

        declaracao.executeUpdate(querySQL);
    }

    public ResultSet listarTodosClientes(Connection conexao, JTable table) throws SQLException {
        Statement declaracao = conexao.createStatement();

        ResultSet rs = declaracao.executeQuery("SELECT * FROM vendas.cliente");

        String[] colunasTabela = {
            "Id",
            "Nome",
            "Email",
            "Telefone"
        };

        DefaultTableModel modeloTabela = new DefaultTableModel(colunasTabela, 0);

        if (rs != null) {
            while (rs.next()) {
                modeloTabela.addRow(new Object[] {
                    rs.getInt("Id"),
                    rs.getString("Nome"),
                    rs.getString("Email"),
                    rs.getString("Telefone")
                });
            }

            table.setModel(modeloTabela);
        }

        return rs;
    }

    public ResultSet listarClienteId(Connection conexao, int id, JTable tableCliente) throws SQLException {
        Statement declaracao = conexao.createStatement();

        ResultSet rs = declaracao.executeQuery("SELECT * FROM vendas.cliente WHERE Id = " + id);

        String[] colunasTabela = {
            "Id",
            "Nome",
            "Email",
            "Telefone"
        };

        DefaultTableModel modeloTabela = new DefaultTableModel(colunasTabela, 0);

        if (rs != null) {
            while (rs.next()) {
                modeloTabela.addRow(new Object[] {
                    rs.getInt("Id"),
                    rs.getString("Nome"),
                    rs.getString("Email"),
                    rs.getString("Telefone")
                });
            }

            tableCliente.setModel(modeloTabela);
        }

        return rs;
    }

    public ResultSet listarClienteNome(Connection conexao, String nome, JTable tableCliente) throws SQLException {
        Statement declaracao = conexao.createStatement();

        ResultSet rs = declaracao.executeQuery("SELECT * FROM vendas.cliente WHERE Nome = '" + nome + "'");

        String[] colunasTabela = {
            "Id",
            "Nome",
            "Email",
            "Telefone"
        };

        DefaultTableModel modeloTabela = new DefaultTableModel(colunasTabela, 0);

        if (rs != null) {
            while (rs.next()) {
                modeloTabela.addRow(new Object[] {
                    rs.getInt("Id"),
                    rs.getString("Nome"),
                    rs.getString("Email"),
                    rs.getString("Telefone")
                });
            }

            tableCliente.setModel(modeloTabela);
        }

        return rs;
    }
}
