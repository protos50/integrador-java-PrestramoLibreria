package biblioteca;


/**
 * Write a description of class Prestamo here.
 * 
 * @author (your name) 
 * @version (a versión number or a date)
 */
import java.util.*;

public class Prestamo {
    private Calendar fechaRetiro;
    private Calendar fechaDevolucion;
    private Socio socio;
    private Libro libro;

    public Prestamo(Calendar p_fechaRetiro, Socio p_socio, Libro p_libro) {
        this.setFechaRetiro(p_fechaRetiro);
        this.setSocio(p_socio);
        this.setLibro(p_libro);
        this.setFechaDevolucion(null);
    }

    private void setFechaRetiro(Calendar p_fechaRetiro) {
        this.fechaRetiro = p_fechaRetiro;
    }

    public Calendar getFechaRetiro() {
        return this.fechaRetiro;
    }

    private void setFechaDevolucion(Calendar p_fechaDevolucion) {
        this.fechaDevolucion = p_fechaDevolucion;

    }

    public Calendar getFechaDevolucion() {

        return this.fechaDevolucion;
    }

    private void setSocio(Socio p_socio) {
        this.socio = p_socio;
    }

    public Socio getSocio() {
        return this.socio;
    }

    private void setLibro(Libro p_libro) {
        this.libro = p_libro;
    }

    public Libro getLibro() {
        return this.libro;
    }

    public void registrarFechaDevolucion(Calendar p_fechaDevolucion) {

        this.setFechaDevolucion(p_fechaDevolucion);
    }

    /**
     * Muestra todos los vencidos hasta la fecha, sin importar si fueron devueltos o
     * no.
     */
    public boolean vencido(Calendar p_fecha) {
        Calendar fechaVencimiento = Calendar.getInstance();
        fechaVencimiento.set(this.getFechaRetiro().get(Calendar.YEAR), this.getFechaRetiro().get(Calendar.MONTH),
                this.getFechaRetiro().get(Calendar.DATE));
        fechaVencimiento.add(fechaVencimiento.DATE, this.getSocio().getDiasPrestamo());

        if (p_fecha.after(fechaVencimiento)) {
            return true;
        } else {
            return false;
        }

    }

    public String toString() {
        String retiro = this.getFechaRetiro().get(Calendar.DATE) + "/"
                + ((this.getFechaRetiro().get(Calendar.MONTH)) + 1) + "/" + this.getFechaRetiro().get(Calendar.YEAR);
        String devolucion = " ";
        if (this.getFechaDevolucion() == null) {
            devolucion = "-";
        } else {
            devolucion = this.getFechaDevolucion().get(this.getFechaDevolucion().DATE) + "/"
                    + (this.getFechaDevolucion().get(this.getFechaDevolucion().MONTH) + 1) + "/"
                    + this.getFechaDevolucion().get(this.getFechaDevolucion().YEAR);
        }

        String libro = this.getLibro().getTitulo();
        String socio = this.getSocio().getNombre();
        return "Retiro: " + retiro + " - Devolucion: " + devolucion + "\n Libro: " + libro + "\n Socio: " + socio;
    }

}
