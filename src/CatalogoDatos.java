import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.*;

public class CatalogoDatos {
    public void mostrarPinturas() {
    String sql = "SELECT Cod_Pintura, Año, Valor, Titulo, Cod_Autor, Rut_Prestamista, Cod_Sala " +
                 "FROM Pintura ORDER BY Cod_Pintura ASC";

    try (Connection conn = ConexionDB.conectar();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {

        System.out.println("\n--- Catálogo de Pinturas ---");
        System.out.printf("%-8s | %-6s | %-10s | %-25s | %-10s | %-18s | %-8s%n",
                "CÓDIGO", "AÑO", "VALOR", "TÍTULO", "AUTOR", "PRESTAMISTA", "SALA");
        System.out.println("-----------------------------------------------------------------------------------------------");

        while (rs.next()) {
            System.out.printf("%-8d | %-6d | %-10d | %-25s | %-10d | %-18s | %-8d%n",
                    rs.getInt("Cod_Pintura"),
                    rs.getInt("Año"),
                    rs.getInt("Valor"),
                    rs.getString("Titulo"),
                    rs.getInt("Cod_Autor"),
                    rs.getString("Rut_Prestamista"),
                    rs.getInt("Cod_Sala"));
        }

    } catch (SQLException e) {
        System.err.println("Error al obtener las pinturas: " + e.getMessage());
    }
}
    // 1. Mostrar Autores
    public void mostrarAutores() {
        String sql = "SELECT Cod_Autor, Nombre, Nacionalidad FROM Autor ORDER BY Cod_Autor ASC";
        
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            System.out.println("\n--- Catálogo de Autores ---");
            System.out.printf("%-10s | %-25s | %-20s%n", "CÓDIGO", "NOMBRE", "NACIONALIDAD");
            System.out.println("---------------------------------------------------------------");
            
            while (rs.next()) {
                System.out.printf("%-10d | %-25s | %-20s%n", 
                                  rs.getInt("Cod_Autor"), 
                                  rs.getString("Nombre"), 
                                  rs.getString("Nacionalidad"));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los autores: " + e.getMessage());
        }
    }

    // 2. Mostrar Salas
    public void mostrarSalas() {
        String sql = "SELECT Cod_Sala, Nombre, Capacidad_Max FROM Sala ORDER BY Cod_Sala ASC";
        
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            System.out.println("\n--- Catálogo de Salas ---");
            System.out.printf("%-10s | %-20s | %-15s%n", "CÓDIGO", "NOMBRE", "CAPACIDAD MAX");
            System.out.println("---------------------------------------------------------");
            
            while (rs.next()) {
                System.out.printf("%-10d | %-20s | %-15d%n", 
                                  rs.getInt("Cod_Sala"), 
                                  rs.getString("Nombre"), 
                                  rs.getInt("Capacidad_Max"));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener las salas: " + e.getMessage());
        }
    }

    // 3. Mostrar Prestamistas
    public void mostrarPrestamistas() {
        String sql = "SELECT Rut_Prestamista, Nombre, Telefono FROM Prestamista ORDER BY Nombre ASC";
        
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            System.out.println("\n--- Catálogo de Prestamistas ---");
            System.out.printf("%-15s | %-20s | %-15s%n", "RUT", "NOMBRE", "TELÉFONO");
            System.out.println("---------------------------------------------------------");
            
            while (rs.next()) {
                System.out.printf("%-15s | %-20s | %-15s%n", 
                                  rs.getString("Rut_Prestamista"), 
                                  rs.getString("Nombre"), 
                                  rs.getString("Telefono"));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los prestamistas: " + e.getMessage());
        }
    }
}