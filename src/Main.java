import java.util.Scanner;

import Modelo.Pintura;
import Modelo.Venta;
import Modelo.Cliente;

import Datos.CatalogoDatos;
import Datos.ClienteDatos;
import Datos.PinturaDatos;
import Datos.VentaDatos;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final PinturaDatos pinturaDatos = new PinturaDatos();
    private static final VentaDatos ventaDatos = new VentaDatos();
    private static final CatalogoDatos catalogoDatos = new CatalogoDatos();
    private static final ClienteDatos clienteDatos = new ClienteDatos();
    public static  void main(String[] args) {
        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1 -> { 
                    System.out.println("Listando pinturas...");
                    catalogoDatos.mostrarPinturas();
                }

                case 2 -> { 
                    System.out.println("Agregar pintura...");
                    pinturaDatos.registrarPintura(datosPintura());
                }

                case 3 -> {
                    System.out.println("Actualizar pintura...");
                    catalogoDatos.mostrarPinturas();
                    pinturaDatos.actualizarPintura(pinturaActualizar());
                }

                case 4 -> {
                    System.out.println("Eliminar pintura...");
                    catalogoDatos.mostrarPinturas();
                    pinturaDatos.eliminarPintura(leerEntero("Código de la pintura a eliminar: "));
                }

                case 5 -> {
                    procesarVenta();
                }
                case 6 -> {
                    crearCliente();
                }
                case 7-> {
                    System.out.println("Listar Ventas...");
                    catalogoDatos.mostrarVentas();
                }
                 
                case 0 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción no válida.");
            }

            if (opcion != 0) {
                System.out.println("\nPresione Enter para continuar...");
                sc.nextLine();
            }

        } while (opcion != 0);
    }

    private static void mostrarMenu() {
        System.out.println("\n===== GALERÍA DE ARTE =====");
        System.out.println("1. Listar pinturas");
        System.out.println("2. Agregar pintura");
        System.out.println("3. Actualizar pintura");
        System.out.println("4. Eliminar pintura");
        System.out.println("5. Procesar Venta");
        System.out.println("6. Crear Cliente");
        System.out.println("7. Ver Ventas");
        System.out.println("0. Salir");
    }

    private static Pintura datosPintura() {
        System.out.println("\n--- Ingreso de datos de la pintura ---");
        int anio = leerEntero("Año: ");
        int valor = leerEntero("Valor: ");
        String titulo = leerTexto("Título: ");
        String estado = "Disponible";
        catalogoDatos.mostrarAutores();
        int codAutor = leerEntero("Código de autor: ");

        Pintura p = new Pintura(); 
        p.setAnio(anio);
        p.setValor(valor);
        p.setTitulo(titulo);
        p.setEstado(estado);
        p.setCodAutor(codAutor);

        return p;
    }

    private static Pintura pinturaActualizar() {
        System.out.println("\n--- Actualización de pintura ---");
        
        int codPintura = leerEntero("Código de la pintura a actualizar: ");
        int anio = leerEntero("Año: ");
        int valor = leerEntero("Valor: ");
        String titulo = leerTexto("Título: ");

        catalogoDatos.mostrarAutores();
        int codAutor = leerEntero("Código de autor: ");

        Pintura p = new Pintura();
        p.setCodPintura(codPintura);
        p.setAnio(anio);
        p.setValor(valor);
        p.setTitulo(titulo);
        p.setCodAutor(codAutor);
        return p;
    }

    private static void crearCliente(){
        System.out.println("Procesar Venta...");
        System.out.println("\n--- Crear nuevo cliente ---");

        String rutCliente = leerTexto("Ingrese el RUT del cliente: ");
        String nombre = leerTexto("Ingrese el nombre del cliente: ");
        String correo = leerTexto("Ingrese el correo del cliente: ");
        String telefono = leerTexto("Ingrese el telefono del cliente: ");

        Cliente cliente = new Cliente();
        cliente.setRutCliente(rutCliente);
        cliente.setNombre(nombre);
        cliente.setCorreo(correo);
        cliente.setTelefono(telefono);

        clienteDatos.registrarCliente(cliente);
    }

    private static void procesarVenta() {
        System.out.println("Procesar Venta...");
        System.out.println("\n--- Procesar Nueva Venta ---");
        
        catalogoDatos.mostrarClientes();
        String rutCliente = leerTexto("Ingrese el RUT del cliente: ");
        catalogoDatos.mostrarPinturas();
        int codPintura = leerEntero("Ingrese el CÓDIGO de la pintura: ");
        int valorPintura = pinturaDatos.obtenerValorPintura(codPintura);
        
        if (valorPintura == -1) {
            System.out.println("Error: No se encontró ninguna pintura con ese código en el catálogo.");
        } else {
            System.out.println("El valor de la obra es: $" + valorPintura);
            
            Venta nuevaVenta = new Venta();
            nuevaVenta.setRutCliente(rutCliente);
            nuevaVenta.setCodPintura(codPintura);
            nuevaVenta.setMontoTotal(valorPintura);

            ventaDatos.procesarVenta(nuevaVenta);
        }
    }

    private static String leerTexto(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = sc.nextLine().trim();
            if (!texto.isEmpty()) {
                return texto;
            }
            System.out.println("Error: el campo no puede estar vacío.");
        }
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                int valor = Integer.parseInt(sc.nextLine());
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número válido.");
            }
        }
    }
}