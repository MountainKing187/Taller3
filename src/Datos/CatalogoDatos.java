package Datos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.*;

public class CatalogoDatos {
    public void mostrarPinturas() {
    String sql = "SELECT Cod_Pintura, Año, Valor, Titulo, Estado, Cod_Autor " +
                 "FROM Pintura ORDER BY Cod_Pintura ASC";

    try (Connection conn = ConexionDB.conectar();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {

        System.out.println("\n--- Catálogo de Pinturas ---");
        System.out.printf("%-8s | %-6s | %-10s | %-25s | %-15s | %-10s%n",
                "CÓDIGO", "AÑO", "VALOR", "TÍTULO", "Estado","AUTOR");
        System.out.println("-----------------------------------------------------------------------------------------------");

        while (rs.next()) {
            System.out.printf("%-8d | %-6d | %-10d | %-25s | %-15s | %-8d%n",
                    rs.getInt("Cod_Pintura"),
                    rs.getInt("Año"),
                    rs.getInt("Valor"),
                    rs.getString("Titulo"),
                    rs.getString("Estado"),
                    rs.getInt("Cod_Autor"));
        }

    } catch (SQLException e) {
        System.err.println("Error al obtener las pinturas: " + e.getMessage());
    }
}

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


    public void mostrarClientes() {
        String sql = "SELECT Rut_Cliente, Nombre, Telefono, Correo FROM Cliente";
        
        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            System.out.println("\n--- Catálogo de Clientes ---");
            System.out.printf("%-10s | %-25s | %-12s |%-25s%n", "Rut Cliente", "Nombre", "Telefono", "Correo");
            System.out.println("---------------------------------------------------------------");
            
            while (rs.next()) {
                System.out.printf("%-10s | %-25s | %-12s |%-25s%n", 
                                  rs.getString("Rut_Cliente"), 
                                  rs.getString("Nombre"), 
                                  rs.getString("Telefono"),
                                  rs.getString("Correo"));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los clientes: " + e.getMessage());
        }
    }
    public void mostrarVentas() {
    String sql = "SELECT v.Cod_Venta, v.Fecha_Venta, v.Monto_Total, " +
                 "c.Nombre AS Nombre_Cliente, p.Titulo AS Titulo_Pintura " +
                 "FROM Venta v " +
                 "INNER JOIN Cliente c ON v.Rut_Cliente = c.Rut_Cliente " +
                 "INNER JOIN Pintura p ON v.Cod_Pintura = p.Cod_Pintura " +
                 "ORDER BY v.Cod_Venta ASC";

    try (Connection conn = ConexionDB.conectar();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {

        System.out.println("\n--- Registro de Ventas ---");
        System.out.printf("%-10s | %-19s | %-12s | %-20s | %-25s%n",
                "CÓDIGO", "FECHA", "MONTO", "CLIENTE", "PINTURA");
        System.out.println("-------------------------------------------------------------------------------------");

        while (rs.next()) {
            System.out.printf("%-10d | %-19s | %-12.2f | %-20s | %-25s%n",
                    rs.getInt("Cod_Venta"),
                    rs.getDate("Fecha_Venta").toString() + " " +
                    rs.getTime("Fecha_Venta").toString(),
                    rs.getDouble("Monto_Total"),
                    rs.getString("Nombre_Cliente"),
                    rs.getString("Titulo_Pintura"));
        }

    } catch (SQLException e) {
        System.err.println("Error al obtener las ventas: " + e.getMessage());
    }
}
}