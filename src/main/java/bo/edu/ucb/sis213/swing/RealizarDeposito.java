package bo.edu.ucb.sis213.swing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import bo.edu.ucb.sis213.Logic.Logica;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;

public class RealizarDeposito {

    private JFrame frame;
    private Connection connection;
    private int usuarioId;

	private Logica logica = new Logica();

    public RealizarDeposito(JFrame frame, Connection connection, int usuarioId) {
        this.frame = frame;
        this.connection = connection;
        this.usuarioId = usuarioId;
    }

    public JPanel panelRealizarDeposito() {
        JPanel contentPane = new JPanel();
        contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		JLabel txtTitulo = new JLabel();
		txtTitulo.setBackground(Color.WHITE);
		txtTitulo.setForeground(Color.BLACK);
		txtTitulo.setText("Realice su deposito");
		txtTitulo.setFont(new Font("Verdana", Font.BOLD, 25));
		txtTitulo.setBounds(124, 32, 285, 40);
		contentPane.add(txtTitulo);

		JLabel txtAlias = new JLabel("Monto a depositar $:");
		txtAlias.setForeground(Color.BLACK);
		txtAlias.setBounds(73, 111, 155, 14);
		contentPane.add(txtAlias);
		
		JTextField montoField = new JTextField();
		montoField.setBounds(239, 106, 200, 20);
		contentPane.add(montoField);
		montoField.setColumns(10);
		
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
		btnVolverMenu.setBounds(125, 269, 120, 23);
		contentPane.add(btnVolverMenu);
		
		JTextArea txtArea = new JTextArea();
		txtArea.setFont(new Font("Monospaced", Font.BOLD, 15));
		txtArea.setWrapStyleWord(true);
		txtArea.setBackground(Color.LIGHT_GRAY);
		txtArea.setEditable(false);
		txtArea.setForeground(Color.BLACK);
		txtArea.setLineWrap(true);
		txtArea.setBounds(137, 152, 215, 90);
		contentPane.add(txtArea);

		JButton btnDepositar = new JButton("Depositar");
		btnDepositar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String montoIntroducido = montoField.getText();
				//Obtenemos texto de Logica
				txtArea.setText(logica.depositoLogica(montoIntroducido, connection, usuarioId));
			}
		});
		btnDepositar.setForeground(Color.WHITE);
		btnDepositar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnDepositar.setBackground(Color.BLACK);
		btnDepositar.setBounds(265, 269, 120, 23);
		contentPane.add(btnDepositar);

        return contentPane;
    }

}
