package gerenciadorDeEstoque.view.venda;

import gerenciadorDeEstoque.view.home.HomePanel;
import gerenciadorDeEstoque.view.home.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;


public class VendasPanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tableVendas;

	public VendasPanel(Main main) {
    	setBackground(new Color(192, 192, 192)); 
        JButton btnVoltar = new JButton("Voltar para Home");
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(VendasPanel.this);
                frame.setContentPane(new HomePanel(main)); 
                frame.revalidate();
            }
        });
        SpringLayout springLayout = new SpringLayout();
        springLayout.putConstraint(SpringLayout.WEST, btnVoltar, 10, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.SOUTH, btnVoltar, -10, SpringLayout.SOUTH, this);
        setLayout(springLayout);
        add(btnVoltar);
        
        JButton btnExcluir = new JButton("Excluir");
        springLayout.putConstraint(SpringLayout.NORTH, btnExcluir, 0, SpringLayout.NORTH, btnVoltar);
        springLayout.putConstraint(SpringLayout.EAST, btnExcluir, -10, SpringLayout.EAST, this);
        add(btnExcluir);
        
        JLabel lblTitulo = new JLabel("Listagem de Vendas");
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
        	}
        });
        add(btnPesquisar);
        
        JButton btnNovo = new JButton("Novo");
        springLayout.putConstraint(SpringLayout.NORTH, btnNovo, 6, SpringLayout.SOUTH, lblTitulo);
        springLayout.putConstraint(SpringLayout.WEST, btnNovo, 0, SpringLayout.WEST, btnVoltar);
        springLayout.putConstraint(SpringLayout.EAST, btnNovo, -272, SpringLayout.WEST, btnPesquisar);
        btnNovo.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        add(btnNovo);
        
        JButton btnAtualizar = new JButton("Atualizar Lista");
        springLayout.putConstraint(SpringLayout.WEST, btnAtualizar, 38, SpringLayout.EAST, btnVoltar);
        springLayout.putConstraint(SpringLayout.SOUTH, btnAtualizar, -10, SpringLayout.SOUTH, this);
        springLayout.putConstraint(SpringLayout.EAST, btnAtualizar, -44, SpringLayout.WEST, btnExcluir);
        btnAtualizar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        add(btnAtualizar);
        
        tableVendas = new JTable();
        springLayout.putConstraint(SpringLayout.NORTH, tableVendas, 6, SpringLayout.SOUTH, btnPesquisar);
        springLayout.putConstraint(SpringLayout.WEST, tableVendas, 10, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.SOUTH, tableVendas, -6, SpringLayout.NORTH, btnVoltar);
        springLayout.putConstraint(SpringLayout.EAST, tableVendas, 0, SpringLayout.EAST, btnExcluir);
        add(tableVendas);
    }
}
