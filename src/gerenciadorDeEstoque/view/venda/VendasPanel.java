package gerenciadorDeEstoque.view.venda;

import gerenciadorDeEstoque.Main;
import gerenciadorDeEstoque.controller.VendasController;
import gerenciadorDeEstoque.model.Item;
import gerenciadorDeEstoque.model.Pedido;
import gerenciadorDeEstoque.repository.Conexao;
import gerenciadorDeEstoque.view.home.HomePanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.*;
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
	private int pedidoId;
	private int idCliente; 
	private int ultimoPedidoId = -1;

    
	public VendasPanel(Main main){
		
    	setBackground(new Color(192, 192, 192)); 
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {
            		vendasController.atualizarTabelaVendas(conexaoBd, tableVendas);
					conexaoBd.rollback();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(VendasPanel.this);
                frame.setContentPane(new HomePanel(main)); 
                frame.revalidate();
            }
        });
        
        JLabel lblTitulo = new JLabel("Criar Pedidos");
        lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        
        tableVendas = new JTable();
        
        JComboBox<String> comboBox_cliente = new JComboBox<String>();
		VendasController.atualizarClientesComboBox(conexaoBd, comboBox_cliente);
		comboBox_cliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	idCliente = comboBox_cliente.getSelectedIndex() + 1; 

            	if(textField_id.getText().equals("")){
            		if (ultimoPedidoId != -1) {
            		    textField_id.setText(String.valueOf(ultimoPedidoId));
            		} else {
	            		try {
	    					conexaoBd.setAutoCommit(false);
	    					Pedido pedido = new Pedido(idCliente);
	    	                pedidoId = vendasController.gravarPedido(conexaoBd, pedido);
	    	                String pedidoIdstr = String.valueOf(pedidoId);
	    	                textField_id.setText(pedidoIdstr);
	    	                ultimoPedidoId = pedidoId;
	    				} catch (SQLException e1) {
	    					e1.printStackTrace();
	    				}
            		}
            	}else {
            		try {
						conexaoBd.rollback();
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
            	}
            	
            }
        });

        
        JButton btnAdicionar = new JButton("Salvar Pedido");
        btnAdicionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {
            		
            		 conexaoBd.commit();
            		 JOptionPane.showMessageDialog(null, "Pedido salvo com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            		 textField_id.setText("");
            		 vendasController.atualizarTabelaVendas(conexaoBd, tableVendas);
                } catch (SQLException rollbackEx) {
                    try {
						conexaoBd.rollback();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
                    rollbackEx.printStackTrace(); 
            }
            }
        });


        JComboBox<String> comboBox_produtos = new JComboBox<String>();
        vendasController.atualizarProdutosComboBox(conexaoBd, comboBox_produtos);
        comboBox_produtos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        
        
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
		
		JButton btnNewButton = new JButton("Editar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnNewButton_1 = new JButton("Excluir");
		
		JButton btnAdicionarItem = new JButton("Adicionar Item");
		btnAdicionarItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

	                try {
	                	int idProduto = comboBox_produtos.getSelectedIndex() + 1; 
	                	
	                    double preco = vendasController.listarPrecoProdutoId(conexaoBd, idProduto);
	                    
	                    int quantidade = Integer.parseInt(textField_qtd.getText()); 
	                    
	                    Item item = new Item(pedidoId, idProduto, quantidade, preco); 
	                    
	                    vendasController.gravarItemPedido(conexaoBd, item); 

	                    vendasController.atualizarTabelaVendas(conexaoBd, tableVendas);
	                    
	                    textField_qtd.setText(""); 
	                    
					} catch (SQLException e1) {
						try {
							conexaoBd.rollback();
						} catch (SQLException e2) {
							e2.printStackTrace();
						}
					e1.printStackTrace();
				} 
			}
		});
		
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
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnVoltar, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
							.addGap(31)
							.addComponent(btnAdicionar, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(tableVendas, GroupLayout.PREFERRED_SIZE, 402, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
								.addContainerGap()
								.addComponent(lblNewLabel_5)
								.addGap(37)
								.addComponent(lblNewLabel_6)
								.addGap(27)
								.addComponent(lblNewLabel_7)
								.addGap(28)
								.addComponent(lblNewLabel_8)
								.addGap(37)
								.addComponent(lblNewLabel_9)
								.addGap(34)
								.addComponent(lblNewLabel_10))
							.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
								.addContainerGap()
								.addComponent(lblNewLabel_4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
								.addGap(10)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addComponent(lblNewLabel_3, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblNewLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblNewLabel_2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addGroup(groupLayout.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(textField_id, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(lblTitulo, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE))
									.addGroup(groupLayout.createSequentialGroup()
										.addGap(11)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
											.addComponent(comboBox_produtos, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(comboBox_cliente, 0, 225, Short.MAX_VALUE))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(textField_qtd, 0, 0, Short.MAX_VALUE))
											.addComponent(btnAdicionarItem)))))))
					.addContainerGap(30, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(textField_id, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblTitulo, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(13)
							.addComponent(lblNewLabel_2)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(comboBox_cliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1)
						.addComponent(textField_qtd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(lblNewLabel))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(6)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(comboBox_produtos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnAdicionarItem))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_5)
						.addComponent(lblNewLabel_8)
						.addComponent(lblNewLabel_9)
						.addComponent(lblNewLabel_10)
						.addComponent(lblNewLabel_6)
						.addComponent(lblNewLabel_7))
					.addGap(3)
					.addComponent(tableVendas, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnVoltar)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnAdicionar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(7))
		);
		setLayout(groupLayout);
		
		
		vendasController.atualizarTabelaVendas(conexaoBd, tableVendas);

		
    }
}