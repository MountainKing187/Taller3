package Datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Modelo.Cliente;
import util.ConexionDB;

public class ClienteDatos {
        public boolean registrarCliente(Cliente cliente) {
        String sql = "INSERT INTO Cliente (Rut_Cliente, Nombre, Telefono, Correo) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = ConexionDB.conectar()) {
            conn.setAutoCommit(false); // 1. Iniciar transacción
            
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, cliente.getRutCliente());
                pstmt.setString(2, cliente.getNombre());
                pstmt.setString(3, cliente.getTelefono());
                pstmt.setString(4, cliente.getCorreo());
                
                int filasAfectadas = pstmt.executeUpdate();
                
                if (filasAfectadas > 0) {
                    conn.commit();
                    return true;
                } else {
                    conn.rollback();
                    return false;
                }
            } catch (SQLException e) {
                conn.rollback();
                System.err.println("Rollback al registrar la pintura: " + e.getMessage());
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e.getMessage());
            return false;
        }
    }
}
