import java.time.LocalDate;

/** Producto especializado con garantía, derivado de Producto. */
public class ProductoElectronico extends Producto {
    private final int mesesGarantia;

    /**
     * Delega la validación común en el constructor de la clase base.
     * @param nombre nombre del producto
     * @param precio precio por unidad
     * @param stock existencias iniciales
     * @param mesesGarantia meses de cobertura; cero significa sin garantía
     */
    public ProductoElectronico(String nombre, double precio, int stock,
                              int mesesGarantia) {
        super(nombre, precio, stock);
        if (mesesGarantia < 0) {
            throw new IllegalArgumentException("La garantía no puede ser negativa.");
        }
        this.mesesGarantia = mesesGarantia;
    }

    /** @return meses de garantía específicos de esta subclase */
    public int getMesesGarantia() { return mesesGarantia; }

    /**
     * Método exclusivo: evalúa una cobertura ilustrativa por meses calendario.
     * Incluye la compra y el último día; no representa condiciones legales.
     * @param fechaCompra inicio de la cobertura
     * @param fechaConsulta día que se desea evaluar
     * @return true si hay meses contratados y la consulta cae en el intervalo
     */
    public boolean garantiaVigente(LocalDate fechaCompra, LocalDate fechaConsulta) {
        if (fechaCompra == null || fechaConsulta == null) {
            throw new IllegalArgumentException("Las fechas son obligatorias.");
        }
        return mesesGarantia > 0
                && !fechaConsulta.isBefore(fechaCompra)
                && !fechaConsulta.isAfter(fechaCompra.plusMonths(mesesGarantia));
    }

    /** Sobrescribe la impresión y llama al método original de Producto. */
    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Garantía: " + mesesGarantia + " meses");
    }
}
