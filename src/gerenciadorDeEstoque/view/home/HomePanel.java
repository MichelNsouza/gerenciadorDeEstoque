package gerenciadorDeEstoque.view.home;

import gerenciadorDeEstoque.Main;
import gerenciadorDeEstoque.view.cliente.ClientePanel;
import gerenciadorDeEstoque.view.produto.ProdutoPanel;
import gerenciadorDeEstoque.view.venda.VendasPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;


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
        
        JLabel lblNewLabel = new JLabel("Cadastro de novos Produtos:");
        lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        
        JLabel lblCadastroDeNovos = new JLabel("Cadastro de novos Clientes:");
        lblCadastroDeNovos.setFont(new Font("Arial", Font.PLAIN, 16));
        
        JLabel lblRegistreNovaVenda = new JLabel("Registre novas vendas:");
        lblRegistreNovaVenda.setFont(new Font("Arial", Font.PLAIN, 16));
        
                JLabel lblBemVindo = new JLabel("Bem-vindo", SwingConstants.CENTER);
                lblBemVindo.setFont(new Font("Arial", Font.BOLD, 24));
        
        JDesktopPane desktopPane = new JDesktopPane();
        GroupLayout gl_buttonPanel = new GroupLayout(buttonPanel);
        gl_buttonPanel.setHorizontalGroup(
        	gl_buttonPanel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_buttonPanel.createSequentialGroup()
        			.addGap(10)
        			.addGroup(gl_buttonPanel.createParallelGroup(Alignment.TRAILING, false)
        				.addGroup(gl_buttonPanel.createSequentialGroup()
        					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)
        					.addGap(6)
        					.addComponent(btnProdutos, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        				.addGroup(gl_buttonPanel.createSequentialGroup()
        					.addComponent(lblCadastroDeNovos, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)
        					.addGap(6)
        					.addComponent(btnClientes, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        				.addGroup(gl_buttonPanel.createSequentialGroup()
        					.addComponent(lblRegistreNovaVenda, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)
        					.addGap(6)
        					.addComponent(btnVendas, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
        			.addGap(53))
        		.addComponent(lblBemVindo, GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
        );
        gl_buttonPanel.setVerticalGroup(
        	gl_buttonPanel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_buttonPanel.createSequentialGroup()
        			.addComponent(lblBemVindo)
        			.addGap(16)
        			.addGroup(gl_buttonPanel.createParallelGroup(Alignment.TRAILING)
        				.addGroup(gl_buttonPanel.createSequentialGroup()
        					.addGroup(gl_buttonPanel.createParallelGroup(Alignment.LEADING)
        						.addComponent(lblNewLabel)
        						.addComponent(btnProdutos))
        					.addGap(41)
        					.addGroup(gl_buttonPanel.createParallelGroup(Alignment.LEADING)
        						.addComponent(lblCadastroDeNovos)
        						.addComponent(btnClientes))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(gl_buttonPanel.createParallelGroup(Alignment.LEADING)
        						.addGroup(gl_buttonPanel.createSequentialGroup()
        							.addGap(46)
        							.addComponent(lblRegistreNovaVenda))
        						.addGroup(gl_buttonPanel.createSequentialGroup()
        							.addGap(42)
        							.addComponent(btnVendas))))
        				.addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)))
        );
        buttonPanel.setLayout(gl_buttonPanel);
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
    }
}
