package gerenciadorDeEstoque.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import gerenciadorDeEstoque.model.Produto;

public class ProdutosController {

    public ProdutosController() {}

    public void gravarProduto(Connection conexao, Produto produto) throws SQLException {
        Statement declaracao = conexao.createStatement();

        String querySQL = "INSERT INTO vendas.produtos(Descricao, Preco) VALUES('" + produto.getDescricao() + "', " + produto.getPreco() + ")";

        declaracao.executeUpdate(querySQL);
    }

    public void apagarProduto(Connection conexao, int id) throws SQLException {
        Statement declaracao = conexao.createStatement();

        String querySQL = "DELETE FROM vendas.produtos WHERE Id = " + id;

        declaracao.executeUpdate(querySQL);
    }

    public void atualizarProduto(Connection conexao, int id, String descricao, double preco) throws SQLException {
        Statement declaracao = conexao.createStatement();

        String querySQL = "UPDATE vendas.produtos SET Descricao = '" + descricao + "', Preco = " + preco + " WHERE Id = " + id;

        declaracao.executeUpdate(querySQL);
    }

    public DefaultTableModel listarTodosProdutos(Connection conexao, JTable table) throws SQLException {
        Statement declaracao = conexao.createStatement();

        ResultSet rs = declaracao.executeQuery("SELECT * FROM vendas.produtos");

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"Id", "Descrição", "Preço"});

        while (rs.next()) {
            model.addRow(new Object[]{rs.getInt("Id"), rs.getString("Descricao"), rs.getDouble("Preco")});
        }

        table.setModel(model);

        return model;
    }


    public DefaultTableModel listarProdutoId(Connection conexao, int id, JTable tableProduto) throws SQLException {
        Statement declaracao = conexao.createStatement();

        ResultSet rs = declaracao.executeQuery("SELECT * FROM vendas.produtos WHERE Id = " + id);

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"Id", "Descrição", "Preço"});

        while (rs.next()) {
            model.addRow(new Object[]{rs.getInt("Id"), rs.getString("Descricao"), rs.getDouble("Preco")});
        }

        tableProduto.setModel(model);

        return model;
    }

    public DefaultTableModel listarProdutoDescricao(Connection conexao, String descricao, JTable tableProduto) throws SQLException {
        Statement declaracao = conexao.createStatement();

        ResultSet rs = declaracao.executeQuery("SELECT * FROM vendas.produtos WHERE Descricao LIKE '%" + descricao + "%'");

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"Id", "Descrição", "Preço"});

        while (rs.next()) {
            model.addRow(new Object[]{rs.getInt("Id"), rs.getString("Descricao"), rs.getDouble("Preco")});
        }

        tableProduto.setModel(model);

        return model;
    }
}