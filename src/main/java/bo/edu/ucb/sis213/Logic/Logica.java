package bo.edu.ucb.sis213.Logic;

import bo.edu.ucb.sis213.BD.Conexion;
import bo.edu.ucb.sis213.BD.ObtencionID;
import bo.edu.ucb.sis213.BD.OperacionesBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Logica {
    private int intentos = 3;

    //GENERAL
    public Connection obtenerConexion() throws SQLException {
        return Conexion.getConnection();
    }

    public int obtenerUsuarioIdPorPinYAlias(Connection connection, String aliasIngresado, int pinIngresado) throws SQLException {
        return ObtencionID.obtenerUsuarioIdPorPinYAlias(connection, aliasIngresado, pinIngresado);
    }

    //INICIAR SESION LOGIC
    public boolean verificarPIN(String alias, int pin) {
        if (intentos > 0) {
            try {
                Connection connection = Conexion.getConnection();
                int usuarioId = ObtencionID.obtenerUsuarioIdPorPinYAlias(connection, alias, pin);
                if (usuarioId != -1) {
                    return true;
                } else {
                    intentos--;
                    return false;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    public int getIntentosRestantes() {
        return intentos;
    }

    //CONSULTAR SALDO LOGIC
    public String consultarSaldo(Connection connection, int usuarioId) {
        OperacionesBD op = new OperacionesBD(connection, usuarioId);
        String txtShow="";
        try {
            txtShow=op.consultarSaldo();
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        return txtShow;
    }

    //REALIZAR DEPOSITO LOGIC
    public String depositoLogica(String montoIntroducido, Connection connection, int usuarioId){
		OperacionesBD op = new OperacionesBD(connection, usuarioId);
        String txtShow=""; 
        try {
            txtShow=op.realizarDeposito(montoIntroducido);
        } 
		catch (SQLException e1) {
            e1.printStackTrace();
        }
		return txtShow;
	}

    //REALIZAR RETIRO LOGIC
    public String retiroLogica(String montoIntroducido, Connection connection, int usuarioId){
		OperacionesBD op = new OperacionesBD(connection, usuarioId);
        String txtShow=""; 
        try {
            txtShow=op.realizarRetiro(montoIntroducido);
        } 
        catch (SQLException e1) {
            e1.printStackTrace();
        }
		return txtShow;
	}

    //CAMBIAR PIN LOGIC
    public String cambiarPINLogica(String pinactual,String pinnuevo,String pinconfirmacion, Connection connection, int usuarioId) {
        OperacionesBD op = new OperacionesBD(connection, usuarioId);
        String txt="";
        try {
            txt= op.cambiarPIN(pinactual, pinnuevo, pinconfirmacion);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return txt;
    }

    //HISTORIAL TRANSACCIONES LOGIC
    public ArrayList<String[]> cargarHistorial(Connection connection, int usuarioId) throws SQLException {
        ArrayList <String[]> historial = new ArrayList<>();
        OperacionesBD op = new OperacionesBD(connection, usuarioId);
        ResultSet resultSet = op.verhistorial();

        while (resultSet.next()) {
            String tipo_operacion = resultSet.getString("tipo_operacion");
            double cantidad = resultSet.getDouble("cantidad");
            String fecha = resultSet.getString("fecha");

            String formattedTipoOperacion = tipo_operacion;
            String formattedCantidad = "$" + cantidad;

            historial.add(new String[]{formattedTipoOperacion, formattedCantidad, fecha});
        }
        
        return historial;
    }

}

