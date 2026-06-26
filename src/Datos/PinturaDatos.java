package Datos;
import Modelo.Pintura;
import java.sql.*;
import util.ConexionDB;

public class PinturaDatos {
    public boolean registrarPintura(Pintura pintura) {
        String sql = "INSERT INTO Pintura (Año, Valor, Titulo, Estado, Cod_Autor) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexionDB.conectar()) {
            
            conn.setAutoCommit(false);

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                
                pstmt.setInt(1, pintura.getAnio());
                pstmt.setInt(2, pintura.getValor());
                pstmt.setString(3, pintura.getTitulo());
                pstmt.setString(4, pintura.getEstado());
                pstmt.setInt(5, pintura.getCodAutor());
                
                int filasAfectadas = pstmt.executeUpdate();
                if (filasAfectadas != 0) {
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

    public boolean actualizarPintura(Pintura pintura) {
        String sql = "UPDATE Pintura SET Año = ?, Valor = ?, Titulo = ?, Cod_Autor = ? WHERE Cod_Pintura = ?";

        try (Connection conn = ConexionDB.conectar()) {
                conn.setAutoCommit(false);   
            
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, pintura.getAnio());
                pstmt.setInt(2, pintura.getValor());
                pstmt.setString(3, pintura.getTitulo());
                pstmt.setInt(4, pintura.getCodAutor());
                pstmt.setInt(5, pintura.getCodPintura());

                int filasAfectadas = pstmt.executeUpdate();

                if (filasAfectadas > 0) {
                        conn.commit();
                        return true;
                    } else {
                        conn.rollback();
                        return false;
                    }
                }   catch (SQLException e) {
                        conn.rollback();
                        if ("23503".equals(e.getSQLState())) {
                            System.err.println("Error: el código de autor no existe.");
                        } else {
                            System.err.println("Error al actualizar la pintura: " + e.getMessage());
                        }
                        return false;
                    }
        }   catch (SQLException e) {
                System.err.println("Error de conexión: " + e.getMessage());
                return false;
            }
    }

    public boolean eliminarPintura(int codPintura) {
        String sql = "DELETE FROM Pintura WHERE Cod_Pintura = ?";
        
        try (Connection conn = ConexionDB.conectar()) {
            conn.setAutoCommit(false);
            
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, codPintura);
                
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
                System.err.println("Error al eliminar la pintura: " + e.getMessage());
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e.getMessage());
            return false;
        }
    }

    public int obtenerValorPintura(int codPintura) {
        String sql = "SELECT Valor FROM Pintura WHERE Cod_Pintura = ?";
        
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, codPintura);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("Valor");
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener el valor de la pintura: " + e.getMessage());
        }
        
        return -1;
    }
}