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
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;


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
        
        JLabel lblTitulo = new JLabel("Listagem de Vendas");
        lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        
        tableVendas = new JTable();
        
        JComboBox comboBox_cliente = new JComboBox();
        VendasController.atualizarClientesComboBox(conexaoBd, comboBox_cliente);

        
        JButton btnAdicionar = new JButton("Adicionar");
        
       
        JComboBox comboBox_produtos = new JComboBox();
        VendasController.atualizarProdutosComboBox(conexaoBd, comboBox_produtos);
        
        JLabel lblNewLabel = new JLabel("Produto");
        
        JLabel lblNewLabel_1 = new JLabel("Qtd:");
        
        textField_id = new JTextField();
        textField_id.setEditable(false);
        textField_id.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Id:");
        
        JLabel lblNewLabel_3 = new JLabel("Cliente");
        
        JLabel lblNewLabel_4 = new JLabel("Pedidos do dia");
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
        
        textField_qtd = new JTextField();
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
		vendasController.atualizarTabelaVendas(conexaoBd, tableVendas);
		
		JButton btnNewButton = new JButton("Editar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnNewButton_1 = new JButton("Excluir");
		
		JLabel lblNewLabel_5 = new JLabel("Data");
		
		JLabel lblNewLabel_6 = new JLabel("Produto");
		
		JLabel lblNewLabel_7 = new JLabel("Cliente");
		
		JLabel lblNewLabel_8 = new JLabel("Preço");
		
		JLabel lblNewLabel_9 = new JLabel("Quantidade");
		
		JLabel lblNewLabel_10 = new JLabel("Total");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnVoltar, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
							.addGap(126)
							.addComponent(lblTitulo, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnAdicionar, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(btnNewButton)
							.addGap(6)
							.addComponent(btnNewButton_1)
							.addGap(53)
							.addComponent(lblNewLabel_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_id, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_qtd, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addComponent(lblNewLabel_4, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(lblNewLabel_5)
							.addGap(37)
							.addComponent(lblNewLabel_6)
							.addGap(44)
							.addComponent(lblNewLabel_7)
							.addGap(48)
							.addComponent(lblNewLabel_8)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblNewLabel_9)
							.addGap(40)
							.addComponent(lblNewLabel_10)
							.addGap(12))
						.addComponent(tableVendas, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_3)
									.addGap(16)
									.addComponent(comboBox_cliente, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addGap(11)
									.addComponent(comboBox_produtos, GroupLayout.PREFERRED_SIZE, 372, GroupLayout.PREFERRED_SIZE)))
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(12)
							.addComponent(btnVoltar, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblTitulo, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(lblNewLabel_3))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(6)
							.addComponent(comboBox_cliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(lblNewLabel))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(6)
							.addComponent(comboBox_produtos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAdicionar)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_2)
							.addComponent(textField_id, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel_1)
							.addComponent(textField_qtd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_8)
							.addComponent(lblNewLabel_9)
							.addComponent(lblNewLabel_7))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblNewLabel_10)
								.addGap(2))
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblNewLabel_6)
									.addComponent(lblNewLabel_5))
								.addPreferredGap(ComponentPlacement.RELATED))))
					.addGap(2)
					.addComponent(tableVendas, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
    }
}
