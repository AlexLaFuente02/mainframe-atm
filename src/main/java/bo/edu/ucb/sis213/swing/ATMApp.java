package bo.edu.ucb.sis213.swing;

import javax.swing.*;

public class ATMApp {

    private JFrame frame;

    public ATMApp() {
        frame = new JFrame("ATM App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 550, 350);
        frame.setLocationRelativeTo(null); // Center the frame on the screen

        showLoginScreen();

        frame.setVisible(true);
    }

    private void showLoginScreen() {
        InicioSesion inicioSesion = new InicioSesion(frame);
        frame.getContentPane().add(inicioSesion.panelIniciarSesion());
        frame.revalidate();
        frame.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ATMApp();
            }
        });
    }
}
