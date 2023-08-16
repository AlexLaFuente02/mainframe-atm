package bo.edu.ucb.sis213.swing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import bo.edu.ucb.sis213.OperacionesBD;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;

public class Menu {

    private JFrame frame;
    private Connection connection;
    private int usuarioId;

    public Menu(JFrame frame, Connection connection, int usuarioId) {
        this.frame = frame;
        this.connection = connection;
        this.usuarioId = usuarioId;
    }

    public JPanel panelMenu() {
        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(new Color(128, 128, 128));
        menuPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        menuPanel.setLayout(null);

        JLabel txtMenuPrincipal = new JLabel();
        txtMenuPrincipal.setBackground(Color.WHITE);
        txtMenuPrincipal.setForeground(Color.BLACK);
        txtMenuPrincipal.setText("MENU PRINCIPAL ATM");
        txtMenuPrincipal.setFont(new Font("Verdana", Font.BOLD, 25));
        txtMenuPrincipal.setBounds(99, 26, 413, 40);
        menuPanel.add(txtMenuPrincipal);

        JButton btnConsultarSaldo = new JButton("Consultar Saldo");
        btnConsultarSaldo.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnConsultarSaldo.setForeground(Color.WHITE);
        btnConsultarSaldo.setBackground(Color.BLACK);
        btnConsultarSaldo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarSaldo(connection, usuarioId);
            }
        });
        btnConsultarSaldo.setBounds(88, 92, 170, 23);
        menuPanel.add(btnConsultarSaldo);

        JButton btnRealizarDeposito = new JButton("Realizar Depósito");
        btnRealizarDeposito.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnRealizarDeposito.setForeground(Color.WHITE);
        btnRealizarDeposito.setBackground(Color.BLACK);
        btnRealizarDeposito.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para realizar depósito
            }
        });
        btnRealizarDeposito.setBounds(268, 92, 170, 23);
        menuPanel.add(btnRealizarDeposito);

        JButton btnRealizarRetiro = new JButton("Realizar Retiro");
        btnRealizarRetiro.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnRealizarRetiro.setForeground(Color.WHITE);
        btnRealizarRetiro.setBackground(Color.BLACK);
        btnRealizarRetiro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para realizar retiro
            }
        });
        btnRealizarRetiro.setBounds(88, 135, 170, 23);
        menuPanel.add(btnRealizarRetiro);

        JButton btnCambiarPIN = new JButton("Cambiar PIN");
        btnCambiarPIN.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnCambiarPIN.setForeground(Color.WHITE);
        btnCambiarPIN.setBackground(Color.BLACK);
        btnCambiarPIN.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para cambiar PIN
            }
        });
        btnCambiarPIN.setBounds(268, 135, 170, 23);
        menuPanel.add(btnCambiarPIN);

        JButton btnVerHistorial = new JButton("Historial de Transacciones");
        btnVerHistorial.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnVerHistorial.setForeground(Color.WHITE);
        btnVerHistorial.setBackground(Color.BLACK);
        btnVerHistorial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para ver historial de transacciones
            }
        });
        btnVerHistorial.setBounds(88, 181, 170, 23);
        menuPanel.add(btnVerHistorial);

        JButton btnSalir = new JButton("Salir");
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnSalir.setBackground(Color.BLACK);
        btnSalir.setBounds(268, 181, 170, 23);
        menuPanel.add(btnSalir);

        return menuPanel;
    }


    private void mostrarSaldo(Connection connection, int usuarioId) {
        ConsultarSaldo saldo = new ConsultarSaldo(frame, connection,usuarioId);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(saldo.panelSaldo());
        frame.revalidate();
        frame.repaint();
    }

}