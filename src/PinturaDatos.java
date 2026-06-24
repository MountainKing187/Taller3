import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Modelo.Pintura;
import util.ConexionDB;

public class PinturaDatos {
    public boolean registrarPintura(Pintura pintura) {
        String sql = "INSERT INTO Pintura (Año, Valor, Titulo, Cod_Autor, Rut_Prestamista, Cod_Sala) VALUES (?, ?, ?, ?, ?, ?)";
        
        // El bloque try-with-resources cierra automáticamente la conexión y el statement
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, pintura.getAnio());
            pstmt.setInt(2, pintura.getValor());
            pstmt.setString(3, pintura.getTitulo());
            pstmt.setInt(4, pintura.getCodAutor());
            pstmt.setString(5, pintura.getRutPrestamista());
            pstmt.setInt(6, pintura.getCodSala());
            
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al registrar la pintura: " + e.getMessage());
            return false;
        }
    }

    public List<Pintura> obtenerTodasLasPinturas() {
        List<Pintura> listaPinturas = new ArrayList<>();
        String sql = "SELECT * FROM Pintura ORDER BY Cod_Pintura ASC";
        
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                Pintura p = new Pintura(rs.getInt("Cod_Pintura"),rs.getInt("Año"),rs.getInt("Valor"),rs.getString("Titulo"),rs.getInt("Cod_Autor"),rs.getString("Rut_Prestamista"),rs.getInt("Cod_Sala"));
                listaPinturas.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener las pinturas: " + e.getMessage());
        }
        return listaPinturas;
    }

    public boolean actualizarPintura(Pintura pintura) {
        String sql = "UPDATE Pintura SET Año = ?, Valor = ?, Titulo = ?, Cod_Autor = ?, Rut_Prestamista = ?, Cod_Sala = ? WHERE Cod_Pintura = ?";
        
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, pintura.getAnio());
            pstmt.setInt(2, pintura.getValor());
            pstmt.setString(3, pintura.getTitulo());
            pstmt.setInt(4, pintura.getCodAutor());
            pstmt.setString(5, pintura.getRutPrestamista());
            pstmt.setInt(6, pintura.getCodSala());
            pstmt.setInt(7, pintura.getCodPintura());
            
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
