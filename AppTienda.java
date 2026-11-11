import java.time.LocalDate;

/** Demuestra herencia, reutilización, sobrescritura y métodos exclusivos. */
public class AppTienda {
    /** @param args argumentos no utilizados */
    public static void main(String[] args) {
        // Se instancian las dos subclases con su constructor especializado.
        ProductoPerecedero leche = new ProductoPerecedero(
                "Leche", 28.50, 20, LocalDate.of(2026, 11, 15));
        ProductoElectronico calculadora = new ProductoElectronico(
                "Calculadora", 350.00, 8, 24);
        LocalDate consulta = LocalDate.of(2026, 11, 10);

        // Las referencias base invocan la versión de cada objeto real.
        Producto[] inventario = {leche, calculadora};
        System.out.println("=== PRODUCTOS ESPECIALIZADOS ===");
        for (Producto producto : inventario) {
            producto.mostrarInformacion();
            System.out.println();
        }

        // actualizarStock se hereda sin reescribirlo en ninguna subclase.
        leche.actualizarStock(15);
        calculadora.actualizarStock(10);
        System.out.println("=== STOCK ACTUALIZADO POR HERENCIA ===");
        for (Producto producto : inventario) {
            producto.mostrarInformacion();
            System.out.println();
        }

        // Los métodos exclusivos se llaman mediante referencias especializadas.
        System.out.println("=== CONSULTAS EXCLUSIVAS ===");
        System.out.println("Fecha de consulta: " + consulta);
        System.out.println("Leche caducada: " + leche.estaCaducado(consulta));
        System.out.println("Garantía vigente: " + calculadora.garantiaVigente(
                LocalDate.of(2026, 1, 10), consulta));
        System.out.println("Leche caducada el 2026-11-16: "
                + leche.estaCaducado(LocalDate.of(2026, 11, 16)));
        System.out.println("Garantía vigente el 2028-01-11: "
                + calculadora.garantiaVigente(LocalDate.of(2026, 1, 10),
                                             LocalDate.of(2028, 1, 11)));
    }
}
