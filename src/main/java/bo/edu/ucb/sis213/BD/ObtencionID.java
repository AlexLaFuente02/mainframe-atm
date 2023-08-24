package bo.edu.ucb.sis213.BD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ObtencionID {
    
    //Obtencion de IDUSER para SWING
    public static int obtenerUsuarioIdPorPinYAlias(Connection connection, String aliasIngresado, int pinIngresado) throws SQLException {
        String query = "SELECT id FROM usuarios WHERE pin = ? AND alias = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, pinIngresado);
            preparedStatement.setString(2, aliasIngresado);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            } else {
                return -1;  // No se encontró un usuario con el PIN y alias ingresados
            }
        }
    }
}
