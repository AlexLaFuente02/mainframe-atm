package bo.edu.ucb.sis213;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int intentos = 3;

        System.out.println("Bienvenido al Cajero Automático.");
        while (intentos > 0) {
            System.out.print("Ingrese su PIN de 4 dígitos: ");
            int pinIngresado = scanner.nextInt();
            try {
                Connection connection = DatabaseManager.getConnection();
                int usuarioId = DatabaseManager.obtenerUsuarioIdPorPin(connection, pinIngresado);
                if (usuarioId != -1) {
                    mostrarMenu(connection, usuarioId);
                    break;
                } else {
                    intentos--;
                    if (intentos > 0) {
                        System.out.println("PIN incorrecto. Le quedan " + intentos + " intentos.");
                    } else {
                        System.out.println("PIN incorrecto. Ha excedido el número de intentos.");
                        System.exit(0);
                    }
                }
            } catch (SQLException e) {
                System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            }
        }
    }



    public static void mostrarMenu(Connection connection, int usuarioId) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            OperacionesBD operacionesBD = new OperacionesBD(connection, usuarioId);
            System.out.println("\nMenú Principal:");
            System.out.println("1. Consultar saldo.");  
            System.out.println("2. Realizar un depósito.");
            System.out.println("3. Realizar un retiro.");
            System.out.println("4. Cambiar PIN.");
            System.out.println("5. c.");
            System.out.println("6. Salir.");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    operacionesBD.consultarSaldo();
                    break;
                case 2:
                    operacionesBD.realizarDeposito();
                    break;
                case 3:
                    operacionesBD.realizarRetiro();
                    break;
                case 4:
                    operacionesBD.cambiarPIN();
                    break;
                case 5:
                    operacionesBD.verhistorial();
                    break;
                case 6:
                    System.out.println("Gracias por usar el cajero. ¡Hasta luego!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }

}
