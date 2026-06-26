package Datos;

import Modelo.Venta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import util.ConexionDB;

public class VentaDatos {

    public boolean procesarVenta(Venta venta) {
        String sqlActualizarPintura = "UPDATE Pintura SET Estado = 'Vendido' WHERE Cod_Pintura = ? AND Estado = 'Disponible'";
        
        String sqlInsertarVenta = "INSERT INTO Venta (Monto_Total, Rut_Cliente, Cod_Pintura) VALUES (?, ?, ?)";

        try (Connection conn = ConexionDB.conectar()) {
            conn.setAutoCommit(false);

            try (PreparedStatement pstmtPintura = conn.prepareStatement(sqlActualizarPintura);
                 PreparedStatement pstmtVenta = conn.prepareStatement(sqlInsertarVenta)) {

                pstmtPintura.setInt(1, venta.getCodPintura());
                int filasPintura = pstmtPintura.executeUpdate();

                if (filasPintura == 0) {
                    System.out.println("Transacción rechazada: La pintura ya no está disponible.");
                    conn.rollback();
                    return false;
                }

                pstmtVenta.setInt(1, venta.getMontoTotal());
                pstmtVenta.setString(2, venta.getRutCliente());
                pstmtVenta.setInt(3, venta.getCodPintura());
                
                int filasVenta = pstmtVenta.executeUpdate();

                if (filasVenta > 0) {
                    conn.commit();
                    System.out.println("Venta procesada con éxito. La transacción fue confirmada.");
                    return true;
                } else {
                    conn.rollback();
                    System.out.println("Error al registrar la venta. Se deshicieron los cambios.");
                    return false;
                }

            } catch (SQLException e) {
                if ("23503".equals(e.getSQLState())) {
                     System.err.println("Error: el RUT del cliente no existe o la pintura no es válida.");
                } else {
                    System.err.println("Error al registrar la venta: " + e.getMessage());
                }

                return false;
            } finally {
                conn.setAutoCommit(true);
            }

        } catch (SQLException e) {
            System.err.println("Error de conexión con la base de datos: " + e.getMessage());
            return false;
        }
    }
}