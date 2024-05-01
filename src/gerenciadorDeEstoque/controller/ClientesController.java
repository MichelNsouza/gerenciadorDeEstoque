package gerenciadorDeEstoque.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import gerenciadorDeEstoque.model.Cliente;

public class ClientesController {

	
    public ClientesController() {}

    
    public void gravarCliente(Connection conexao, Cliente cliente) throws SQLException {
        Statement declaracao = conexao.createStatement();

        String querySQL = "INSERT INTO vendas.clientes(Nome) VALUES('" + cliente.getNome() + "')";

        declaracao.executeUpdate(querySQL);
    }
    
    public void apagarCliente(Connection conexao, int id) throws SQLException {
        Statement declaracao = conexao.createStatement();

        String querySQL = "DELETE FROM vendas.clientes WHERE Id = " + id;

        declaracao.executeUpdate(querySQL);
    }

    public void atualizarCliente(Connection conexao, int id, String nome) throws SQLException {
        Statement declaracao = conexao.createStatement();

        String querySQL = "UPDATE vendas.clientes SET Nome = '" + nome + "' WHERE Id = " + id;

        declaracao.executeUpdate(querySQL);
    }

    public DefaultTableModel listarTodosClientes(Connection conexao, JTable table) throws SQLException {
        Statement declaracao = conexao.createStatement();

        ResultSet rs = declaracao.executeQuery("SELECT * FROM vendas.clientes");

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"id", "nome"});

        while (rs.next()) {
            model.addRow(new Object[]{rs.getInt("id"), rs.getString("nome")});
        }

        table.setModel(model);

        return model;
    }

    public DefaultTableModel listarClienteId(Connection conexao, int id, JTable tableCliente) throws SQLException {
        Statement declaracao = conexao.createStatement();

        ResultSet rs = declaracao.executeQuery("SELECT * FROM vendas.clientes WHERE id = " + id);

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"id", "nome"});

        while (rs.next()) {
            model.addRow(new Object[]{rs.getInt("id"), rs.getString("nome")});
        }

        tableCliente.setModel(model);

        return model;
    }

    public DefaultTableModel listarClienteNome(Connection conexao, String nome, JTable tableCliente) throws SQLException {
        Statement declaracao = conexao.createStatement();

        ResultSet rs = declaracao.executeQuery("SELECT * FROM vendas.clientes WHERE Nome LIKE '%" + nome + "%'");

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"id", "nome"});

        while (rs.next()) {
            model.addRow(new Object[]{rs.getInt("id"), rs.getString("nome")});
        }

        tableCliente.setModel(model);

        return model;
    }
}
