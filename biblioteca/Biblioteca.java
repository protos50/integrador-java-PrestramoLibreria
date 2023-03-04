package biblioteca;

import java.util.*;

public class Biblioteca {
    private String nombre;
    private ArrayList<Libro> libros;
    private HashMap<Integer, Socio> socios;

    public Biblioteca(String p_nombre) {
        this.setNombre(p_nombre);
        this.setLibros(new ArrayList<Libro>());
        this.setSocios(new HashMap<Integer, Socio>());
    }

    public Biblioteca(String p_nombre, ArrayList<Libro> p_libros, HashMap<Integer, Socio> p_socios) {
        this.setNombre(p_nombre);
        this.setLibros(p_libros);
        this.setSocios(p_socios);
    }

    private void setNombre(String p_nombre) {
        this.nombre = p_nombre;
    }

    public String getNombre() {
        return this.nombre;
    }

    private void setLibros(ArrayList<Libro> p_libros) {
        this.libros = p_libros;
    }

    public ArrayList<Libro> getLibros() {
        return this.libros;
    }

    private void setSocios(HashMap<Integer, Socio> p_socios) {
        this.socios = p_socios;
    }

    public HashMap<Integer, Socio> getSocios() {
        return this.socios;
    }

    public void addSocio(Socio p_socio) {
        this.getSocios().put(new Integer(p_socio.getDniSocio()), p_socio);
    }

    public void removeSocio(Socio p_socio) {
        boolean tienePrestado = false;
        for (Prestamo pres : p_socio.getPrestamos()) {
            if (pres.getLibro().prestado()) {
                tienePrestado = true;
            }
        }
        if (!tienePrestado) {

            this.getSocios().remove(p_socio.getDniSocio());
            System.out.println("\n***Se ha removido al socio***\n *" + p_socio.toString() + "*");
        } else {
            System.out
                    .println("***" + p_socio.getNombre() + " debe devolver el/los siguiente/s libros primero!***\n\n");
            for (Prestamo prest : this.buscarSocio(p_socio.getDniSocio()).getPrestamos()) {
                if (prest.getLibro().prestado()) {
                    System.out.println(prest.getLibro().toString());
                }
            }
        }
        System.out.println("\n\n\n");
    }

    private boolean addLibro(Libro p_libro) {
        return this.getLibros().add(p_libro);
    }

    public boolean removeLibro(Libro p_libro) {
        boolean registrado = false;
        boolean estaPrestado = false;

        System.out.println("\nSe ha removido el libro!");
        return this.getLibros().remove(p_libro);

    }

    public void nuevoLibro(String p_titulo, int p_edicion, String p_editorial, int p_anio) {
        boolean registrado = false;
        for (Libro libro : this.getLibros()) {
            if (libro.getTitulo().equals(p_titulo) && libro.getEdicion() == p_edicion
                    && libro.getEditorial().equals(p_editorial) && libro.getAnio() == p_anio) {
                registrado = true;
                System.out.println("\nEste libro ya esta registrado!");
            }
        }
        if (registrado == false) {
            Libro libroNuevo = new Libro(p_titulo, p_edicion, p_editorial, p_anio);
            this.addLibro(libroNuevo);
        }
    }

    public void nuevoSocioEstudiante(int p_dniSocio, String p_nombre, String p_carrera) {
        Estudiante socioNuevo = new Estudiante(p_dniSocio, p_nombre, p_carrera);
        this.addSocio(socioNuevo);
    }

    public void nuevoSocioDocente(int p_dniSocio, String p_nombre, String p_area) {
        Docente socioNuevo = new Docente(p_dniSocio, p_nombre, p_area);
        this.addSocio(socioNuevo);
    }

    public int cantidadSociosPorTipo(String p_objeto) {
        int contador = 0;

        for (Map.Entry<Integer, Socio> socio : this.getSocios().entrySet()) {
            if (socio.getValue().soyDeLaClase().equals(p_objeto)) {
                contador += 1;
            }
        }
        return contador;
    }

    public boolean prestarLibro(Calendar p_fechaRetiro, Socio p_socio, Libro p_libro) {
        boolean sePresto = false;
        boolean registrado = false;

        if (p_socio.puedePedir()) { // solo se puede prestar libros a aquellos que lo tienen permitido
            Prestamo libroPrestado = new Prestamo(p_fechaRetiro, p_socio, p_libro);
            p_libro.addPrestamo(libroPrestado);
            p_socio.addPrestamo(libroPrestado);
            sePresto = true;

            System.out.println(
                    "\n***El libro se presto a " + p_socio.soyDeLaClase() + " - " + p_socio.getNombre() + " ***");
        } else {
            // el socio no tiene permitido retirar libros. sePresto se mantiene false
            System.out.println("\n***El socio: " + p_socio.soyDeLaClase() + " - " + p_socio.getNombre()
                    + " no tiene permitido pedir un libro");
        }

        return sePresto;
    }

