package gerenciadorDeEstoque.view.home;

import gerenciadorDeEstoque.Main;
import gerenciadorDeEstoque.view.cliente.ClientePanel;
import gerenciadorDeEstoque.view.produto.ProdutoPanel;
import gerenciadorDeEstoque.view.venda.VendasPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class HomePanel extends JPanel {
	
    public HomePanel(Main main) {

        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(192, 192, 192));
        add(buttonPanel, BorderLayout.CENTER);

        JButton btnClientes = new JButton("Clientes");
        JButton btnProdutos = new JButton("Produtos");
        JButton btnVendas = new JButton("Vendas");
        SpringLayout sl_buttonPanel = new SpringLayout();
        sl_buttonPanel.putConstraint(SpringLayout.WEST, btnProdutos, 30, SpringLayout.EAST, btnClientes);
        sl_buttonPanel.putConstraint(SpringLayout.EAST, btnProdutos, -20, SpringLayout.WEST, btnVendas);
        sl_buttonPanel.putConstraint(SpringLayout.WEST, btnClientes, 47, SpringLayout.WEST, buttonPanel);
        sl_buttonPanel.putConstraint(SpringLayout.NORTH, btnClientes, 0, SpringLayout.NORTH, btnProdutos);
        sl_buttonPanel.putConstraint(SpringLayout.EAST, btnClientes, 142, SpringLayout.WEST, buttonPanel);
        sl_buttonPanel.putConstraint(SpringLayout.EAST, btnVendas, -48, SpringLayout.EAST, buttonPanel);
        sl_buttonPanel.putConstraint(SpringLayout.SOUTH, btnVendas, -120, SpringLayout.SOUTH, buttonPanel);
        sl_buttonPanel.putConstraint(SpringLayout.SOUTH, btnProdutos, -120, SpringLayout.SOUTH, buttonPanel);
        sl_buttonPanel.putConstraint(SpringLayout.WEST, btnVendas, -143, SpringLayout.EAST, buttonPanel);
        buttonPanel.setLayout(sl_buttonPanel);
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
