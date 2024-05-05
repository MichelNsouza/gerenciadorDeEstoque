package gerenciadorDeEstoque.view.venda;

import gerenciadorDeEstoque.Main;
import gerenciadorDeEstoque.controller.VendasController;
import gerenciadorDeEstoque.model.Item;
import gerenciadorDeEstoque.model.Pedido;
import gerenciadorDeEstoque.model.Venda;
import gerenciadorDeEstoque.repository.Conexao;
import gerenciadorDeEstoque.view.home.HomePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.*;

import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;


public class VendasPanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tableVendas;
	private JTextField textField_id;
	private JTextField textField_qtd;
	private Connection conexaoBd = Conexao.obterConexao();
	private VendasController vendasController = new VendasController();

	public VendasPanel(Main main) {
    	setBackground(new Color(192, 192, 192)); 
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(VendasPanel.this);
                frame.setContentPane(new HomePanel(main)); 
                frame.revalidate();
            }
        });
        SpringLayout springLayout = new SpringLayout();
        springLayout.putConstraint(SpringLayout.WEST, btnVoltar, 0, SpringLayout.WEST, this);
        setLayout(springLayout);
        add(btnVoltar);
        
        JLabel lblTitulo = new JLabel("Listagem de Vendas");
        springLayout.putConstraint(SpringLayout.NORTH, btnVoltar, 0, SpringLayout.NORTH, lblTitulo);
        springLayout.putConstraint(SpringLayout.SOUTH, btnVoltar, 0, SpringLayout.SOUTH, lblTitulo);
        springLayout.putConstraint(SpringLayout.NORTH, lblTitulo, 0, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.WEST, lblTitulo, 217, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.EAST, lblTitulo, -10, SpringLayout.EAST, this);
        lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitulo);
        
        tableVendas = new JTable();
        springLayout.putConstraint(SpringLayout.WEST, tableVendas, 10, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.SOUTH, tableVendas, -30, SpringLayout.SOUTH, this);
        springLayout.putConstraint(SpringLayout.EAST, tableVendas, -10, SpringLayout.EAST, this);
        add(tableVendas);
        
        JComboBox comboBox_cliente = new JComboBox();
        springLayout.putConstraint(SpringLayout.NORTH, comboBox_cliente, 29, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.SOUTH, lblTitulo, -6, SpringLayout.NORTH, comboBox_cliente);
        springLayout.putConstraint(SpringLayout.WEST, comboBox_cliente, 170, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.EAST, comboBox_cliente, -13, SpringLayout.EAST, this);
        add(comboBox_cliente);
        VendasController.atualizarClientesComboBox(conexaoBd, comboBox_cliente);

        
        JButton btnAdicionar = new JButton("Adicionar");
        springLayout.putConstraint(SpringLayout.EAST, btnAdicionar, -10, SpringLayout.EAST, this);
        
       
        JComboBox comboBox_produtos = new JComboBox();
        springLayout.putConstraint(SpringLayout.EAST, comboBox_produtos, -186, SpringLayout.EAST, this);
        add(comboBox_produtos);
        VendasController.atualizarProdutosComboBox(conexaoBd, comboBox_produtos);
        
        JLabel lblNewLabel = new JLabel("Produto");
        springLayout.putConstraint(SpringLayout.WEST, comboBox_produtos, 6, SpringLayout.EAST, lblNewLabel);
        springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 10, SpringLayout.WEST, this);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Qtd:");
        springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 4, SpringLayout.NORTH, btnAdicionar);
        springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_1, 6, SpringLayout.EAST, comboBox_produtos);
        add(lblNewLabel_1);
        
        textField_id = new JTextField();
        springLayout.putConstraint(SpringLayout.EAST, btnVoltar, 21, SpringLayout.EAST, textField_id);
        springLayout.putConstraint(SpringLayout.NORTH, textField_id, 25, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 20, SpringLayout.SOUTH, textField_id);
        add(textField_id);
        textField_id.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Id:");
        springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_2, 25, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_2, 10, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.WEST, textField_id, 7, SpringLayout.EAST, lblNewLabel_2);
        add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("Cliente");
        springLayout.putConstraint(SpringLayout.EAST, textField_id, -59, SpringLayout.WEST, lblNewLabel_3);
        springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_3, 4, SpringLayout.NORTH, comboBox_cliente);
        springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_3, -6, SpringLayout.WEST, comboBox_cliente);
        add(lblNewLabel_3);
        
        JLabel lblNewLabel_4 = new JLabel("Pedidos do dia");
        springLayout.putConstraint(SpringLayout.SOUTH, comboBox_produtos, -2, SpringLayout.NORTH, lblNewLabel_4);
        springLayout.putConstraint(SpringLayout.SOUTH, btnAdicionar, -1, SpringLayout.NORTH, lblNewLabel_4);
        springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_4, 89, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_4, 10, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_4, -10, SpringLayout.EAST, this);
        springLayout.putConstraint(SpringLayout.NORTH, tableVendas, 6, SpringLayout.SOUTH, lblNewLabel_4);
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblNewLabel_4);
        
        textField_qtd = new JTextField();
        springLayout.putConstraint(SpringLayout.WEST, btnAdicionar, 8, SpringLayout.EAST, textField_qtd);
        springLayout.putConstraint(SpringLayout.NORTH, textField_qtd, 1, SpringLayout.NORTH, btnAdicionar);
        springLayout.putConstraint(SpringLayout.WEST, textField_qtd, 6, SpringLayout.EAST, lblNewLabel_1);
        springLayout.putConstraint(SpringLayout.EAST, textField_qtd, -111, SpringLayout.EAST, this);
        add(textField_qtd);
        textField_qtd.setColumns(10);
        btnAdicionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int idCliente = comboBox_cliente.getSelectedIndex() + 1; 
                    int idProduto = comboBox_produtos.getSelectedIndex() + 1; 
                    double preco = vendasController.listarPrecoProdutoId(conexaoBd, idProduto);
                    int quantidade = Integer.parseInt(textField_qtd.getText()); 
                    
                    // Iniciar uma transação
                    conexaoBd.setAutoCommit(false);
                    
                    Pedido pedido = new Pedido(idCliente);
                    int pedidoId = vendasController.gravarPedido(conexaoBd, pedido); // Gravar o pedido

                    Item item = new Item(pedidoId, idProduto, quantidade, preco); // Definir o ID do pedido
                    vendasController.gravarItemPedido(conexaoBd, item); // Gravar o item

                    // Confirmar a transação
                    conexaoBd.commit();

                    // Atualizar a tabela de vendas
                    vendasController.atualizarTabelaVendas(conexaoBd, tableVendas);
                    
                    textField_qtd.setText(""); // Limpar o campo de quantidade
                    
                } catch (NumberFormatException ex) {
                    ex.printStackTrace(); // Lidar com exceção de formato numérico
                } catch (SQLException ex) {
                    ex.printStackTrace(); // Lidar com exceção SQL
                    try {
                        // Rollback em caso de exceção
                        conexaoBd.rollback();
                    } catch (SQLException rollbackEx) {
                        rollbackEx.printStackTrace(); // Lidar com exceção de rollback
                    }
                }
            }
        });

        add(btnAdicionar);
		vendasController.atualizarTabelaVendas(conexaoBd, tableVendas);
    }
}
