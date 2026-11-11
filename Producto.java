import java.util.Locale;

/**
 * Clase base de la Unidad 2. Centraliza el estado y las reglas comunes.
 * Los campos privados se consultan con getters, sin duplicarlos en las hijas.
 */
public class Producto {
    private final String nombre;
    private final double precio;
    private int stock;

    /**
     * Inicializa el estado común de cualquier producto.
     * @param nombre nombre no vacío
     * @param precio precio finito y no negativo por unidad
     * @param stock existencias iniciales no negativas
     * @throws IllegalArgumentException si algún dato es inválido
     */
    public Producto(String nombre, double precio, int stock) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (!Double.isFinite(precio) || precio < 0 || stock < 0) {
            throw new IllegalArgumentException("Precio y stock deben ser válidos y no negativos.");
        }
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    /** @return nombre del producto */
    public String getNombre() { return nombre; }
    /** @return precio por unidad */
    public double getPrecio() { return precio; }
    /** @return cantidad disponible */
    public int getStock() { return stock; }

    /** Imprime los datos comunes; las subclases amplían este método. */
    public void mostrarInformacion() {
        System.out.println("Nombre: " + nombre);
        System.out.printf(Locale.ROOT, "Precio: $%.2f%n", precio);
        System.out.println("Stock: " + stock + " unidades");
    }

    /**
     * Sustituye el total de existencias. Las hijas heredan esta implementación.
     * @param nuevoStock nuevo total no negativo, no una cantidad a sumar
     * @throws IllegalArgumentException si el nuevo total es negativo
     */
    public void actualizarStock(int nuevoStock) {
        if (nuevoStock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo.");
        }
        this.stock = nuevoStock;
    }

    /**
     * Conserva la demostración de la Unidad 2 como regresión funcional.
     * @param args argumentos no utilizados
     */
    public static void main(String[] args) {
        Producto producto1 = new Producto("Cuaderno", 35.50, 20);
        Producto producto2 = new Producto("Bolígrafo", 12.00, 50);
        System.out.println("=== PRODUCTOS INICIALES ===");
        producto1.mostrarInformacion();
        producto2.mostrarInformacion();
        producto1.actualizarStock(15);
        producto2.actualizarStock(60);
        System.out.println("=== PRODUCTOS ACTUALIZADOS ===");
        producto1.mostrarInformacion();
        producto2.mostrarInformacion();
    }
}
