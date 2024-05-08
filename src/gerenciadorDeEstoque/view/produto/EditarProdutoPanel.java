package gerenciadorDeEstoque.view.produto;

import javax.swing.JPanel;
import gerenciadorDeEstoque.Main;
import gerenciadorDeEstoque.controller.ProdutosController;
import gerenciadorDeEstoque.model.Produto;
import gerenciadorDeEstoque.repository.Conexao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class EditarProdutoPanel extends JPanel {

	private JTextField nomeTextField;
    private ProdutosController produtoController = new ProdutosController();
    private Connection conexaoBd = Conexao.obterConexao();
    private JTextField precotextField;
    private Produto produto;
    private int id;
	/**
	 * Create the panel.
	 */
	public EditarProdutoPanel(Main main, int id, Produto produto) {

		this.produto = produto;
		this.id = id;
		 
        setBackground(new Color(192, 192, 192));
        SpringLayout springLayout = new SpringLayout();
        setLayout(springLayout);

        JLabel lblTitulo = new JLabel("Editar Produto");
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
        nomeTextField.setText(produto.getDescricao());

        JButton btnSalvar = new JButton("Salvar");
        springLayout.putConstraint(SpringLayout.EAST, btnSalvar, 0, SpringLayout.EAST, lblTitulo);
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String novaDescricao = nomeTextField.getText();
                String novoPreco = precotextField.getText();

                if (novaDescricao.isEmpty() || novoPreco.isEmpty()) {
                    JOptionPane.showMessageDialog(EditarProdutoPanel.this,
                            "Preencha todos os campos antes de salvar.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Exibe uma confirmação antes de salvar as alterações
                int confirmacao = JOptionPane.showConfirmDialog(EditarProdutoPanel.this,
                        "Deseja salvar as alterações no produto?", "Confirmação", JOptionPane.YES_NO_OPTION);

                if (confirmacao == JOptionPane.YES_OPTION) {
                    try {
                        double novoPrecoDouble = Double.parseDouble(novoPreco);
                        produtoController.atualizarProduto(conexaoBd, id, novaDescricao, novoPrecoDouble);
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(EditarProdutoPanel.this,
                                "Erro ao salvar produto: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        JButton btnVoltar = new JButton("Voltar");
        springLayout.putConstraint(SpringLayout.NORTH, btnSalvar, 0, SpringLayout.NORTH, btnVoltar);
        springLayout.putConstraint(SpringLayout.WEST, btnVoltar, 10, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.SOUTH, btnVoltar, -10, SpringLayout.SOUTH, this);
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(EditarProdutoPanel.this);
                frame.setContentPane(new ProdutoPanel(main));
                frame.revalidate();
            }
        });
        add(btnVoltar);

        JLabel lblNome = new JLabel("Informe o novo nome do produto:");
        springLayout.putConstraint(SpringLayout.NORTH, lblNome, 39, SpringLayout.SOUTH, lblTitulo);
        springLayout.putConstraint(SpringLayout.SOUTH, lblNome, -211, SpringLayout.SOUTH, this);
        springLayout.putConstraint(SpringLayout.NORTH, nomeTextField, 6, SpringLayout.SOUTH, lblNome);
        springLayout.putConstraint(SpringLayout.WEST, lblNome, 0, SpringLayout.WEST, lblTitulo);
        springLayout.putConstraint(SpringLayout.EAST, lblNome, 0, SpringLayout.EAST, lblTitulo);
        lblNome.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblNome);
        add(btnSalvar);
        
        precotextField = new JTextField();
        springLayout.putConstraint(SpringLayout.WEST, precotextField, 10, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.EAST, precotextField, 0, SpringLayout.EAST, lblTitulo);
        add(precotextField);
        precotextField.setColumns(10);
        precotextField.setText(Double.toString(produto.getPreco()));

        JLabel lblInformeOPreo = new JLabel("Informe o novo preço do produto:");
        springLayout.putConstraint(SpringLayout.SOUTH, lblInformeOPreo, -110, SpringLayout.SOUTH, this);
        springLayout.putConstraint(SpringLayout.NORTH, precotextField, 6, SpringLayout.SOUTH, lblInformeOPreo);
        springLayout.putConstraint(SpringLayout.WEST, lblInformeOPreo, 10, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.EAST, lblInformeOPreo, 0, SpringLayout.EAST, lblTitulo);
        lblInformeOPreo.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblInformeOPreo);
        
        JLabel lblNewLabel = new JLabel("E/OU");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 18, SpringLayout.SOUTH, nomeTextField);
        springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 0, SpringLayout.WEST, lblTitulo);
        springLayout.putConstraint(SpringLayout.EAST, lblNewLabel, 0, SpringLayout.EAST, lblTitulo);
        add(lblNewLabel);
    }
}
