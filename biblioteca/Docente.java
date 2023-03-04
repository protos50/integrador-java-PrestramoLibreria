package biblioteca;

import java.util.*;

/**
 * Docente que retira libros, informa si es responsable y agrega días de
 * préstamos.
 * 
 * @author (Micaela Soto)
 * @version (12/11/2022)
 */
public class Docente extends Socio {
    private String area;

    public Docente(int p_dniSocio, String p_nombre, String p_area) {
        super(p_dniSocio, p_nombre, 5);
        this.setArea(p_area);
    }

    private void setArea(String p_area) {
        this.area = p_area;
    }

    public String getArea() {
        return this.area;
    }

    /**
     * Es responsable cuando nunca tuvo un préstamo vencido.
     */
    public boolean esResponsable() {
        boolean esResponsable = false;
        Calendar fechaHoy = Calendar.getInstance();

        // si la lista no está vacía y actualmente no tiene préstamos vencidos
        // recorre el listado de préstamos anteriores, a ver si alguno estaba vencido
        if ((getPrestamos().size() > 0) && (this.puedePedir() == true)) {
            for (Prestamo unP : this.getPrestamos()) {
                if (unP.vencido(fechaHoy) == false) {
                    esResponsable = true;
                } else {
                    esResponsable = false;
                }
            }

        }

        return esResponsable;
    }

    public String soyDeLaClase() {
        return "Docente";
    }

    /**
     * Adiciona días de préstamo si es responsable
     */
    public void agregarDiasDePrestamos(int p_dias) {
        if (this.esResponsable() == true) {
            this.setDiasPrestamo(this.getDiasPrestamo() + p_dias);
            System.out.println("\n\n***Se agregaron los dias de prestamo al docente***\n\n");

        } else {
            System.out.println("\n\n***ERROR - No se pueden agregar días porque no es responsable***\n\n");

        }
    }
}
