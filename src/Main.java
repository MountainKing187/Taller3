import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1 -> System.out.println("Listar pinturas...");
                // listarPinturas();
                case 2 -> System.out.println("Agregar pintura...");
                // agregarPintura();
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