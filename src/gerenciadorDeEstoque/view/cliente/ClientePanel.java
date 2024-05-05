package gerenciadorDeEstoque.view.cliente;

import gerenciadorDeEstoque.Main;
import gerenciadorDeEstoque.controller.ClientesController;
import gerenciadorDeEstoque.repository.Conexao;
import gerenciadorDeEstoque.view.home.HomePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.Color;
import java.awt.Font;


public class ClientePanel extends JPanel {
	
	private JTable clientestable;
	private JTextField idtextField;
	private JTextField nometextField;
	private JLabel lblNome;
	private ClientesController clientesController = new ClientesController();
    private Connection conexaoBd = Conexao.obterConexao();
    private ResultSet resultSetClientes;
    
    public ClientePanel(Main main) {
    	
    	
    	setBackground(new Color(192, 192, 192));
    	
        JButton btnVoltar = new JButton("Voltar para Home");
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(ClientePanel.this);
                frame.setContentPane(new HomePanel(main));
                frame.revalidate();
            }
        });
        SpringLayout springLayout = new SpringLayout();
        springLayout.putConstraint(SpringLayout.WEST, btnVoltar, 10, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.SOUTH, btnVoltar, -10, SpringLayout.SOUTH, this);
        setLayout(springLayout);
        add(btnVoltar);
        
        JButton novo = new JButton("Novo");
        springLayout.putConstraint(SpringLayout.WEST, novo, 67, SpringLayout.EAST, btnVoltar);
        springLayout.putConstraint(SpringLayout.SOUTH, novo, 0, SpringLayout.SOUTH, btnVoltar);
        novo.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(ClientePanel.this);
                frame.setContentPane(new NovoClientePanel(main));
                frame.revalidate();}
        	
        });
        add(novo);
        
        JButton btnExcluir = new JButton("Excluir");
        springLayout.putConstraint(SpringLayout.NORTH, btnExcluir, 0, SpringLayout.NORTH, btnVoltar);
        springLayout.putConstraint(SpringLayout.EAST, btnExcluir, -10, SpringLayout.EAST, this);
        btnExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = clientestable.getSelectedRow();
                if (selectedRow != -1) {
                    int option = JOptionPane.showConfirmDialog(ClientePanel.this, "Deseja realmente excluir este cliente?", "Confirmação", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                    	
                    	int clienteId = (int) clientestable.getValueAt(selectedRow, 0);
                        

                        try {
                        	
							clientesController.apagarCliente(conexaoBd, clienteId);
							
	                        clientestable.setModel(clientesController.listarTodosClientes(conexaoBd, clientestable));
						
                        } catch (SQLException e1) {
							e1.printStackTrace();
						}

                    }
                } else {
                    JOptionPane.showMessageDialog(ClientePanel.this, "Selecione um cliente para excluir.", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        add(btnExcluir);
        
        JLabel lblTitulo = new JLabel("Listagem de clientes");
        springLayout.putConstraint(SpringLayout.NORTH, lblTitulo, 0, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.SOUTH, lblTitulo, -266, SpringLayout.SOUTH, this);
        lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        springLayout.putConstraint(SpringLayout.WEST, lblTitulo, 0, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.EAST, lblTitulo, 450, SpringLayout.WEST, this);
        add(lblTitulo);
        
        JButton btnPesquisar = new JButton("Pesquisar");
        springLayout.putConstraint(SpringLayout.NORTH, btnPesquisar, 6, SpringLayout.SOUTH, lblTitulo);
        springLayout.putConstraint(SpringLayout.EAST, btnPesquisar, 0, SpringLayout.EAST, btnExcluir);
        btnPesquisar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	String id = idtextField.getText();

            	
            	String nome = nometextField.getText();
            	
				try {
					if(!id.isEmpty()) {
						
		            	int idInt = Integer.parseInt(id);

	            		clientestable.setModel(clientesController.listarClienteId(conexaoBd, idInt, clientestable));
	            		
	            	}else if (!nome.isEmpty()) {
	            		
	            		clientestable.setModel(clientesController.listarClienteNome(conexaoBd, nome, clientestable));
	            	}
				} catch (SQLException e1) {

					e1.printStackTrace();
				}
				
            	

            }
        });
        add(btnPesquisar);
        
        JButton btnAtualizar = new JButton("Atualizar Lista");
        springLayout.putConstraint(SpringLayout.NORTH, btnAtualizar, 20, SpringLayout.SOUTH, lblTitulo);
        springLayout.putConstraint(SpringLayout.WEST, btnAtualizar, 10, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.EAST, btnAtualizar, -284, SpringLayout.EAST, this);
        btnAtualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                     
                    clientestable.setModel(clientesController.listarTodosClientes(conexaoBd, clientestable));
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        add(btnAtualizar);
    
        clientestable = new JTable();
        springLayout.putConstraint(SpringLayout.WEST, clientestable, 10, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.SOUTH, clientestable, -6, SpringLayout.NORTH, btnVoltar);
        springLayout.putConstraint(SpringLayout.EAST, clientestable, 0, SpringLayout.EAST, btnExcluir);
        add(clientestable);
        
        idtextField = new JTextField();
        springLayout.putConstraint(SpringLayout.NORTH, idtextField, 1, SpringLayout.NORTH, btnPesquisar);
        springLayout.putConstraint(SpringLayout.EAST, idtextField, -28, SpringLayout.WEST, btnPesquisar);
        add(idtextField);
        idtextField.setColumns(10);
        
        nometextField = new JTextField();
        springLayout.putConstraint(SpringLayout.WEST, idtextField, 0, SpringLayout.WEST, nometextField);
        springLayout.putConstraint(SpringLayout.NORTH, clientestable, 14, SpringLayout.SOUTH, nometextField);
        springLayout.putConstraint(SpringLayout.NORTH, nometextField, 6, SpringLayout.SOUTH, btnPesquisar);
        springLayout.putConstraint(SpringLayout.WEST, nometextField, 277, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.EAST, nometextField, 0, SpringLayout.EAST, btnExcluir);
        add(nometextField);
        nometextField.setColumns(10);
        
        JLabel lblid = new JLabel("Id");
        springLayout.putConstraint(SpringLayout.NORTH, lblid, 6, SpringLayout.SOUTH, lblTitulo);
        springLayout.putConstraint(SpringLayout.EAST, lblid, -6, SpringLayout.WEST, idtextField);
        add(lblid);
        
        lblNome = new JLabel("Nome");
        springLayout.putConstraint(SpringLayout.NORTH, lblNome, 3, SpringLayout.NORTH, nometextField);
        springLayout.putConstraint(SpringLayout.EAST, lblNome, -6, SpringLayout.WEST, nometextField);
        add(lblNome);
      
    }
}
