package bo.edu.ucb.sis213.swing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import bo.edu.ucb.sis213.OperacionesBD;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;

public class ConsultarSaldo {

    private JFrame frame;
    private Connection connection;
    private int usuarioId;

    public ConsultarSaldo(JFrame frame, Connection connection, int usuarioId) {
        this.frame = frame;
        this.connection = connection;
        this.usuarioId = usuarioId;
    }

    public JPanel panelSaldo() {
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

			}
		});
		btnVolverMenu.setForeground(Color.WHITE);
		btnVolverMenu.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnVolverMenu.setBackground(Color.BLACK);
		btnVolverMenu.setBounds(208, 258, 109, 23);
		contentPane.add(btnVolverMenu);
		
		JTextArea txtrHolaMiNombre = new JTextArea();
        OperacionesBD op = new OperacionesBD(connection, usuarioId);
        String txtShow="";
        try {
            txtShow=op.consultarSaldo();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

		txtrHolaMiNombre.setText(txtShow);
		txtrHolaMiNombre.setBounds(158, 117, 213, 89);
		contentPane.add(txtrHolaMiNombre);

        return contentPane;
    }


}
