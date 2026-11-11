import java.time.LocalDate;

/** Producto especializado con fecha de caducidad, derivado de Producto. */
public class ProductoPerecedero extends Producto {
    private final LocalDate fechaCaducidad;

    /**
     * Reutiliza el constructor de Producto mediante super.
     * @param nombre nombre del producto
     * @param precio precio por unidad
     * @param stock existencias iniciales
     * @param fechaCaducidad último día válido de consumo en esta demostración
     */
    public ProductoPerecedero(String nombre, double precio, int stock,
                             LocalDate fechaCaducidad) {
        super(nombre, precio, stock);
        if (fechaCaducidad == null) {
            throw new IllegalArgumentException("La fecha de caducidad es obligatoria.");
        }
        this.fechaCaducidad = fechaCaducidad;
    }

    /** @return fecha de caducidad específica de esta subclase */
    public LocalDate getFechaCaducidad() { return fechaCaducidad; }

    /**
     * Método exclusivo: consulta la caducidad sin modificar el stock.
     * @param fechaConsulta fecha explícita para obtener resultados reproducibles
     * @return true si la consulta es posterior a la caducidad
     */
    public boolean estaCaducado(LocalDate fechaConsulta) {
        if (fechaConsulta == null) {
            throw new IllegalArgumentException("La fecha de consulta es obligatoria.");
        }
        return fechaConsulta.isAfter(fechaCaducidad);
    }

    /** Sobrescribe la impresión y reutiliza la parte común con super. */
    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Caducidad: " + fechaCaducidad);
    }
}