    public void devolverLibro(Libro p_libro) {
        Calendar fechaActual = new GregorianCalendar();

        if (p_libro.prestado()) {
            p_libro.getPrestamo().registrarFechaDevolucion(fechaActual);
        } else {
            System.out.println("El libro " + p_libro.getTitulo() + " NO fué prestado a nadie");
        }
    }

    public ArrayList<Prestamo> prestamosVencidos() {
        Calendar fechaActual = new GregorianCalendar();
        ArrayList<Prestamo> listaPrestamosVencidos = new ArrayList<Prestamo>();
        // se recorre el hashmap de dni,socios obteniendose un socio por cada iteracion
        for (Map.Entry<Integer, Socio> socio : this.getSocios().entrySet()) {

            // se recorre la lista de prestamos de un socio
            for (int i = 0; i < socio.getValue().getPrestamos().size(); i++) {
                Prestamo prestamo = socio.getValue().getPrestamos().get(i);

                // si un prestamos está vencido se agrega a la lista de prestamos vencidos
                if (prestamo.vencido(fechaActual)) {
                    listaPrestamosVencidos.add(prestamo);
                }
            }
        }
        return listaPrestamosVencidos;
    }

    public ArrayList<Docente> docentesResponsables() {
        ArrayList<Docente> listaDocentesResponsables = new ArrayList<Docente>();

        for (Map.Entry<Integer, Socio> socio : this.getSocios().entrySet()) {

            if (socio.getValue().soyDeLaClase().equals("Docente")) {
                Docente docente = (Docente) socio.getValue();
                if (docente.esResponsable()) {
                    listaDocentesResponsables.add(docente);
                }
            }
        }
        return listaDocentesResponsables;
    }

    public String quienTieneElLibro(Libro p_libro) {
        String nombre;
        boolean registrado = false;

        for (Libro libro : this.getLibros()) {
            if (libro.getTitulo().equals(p_libro.getTitulo()) && libro.getEdicion() == p_libro.getEdicion()
                    && libro.getEditorial().equals(p_libro.getEditorial()) && libro.getAnio() == p_libro.getAnio()) {
                registrado = true;
            }
        }
        if (p_libro.prestado()) {
            nombre = p_libro.getPrestamo().getSocio().getNombre();
        } else if (registrado == true) {
            nombre = "El libro se encuentra en la biblioteca";
        } else {
            nombre = "El libro no se encuentra registrado en la biblioteca";
        }

        return nombre;
    }

    public String listaDeSocios() {
        String detallesSocios;

        if (getSocios().isEmpty()) {
            detallesSocios = "\n\tLista de socios vacia\n";
        } else {
            int indice = 1;
            detallesSocios = "\n\tLista de Socios:\n\n";

            for (Map.Entry<Integer, Socio> socio : this.getSocios().entrySet()) {
                detallesSocios += indice + ") " + socio.getValue().toString() + "\n";
                indice += 1;
            }

            detallesSocios += "\n**********************************";
            detallesSocios += "\nCant. Socios de tipo Estudiante:" + this.cantidadSociosPorTipo("Estudiante");
            detallesSocios += "\nCant. Socios de tipo Docente:" + this.cantidadSociosPorTipo("Docente");
            detallesSocios += "\n**********************************";
        }
        return detallesSocios;
    }

    public String listaDeLibros() {
        String detallesLibros;
        int indice = 1;
        String prestado = "";
        detallesLibros = "\n***Lista de libros***\n\n";

        for (Libro libro : this.getLibros()) {

            if (libro.prestado()) {
                prestado = "Si";
            } else {
                prestado = "No";
            }

            detallesLibros += indice + ") " + libro.toString() + "         Prestado: (" + prestado + ")" + "\n";
            indice += 1;
        }

        return detallesLibros;
    }

    public String listaDeDocentesResponsables() {
        String listaDocentesResponsables = "";

        if (this.docentesResponsables().isEmpty()) {
            listaDocentesResponsables = "\n\tLista de docentes responsables vacia\n";
        } else {
            listaDocentesResponsables = "\n\tLista de docentes responsables:\n";

            for (Docente docente : this.docentesResponsables()) {
                listaDocentesResponsables += "*" + docente.toString() + "\n";
            }
        }
        return listaDocentesResponsables;
    }

    public Socio buscarSocio(int p_dni) {

        return this.getSocios().get(p_dni);
    }
}
