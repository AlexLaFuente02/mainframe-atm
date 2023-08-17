package bo.edu.ucb.sis213.swing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import bo.edu.ucb.sis213.DatabaseManager;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;

public class InicioSesion {

    private JFrame frame;
    private JPanel loginPanel;
	private JTextField aliasField;
	private JPasswordField pinField;

	private int intentos = 3;

    public InicioSesion(JFrame frame) {
        this.frame = frame;
    }

    public JPanel panelIniciarSesion() {
        loginPanel = new JPanel();
        loginPanel.setBackground(new Color(128, 128, 128));
		loginPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		loginPanel.setLayout(null);

        JLabel txtIniciarSesion = new JLabel();
		txtIniciarSesion.setBackground(Color.WHITE);
		txtIniciarSesion.setForeground(Color.BLACK);
		txtIniciarSesion.setText("BIENVENIDO A TU ATM");
		txtIniciarSesion.setFont(new Font("Verdana", Font.BOLD, 25));
		txtIniciarSesion.setBounds(99, 26, 413, 40);
		loginPanel.add(txtIniciarSesion);
		
		JLabel txtAlias = new JLabel("Ingresa tu alias:");
		txtAlias.setForeground(Color.BLACK);
		txtAlias.setBounds(74, 124, 155, 14);
		loginPanel.add(txtAlias);
		
		aliasField = new JTextField();
		aliasField.setBounds(240, 119, 200, 20);
		loginPanel.add(aliasField);
		aliasField.setColumns(10);
		
		JLabel txtPin = new JLabel("Ingresa tu PIN:");
		txtPin.setForeground(Color.BLACK);
		txtPin.setBounds(74, 146, 112, 14);
		loginPanel.add(txtPin);
		
		pinField = new JPasswordField();
		pinField.setColumns(10);
		pinField.setBounds(240, 145, 200, 20);
		loginPanel.add(pinField);
		
		JButton btnIniciarSesion = new JButton("Iniciar Sesión");
		btnIniciarSesion.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnIniciarSesion.setForeground(Color.WHITE);
		btnIniciarSesion.setBackground(Color.BLACK);
		btnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verificarPIN();
			}
		});
		btnIniciarSesion.setBounds(269, 200, 105, 23);
		loginPanel.add(btnIniciarSesion);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                System.exit(0);
			}
		});
		btnCerrar.setForeground(Color.WHITE);
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnCerrar.setBackground(Color.BLACK);
		btnCerrar.setBounds(112, 200, 89, 23);
		loginPanel.add(btnCerrar);

        return loginPanel; 
    }

	private void verificarPIN() {
        if (intentos > 0) { // Verificar si quedan intentos
            int pinIngresado = Integer.parseInt(new String(pinField.getPassword())); // Obtener el PIN ingresado
            String aliasIngresado = aliasField.getText();
			try {
                Connection connection = DatabaseManager.getConnection();
                int usuarioId = DatabaseManager.obtenerUsuarioIdPorPinYAlias(connection, aliasIngresado, pinIngresado);
                if (usuarioId != -1) {
                    mostrarMenu(connection,usuarioId);
                } 
				else {
                    intentos--;
                    if (intentos > 0) {
                        mostrarMensajeError("PIN incorrecto. Le quedan " + intentos + " intentos.");
                    } 
					else {
                        mostrarMensajeError("PIN incorrecto. Ha excedido el número de intentos.");
                        System.exit(0);
                    }
                }
            } 
			catch (SQLException ex) {
                mostrarMensajeError("Error al conectar a la base de datos: " + ex.getMessage());
            }
        } 
		else {
            mostrarMensajeError("Ha excedido el número de intentos.");
            System.exit(0);
        }
    }

	private void mostrarMenu(Connection connection, int usuarioId) {
        Menu menu = new Menu(frame, connection,usuarioId);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(menu.panelMenu());
        frame.revalidate();
        frame.repaint();
    }

	private void mostrarMensajeError(String mensaje) {
        JOptionPane.showMessageDialog(frame, mensaje, "Error de Inicio de Sesión", JOptionPane.ERROR_MESSAGE);
    }


	
}
