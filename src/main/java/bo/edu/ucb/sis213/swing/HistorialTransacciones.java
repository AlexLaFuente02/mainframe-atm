package bo.edu.ucb.sis213.swing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bo.edu.ucb.sis213.Logic.Logica;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class HistorialTransacciones {

    private JFrame frame;
    private Connection connection;
    private int usuarioId;

    private Logica logica = new Logica();

    public HistorialTransacciones(JFrame frame, Connection connection, int usuarioId) {
        this.frame = frame;
        this.connection = connection;
        this.usuarioId = usuarioId;
    }

    public JPanel panelHistorialTransacciones() {
        JPanel contentPane = new JPanel();
        contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		JLabel txtTitulo = new JLabel();
		txtTitulo.setBackground(Color.WHITE);
		txtTitulo.setForeground(Color.BLACK);
		txtTitulo.setText("Historial de Transacciones");
		txtTitulo.setFont(new Font("Verdana", Font.BOLD, 25));
		txtTitulo.setBounds(84, 32, 370, 40);
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
		btnVolverMenu.setBounds(192, 287, 120, 23);
		contentPane.add(btnVolverMenu);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(46, 82, 436, 189);
		contentPane.add(scrollPane_1);

		JTable table = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel(
            new Object[][] {},
            new String[] {
                "TIPO_OPERACION", "CANTIDAD", "FECHA"
            }
        );
        table.setModel(tableModel);
        scrollPane_1.setViewportView(table);


        try {
            ArrayList<String[]> historialData = logica.cargarHistorial(connection, usuarioId);
            for (String[] row : historialData) {
                tableModel.addRow(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return contentPane;
    }

}
