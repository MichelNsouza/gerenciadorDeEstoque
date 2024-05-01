package gerenciadorDeEstoque.view.cliente;

import gerenciadorDeEstoque.view.home.Main;
import gerenciadorDeEstoque.controller.ClientesController;
import gerenciadorDeEstoque.model.Cliente;
import gerenciadorDeEstoque.repository.Conexao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class NovoClientePanel extends JPanel {
    private JTextField nomeTextField;
    private ClientesController clientesController = new ClientesController();
    private Connection conexaoBd = Conexao.obterConexao();

    public NovoClientePanel(Main main) {

        setBackground(new Color(192, 192, 192));
        SpringLayout springLayout = new SpringLayout();
        setLayout(springLayout);

        JLabel lblTitulo = new JLabel("Cadastro de Clientes");
        springLayout.putConstraint(SpringLayout.NORTH, lblTitulo, 5, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.WEST, lblTitulo, 10, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.EAST, lblTitulo, -10, SpringLayout.EAST, this);
        lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitulo);

        nomeTextField = new JTextField();
        springLayout.putConstraint(SpringLayout.NORTH, nomeTextField, 167, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.WEST, nomeTextField, 22, SpringLayout.WEST, lblTitulo);
        springLayout.putConstraint(SpringLayout.EAST, nomeTextField, -32, SpringLayout.EAST, lblTitulo);
        add(nomeTextField);
        nomeTextField.setColumns(10);

        JButton btnSalvar = new JButton("Salvar");
        springLayout.putConstraint(SpringLayout.EAST, btnSalvar, 0, SpringLayout.EAST, lblTitulo);
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
                String nome = nomeTextField.getText();


                try {
                	
                	Cliente cliente = new Cliente(nome);
                	
                    clientesController.gravarCliente(conexaoBd, cliente);
                    
                    JOptionPane.showMessageDialog(NovoClientePanel.this, "Cliente salvo com sucesso!");
                    nomeTextField.setText("");
                    
                } catch (SQLException ex) {
                	
                    JOptionPane.showMessageDialog(NovoClientePanel.this, "Erro ao salvar cliente: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                
                }
            }
        });
        
                JButton btnVoltar = new JButton("Voltar");
                springLayout.putConstraint(SpringLayout.NORTH, btnSalvar, 0, SpringLayout.NORTH, btnVoltar);
                springLayout.putConstraint(SpringLayout.WEST, btnVoltar, 10, SpringLayout.WEST, this);
                springLayout.putConstraint(SpringLayout.SOUTH, btnVoltar, -10, SpringLayout.SOUTH, this);
                btnVoltar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(NovoClientePanel.this);
                        frame.setContentPane(new ClientePanel(main));
                        frame.revalidate();
                    }
                });
                add(btnVoltar);
        
                JLabel lblNome = new JLabel("Informe o nome do cliente:");
                springLayout.putConstraint(SpringLayout.NORTH, lblNome, 50, SpringLayout.SOUTH, lblTitulo);
                springLayout.putConstraint(SpringLayout.WEST, lblNome, 71, SpringLayout.WEST, this);
                springLayout.putConstraint(SpringLayout.SOUTH, lblNome, -67, SpringLayout.NORTH, nomeTextField);
                springLayout.putConstraint(SpringLayout.EAST, lblNome, -89, SpringLayout.EAST, this);
                lblNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
                add(lblNome);
        add(btnSalvar);
    }
}
