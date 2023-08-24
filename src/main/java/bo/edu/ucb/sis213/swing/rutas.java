package bo.edu.ucb.sis213.swing;

import java.sql.Connection;

import javax.swing.JFrame;

public class rutas {
    
    private JFrame frame;
    private Connection connection;
    private int usuarioId;

    public rutas(JFrame frame, Connection connection, int usuarioId) {
        this.frame = frame;
        this.connection = connection;
        this.usuarioId = usuarioId;
    }

    public void mostrarMenu() {
        Menu menu = new Menu(frame, connection,usuarioId);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(menu.panelMenu());
        frame.revalidate();
        frame.repaint();
    }

    public void mostrarSaldo() {
        ConsultarSaldo saldo = new ConsultarSaldo(frame, connection,usuarioId);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(saldo.panelSaldo());
        frame.revalidate();
        frame.repaint();
    }

    public void mostrarDeposito() {
        RealizarDeposito deposito = new RealizarDeposito(frame, connection,usuarioId);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(deposito.panelRealizarDeposito());
        frame.revalidate();
        frame.repaint();
    }

    public void mostrarRetiro() {
        RealizarRetiro retiro = new RealizarRetiro(frame, connection,usuarioId);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(retiro.panelRealizarRetiro());
        frame.revalidate();
        frame.repaint();
    }

    public void mostrarCambioPIN() {
        CambioPIN cambiopin = new CambioPIN(frame, connection,usuarioId);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(cambiopin.panelCambioPIN());
        frame.revalidate();
        frame.repaint();
    }

    public void mostrarHistorialTransacciones() {
        HistorialTransacciones historial = new HistorialTransacciones(frame, connection,usuarioId);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(historial.panelHistorialTransacciones());
        frame.revalidate();
        frame.repaint();
    }
    
}

