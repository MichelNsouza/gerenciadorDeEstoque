package gerenciadorDeEstoque.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class VendasController {


    public VendasController() {}

    public void gravarVenda(Connection conexao, int idCliente, double valorTotal) throws SQLException {
        Statement declaracao = conexao.createStatement();

        String querySQL = "INSERT INTO vendas.venda(IdCliente, ValorTotal) VALUES(" + idCliente + ", " + valorTotal + ")";

        declaracao.executeUpdate(querySQL);
    }

    public void apagarVenda(Connection conexao, int id) throws SQLException {
        Statement declaracao = conexao.createStatement();

        String querySQL = "DELETE FROM vendas.venda WHERE Id = " + id;

        declaracao.executeUpdate(querySQL);
    }

    public void atualizarVenda(Connection conexao, int id, int idCliente, double valorTotal) throws SQLException {
        Statement declaracao = conexao.createStatement();

        String querySQL = "UPDATE vendas.venda SET IdCliente =" + idCliente + ", ValorTotal = " + valorTotal + " WHERE Id = " + id;

        declaracao.executeUpdate(querySQL);
    }

    public ResultSet listarTodasVendas(Connection conexao, JTable table) throws SQLException {
        Statement declaracao = conexao.createStatement();

        ResultSet rs = declaracao.executeQuery("SELECT * FROM vendas.venda");

        String[] colunasTabela = {
            "Id",
            "IdCliente",
            "ValorTotal"
        };

        DefaultTableModel modeloTabela = new DefaultTableModel(colunasTabela, 0);

        if (rs != null) {
            while (rs.next()) {
                modeloTabela.addRow(new Object[] {
                    rs.getInt("Id"),
                    rs.getInt("IdCliente"),
                    rs.getDouble("ValorTotal")
                });
            }

            table.setModel(modeloTabela);
        }

        return rs;
    }

    public ResultSet listarVendaId(Connection conexao, int id, JTable tableVenda) throws SQLException {
        Statement declaracao = conexao.createStatement();

        ResultSet rs = declaracao.executeQuery("SELECT * FROM vendas.venda WHERE Id = " + id);

        String[] colunasTabela = {
            "Id",
            "IdCliente",
            "ValorTotal"
        };

        DefaultTableModel modeloTabela = new DefaultTableModel(colunasTabela, 0);

        if (rs != null) {
            while (rs.next()) {
                modeloTabela.addRow(new Object[] {
                    rs.getInt("Id"),
                    rs.getInt("IdCliente"),
                    rs.getDouble("ValorTotal")
                });
            }

            tableVenda.setModel(modeloTabela);
        }

        return rs;
    }
}