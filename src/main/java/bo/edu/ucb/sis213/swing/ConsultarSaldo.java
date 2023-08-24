package bo.edu.ucb.sis213.swing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import bo.edu.ucb.sis213.Logic.Logica;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;

public class ConsultarSaldo {

    private JFrame frame;
    private Connection connection;
    private int usuarioId;
	
	private Logica logica = new Logica();

    public ConsultarSaldo(JFrame frame, Connection connection, int usuarioId) {
        this.frame = frame;
        this.connection = connection;
        this.usuarioId = usuarioId;
    }

    public JPanel panelSaldo(){
        JPanel contentPane = new JPanel();
        contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		JLabel txtTitulo = new JLabel();
		txtTitulo.setBackground(Color.WHITE);
		txtTitulo.setForeground(Color.BLACK);
		txtTitulo.setText("Consulta de Saldo");
		txtTitulo.setFont(new Font("Verdana", Font.BOLD, 25));
		txtTitulo.setBounds(124, 32, 285, 40);
		contentPane.add(txtTitulo);
		
		JButton btnVolverMenu = new JButton("Volver al Menú");
		btnVolverMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rutas rutas = new rutas(frame, connection, usuarioId);
				rutas.mostrarMenu();
			}
		});
		btnVolverMenu.setForeground(Color.WHITE);
		btnVolverMenu.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnVolverMenu.setBackground(Color.BLACK);
		btnVolverMenu.setBounds(190, 236, 120, 23);
		contentPane.add(btnVolverMenu);
		
		JTextArea txtArea = new JTextArea();
		txtArea.setFont(new Font("Monospaced", Font.BOLD, 15));
		txtArea.setWrapStyleWord(true);
		txtArea.setBackground(Color.LIGHT_GRAY);
		txtArea.setEditable(false);
		txtArea.setForeground(Color.BLACK);
		txtArea.setLineWrap(true);
		//Agregamos el texto obtenido de la logica
		txtArea.setText(logica.consultarSaldo(connection, usuarioId));
		txtArea.setBounds(138, 95, 215, 90);
		contentPane.add(txtArea);

        return contentPane;
    }

}
