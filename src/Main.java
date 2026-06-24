import Modelo.Pintura;
import java.util.Scanner;
public class Main {
    private static final Scanner sc = new Scanner(System.in);

    public  void main(String[] args) {
        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1 -> System.out.println("Listar pinturas...");
                // listarPinturas();
                case 2 -> { 
                System.out.println("Agregar pintura...");
                PinturaDatos.registrarPintura(datosPintura());
                }
                case 3 -> System.out.println("Actualizar pintura...");
                // actualizarPintura();
                case 4 -> System.out.println("Eliminar pintura...");
                // eliminarPintura();
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
        System.out.println("5. Listar autores");
        System.out.println("0. Salir");
    }

    private static Pintura datosPintura() {
        System.out.println("\n--- Ingreso de datos de la pintura ---");

        int codPintura = leerEntero("Código de pintura: ");
        int anio = leerEntero("Año: ");
        int valor = leerEntero("Valor: ");
        String titulo = leerTexto("Título: ");
        int codAutor = leerEntero("Código de autor: ");
        String rutPrestamista = leerTexto("RUT prestamista: ");
        int codSala = leerEntero("Código de sala: ");

        return new Pintura(codPintura, anio, valor, titulo, codAutor, rutPrestamista, codSala);
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