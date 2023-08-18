package bo.edu.ucb.sis213.swing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import bo.edu.ucb.sis213.DatabaseManager;
import bo.edu.ucb.sis213.OperacionesBD;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;

public class CambioPIN {

    private JFrame frame;
    private Connection connection;
    private int usuarioId;

    public CambioPIN(JFrame frame, Connection connection, int usuarioId) {
        this.frame = frame;
        this.connection = connection;
        this.usuarioId = usuarioId;
    }

    public JPanel panelCambioPIN() {
        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(128, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

        JLabel txtIniciarSesion = new JLabel();
		txtIniciarSesion.setBackground(Color.WHITE);
		txtIniciarSesion.setForeground(Color.BLACK);
		txtIniciarSesion.setText("Cambio de PIN");
		txtIniciarSesion.setFont(new Font("Verdana", Font.BOLD, 25));
		txtIniciarSesion.setBounds(120, 26, 413, 40);
		contentPane.add(txtIniciarSesion);
		
		JLabel txtPinActual = new JLabel("Ingresa tu PIN actual:");
		txtPinActual.setForeground(Color.BLACK);
		txtPinActual.setBounds(74, 124, 155, 14);
		contentPane.add(txtPinActual);
		
		JTextField pinActual = new JTextField();
		pinActual.setBounds(240, 119, 200, 20);
		contentPane.add(pinActual);
		pinActual.setColumns(10);
		
		JLabel txtPinNuevo = new JLabel("Ingresa tu PIN nuevo:");
		txtPinNuevo.setForeground(Color.BLACK);
		txtPinNuevo.setBounds(74, 146, 140, 14);
		contentPane.add(txtPinNuevo);
		
		JTextField pinField = new JTextField();
		pinField.setColumns(10);
		pinField.setBounds(240, 145, 200, 20);
		contentPane.add(pinField);

		JLabel txtPinConfirmacion = new JLabel("Reingresa tu PIN nuevo:");
		txtPinConfirmacion.setForeground(Color.BLACK);
		txtPinConfirmacion.setBounds(74, 175, 155, 14);
		contentPane.add(txtPinConfirmacion);
		
		JTextField pinConfir = new JTextField();
		pinConfir.setColumns(10);
		pinConfir.setBounds(240, 170, 200, 20);
		contentPane.add(pinConfir);
		
		JButton btnIniciarSesion = new JButton("Actualizar PIN");
		btnIniciarSesion.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnIniciarSesion.setForeground(Color.WHITE);
		btnIniciarSesion.setBackground(Color.BLACK);
		btnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pinactual = pinActual.getText();
				String pinnuevo = pinField.getText();
				String pinconfirmacion = pinConfir.getText();
				try {
					cambiarPIN(pinactual, pinnuevo, pinconfirmacion);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnIniciarSesion.setBounds(269, 211, 135, 23);
		contentPane.add(btnIniciarSesion);
		
		JButton btnCerrar = new JButton("Volver al menú");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                mostrarMenu(connection, usuarioId);
			}
		});
		btnCerrar.setForeground(Color.WHITE);
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnCerrar.setBackground(Color.BLACK);
		btnCerrar.setBounds(100, 211, 130, 23);
		contentPane.add(btnCerrar);
		
        return contentPane; 
    }

	private void cambiarPIN(String pinactual,String pinnuevo,String pinconfirmacion) throws SQLException {
        OperacionesBD op = new OperacionesBD(connection, usuarioId);
		mostrarMensaje(op.cambiarPIN(pinactual, pinnuevo, pinconfirmacion));
		
    }

	private void mostrarMenu(Connection connection, int usuarioId) {
        Menu menu = new Menu(frame, connection,usuarioId);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(menu.panelMenu());
        frame.revalidate();
        frame.repaint();
    }

	private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(frame, mensaje, "Mensaje", JOptionPane.DEFAULT_OPTION);
    }
	
}
