package gerenciadorDeEstoque.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import gerenciadorDeEstoque.model.Item;
import gerenciadorDeEstoque.model.Pedido;
import gerenciadorDeEstoque.model.Venda;
import gerenciadorDeEstoque.view.venda.VendasPanel;

public class VendasController {


    public VendasController() {}

    public static void atualizarClientesComboBox(Connection conexao, JComboBox<String> comboBox) {
        try {
            String sql = "SELECT nome FROM clientes";
            try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
                ResultSet rs = pstmt.executeQuery();

                ArrayList<String> dados = new ArrayList<>();
                while (rs.next()) {
                    dados.add(rs.getString("nome"));
                }

                comboBox.setModel(new DefaultComboBoxModel<>(dados.toArray(new String[0])));

                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void atualizarProdutosComboBox(Connection conexao, JComboBox<String> comboBox) {
    	System.out.println("atualiza");

        try {
            String sql = "SELECT descricao FROM produtos";
            try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
                ResultSet rs = pstmt.executeQuery();

                ArrayList<String> dados = new ArrayList<>();
                while (rs.next()) {
                    dados.add(rs.getString("descricao"));
                }

                comboBox.setModel(new DefaultComboBoxModel<>(dados.toArray(new String[0])));

                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   
    public int gravarPedido(Connection conexao, Pedido pedido) throws SQLException {
        String sql = "INSERT INTO Pedidos (dtCadastro, ClienteId) VALUES (CURRENT_TIMESTAMP, ?)";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, pedido.getClienteId());
            System.out.println("Gravando pedido ...");
            pstmt.executeUpdate();
            System.out.println("Gravando pedido 2 ...");
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                int pedidoId = rs.getInt(1);
                pedido.setId(pedidoId);
                return pedidoId;
            }
            throw new SQLException("Falha ao obter o ID do pedido gerado.");
        } catch (SQLException ex) {
            ex.printStackTrace();
            conexao.rollback(); // Rollback em caso de exceção
            throw ex; // Re-throw a exceção para que seja tratada no código cliente
        }
    }
   
    public void gravarItemPedido(Connection conexao, Item item) throws SQLException {
    	System.out.println("Gravando item do pedido...");

        String sql = "INSERT INTO Itens (PedidoId, ProdutoId, Quantidade, Preco) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setInt(1, item.getPedidoId());
            pstmt.setInt(2, item.getProdutoId());
            pstmt.setInt(3, item.getQuantidade());
            pstmt.setDouble(4, item.getPreco());
            pstmt.executeUpdate();
        }
    }
    
    public double listarPrecoProdutoId(Connection conexao, int id) throws SQLException {
        double preco = 0.0;


        try (Statement declaracao = conexao.createStatement()) {

            String query = "SELECT Preco FROM vendas.produtos WHERE Id = " + id;
            try (ResultSet rs = declaracao.executeQuery(query)) {
                if (rs.next()) {
                    preco = rs.getDouble("Preco");
                } else {
                    throw new SQLException("Produto com o ID " + id + " não encontrado.");
                }
            }
        }

        return preco;
    }

    public void atualizarTabelaVendas(Connection conexao, JTable tableVendas) {
        try {
            String sql = "SELECT P.dtCadastro AS data, Pr.descricao AS produto, C.nome AS cliente, I.preco, I.quantidade, I.preco * I.quantidade AS total " +
                         "FROM Pedidos P " +
                         "INNER JOIN Itens I ON P.id = I.PedidoId " +
                         "INNER JOIN Produtos Pr ON I.ProdutoId = Pr.id " +
                         "INNER JOIN Clientes C ON P.ClienteId = C.id " +
                         "WHERE P.dtCadastro >= CURDATE() " +
                         "ORDER BY Pr.descricao, C.nome, I.preco, I.quantidade";

            try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
                try (ResultSet rs = pstmt.executeQuery()) {
                    DefaultTableModel model = new DefaultTableModel();
                    model.addColumn("Data");
                    model.addColumn("Produto");
                    model.addColumn("Cliente");
                    model.addColumn("Preço");
                    model.addColumn("Quantidade");
                    model.addColumn("Total");

                    while (rs.next()) {
                        Object[] row = {
                            rs.getDate("data"),
                            rs.getString("produto"),
                            rs.getString("cliente"),
                            rs.getDouble("preco"),
                            rs.getInt("quantidade"),
                            rs.getDouble("total")
                        };
                        model.addRow(row);
                    }
                    
                    Object[] columnNames = {"Data", "Produto", "Cliente", "Preço", "Quantidade", "Total"};
                    model.addRow(columnNames);

                    tableVendas.setModel(model);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public static void gravarVenda(Connection conexao, Venda venda) throws SQLException {
        String sql = "INSERT INTO Vendas (data, clienteId, total) VALUES (CURRENT_TIMESTAMP, ?, ?)";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setInt(1, venda.getClienteId());
            pstmt.setDouble(2, venda.getTotal());
            pstmt.executeUpdate();
        }
    }

}
