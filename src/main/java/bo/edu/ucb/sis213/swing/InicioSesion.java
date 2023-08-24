package bo.edu.ucb.sis213.swing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import bo.edu.ucb.sis213.Logic.Logica;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class InicioSesion {

    private JFrame frame;
    private JPanel loginPanel;
	private JTextField aliasField;
	private JPasswordField pinField;
	private Logica logica = new Logica();

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
				try {
					LogicSignIn();
				} 
				catch (SQLException e1) {
					e1.printStackTrace();
				}
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

	// Uso de logica
	private void LogicSignIn() throws SQLException {
        String alias = aliasField.getText();
        int pin = Integer.parseInt(new String(pinField.getPassword()));
        if (logica.verificarPIN(alias, pin)) {
			System.out.println("Workiiin");
            rutas rutas = new rutas(frame,logica.obtenerConexion(), logica.obtenerUsuarioIdPorPinYAlias(logica.obtenerConexion(), alias, pin));
			rutas.mostrarMenu();        
		} 
		else {
    	    int intentosRestantes = logica.getIntentosRestantes();
            if (intentosRestantes > 0) {
                mostrarMensajeError("PIN incorrecto. Le quedan " + intentosRestantes + " intentos.");
            } 
			else {
                mostrarMensajeError("PIN incorrecto. Ha excedido el número de intentos.");
                System.exit(0);
            }
        }
    }

	private void mostrarMensajeError(String mensaje) {
        JOptionPane.showMessageDialog(frame, mensaje, "Error de Inicio de Sesión", JOptionPane.ERROR_MESSAGE);
    }

}
