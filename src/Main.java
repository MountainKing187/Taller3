import Modelo.Pintura;
import java.util.Scanner;
public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final PinturaDatos pinturaDatos = new PinturaDatos();
    private static final CatalogoDatos catalogoDatos = new CatalogoDatos();
    public static  void main(String[] args) {
        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1 -> { 
                System.out.println("Listar pinturas...");
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
        System.out.println("0. Salir");
    }

    private static Pintura datosPintura() {
        System.out.println("\n--- Ingreso de datos de la pintura ---");
        int anio = leerEntero("Año: ");
        int valor = leerEntero("Valor: ");
        String titulo = leerTexto("Título: ");
        catalogoDatos.mostrarAutores();
        int codAutor = leerEntero("Código de autor: ");
        catalogoDatos.mostrarPrestamistas();
        String rutPrestamista = leerTexto("RUT prestamista: ");
        catalogoDatos.mostrarSalas();
        int codSala = leerEntero("Código de sala: ");

        Pintura p = new Pintura(); 
        p.setAnio(anio);
        p.setValor(valor);
        p.setTitulo(titulo);
        p.setCodAutor(codAutor);
        p.setRutPrestamista(rutPrestamista);
        p.setCodSala(codSala);

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

    catalogoDatos.mostrarPrestamistas();
    String rutPrestamista = leerTexto("RUT prestamista: ");

    catalogoDatos.mostrarSalas();
    int codSala = leerEntero("Código de sala: ");

    Pintura p = new Pintura();
    p.setCodPintura(codPintura);
    p.setAnio(anio);
    p.setValor(valor);
    p.setTitulo(titulo);
    p.setCodAutor(codAutor);
    p.setRutPrestamista(rutPrestamista);
    p.setCodSala(codSala);

    return p;
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