package biblioteca;


/**
 * Registra socios docentes y estudiantes, libros y gestiona los préstamos.
 * 
 * @author (Grupo 1 - Laboratorio 4) 
 * @version (18/11/2022)
 */

import java.util.*;

public class GestionBiblioteca {
    public static Art animation = new Art();
    private static Scanner input = new Scanner(System.in);
    private static int opcion;

    /**
     * Muestra animación, crea biblioteca y solicita ejecución del método que
     * muestra el menú.
     */
    public static void main(String Args[]) throws InterruptedException {
        animation.renderArt2();
        Biblioteca biblioteca1 = new Biblioteca("Biblioteca 1");
        menuPrincipal(biblioteca1);
        animation.renderArt();
    }

    /**
     * Instancia objetos libro, docente y estudiante y solicita la ejecución de los
     * submenús: socios, libros y préstamos.
     */
    public static void menuPrincipal(Biblioteca biblioteca1) {
        System.out.print("\u000C");
        // bandera: si es falsa (opción 4) finaliza la ejecución del programa
        boolean flag = true;
        boolean pudo = true;
        String opcion1 = "1";
        biblioteca1.nuevoLibro("Harry Potter", 1, "Bloomsbury", 1998);
        biblioteca1.nuevoSocioEstudiante(40, "Franco González", "Lic. Sistemas");
        Calendar fecha11 = new GregorianCalendar();
        fecha11.set(2022, 5, 1);
        biblioteca1.prestarLibro(fecha11, biblioteca1.getSocios().get(40), biblioteca1.getLibros().get(0));
        biblioteca1.nuevoLibro("Java", 1, "Tipler", 2000);
        biblioteca1.nuevoSocioDocente(100, "Marta Pérez", "Sistemas");
        biblioteca1.prestarLibro(new GregorianCalendar(2022, 10, 15), biblioteca1.getSocios().get(100),
        biblioteca1.getLibros().get(1));
        biblioteca1.nuevoLibro("POO", 1, "UNNE", 2008);
        biblioteca1.nuevoLibro("Quimica", 2, "Bloom", 2004);
        biblioteca1.nuevoLibro("Fisica", 1, "Mosca", 2011);
        System.out.print("\u000C");

        while (flag) {

            System.out.print("\u000C");
            System.out.println(animation.art3);
            System.out.println("                ##########################################");
            System.out.println("                ***            MENÚ PRINCIPAL          ***");
            System.out.println("                ******************************************");
            System.out.println("                ***        (1) Gestión socios          ***");
            System.out.println("                ******************************************");
            System.out.println("                ***        (2) Gestión libros          ***");
            System.out.println("                ******************************************");
            System.out.println("                ***        (3) Gestión préstamos       ***");
            System.out.println("                ******************************************");
            System.out.println("                ***        (4) Fin programa            ***");
            System.out.println("                ##########################################");

            switch (preguntar(1, 4)) {
                case 1:
                    System.out.print("\u000C");
                    gestionSocios(biblioteca1);
                    break;

                case 2:
                    System.out.print("\u000C");
                    gestionLibros(biblioteca1);
                    break;

                case 3:
                    System.out.print("\u000C");
                    gestionPrestamos(biblioteca1);
                    break;

                case 4:
                    System.out.print("\u000C");
                    System.out.println("                ##########################################");
                    System.out.println("                ###------------Fin programa------------###");
                    System.out.println("                ##########################################\n\n");

                    flag = false;
                    break;

            }
        }
    }

    public static int preguntar(int p_inicio, int p_final) {
        int opcion;
        int cont = 0;
        ;
        Scanner input = new Scanner(System.in);
        String valor;

        do {
            if (cont == 1) {
                System.out.println("\n\n***No es una opcion valida***\n\n");
            }
            valor = input.nextLine();
            try {
                opcion = Integer.parseInt(valor);
                if (!(opcion >= p_inicio && opcion <= p_final)) {
                    opcion = -1;
                    cont = cont + 1;
                }
            } catch (Exception e) {
                System.out.println("\n\nIngrese una opcion correcta: \n\n");
                opcion = -1;
                cont = cont + 1;

            }
        } while (opcion == -1);
        return opcion;
    }

