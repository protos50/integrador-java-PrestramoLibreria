package biblioteca;


/**
 * Write a description of class Socio here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public abstract class Socio {
    private int dniSocio;
    private String nombre;
    private int diasPrestamo;
    private ArrayList<Prestamo> prestamos;

    public Socio(int p_dniSocio, String p_nombre, int p_diasPrestamo, ArrayList<Prestamo> p_prestamos) {
        this.setDniSocio(p_dniSocio);
        this.setNombre(p_nombre);
        this.setDiasPrestamo(p_diasPrestamo);
        this.setPrestamos(p_prestamos);
    }

    public Socio(int p_dniSocio, String p_nombre, int p_diasPrestamo, Prestamo p_prestamo) {
        this.setDniSocio(p_dniSocio);
        this.setNombre(p_nombre);
        this.setDiasPrestamo(p_diasPrestamo);
        this.setPrestamos(new ArrayList<Prestamo>());
        this.addPrestamo(p_prestamo);
    }

    public Socio(int p_dniSocio, String p_nombre, int p_diasPrestamo) {
        this.setDniSocio(p_dniSocio);
        this.setNombre(p_nombre);
        this.setDiasPrestamo(p_diasPrestamo);
        this.setPrestamos(new ArrayList<Prestamo>());

    }

    private void setDniSocio(int p_dniSocio) {
        this.dniSocio = p_dniSocio;
    }

    public int getDniSocio() {
        return this.dniSocio;
    }

    private void setNombre(String p_nombre) {
        this.nombre = p_nombre;
    }

    public String getNombre() {
        return this.nombre;
    }

    protected void setDiasPrestamo(int p_diasPrestamo) {
        this.diasPrestamo = p_diasPrestamo;
    }

    public int getDiasPrestamo() {
        return this.diasPrestamo;
    }

    private void setPrestamos(ArrayList<Prestamo> p_prestamos) {
        this.prestamos = p_prestamos;
    }

    public ArrayList<Prestamo> getPrestamos() {
        return this.prestamos;
    }

    public boolean addPrestamo(Prestamo p_prestamo) {
        return this.getPrestamos().add(p_prestamo);
    }

    public boolean removePrestamo(Prestamo p_prestamo) {
        return this.getPrestamos().remove(p_prestamo);
    }

    public int cantLibrosPrestados() {
        int cantidad = 0;
        for (Prestamo prest : this.getPrestamos()) {
            if (prest.getFechaDevolucion() == null) {
                cantidad = cantidad + 1;
            }
        }
        return cantidad;
    }

    /**
     * Puede pedir cuando no tenga un préstamo vigente vencido.
     */
    public boolean puedePedir() {
        boolean aux = true;
        Calendar hoy = new GregorianCalendar();
        for (Prestamo prest : this.getPrestamos()) {
            if (prest.vencido(hoy) && (prest.getFechaDevolucion() == null)) {
                aux = false;
                break;
            }

        }

        return aux;
    }

    public String toString() {
        return "D.N.I: " + this.getDniSocio() + "  || " + this.getNombre() + " (" + this.soyDeLaClase()
                + ") || Libros Prestados: " + this.cantLibrosPrestados();
    }

    public abstract String soyDeLaClase();
}
