package gerenciadorDeEstoque.view.cliente;

import javax.swing.JPanel;
import gerenciadorDeEstoque.Main;
import gerenciadorDeEstoque.controller.ClientesController;
import gerenciadorDeEstoque.controller.ProdutosController;
import gerenciadorDeEstoque.model.Cliente;
import gerenciadorDeEstoque.model.Produto;
import gerenciadorDeEstoque.repository.Conexao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class EditarClientePanel extends JPanel {

	private JTextField nomeTextField;
    private ClientesController clientesController = new ClientesController();
    private Connection conexaoBd = Conexao.obterConexao();
    private Cliente cliente;
    private int id;
	/**
	 * Create the panel.
	 */
	public EditarClientePanel(Main main, int id, Cliente cliente) {

		this.cliente = cliente;
		this.id = id;
		 
        setBackground(new Color(192, 192, 192));
        SpringLayout springLayout = new SpringLayout();
        setLayout(springLayout);

        JLabel lblTitulo = new JLabel("Editar Cliente");
        springLayout.putConstraint(SpringLayout.NORTH, lblTitulo, 5, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.WEST, lblTitulo, 10, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.EAST, lblTitulo, -10, SpringLayout.EAST, this);
        lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitulo);

        nomeTextField = new JTextField();
        springLayout.putConstraint(SpringLayout.WEST, nomeTextField, 0, SpringLayout.WEST, lblTitulo);
        springLayout.putConstraint(SpringLayout.EAST, nomeTextField, 0, SpringLayout.EAST, lblTitulo);
        add(nomeTextField);
        nomeTextField.setColumns(10);
        nomeTextField.setText(cliente.getNome());

        JButton btnSalvar = new JButton("Salvar");
        springLayout.putConstraint(SpringLayout.EAST, btnSalvar, 0, SpringLayout.EAST, lblTitulo);
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String novaDescricao = nomeTextField.getText();

                if (novaDescricao.isEmpty()) {
                    JOptionPane.showMessageDialog(EditarClientePanel.this,
                            "Preencha o campo antes de salvar.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }


                int confirmacao = JOptionPane.showConfirmDialog(EditarClientePanel.this,
                        "Deseja salvar a alterações no cliente?", "Confirmação", JOptionPane.YES_NO_OPTION);

                if (confirmacao == JOptionPane.YES_OPTION) {
                    try {
                    	clientesController.atualizarCliente(conexaoBd, id, novaDescricao);
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(EditarClientePanel.this,
                                "Erro ao salvar produto: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        JButton btnVoltar = new JButton("Voltar");
        springLayout.putConstraint(SpringLayout.SOUTH, nomeTextField, -66, SpringLayout.NORTH, btnVoltar);
        springLayout.putConstraint(SpringLayout.NORTH, btnSalvar, 0, SpringLayout.NORTH, btnVoltar);
        springLayout.putConstraint(SpringLayout.WEST, btnVoltar, 10, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.SOUTH, btnVoltar, -10, SpringLayout.SOUTH, this);
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(EditarClientePanel.this);
                frame.setContentPane(new ClientePanel(main));
                frame.revalidate();
            }
        });
        add(btnVoltar);
        add(btnSalvar);
        
        JLabel lblInformeONovo = new JLabel("Informe o novo nome do cliente:");
        springLayout.putConstraint(SpringLayout.WEST, lblInformeONovo, 0, SpringLayout.WEST, lblTitulo);
        springLayout.putConstraint(SpringLayout.SOUTH, lblInformeONovo, -58, SpringLayout.NORTH, nomeTextField);
        springLayout.putConstraint(SpringLayout.EAST, lblInformeONovo, 0, SpringLayout.EAST, lblTitulo);
        lblInformeONovo.setHorizontalAlignment(SwingConstants.CENTER);
        lblInformeONovo.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(lblInformeONovo);
    }
}