    public static int preguntar2() {
        int opcion;
        int cont = 0;
        ;
        Scanner input = new Scanner(System.in);
        String valor;

        do {
            if (cont == 1) {
                System.out.println("\n\n***No es un numero valido***\n\n");
            }
            valor = input.nextLine();
            try {
                opcion = Integer.parseInt(valor);
                if (!(opcion >= 1)) {
                    opcion = -1;
                    cont = cont + 1;
                }
            } catch (Exception e) {
                System.out.println("\n\nIngrese una opcion correcta: \n\n");
                opcion = -1;
                cont = cont + 1;
            }
        } while (opcion == -1);
        return opcion;
    }

    /**
     * Alta y baja de socios, emite listado de todos los registrados con la cantidad
     * por tipo, muestra datos de cierto socio y los docentes responsables.
     */
    public static void gestionSocios(Biblioteca biblioteca1) {
        int dni;
        String opcion1 = "1";

        System.out.println(animation.art3);
        System.out.println("                *********************************************");
        System.out.println("                ****           GESTIÓN DE SOCIOS         ****");
        System.out.println("                *********************************************");
        System.out.println("                **        (1) Registrar nuevo socio        **");
        System.out.println("                **        (2) Dar de baja un socio         **");
        System.out.println("                **        (3) Ver todos los socios         **");
        System.out.println("                **        (4) Consultar datos de socio     **");
        System.out.println("                **        (5) Ver docentes responsables    **");
        System.out.println("                **        (6) Agregar días de préstamo     **");
        System.out.println("                **        (7) Volver atrás                 **");
        System.out.println("                *********************************************\n");
        System.out.println("                Presione ENTER para CONTINUAR");

        switch (preguntar(1, 7)) {

            case 1:
                String nomYApe;
                String dni1 = "1";

                System.out.print("\u000C");
                System.out.println("\nIntroduzca DNI:");

                dni = preguntar2();
                // Para dar de alta un socio, primero controla que el mismo no esté registrado,
                // con el número de DNI.
                if (biblioteca1.buscarSocio(dni) == null) {
                    System.out.println("Introduzca nombre y apellido del nuevo socio:");
                    nomYApe = input.nextLine();

                    System.out.println("Introduzca categoría del socio (1-Docente  2-Estudiante):");

                    opcion = preguntar(1, 2);
                    // 1) Opción docente
                    if (opcion == 1) {
                        String area;
                        System.out.println("Introduzca área del docente:");
                        area = input.nextLine();
                        biblioteca1.nuevoSocioDocente(dni, nomYApe, area);
                        System.out.println("\n\n***Docente registrado con éxito***\n");
                    }
                    // 2) Opción estudiante
                    if (opcion == 2) {
                        String carrera;
                        System.out.println("Introduzca la carrera cursada por el estudiante:");
                        carrera = input.nextLine();
                        biblioteca1.nuevoSocioEstudiante(dni, nomYApe, carrera);
                        System.out.println("\n\n***Estudiante registrado exitosamente***\n");
                    }

                    pressAnyKeyToContinue();
                    System.out.print("\u000C");
                    gestionSocios(biblioteca1);
                    break;
                }

                // Si el socio ya está registrado:
                else {
                    System.out.print(
                            "\n***El socio ya está registrado***\n" + biblioteca1.buscarSocio(dni).toString() + "\n\n");
                    pressAnyKeyToContinue();
                    System.out.print("\u000C");
                    gestionSocios(biblioteca1);
                    break;
                }

            case 2:
                int dni2;
                System.out.print("\u000C");
                System.out.println("Ingrese el DNI del socio a borrar:");
                dni = preguntar2();
                // Verifica que el socio a borrar se encuentre registrado, por nro. DNI
                if (biblioteca1.buscarSocio(dni) == null) {
                    System.out.print("\n\n***El socio no está registrado***\n\n");
                    pressAnyKeyToContinue();
                    System.out.print("\u000C");
                    gestionSocios(biblioteca1);
                    break;
                } else {
                    biblioteca1.removeSocio(biblioteca1.buscarSocio(dni));
                    pressAnyKeyToContinue();
                    System.out.print("\u000C");
                    gestionSocios(biblioteca1);
                    break;
                }

            case 3:
                // Muestra la lista de socios
                System.out.print("\u000C");
                System.out.println(biblioteca1.listaDeSocios());

                pressAnyKeyToContinue();
                System.out.print("\u000C");
                gestionSocios(biblioteca1);
                break;

            case 4:
                System.out.print("\u000C");
                System.out.println("Ingrese el DNI del socio a buscar:");
                dni = preguntar2();
                // Antes de mostrar los datos del socio, verifica si el mismo está registrado
                if (biblioteca1.buscarSocio(dni) != null) {
                    System.out.println(biblioteca1.buscarSocio(dni).toString() + "\n\n");
                } else {
                    System.out.println("\n\n***El socio D.N.I.: " + dni + " no está registrado***\n\n");
                }
                pressAnyKeyToContinue();
                System.out.print("\u000C");
                gestionSocios(biblioteca1);
                break;

            // Muestra docentes responsables (solo aquellos que hayan pedido al menos un
            // préstamo)
            case 5:
                System.out.print("\u000C");
                System.out.println(biblioteca1.listaDeDocentesResponsables());
                pressAnyKeyToContinue();
                System.out.print("\u000C");
                gestionSocios(biblioteca1);
                break;

            // agrega días de préstamo a un docente en particular
            case 6:
                System.out.print("\u000C");
                System.out.println("Ingrese el DNI del Docente a quien desea agregar días de préstamo:");
                dni = preguntar2();
                // Antes de mostrar los datos del socio, verifica si el mismo está registrado
                if (biblioteca1.buscarSocio(dni) != null) {
                    if (biblioteca1.buscarSocio(dni).soyDeLaClase().equals("Docente")) {
                        System.out.println("¿Cuántos días desea agregar?");
                        int dias = preguntar2();// Devuelve solo enteros positivos
                        // casteo
                        Docente docente1 = (Docente) biblioteca1.buscarSocio(dni);
                        docente1.agregarDiasDePrestamos(dias);

                    } else {
                        System.out.println("\n\n***El socio no es un docente***\n\n");
                    }
                } else {
                    System.out.println("\n\n***El socio D.N.I.: " + dni + " no está registrado***\n\n");
                }
                pressAnyKeyToContinue();
                System.out.print("\u000C");
                gestionSocios(biblioteca1);
                break;

            case 7:
                // Si desea salir del submenú socios, limpia la pantalla y vuelve al menú
                // principal
                System.out.print("\u000C");
                break;
        }
    }

