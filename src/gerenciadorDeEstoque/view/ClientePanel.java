package gerenciadorDeEstoque.view;

import gerenciadorDeEstoque.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.awt.event.*;


public class ClientePanel extends JPanel {
    public ClientePanel(Main main) { // Modifique o construtor para aceitar uma referência de Main
        JButton btnVoltar = new JButton("Voltar para Home");
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(ClientePanel.this);
                frame.setContentPane(new Main.HomeTela(main)); // Passe a referência de Main para o construtor de HomeTela
                frame.revalidate();
            }
        });
        add(btnVoltar);
    }
}
