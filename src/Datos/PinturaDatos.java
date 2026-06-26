package Datos;
import java.sql.*;

import Modelo.Pintura;
import util.ConexionDB;

public class PinturaDatos {
    public boolean registrarPintura(Pintura pintura) {
        String sql = "INSERT INTO Pintura (Año, Valor, Titulo, Estado, Cod_Autor) VALUES (?, ?, ?, ?, ?)";
        
        // El bloque try-with-resources cierra automáticamente la conexión y el statement
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, pintura.getAnio());
            pstmt.setInt(2, pintura.getValor());
            pstmt.setString(3, pintura.getTitulo());
            pstmt.setString(4, pintura.getEstado());
            pstmt.setInt(5, pintura.getCodAutor());
            
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al registrar la pintura: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizarPintura(Pintura pintura) {
        String sql = "UPDATE Pintura SET Año = ?, Valor = ?, Titulo = ?, Estado = ?, Cod_Autor = ? WHERE Cod_Pintura = ?";
        
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, pintura.getAnio());
            pstmt.setInt(2, pintura.getValor());
            pstmt.setString(3, pintura.getTitulo());
            pstmt.setString(4, pintura.getEstado());
            pstmt.setInt(5, pintura.getCodAutor());
            pstmt.setInt(6, pintura.getCodPintura());
            
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al actualizar la pintura: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarPintura(int codPintura) {
        String sql = "DELETE FROM Pintura WHERE Cod_Pintura = ?";
        
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, codPintura);
            
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar la pintura: " + e.getMessage());
            return false;
        }
    }
}
