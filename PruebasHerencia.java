import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

/** Pruebas de comportamiento sin dependencias ni activación de assert. */
public class PruebasHerencia {
    private static int total;

    private static void verificar(String caso, boolean condicion) {
        if (!condicion) { throw new AssertionError("FALLO: " + caso); }
        total++;
        System.out.println("OK " + total + " - " + caso);
    }

    private static void rechaza(String caso, Runnable operacion) {
        try {
            operacion.run();
        } catch (IllegalArgumentException esperada) {
            verificar(caso, true);
            return;
        }
        throw new AssertionError("FALLO: no rechazó " + caso);
    }

    private static String capturar(Producto producto) {
        PrintStream original = System.out;
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        try (PrintStream temporal = new PrintStream(bytes, true, StandardCharsets.UTF_8)) {
            System.setOut(temporal);
            producto.mostrarInformacion();
        } finally {
            System.setOut(original);
        }
        return bytes.toString(StandardCharsets.UTF_8);
    }

    /** @param args argumentos no utilizados */
    public static void main(String[] args) {
        Producto cuaderno = new Producto("Cuaderno", 35.50, 20);
        Producto boligrafo = new Producto("Bolígrafo", 12, 50);
        verificar("Unidad 2 conserva nombre y precio", cuaderno.getNombre().equals("Cuaderno")
                && cuaderno.getPrecio() == 35.50);
        cuaderno.actualizarStock(15);
        verificar("Unidad 2 actualiza cuaderno a 15", cuaderno.getStock() == 15);
        verificar("Los objetos tienen estado independiente", boligrafo.getStock() == 50);
        boligrafo.actualizarStock(60);
        verificar("Unidad 2 actualiza bolígrafo a 60", boligrafo.getStock() == 60);
        verificar("Acepta precio y stock cero", new Producto("Muestra", 0, 0).getStock() == 0);
        rechaza("Precio negativo", () -> new Producto("X", -1, 1));
        rechaza("Stock inicial negativo", () -> new Producto("X", 1, -1));
        rechaza("Nombre nulo", () -> new Producto(null, 1, 1));
        rechaza("Nombre vacío", () -> new Producto("  ", 1, 1));
        rechaza("Precio NaN", () -> new Producto("X", Double.NaN, 1));
        rechaza("Precio infinito", () -> new Producto("X", Double.POSITIVE_INFINITY, 1));

        LocalDate caduca = LocalDate.of(2026, 11, 15);
        ProductoPerecedero leche = new ProductoPerecedero("Leche", 28.50, 20, caduca);
        ProductoElectronico equipo = new ProductoElectronico("Calculadora", 350, 8, 24);
        verificar("Perecedero es un Producto", leche instanceof Producto);
        verificar("Electrónico es un Producto", equipo instanceof Producto);
        verificar("Constructor super inicializa perecedero", leche.getNombre().equals("Leche")
                && leche.getPrecio() == 28.50 && leche.getStock() == 20);
        verificar("Constructor super inicializa electrónico", equipo.getNombre().equals("Calculadora")
                && equipo.getPrecio() == 350 && equipo.getStock() == 8);
        leche.actualizarStock(15);
        equipo.actualizarStock(10);
        verificar("Perecedero reutiliza actualizarStock", leche.getStock() == 15);
        verificar("Electrónico reutiliza actualizarStock", equipo.getStock() == 10);
        rechaza("Validación heredada de stock", () -> leche.actualizarStock(-1));
        verificar("Rechazo conserva estado anterior", leche.getStock() == 15);
        leche.actualizarStock(0);
        verificar("Método heredado admite cero", leche.getStock() == 0);
        verificar("Subclase conserva atributo propio", leche.getFechaCaducidad().equals(caduca));
        verificar("Antes de caducar", !leche.estaCaducado(caduca.minusDays(1)));
        verificar("Día de caducidad incluido", !leche.estaCaducado(caduca));
        verificar("Día posterior caducado", leche.estaCaducado(caduca.plusDays(1)));
        rechaza("Caducidad nula", () -> new ProductoPerecedero("X", 1, 1, null));
        rechaza("Consulta de caducidad nula", () -> leche.estaCaducado(null));
        rechaza("Subclase aplica validación de precio base",
                () -> new ProductoPerecedero("X", -1, 1, caduca));
        LocalDate compra = LocalDate.of(2026, 1, 10);
        verificar("Meses propios del electrónico", equipo.getMesesGarantia() == 24);
        verificar("Garantía inicia en compra", equipo.garantiaVigente(compra, compra));
        verificar("Garantía antes de compra falsa", !equipo.garantiaVigente(compra, compra.minusDays(1)));
        verificar("Último día de garantía incluido", equipo.garantiaVigente(compra, compra.plusMonths(24)));
        verificar("Garantía vencida", !equipo.garantiaVigente(compra, compra.plusMonths(24).plusDays(1)));
        verificar("Cero meses sin garantía", !new ProductoElectronico("X", 1, 1, 0)
                .garantiaVigente(compra, compra));
        rechaza("Garantía negativa", () -> new ProductoElectronico("X", 1, 1, -1));
        rechaza("Compra nula", () -> equipo.garantiaVigente(null, compra));
        rechaza("Consulta de garantía nula", () -> equipo.garantiaVigente(compra, null));
        Producto referenciaBase = leche;
        String salida = capturar(referenciaBase);
        verificar("Despacho dinámico y super en perecedero", salida.contains("Nombre: Leche")
                && salida.contains("Precio: $28.50") && salida.contains("Caducidad: 2026-11-15"));
        referenciaBase = equipo;
        salida = capturar(referenciaBase);
        verificar("Despacho dinámico y super en electrónico", salida.contains("Stock: 10 unidades")
                && salida.contains("Garantía: 24 meses"));
        System.out.println("RESULTADO: " + total + " comprobaciones aprobadas; 0 fallos.");
    }
}
