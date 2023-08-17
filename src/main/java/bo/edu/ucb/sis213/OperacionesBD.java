package bo.edu.ucb.sis213;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class OperacionesBD {
    private Connection connection;
    private int usuarioId;
    private Scanner scanner;

    public OperacionesBD(Connection connection, int usuarioId) {
        this.connection = connection;
        this.usuarioId = usuarioId;
        this.scanner = new Scanner(System.in);
    }

    public String consultarSaldo() throws SQLException {
        String query = "SELECT nombre, saldo FROM usuarios WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, usuarioId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String nombreUsuario = resultSet.getString("nombre");
                double saldo = resultSet.getDouble("saldo");
                System.out.println("Usuario: " + nombreUsuario);
                System.out.println("Saldo actual: $" + saldo);
                String answer = "Usuario: " + nombreUsuario + "\nSaldo actual: $" + saldo;
                return answer;
            } else {
                System.out.println("No se encontró el usuario.");
            }
        }
        
        return ":(";
    }

    public String realizarDeposito(String mnt) throws SQLException {
        System.out.print("Ingrese la cantidad a depositar: $");
        //double cantidad = scanner.nextDouble();
        double cantidad = Double.parseDouble(mnt);

        if (cantidad <= 0) {
            System.out.println("Cantidad no válida.");
            return "Cantidad no válida.";
        }

        String saldoQuery = "SELECT saldo FROM usuarios WHERE id = ?";
        try (PreparedStatement saldoStatement = connection.prepareStatement(saldoQuery)) {
            saldoStatement.setInt(1, usuarioId);
            ResultSet saldoResultSet = saldoStatement.executeQuery();
            if (saldoResultSet.next()) {
                double saldoActual = saldoResultSet.getDouble("saldo");

                // Actualizar el saldo en la base de datos
                String updateQuery = "UPDATE usuarios SET saldo = saldo + ? WHERE id = ?";
                try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                    updateStatement.setDouble(1, cantidad);
                    updateStatement.setInt(2, usuarioId);
                    updateStatement.executeUpdate();
                    System.out.println("Depósito realizado con éxito. Su nuevo saldo es: $" + (saldoActual + cantidad));
                    return "Depósito realizado con éxito. Su nuevo saldo es: $" + (saldoActual + cantidad);
                }
            }
        }
        return ":(";
    }

    public String realizarRetiro(String monto) throws SQLException {
        System.out.print("Ingrese la cantidad a retirar: $");
        //double cantidad = scanner.nextDouble();
        double cantidad = Double.parseDouble(monto);

        if (cantidad <= 0) {
            System.out.println("Cantidad no válida.");
            return "Cantidad no válida.";
        }

        String saldoQuery = "SELECT saldo FROM usuarios WHERE id = ?";
        try (PreparedStatement saldoStatement = connection.prepareStatement(saldoQuery)) {
            saldoStatement.setInt(1, usuarioId);
            ResultSet saldoResultSet = saldoStatement.executeQuery();
            if (saldoResultSet.next()) {
                double saldoActual = saldoResultSet.getDouble("saldo");
                if (cantidad > saldoActual) {
                    System.out.println("Saldo insuficiente.");
                    return "Saldo insuficiente.";
                }

                // Actualizar el saldo en la base de datos
                String updateQuery = "UPDATE usuarios SET saldo = saldo - ? WHERE id = ?";
                try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                    updateStatement.setDouble(1, cantidad);
                    updateStatement.setInt(2, usuarioId);
                    updateStatement.executeUpdate();
                    System.out.println("Retiro realizado con éxito. Su nuevo saldo es: $" + (saldoActual - cantidad));
                    return "Retiro realizado con éxito. Su nuevo saldo es: $" + (saldoActual - cantidad);
                }
            } else {
                System.out.println("No se encontró el usuario.");
            }
        }
        return ":(";
    }

    public void cambiarPIN() throws SQLException {
        System.out.print("Ingrese su PIN actual: ");
        int pinIngresado = scanner.nextInt();
    
        // Verificar si el PIN ingresado es correcto y obtener el ID del usuario
        String query = "SELECT id FROM usuarios WHERE id = ? AND pin = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, usuarioId);
            preparedStatement.setInt(2, pinIngresado);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                // El PIN es correcto, proceder a cambiarlo
                System.out.print("Ingrese su nuevo PIN: ");
                int nuevoPin = scanner.nextInt();
                System.out.print("Confirme su nuevo PIN: ");
                int confirmacionPin = scanner.nextInt();
    
                if (nuevoPin == confirmacionPin) {
                    // Actualizar el PIN en la base de datos
                    String updateQuery = "UPDATE usuarios SET pin = ? WHERE id = ?";
                    try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                        updateStatement.setInt(1, nuevoPin);
                        updateStatement.setInt(2, usuarioId);
                        int rowsAffected = updateStatement.executeUpdate();
                        if (rowsAffected > 0) {
                            System.out.println("PIN actualizado con éxito.");
                        } else {
                            System.out.println("No se pudo actualizar el PIN.");
                        }
                    }
                } else {
                    System.out.println("Los PINs no coinciden.");
                }
            } else {
                System.out.println("PIN incorrecto.");
            }
        }
    }

    public void verhistorial() throws SQLException {
        String query = "SELECT tipo_operacion, cantidad, fecha FROM historico WHERE usuario_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, usuarioId);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("TIPO_OPERACION        CANTIDAD            FECHA");
            while (resultSet.next()) {
                String tipo_operacion = resultSet.getString("tipo_operacion");
                double cantidad = resultSet.getDouble("cantidad");
                String fecha = resultSet.getString("fecha");
    
                String formattedTipoOperacion = String.format("%-22s", tipo_operacion);
                String formattedCantidad = String.format("%-20s", "$" + cantidad);
                System.out.println(formattedTipoOperacion + formattedCantidad + fecha);
            }
        }catch (SQLException e) {
            System.out.println("Error al obtener el historial: " + e.getMessage());
        }
    }
}
