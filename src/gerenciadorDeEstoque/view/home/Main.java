package gerenciadorDeEstoque.view.home;

import javax.swing.*;

import gerenciadorDeEstoque.repository.Conexao;

import java.awt.*;
import java.sql.Connection;

public class Main extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    
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
    	
    	getContentPane().setBackground(new Color(192, 192, 192));
        setTitle("Gerenciador de Estoque");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(451, 299);

        HomePanel home = new HomePanel(this);
        getContentPane().add(home);
    }
}

