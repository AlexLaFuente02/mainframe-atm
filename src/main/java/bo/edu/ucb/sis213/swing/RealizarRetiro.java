package bo.edu.ucb.sis213.swing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import bo.edu.ucb.sis213.OperacionesBD;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;

public class RealizarRetiro {

    private JFrame frame;
    private Connection connection;
    private int usuarioId;

    public RealizarRetiro(JFrame frame, Connection connection, int usuarioId) {
        this.frame = frame;
        this.connection = connection;
        this.usuarioId = usuarioId;
    }

    public JPanel panelRealizarRetiro() {
        JPanel contentPane = new JPanel();
        contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		JLabel txtTitulo = new JLabel();
		txtTitulo.setBackground(Color.WHITE);
		txtTitulo.setForeground(Color.BLACK);
		txtTitulo.setText("Realice su retiro");
		txtTitulo.setFont(new Font("Verdana", Font.BOLD, 25));
		txtTitulo.setBounds(124, 32, 285, 40);
		contentPane.add(txtTitulo);

		JLabel txtAlias = new JLabel("Monto a retirar $:");
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
				mostrarMenu(connection, usuarioId);
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

		JButton btnDepositar = new JButton("Retirar");
		btnDepositar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String montoIntroducido = montoField.getText();
				txtArea.setText(msgTxtArea(montoIntroducido));
			}
		});
		btnDepositar.setForeground(Color.WHITE);
		btnDepositar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnDepositar.setBackground(Color.BLACK);
		btnDepositar.setBounds(265, 269, 120, 23);
		contentPane.add(btnDepositar);

        return contentPane;
    }


	private void mostrarMenu(Connection connection, int usuarioId) {
        Menu menu = new Menu(frame, connection,usuarioId);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(menu.panelMenu());
        frame.revalidate();
        frame.repaint();
    }

	
	private String msgTxtArea(String montoIntroducido){
		OperacionesBD op = new OperacionesBD(connection, usuarioId);
        String txtShow=""; 
        try {
            txtShow=op.realizarRetiro(montoIntroducido);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
		return txtShow;
	}

}
