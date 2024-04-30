package gerenciadorDeEstoque;

import javax.swing.*;

import gerenciadorDeEstoque.repository.Conexao;
import gerenciadorDeEstoque.view.ClientePanel;
import gerenciadorDeEstoque.view.ProdutoPanel;
import gerenciadorDeEstoque.view.VendasPanel;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;


public class Main extends JFrame {

    private static final long serialVersionUID = 1L;
    private Connection conexaoBd = Conexao.obterConexao();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main frame = new Main();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Main() {
        setTitle("Gerenciador de Estoque");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);


        HomeTela home = new HomeTela(this);
        add(home);
    }

    public static class HomeTela extends JPanel {
        public HomeTela(Main main) {
            setBackground(Color.WHITE);
            setLayout(new BorderLayout());

            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
            add(buttonPanel, BorderLayout.CENTER);

            JButton btnClientes = new JButton("Clientes");
            JButton btnProdutos = new JButton("Produtos");
            JButton btnVendas = new JButton("Vendas");

            buttonPanel.add(btnClientes);
            buttonPanel.add(btnProdutos);
            buttonPanel.add(btnVendas);

            btnClientes.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    main.setContentPane(new ClientePanel(main));
                    main.revalidate();
                }
            });

            btnProdutos.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    main.setContentPane(new ProdutoPanel(main));
                    main.revalidate();
                }
            });

            btnVendas.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    main.setContentPane(new VendasPanel(main));
                    main.revalidate();
                }
            });

            JLabel lblBemVindo = new JLabel("Bem-vindo", SwingConstants.CENTER);
            lblBemVindo.setFont(new Font("Arial", Font.BOLD, 24));
            add(lblBemVindo, BorderLayout.NORTH);
        }
    }
}