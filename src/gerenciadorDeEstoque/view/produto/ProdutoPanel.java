package gerenciadorDeEstoque.view.produto;

import gerenciadorDeEstoque.Main;
import gerenciadorDeEstoque.controller.ProdutosController;
import gerenciadorDeEstoque.repository.Conexao;
import gerenciadorDeEstoque.view.home.HomePanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class ProdutoPanel extends JPanel {

    private JTable produtostable;
    private JTextField idtextField;
    private JTextField descricaotextField;
    private JLabel lblNome;
    private ProdutosController produtoController = new ProdutosController();
    private Connection conexaoBd = Conexao.obterConexao();


    public ProdutoPanel(Main main) {
        setBackground(new Color(192, 192, 192));

        JButton btnVoltar = new JButton("Voltar para Home");
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(ProdutoPanel.this);
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
        springLayout.putConstraint(SpringLayout.NORTH, novo, 0, SpringLayout.NORTH, btnVoltar);
        springLayout.putConstraint(SpringLayout.WEST, novo, 47, SpringLayout.EAST, btnVoltar);
        novo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(ProdutoPanel.this);
                frame.setContentPane(new NovoProdutoPanel(main));
                frame.revalidate();
            }
        });

        add(novo);

        JButton btnExcluir = new JButton("Excluir");
        springLayout.putConstraint(SpringLayout.NORTH, btnExcluir, 0, SpringLayout.NORTH, btnVoltar);
        springLayout.putConstraint(SpringLayout.EAST, btnExcluir, -10, SpringLayout.EAST, this);
        btnExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = produtostable.getSelectedRow();
                if (selectedRow != -1) {
                    int option = JOptionPane.showConfirmDialog(ProdutoPanel.this, "Deseja realmente excluir este produto?",
                            "Confirmação", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        int produtoId = (int) produtostable.getValueAt(selectedRow, 0);
                        try {
                            produtoController.apagarProduto(conexaoBd, produtoId);
                            produtostable.setModel(produtoController.listarTodosProdutos(conexaoBd, produtostable));
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(ProdutoPanel.this, "Selecione um produto para excluir.", "Aviso",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        add(btnExcluir);

        JLabel lblTitulo = new JLabel("Listagem de produtos");
        springLayout.putConstraint(SpringLayout.NORTH, lblTitulo, 4, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.WEST, lblTitulo, 0, SpringLayout.WEST, btnVoltar);
        springLayout.putConstraint(SpringLayout.EAST, lblTitulo, 0, SpringLayout.EAST, btnExcluir);
        lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitulo);

        JButton btnPesquisar = new JButton("Pesquisar");
        JButton btnAtualizar = new JButton("Atualizar Lista");


        idtextField = new JTextField();
        springLayout.putConstraint(SpringLayout.NORTH, idtextField, 6, SpringLayout.SOUTH, lblTitulo);
        springLayout.putConstraint(SpringLayout.EAST, idtextField, -124, SpringLayout.EAST, this);
        add(idtextField);
        idtextField.setColumns(10);

        descricaotextField = new JTextField();
        springLayout.putConstraint(SpringLayout.NORTH, descricaotextField, 6, SpringLayout.SOUTH, idtextField);
        springLayout.putConstraint(SpringLayout.WEST, descricaotextField, 0, SpringLayout.WEST, idtextField);
        springLayout.putConstraint(SpringLayout.EAST, descricaotextField, -10, SpringLayout.EAST, this);
        add(descricaotextField);
        descricaotextField.setColumns(10);

        lblNome = new JLabel("Descrição");
        springLayout.putConstraint(SpringLayout.NORTH, lblNome, 3, SpringLayout.NORTH, descricaotextField);
        springLayout.putConstraint(SpringLayout.EAST, lblNome, -6, SpringLayout.WEST, descricaotextField);
        add(lblNome);
        springLayout.putConstraint(SpringLayout.NORTH, btnPesquisar, 10, SpringLayout.SOUTH, lblTitulo);
        springLayout.putConstraint(SpringLayout.WEST, btnPesquisar, 0, SpringLayout.WEST, btnVoltar);
        springLayout.putConstraint(SpringLayout.NORTH, btnAtualizar, 0, SpringLayout.NORTH, btnPesquisar);
        springLayout.putConstraint(SpringLayout.WEST, btnAtualizar, 10, SpringLayout.EAST, btnPesquisar);
        
        JButton btnPesquisarProduto = new JButton("Pesquisar");
        springLayout.putConstraint(SpringLayout.NORTH, btnPesquisarProduto, -1, SpringLayout.NORTH, idtextField);
        springLayout.putConstraint(SpringLayout.EAST, btnPesquisarProduto, -10, SpringLayout.EAST, this);
        btnPesquisarProduto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = idtextField.getText();
                String nome = descricaotextField.getText();

                try {
                    if (!id.isEmpty()) {
                        int idInt = Integer.parseInt(id);
                        produtostable.setModel(produtoController.listarProdutoId(conexaoBd, idInt, produtostable));
                    } else if (!nome.isEmpty()) {
                        produtostable.setModel(produtoController.listarProdutoDescricao(conexaoBd, nome, produtostable));
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        add(btnPesquisarProduto);
        
        JLabel lblId = new JLabel("Id");
        springLayout.putConstraint(SpringLayout.EAST, lblId, -176, SpringLayout.EAST, this);
        springLayout.putConstraint(SpringLayout.WEST, idtextField, 6, SpringLayout.EAST, lblId);
        springLayout.putConstraint(SpringLayout.NORTH, lblId, 6, SpringLayout.SOUTH, lblTitulo);
        add(lblId);
        
        JButton btnAtualizarLista = new JButton("Atualizar Lista");
        btnAtualizarLista.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		try {
                    produtostable.setModel(produtoController.listarTodosProdutos(conexaoBd, produtostable));
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
        		
        		
        		
        		
        		
        	}
        });
        springLayout.putConstraint(SpringLayout.NORTH, btnAtualizarLista, 24, SpringLayout.SOUTH, lblTitulo);
        springLayout.putConstraint(SpringLayout.WEST, btnAtualizarLista, 0, SpringLayout.WEST, btnVoltar);
        springLayout.putConstraint(SpringLayout.EAST, btnAtualizarLista, 135, SpringLayout.WEST, btnVoltar);
        add(btnAtualizarLista);
        
        produtostable = new JTable();
        springLayout.putConstraint(SpringLayout.NORTH, produtostable, 12, SpringLayout.SOUTH, descricaotextField);
        springLayout.putConstraint(SpringLayout.WEST, produtostable, 10, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.SOUTH, produtostable, -10, SpringLayout.NORTH, btnVoltar);
        springLayout.putConstraint(SpringLayout.EAST, produtostable, 0, SpringLayout.EAST, btnExcluir);
        add(produtostable);
    }
}
