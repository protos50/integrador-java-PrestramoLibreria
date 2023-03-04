package biblioteca;

import java.util.*;

/**
 * Estudiante que sobreescribe puedePedir() porque le agrega la condición del
 * límite de 3 libros.
 * 
 * @author (Micaela Soto)
 * @version (12/11/2022)
 */
public class Estudiante extends Socio {
    private String carrera;

    public Estudiante(int p_dniSocio, String p_nombre, String p_carrera) {
        super(p_dniSocio, p_nombre, 20);
        this.setCarrera(p_carrera);
    }

    private void setCarrera(String p_carrera) {
        this.carrera = p_carrera;
    }

    public String getCarrera() {
        return this.carrera;
    }

    /**
     * Puede pedir prestado un libro sólo si no tiene más de 3 libros en su poder y
     * si no tiene ningún libro vencido (20 días después de la fecha de retiro).
     */
    public boolean puedePedir() {
        boolean puede = false;
        if (this.cantLibrosPrestados() < 3 && super.puedePedir() == true) {
            puede = true;
        }
        return puede;
    }

    public String soyDeLaClase() {
        return "Estudiante";
    }

}
