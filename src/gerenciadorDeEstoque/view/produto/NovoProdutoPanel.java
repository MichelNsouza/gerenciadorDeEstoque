package gerenciadorDeEstoque.view.produto;

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

public class NovoProdutoPanel extends JPanel {
    private JTextField nomeTextField;
    private ProdutosController produtoController = new ProdutosController();
    private Connection conexaoBd = Conexao.obterConexao();
    private JTextField precotextField;

    public NovoProdutoPanel(Main main) {

        setBackground(new Color(192, 192, 192));
        SpringLayout springLayout = new SpringLayout();
        setLayout(springLayout);

        JLabel lblTitulo = new JLabel("Cadastro de Produtos");
        springLayout.putConstraint(SpringLayout.NORTH, lblTitulo, 5, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.WEST, lblTitulo, 10, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.EAST, lblTitulo, -10, SpringLayout.EAST, this);
        lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitulo);

        nomeTextField = new JTextField();
        springLayout.putConstraint(SpringLayout.NORTH, nomeTextField, 133, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.WEST, nomeTextField, 0, SpringLayout.WEST, lblTitulo);
        springLayout.putConstraint(SpringLayout.EAST, nomeTextField, 0, SpringLayout.EAST, lblTitulo);
        add(nomeTextField);
        nomeTextField.setColumns(10);

        JButton btnSalvar = new JButton("Salvar");
        springLayout.putConstraint(SpringLayout.EAST, btnSalvar, 0, SpringLayout.EAST, lblTitulo);
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String descricao = nomeTextField.getText();
                String preco = precotextField.getText();

                try {
                	
                    Double precoDouble = Double.parseDouble(preco);
                    Produto produto = new Produto(descricao,precoDouble);
                    produtoController.gravarProduto(conexaoBd, produto);
                    
                    precotextField.setText("");
                    nomeTextField.setText("");
                    
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(NovoProdutoPanel.this, "Erro ao salvar produto: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton btnVoltar = new JButton("Voltar");
        springLayout.putConstraint(SpringLayout.NORTH, btnSalvar, 0, SpringLayout.NORTH, btnVoltar);
        springLayout.putConstraint(SpringLayout.WEST, btnVoltar, 10, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.SOUTH, btnVoltar, -10, SpringLayout.SOUTH, this);
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(NovoProdutoPanel.this);
                frame.setContentPane(new ProdutoPanel(main));
                frame.revalidate();
            }
        });
        add(btnVoltar);

        JLabel lblNome = new JLabel("Informe o nome do produto:");
        springLayout.putConstraint(SpringLayout.NORTH, lblNome, 67, SpringLayout.SOUTH, lblTitulo);
        springLayout.putConstraint(SpringLayout.WEST, lblNome, 0, SpringLayout.WEST, lblTitulo);
        springLayout.putConstraint(SpringLayout.SOUTH, lblNome, -16, SpringLayout.NORTH, nomeTextField);
        springLayout.putConstraint(SpringLayout.EAST, lblNome, 0, SpringLayout.EAST, lblTitulo);
        lblNome.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblNome);
        add(btnSalvar);
        
        precotextField = new JTextField();
        springLayout.putConstraint(SpringLayout.NORTH, precotextField, 43, SpringLayout.SOUTH, nomeTextField);
        springLayout.putConstraint(SpringLayout.WEST, precotextField, 10, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.EAST, precotextField, 440, SpringLayout.WEST, this);
        add(precotextField);
        precotextField.setColumns(10);
        
        JLabel lblInformeOPreo = new JLabel("Informe o preço do produto:");
        springLayout.putConstraint(SpringLayout.WEST, lblInformeOPreo, 10, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.SOUTH, lblInformeOPreo, -6, SpringLayout.NORTH, precotextField);
        springLayout.putConstraint(SpringLayout.EAST, lblInformeOPreo, 0, SpringLayout.EAST, lblTitulo);
        lblInformeOPreo.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblInformeOPreo);
    }
}