    /**
     * Presta y devuelve libros, muestra los préstamos vencidos y el registro
     * histórico de préstamos de un socio y libro específicos.
     */
    public static void gestionPrestamos(Biblioteca biblioteca1) {
        input.useDelimiter("\n");
        System.out.println(animation.art3);
        System.out.println("                ***************************************************************");
        System.out.println("                ****                 GESTIÓN DE PRÉSTAMOS                  ****");
        System.out.println("                ***************************************************************");
        System.out.println("                *** (1) Prestar libro                                       ***");
        System.out.println("                *** (2) Devolver libro                                      ***");
        System.out.println("                *** (3) Mostrar libros prestados y no prestados             ***");
        System.out.println("                *** (4) Mostrar préstamos vencidos y no devueltos           ***");
        System.out.println("                *** (5) Mostrar todos los préstamos vencidos hasta la fecha ***");
        System.out.println("                *** (6) Ver registro histórico de préstamos de un socio     ***");
        System.out.println("                *** (7) Ver registro histórico de préstamos de un libro     ***");
        System.out.println("                *** (8) Volver atrás                                        ***");
        System.out.println("                ***************************************************************\n");

        System.out.println("                Presione ENTER para CONTINUAR");

        switch (preguntar(1, 8)) {
            /**
             * Antes de prestar, verifica si: el socio está registrado y puede pedir y si el
             * libro está listado y no prestado.
             */
            case 1: {
                System.out.print("\u000C");

                Calendar fechaHoy = Calendar.getInstance();
                int index = 0;
                boolean registrado = false;
                System.out.println("Ingrese el DNI del socio que solicita el préstamo:");
                int dni = preguntar2();
                // Antes de ingresar los datos del libro a prestar, verifica si a) el
                // solicitante es socio y b) puede pedir.
                if (biblioteca1.buscarSocio(dni) != null) {

                    if (biblioteca1.buscarSocio(dni).puedePedir()) {
                        // Muestra los datos de los libros registrados para que el usuario sepa que
                        // datos ingresar.
                        for (Libro libro : biblioteca1.getLibros()) {
                            System.out.println(" -Título: " + libro.getTitulo() + "   -Edición: " + libro.getEdicion()
                                    + "   -Editorial: " + libro.getEditorial() +
                                    "   -Año: " + libro.getAnio());
                        }
                        // Solicita al usuario los datos del libro a prestar
                        System.out.println("\nIngrese el título del libro: ");
                        String titulo = input.next();
                        System.out.println("Ingrese la edición del libro: ");
                        int edicion = preguntar2();
                        System.out.println("Ingrese la editorial del libro: ");
                        String editorial = input.next();
                        System.out.println("Ingrese el año: ");
                        int año = preguntar2();
                        // Busca el libro en el listado. Guarda la posición en el ArrayList y si lo
                        // encuentra, registrado es true.
                        for (int i = 0; i < biblioteca1.getLibros().size(); i++) {
                            if (biblioteca1.getLibros().get(i).getTitulo().equals(titulo)
                                    && biblioteca1.getLibros().get(i).getEdicion() == edicion &&
                                    biblioteca1.getLibros().get(i).getEditorial().equals(editorial)
                                    && biblioteca1.getLibros().get(i).getAnio() == año) {
                                index = i;
                                registrado = true;
                            }
                        }
                        // Si el libro está registrado:
                        if (registrado == true) {
                            // verifica que el libro no esté prestado. Para ello, accede al elemento en
                            // cuestión con el index obtenido en el recorrido anterior:
                            if (!biblioteca1.getLibros().get(index).prestado()) {
                                // Si no está prestado, solicita al usuario el ingreso de la fecha de retiro
                                System.out.println("Día de retiro:");
                                int dia = preguntar(1, 31);
                                System.out.println("Mes de retiro:");
                                int mes = preguntar(0, 11);
                                System.out.println("Año de retiro:");
                                int anio = preguntar2();
                                fechaHoy.set(anio, mes - 1, dia);
                                // Busca al socio solicitante con la clave DNI
                                Socio socio1 = biblioteca1.buscarSocio(dni);
                                biblioteca1.prestarLibro(fechaHoy, socio1, biblioteca1.getLibros().get(index));
                            } else {
                                // si el libro ya está prestado:
                                System.out.println("\n**El Libro ya se encuentra prestado**");
                            }

                        } else {
                            // Si el libro no está registrado:
                            System.out.println("\n***Este libro no está registrado***\n - " + titulo);
                        }
                        // Si el socio solicitante no está registrado:
                    } else {
                        System.out.println("\n El socio D.N.I:" + dni + " no puede pedir más libros!");
                    }

                } else {
                    System.out.println("\n El socio D.N.I:" + dni + " no está registrado!");
                }
                pressAnyKeyToContinue();
                System.out.print("\u000C");
                gestionPrestamos(biblioteca1);
            }
                break;
            /**
             * Antes de devolver un libro, controla que el socio y el libro estén
             * registrados y que este último esté prestado.
             */
            case 2: {
                System.out.print("\u000C");
                boolean flag = true;
                Calendar fechaHoy = Calendar.getInstance();
                System.out.println("Ingrese el DNI del socio:");
                int dni = preguntar2();
                if (biblioteca1.buscarSocio(dni) != null) {
                    Socio socio1 = biblioteca1.buscarSocio(dni);

                    // lista registro histórico de préstamos del socio
                    if (biblioteca1.buscarSocio(dni).getPrestamos().size() > 0) {
                        System.out.println("*** Libros en poder del socio " + socio1.getNombre() + " ***");
                        for (Prestamo prestamo : biblioteca1.buscarSocio(dni).getPrestamos()) {
                            if (prestamo.getFechaDevolucion() == null) {
                                System.out.println(prestamo.toString() + "\n\n");
                            }
                        }
                    } else {
                        System.out.println("***El socio no registra préstamos***\n\n");
                    }

                    // Solicita datos del libro
                    boolean noPrestado = false;
                    System.out.println("Ingrese el título del libro: ");
                    String titulo = input.next();
                    System.out.println("Ingrese la edición del libro: ");
                    int edicion = preguntar2();
                    System.out.println("Ingrese la editorial del libro: ");
                    String editorial = input.next();
                    System.out.println("Ingrese el año: ");
                    int año = preguntar2();
                    // Busca el libro
                    for (int i = 0; i < biblioteca1.getLibros().size(); i++) {

                        if (biblioteca1.getLibros().get(i).getTitulo().equals(titulo)
                                && biblioteca1.getLibros().get(i).getEdicion() == edicion &&
                                biblioteca1.getLibros().get(i).getEditorial().equals(editorial)
                                && biblioteca1.getLibros().get(i).getAnio() == año) {
                            // Si encontró el libro, y está prestado, lo devuelve
                            if (biblioteca1.getLibros().get(i).prestado() == true) {
                                biblioteca1.devolverLibro(biblioteca1.getLibros().get(i));
                                System.out.println("***Se ha devuelto el libro***");
                                noPrestado = true;
                            }
                        }
                    }
                    if (!noPrestado) {
                        System.out.println("***No se encontró el libro***");
                    }

                } else {
                    System.out.println("\n***El socio D.N.I:" + dni + " no está registrado***");
                }

                pressAnyKeyToContinue();
                System.out.print("\u000C");
                gestionPrestamos(biblioteca1);
            }
                break;

            /**
             * Muestra libros prestados y no prestados
             */
            case 3: {
                System.out.print("\u000C");
                System.out.println("\n" + biblioteca1.listaDeLibros());
                System.out.println("");
                pressAnyKeyToContinue();
                System.out.print("\u000C");
                gestionPrestamos(biblioteca1);
            }
                break;

            /**
             * Muestra los vencidos y no devueltos
             */
            case 4: {
                System.out.print("\u000C");
                // Primero verifica que existan préstamos vencidos:
                if (biblioteca1.prestamosVencidos().isEmpty()) {
                    System.out.println("\n***No hay préstamos vencidos***");
                } else {
                    System.out.println("**Lista de préstamos vencidos**");
                    for (Prestamo presta : biblioteca1.prestamosVencidos()) {
                        // Si no tienen fecha de devolución, muestra los datos
                        if (presta.getFechaDevolucion() == null) {
                            System.out.println(presta.toString());
                        }

                    }
                }
                pressAnyKeyToContinue();
                System.out.print("\u000C");
                gestionPrestamos(biblioteca1);
            }
                break;

            /**
             * Muestra todos los vencidos hasta la fecha, incluso si ya fueron devueltos
             */
            case 5: {
                System.out.print("\u000C");
                // Primero verifica que existan préstamos vencidos:
                if (biblioteca1.prestamosVencidos().isEmpty()) {
                    System.out.println("\n***No hay préstamos vencidos***");
                } else {
                    System.out.println("**Lista de préstamos vencidos**");
                    for (Prestamo presta : biblioteca1.prestamosVencidos()) {
                        System.out.println(presta.toString());
                    }
                }
                pressAnyKeyToContinue();
                System.out.print("\u000C");
                gestionPrestamos(biblioteca1);
            }
                break;

            case 6: {
                System.out.print("\u000C");
                System.out.println("Ingrese el DNI del socio a buscar:");
                int dni = preguntar2();
                // si el socio está registrado:
                if (biblioteca1.buscarSocio(dni) != null) {
                    // si el socio pidió prestado al menos un libro:
                    if (biblioteca1.buscarSocio(dni).getPrestamos().size() > 0) {
                        // lista registro histórico de préstamos del socio
                        for (Prestamo prestamo : biblioteca1.buscarSocio(dni).getPrestamos()) {
                            System.out.println(prestamo.toString() + "\n\n");
                        }
                    } else {
                        System.out.println("***El socio no registra préstamos***\n\n");
                    }
                } else {
                    System.out.println("\n\n***El socio D.N.I.: " + dni + " no está registrado***\n\n");
                }

                pressAnyKeyToContinue();
                System.out.print("\u000C");
                gestionPrestamos(biblioteca1);
            }
                break;

            case 7: {
                System.out.print("\u000C");
                // Emite listado de libros registrados para que el usuario sepa que datos
                // ingresar:
                System.out.println("\n\n***Lista de Libros***\n\n ");
                for (Libro libro : biblioteca1.getLibros()) {
                    System.out.println(" -Título: " + libro.getTitulo() + "   -Edición: " + libro.getEdicion()
                            + "   -Editorial: " + libro.getEditorial() +
                            "   -Año: " + libro.getAnio());

                }
                // Solicita ingreso de datos del libro:
                System.out.println("\n\nIngrese el título del libro: ");
                String titulo = input.next();
                int index = -1;
                System.out.println("Ingrese la edición del libro: ");
                int edicion = preguntar2();
                System.out.println("Ingrese la editorial del libro: ");
                String editorial = input.next();
                System.out.println("Ingrese el año: ");
                int año = preguntar2();

                for (int i = 0; i < biblioteca1.getLibros().size(); i++) {
                    if (biblioteca1.getLibros().get(i).getTitulo().equals(titulo)
                            && biblioteca1.getLibros().get(i).getEdicion() == edicion &&
                            biblioteca1.getLibros().get(i).getEditorial().equals(editorial)
                            && biblioteca1.getLibros().get(i).getAnio() == año) {
                        // Si encuentra el libro, guarda la posición en index
                        index = i;
                    }
                }

                // Si encontró el libro:
                if (index != -1) {
                    // Si el libro fue prestado al menos una vez, recorre la lista y muestra los
                    // datos de cada préstamo histórico
                    if (biblioteca1.getLibros().get(index).getPrestamos().size() > 0) {
                        for (Prestamo prestamo : biblioteca1.getLibros().get(index).getPrestamos()) {
                            System.out.println(prestamo.toString());
                            System.out.println("***********************************");
                        }
                    } else {
                        System.out.println("\n\n***Este libro no registra préstamos***\n\n");
                    }
                } else {
                    System.out.println("\n\n***Este libro no está registrado en la biblioteca!\n\n");
                }

                pressAnyKeyToContinue();
                System.out.print("\u000C");
                gestionPrestamos(biblioteca1);
            }
                break;

            case 8: {
                System.out.print("\u000C");
            }
                break;
        }

    }

