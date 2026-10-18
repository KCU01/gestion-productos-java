/**
 * Representa un producto de una tienda.
 * Cada objeto tiene su propio nombre, precio y cantidad disponible.
 */
public class Producto {

    // Nombre que identifica al producto.
    private String nombre;

    // Precio de venta por unidad.
    private double precio;

    // Cantidad de unidades disponibles en la tienda.
    private int stock;

    /**
     * Constructor: inicializa los atributos al crear un producto.
     * La palabra "this" distingue los atributos de los parámetros.
     *
     * @param nombre nombre del producto.
     * @param precio precio por unidad; debe ser mayor o igual a cero.
     * @param stock cantidad inicial; debe ser mayor o igual a cero.
     */
    public Producto(String nombre, double precio, int stock) {
        if (precio < 0 || stock < 0) {
            throw new IllegalArgumentException(
                "El precio y el stock no pueden ser negativos."
            );
        }

        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    /**
     * Muestra en la consola el nombre, precio y stock del producto.
     * El formato %.2f presenta el precio con dos decimales.
     */
    public void mostrarInformacion() {
        System.out.println("Nombre: " + nombre);
        System.out.printf("Precio: $%.2f%n", precio);
        System.out.println("Stock: " + stock + " unidades");
        System.out.println();
    }

    /**
     * Reemplaza el stock actual por la cantidad indicada.
     * Por ejemplo, actualizarStock(15) establece el stock en 15 unidades.
     *
     * @param nuevoStock nueva cantidad disponible; no puede ser negativa.
     */
    public void actualizarStock(int nuevoStock) {
        if (nuevoStock < 0) {
            throw new IllegalArgumentException(
                "El stock no puede ser negativo."
            );
        }

        this.stock = nuevoStock;
    }

    /**
     * Punto de entrada del programa.
     * Crea dos productos y utiliza sus métodos para mostrar y actualizar
     * sus datos. La segunda impresión permite verificar los cambios.
     *
     * @param args argumentos de consola; no se utilizan en este ejemplo.
     */
    public static void main(String[] args) {
        // El constructor inicializa cada objeto con datos diferentes.
        Producto producto1 = new Producto("Cuaderno", 35.50, 20);
        Producto producto2 = new Producto("Bolígrafo", 12.00, 50);

        // Se consulta la información inicial de ambos productos.
        System.out.println("=== PRODUCTOS INICIALES ===");
        producto1.mostrarInformacion();
        producto2.mostrarInformacion();

        // Se establece una nueva cantidad de stock para cada producto.
        producto1.actualizarStock(15);
        producto2.actualizarStock(60);

        // Se verifica que cada objeto conserva su stock actualizado.
        System.out.println("=== PRODUCTOS ACTUALIZADOS ===");
        producto1.mostrarInformacion();
        producto2.mostrarInformacion();
    }
}
