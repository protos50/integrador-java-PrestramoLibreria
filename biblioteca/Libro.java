package biblioteca;

import java.util.*;

/**
 * Libro con listado de préstamos en la historia, que informa el estado actual
 * (prestado/no prestado).
 * 
 * @author (Micaela Soto)
 * @version (12/11/2022)
 */
public class Libro {
    private String titulo;
    private int edicion;
    private String editorial;
    private int anio;
    private ArrayList<Prestamo> prestamos;

    /**
     * Constructor que inicializa el objeto sin préstamos.
     */
    public Libro(String p_titulo, int p_edicion, String p_editorial, int p_anio) {
        this.setTitulo(p_titulo);
        this.setEdicion(p_edicion);
        this.setEditorial(p_editorial);
        this.setAnio(p_anio);
        this.setPrestamos(new ArrayList());
    }

    /**
     * Constructor que recibe el ArrayList de préstamos
     */
    public Libro(String p_titulo, int p_edicion, String p_editorial, int p_anio, ArrayList<Prestamo> p_prestamos) {
        this.setTitulo(p_titulo);
        this.setEdicion(p_edicion);
        this.setEditorial(p_editorial);
        this.setAnio(p_anio);
        this.setPrestamos(p_prestamos);
    }

    /**
     * Constructor que recibe objeto de tipo Préstamo.
     */
    public Libro(String p_titulo, int p_edicion, String p_editorial, int p_anio, Prestamo p_prestamo) {
        this.setTitulo(p_titulo);
        this.setEdicion(p_edicion);
        this.setEditorial(p_editorial);
        this.setAnio(p_anio);
        this.setPrestamos(new ArrayList());
        this.addPrestamo(p_prestamo);
    }

    private void setTitulo(String p_titulo) {
        this.titulo = p_titulo;
    }

    private void setEdicion(int p_edicion) {
        this.edicion = p_edicion;
    }

    private void setEditorial(String p_editorial) {
        this.editorial = p_editorial;
    }

    private void setAnio(int p_anio) {
        this.anio = p_anio;
    }

    private void setPrestamos(ArrayList<Prestamo> p_prestamos) {
        this.prestamos = p_prestamos;
    }

    public void addPrestamo(Prestamo p_prestamo) {
        this.getPrestamos().add(p_prestamo);
    }

    public void removePrestamo(Prestamo p_prestamo) {
        if ((this.getPrestamos().isEmpty())) {
            System.out.println("ERROR - No hay préstamos");
        } else {
            this.getPrestamos().remove(p_prestamo);
        }

    }

    public String getTitulo() {
        return this.titulo;
    }

    public int getEdicion() {
        return this.edicion;
    }

    public String getEditorial() {
        return this.editorial;
    }

    public int getAnio() {
        return this.anio;
    }

    public ArrayList<Prestamo> getPrestamos() {
        return this.prestamos;
    }

    /**
     * Devuelve el último Préstamo de la lista
     */
    public Prestamo getPrestamo() {
        // Si la lista no tiene préstamos, devuelve null.
        if (this.getPrestamos().size() == 0) {
            return null;
        } else {
            return (this.getPrestamos()).get(((this.getPrestamos()).size() - 1));
        }

    }

    /**
     * Al pedir un préstamo, se crea solo con fecha de retiro y cuando se devuelve
     * el libro, en el préstamo se indica la fecha de devolución.
     * Entonces, está prestado cuando fechaDevolución es igual a null.
     */
    public boolean prestado() {
        boolean prestado = false;
        if (this.getPrestamos().size() != 0) {
            if (this.getPrestamo().getFechaDevolucion() == null) {
                prestado = true;
            }
        }

        return prestado;
    }

    public String toString() {
        return "Título: " + this.getTitulo();
    }

}