    /**
     * Carga y remueve libros, emite lista de los prestados y muestra que socio
     * tiene en su poder determinado libro.
     */
    public static void gestionLibros(Biblioteca biblioteca1) {

        input.useDelimiter("\n");
        System.out.println(animation.art3);
        System.out.println("                *********************************************");
        System.out.println("                ****           GESTIÓN DE LIBROS         ****");
        System.out.println("                *********************************************");
        System.out.println("                **   (1) Cargar libro                      **");
        System.out.println("                **   (2) Remover libro                     **");
        System.out.println("                **   (3) Lista de libros                   **");
        System.out.println("                **   (4) Mostrar quien tiene 'X' libro hoy **");
        System.out.println("                **   (5) Volver atrás                      **");
        System.out.println("                *********************************************\n");

        System.out.println("                Presione ENTER para CONTINUAR");

        switch (preguntar(1, 5)) {
            case 1: {

                System.out.print("\u000C");
                System.out.println("Ingrese el título del libro: ");
                String titulo = input.next();
                System.out.println("Ingrese la edición del libro: ");
                int edicion = preguntar2();
                System.out.println("Ingrese la editorial del libro: ");
                String editorial = input.next();
                System.out.println("Ingrese el año: ");
                int año = preguntar2();
                biblioteca1.nuevoLibro(titulo, edicion, editorial, año);
                System.out.println("\n\n***Se ha añadido el libro a la biblioteca***\n\n");

                pressAnyKeyToContinue();
                System.out.print("\u000C");
                gestionLibros(biblioteca1);
            }
                break;
            /**
             * Antes de remover libros, verifica que el mismo esté registrado y no se haya
             * prestado.
             */
            case 2: {
                System.out.print("\u000C");
                // Muestra al usuario los libros registrados para que sepa que datos ingresar:
                System.out.println("\n\n***Lista de Libros***\n\n ");
                for (Libro libro : biblioteca1.getLibros()) {
                    System.out.println(" -Título: " + libro.getTitulo() + "   -Edición: " + libro.getEdicion()
                            + "   -Editorial: " + libro.getEditorial() + "   -Año: " + libro.getAnio());

                }

                // Solicita al usuario los datos del libro a remover
                System.out.println("\n\nIngrese el título del libro: ");
                String titulo = input.next();
                System.out.println("Ingrese la edición del libro: ");
                int edicion = preguntar2();
                System.out.println("Ingrese la editorial del libro: ");
                String editorial = input.next();
                System.out.println("Ingrese el año: ");
                int año = preguntar2();
                int index = -1;
                boolean registrado = false;
                boolean estaPrestado = false;
                System.out.println("\n");

                // Busca el libro
                for (int i = 0; i < biblioteca1.getLibros().size(); i++) {
                    if (biblioteca1.getLibros().get(i).getTitulo().equals(titulo)
                            && biblioteca1.getLibros().get(i).getEdicion() == edicion &&
                            biblioteca1.getLibros().get(i).getEditorial().equals(editorial)
                            && biblioteca1.getLibros().get(i).getAnio() == año) {
                        // Si lo encuentra, guarda el índice.
                        index = i;
                        // Controla que esté prestado
                        if (biblioteca1.getLibros().get(index).prestado()) {
                            estaPrestado = true;
                        }
                    }
                }

                // Si se encontró el libro:
                if (index != -1) {
                    // Si el libro no está prestado:
                    if (estaPrestado == false) {
                        biblioteca1.removeLibro(biblioteca1.getLibros().get(index));
                    } else {
                        // Si el libro está prestado:
                        System.out.println("\n***Libro prestado, "
                                + biblioteca1.getLibros().get(index).getPrestamo().getSocio().getNombre() +
                                " debe devolver el libro***\n\n");
                    }
                    // Si no se encontró el libro:
                } else {
                    System.out.println("\n\n***Este libro no está registrado en la biblioteca!\n\n");
                }

                pressAnyKeyToContinue();
                System.out.print("\u000C");
                gestionLibros(biblioteca1);
            }
                break;

            case 3: {
                System.out.print("\u000C");
                System.out.println("\n\n***Lista de Libros***\n\n ");
                for (Libro libro : biblioteca1.getLibros()) {
                    System.out.println(" -Título: " + libro.getTitulo() + "   -Edición: " + libro.getEdicion()
                            + "   -Editorial: " + libro.getEditorial() +
                            "   -Año: " + libro.getAnio());

                }

                pressAnyKeyToContinue();
                System.out.print("\u000C");

                gestionLibros(biblioteca1);
            }
                break;

            case 4: {
                System.out.print("\u000C");
                // Muestra el listado de libros registrados al usuario
                for (Libro libro : biblioteca1.getLibros()) {
                    System.out.println(" -Título: " + libro.getTitulo() + "   -Edición: " + libro.getEdicion()
                            + "   -Editorial: " + libro.getEditorial() +
                            "   -Año: " + libro.getAnio());
                }
                // Solicita al usuario los datos del libro
                System.out.println("\n\n Ingrese el título del libro: ");
                String titulo = input.next();
                int index = -1;
                System.out.println("Ingrese la edición del libro: ");
                int edicion = preguntar2();
                System.out.println("Ingrese la editorial del libro: ");
                String editorial = input.next();
                System.out.println("Ingrese el año: ");
                int año = preguntar2();
                // busca que el libro esté registrado
                for (int i = 0; i < biblioteca1.getLibros().size(); i++) {
                    if (biblioteca1.getLibros().get(i).getTitulo().equals(titulo)
                            && biblioteca1.getLibros().get(i).getEdicion() == edicion &&
                            biblioteca1.getLibros().get(i).getEditorial().equals(editorial)
                            && biblioteca1.getLibros().get(i).getAnio() == año) {
                        index = i;
                    }
                }
                // si el libro está registrado, muestra
                // en caso contrario, lo informa
                if (index != -1) {
                    System.out.println(biblioteca1.quienTieneElLibro(biblioteca1.getLibros().get(index)));
                } else {
                    System.out.println("\n\n***Este libro no está registrado en la biblioteca!\n\n");
                }
                pressAnyKeyToContinue();
                System.out.print("\u000C");
                gestionLibros(biblioteca1);

            }
                break;
            case 5: {
                System.out.print("\u000C");
            }
                break;
        }
    }

    public static void pressAnyKeyToContinue()

    {

        String seguir;

        Scanner teclado = new Scanner(System.in);

        System.out.println("Press Enter key to continue...");

        try

        {

            seguir = teclado.nextLine();

        }

        catch (Exception e)

        {
        }
    }
}
