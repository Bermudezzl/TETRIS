import java.awt.event.KeyListener; // Importa la interfaz KeyListener para detectar eventos de teclado.
import java.awt.event.KeyEvent; // Importa la clase KeyEvent para representar eventos de teclado.
import java.util.ArrayList; // Importa la clase ArrayList para trabajar con listas dinámicas.
import java.util.Random; // Importa la clase Random para generar números aleatorios.

public class Contol implements KeyListener { // Declara la clase Contol que implementa la interfaz KeyListener.

    Pieza actual; // Declara una variable de tipo Pieza llamada actual.
    public enum Accion {LEFT, RIGHT, SPACE, NOTHING}; // Declara una enumeración Accion que representa las acciones posibles.
    Accion accion; // Declara una variable de tipo Accion llamada accion.
    int pinix = 5; // Declara una variable de tipo entero llamada pinix e inicializa su valor en 5.
    int piniy = 0; // Declara una variable de tipo entero llamada piniy e inicializa su valor en 0.
    int pfinalx; // Declara una variable de tipo entero llamada pfinalx.
    int pfinaly; // Declara una variable de tipo entero llamada pfinaly.
    int limitetabd; // Declara una variable de tipo entero llamada limitetabd.
    int limitetabi; // Declara una variable de tipo entero llamada limitetabi.

    ArrayList<Coordenadas> lpiezas = new ArrayList<Coordenadas>(); // Declara una lista de objetos Coordenadas llamada lpiezas y la inicializa como una nueva instancia de ArrayList.

    public Contol() { // Constructor de la clase Contol.
        actual = new Pieza(); // Crea una nueva instancia de la clase Pieza y la asigna a la variable actual.
        crearPieza(); // Llama al método crearPieza para crear una nueva pieza.
        accion = Accion.NOTHING; // Asigna el valor Accion.NOTHING a la variable accion.
    }

    public void crearPieza() { // Define el método crearPieza.
        Random r = new Random(); // Crea una nueva instancia de la clase Random llamada r.
        int npieza = r.nextInt(actual.lnombrepieza.length); // Genera un número aleatorio entre 0 y la longitud del arreglo lnombrepieza de la instancia actual y lo asigna a la variable npieza.
        actual = new Pieza(npieza); // Crea una nueva instancia de la clase Pieza con el número de pieza generado y la asigna a la variable actual.
        moverPiezaaInicio(); // Llama al método moverPiezaaInicio para mover la pieza a la posición inicial.
    }

    public void moverPiezaaInicio() {
        for (Coordenadas c : actual.getBody()) {
            int px = c.getX(); // Obtiene la coordenada x de la posición de la pieza actual.
            int py = c.getY(); // Obtiene la coordenada y de la posición de la pieza actual.
            int cx = px + pinix; // Calcula la nueva coordenada x sumando la posición actual con el valor de pinix.
            int cy = py + piniy; // Calcula la nueva coordenada y sumando la posición actual con el valor de piniy.
            c.setX(cx); // Establece la nueva coordenada x para la coordenada actual.
            c.setY(cy); // Establece la nueva coordenada y para la coordenada actual.
        }
    }
    public void bajarPieza() {
        for (Coordenadas c : actual.getBody()) {
            int py = c.getY(); // Obtiene la coordenada y de la posición de la pieza actual.
            int cy = py + 1; // Incrementa la coordenada y en 1 para mover la pieza hacia abajo.
            c.setY(cy); // Establece la nueva coordenada y para la coordenada actual.
        }
    }
    public void rotarPieza() {
        actual.rotarPieza(); // Llama al método rotarPieza() de la instancia actual de la clase Pieza.
    }

    public void moverDerecha() {
        for (Coordenadas c : actual.getBody()) {
            int x = c.getX(); // Obtiene la coordenada x de la posición de la pieza actual.
            int y = c.getY(); // Obtiene la coordenada y de la posición de la pieza actual.
            x = x + 1; // Incrementa la coordenada x en 1 para mover la pieza hacia la derecha.
            c.setX(x); // Establece la nueva coordenada x para la coordenada actual.
        }
    }

    public void moverIzquierda() {
        for (Coordenadas c : actual.getBody()) {
            int x = c.getX(); // Obtiene la coordenada x de la posición de la pieza actual.
            int y = c.getY(); // Obtiene la coordenada y de la posición de la pieza actual.
            x = x - 1; // Decrementa la coordenada x en 1 para mover la pieza hacia la izquierda.
            c.setX(x); // Establece la nueva coordenada x para la coordenada actual.
        }
    }

    public boolean hayFinalTablero() {
        boolean condicion = false; // Inicializa una variable booleana llamada condicion en falso.
        for (Coordenadas c : actual.getBody()) {
            if (c.getY() + 1 == this.pfinaly) { // Comprueba si la coordenada y de la coordenada actual más 1 es igual a la coordenada y del final del tablero.
                return true; // Si se cumple la condición, significa que hay un final de tablero y se devuelve verdadero.
            }
        }
        return condicion; // Si no se cumple la condición, se devuelve el valor de la variable condicion (falso).
    }

    public boolean hayColisioncontraPieza() {
        boolean condicion = false; // Inicializa una variable booleana llamada condicion en falso.
        for (Coordenadas ct : this.lpiezas) { // Recorre todas las coordenadas de las piezas existentes.
            for (Coordenadas cp : actual.getBody()) { // Recorre todas las coordenadas de la pieza actual.
                if ((cp.getY() + 1 == ct.getY()) && (cp.getX() == ct.getX())) { // Comprueba si existe una colisión entre la pieza actual y alguna de las otras piezas.
                    condicion = true; // Si hay colisión, establece la variable condicion en verdadero.
                }
            }
        }
        return condicion; // Devuelve el valor de la variable condicion (verdadero si hay colisión, falso si no la hay).
    }

    public boolean hayMover() {
        boolean condicion = true; // Inicializa una variable booleana llamada condicion en verdadero.
        for (Coordenadas c : actual.getBody()) { // Recorre todas las coordenadas de la pieza actual.
            if (accion == Accion.RIGHT) { // Comprueba si la acción es mover a la derecha.
                if (c.getX() + 1 == this.limitetabd) { // Comprueba si la coordenada x de la coordenada actual más 1 es igual al límite derecho del tablero.
                    condicion = false; // Si se cumple la condición, establece la variable condicion en falso.
                }
            }
            if (accion == Accion.LEFT) { // Comprueba si la acción es mover a la izquierda.
                if (c.getX() == this.limitetabi) { // Comprueba si la coordenada x de la coordenada actual es igual al límite izquierdo del tablero.
                    condicion = false; // Si se cumple la condición, establece la variable condicion en falso.
                }
            }
        }
        return condicion; // Devuelve el valor de la variable condicion (verdadero si se puede mover, falso si no se puede).
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        char tecla = e.getKeyChar(); // Obtiene el carácter de la tecla presionada.
        System.out.println(tecla); // Imprime el carácter en la consola (puede ser útil para depurar).
        switch (tecla) {
            case 'a':
                accion = Accion.LEFT; // Si la tecla presionada es 'a', establece la acción en mover a la izquierda.
                break;
            case 'd':
                accion = Accion.RIGHT; // Si la tecla presionada es 'd', establece la acción en mover a la derecha.
                break;
            case ' ':
                accion = Accion.SPACE; // Si la tecla presionada es espacio, establece la acción en espacio (puede tener un significado específico en tu código).
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    public Pieza getActual() {
        return actual;
    }

    public void setActual(Pieza actual) {
        this.actual = actual;
    }

    public int getPfinalx() {
        return pfinalx;
    }

    public void setPfinalx(int pfinalx) {
        this.pfinalx = pfinalx;
    }

    public int getPfinaly() {
        return pfinaly;
    }

    public void setPfinaly(int pfinaly) {
        this.pfinaly = pfinaly;
    }

    public ArrayList<Coordenadas> getLpiezas() {
        return lpiezas;
    }

    public void setLpiezas(ArrayList<Coordenadas> lpiezas) {
        this.lpiezas = lpiezas;
    }

    public Accion getAccion() {
        return accion;
    }

    public void setAccion(Accion accion) {
        this.accion = accion;
    }

    public int getLimitetabd() {
        return limitetabd;
    }

    public void setLimitetabd(int limitetabd) {
        this.limitetabd = limitetabd;
    }

    public int getLimitetabi() {
        return limitetabi;
    }

    public void setLimitetabi(int limitetabi) {
        this.limitetabi = limitetabi;
    }

    public void ejecutarFrame() {
        if (this.hayMover()) { // Verifica si es posible mover la pieza actual en la dirección actual.
            if (accion == Accion.RIGHT) { // Si la acción es mover a la derecha.
                this.moverDerecha(); // Mueve la pieza actual hacia la derecha.
            }
            if (accion == Accion.LEFT) { // Si la acción es mover a la izquierda.
                this.moverIzquierda(); // Mueve la pieza actual hacia la izquierda.
            }
            if (accion == Accion.SPACE) { // Si la acción es espacio.
                this.rotarPieza(); // Rota la pieza actual.
            }
            accion = Accion.NOTHING; // Restablece la acción a "NADA" después de realizar el movimiento.
        } else {
            accion = Accion.NOTHING; // Si no es posible mover la pieza, establece la acción a "NADA".
        }
    
        if ((!this.hayFinalTablero()) && (!this.hayColisioncontraPieza())) { // Verifica si no hay colisión con el tablero o con otras piezas.
            this.bajarPieza(); // Baja la pieza actual hacia abajo.
        } else {
            this.getLpiezas().addAll(actual.getBody()); // Agrega las coordenadas de la pieza actual a la lista de piezas existentes.
            eliminarFilasCompletas(); // Verifica y elimina las filas completas en el tablero.
            this.crearPieza(); // Crea una nueva pieza.
        }
    }

public void eliminarFilasCompletas() {
    ArrayList<Integer> filasCompletas = new ArrayList<>(); // Lista para almacenar las filas completas encontradas.

    for (Coordenadas c : lpiezas) { // Itera sobre todas las coordenadas en la lista de piezas.
        int fila = c.getY(); // Obtiene el número de fila de la coordenada.
        boolean filaCompleta = true; // Bandera para indicar si la fila está completa.

        for (int x = 0; x < pfinalx; x++) { // Itera sobre todas las columnas en la fila.
            boolean celdaOcupada = false; // Bandera para indicar si la celda está ocupada.
            for (Coordenadas cp : lpiezas) { // Itera sobre todas las coordenadas en la lista de piezas.
                if (cp.getX() == x && cp.getY() == fila) { // Comprueba si la coordenada ocupa la celda actual.
                    celdaOcupada = true; // La celda está ocupada.
                    break;
                }
            }
            if (!celdaOcupada) { // Si la celda no está ocupada, la fila no está completa.
                filaCompleta = false;
                break;
            }
        }

        if (filaCompleta && !filasCompletas.contains(fila)) { // Si la fila está completa y no se ha agregado a la lista de filas completas.
            filasCompletas.add(fila); // Agrega la fila completa a la lista de filas completas.
        }
    }

       if (!filasCompletas.isEmpty()) {
            // Eliminar filas completas y desplazar las superiores hacia abajo
            for (int fila : filasCompletas) {
                lpiezas.removeIf(c -> c.getY() == fila);
            }

            // Desplazar las filas superiores hacia abajo
            for (Coordenadas c : lpiezas) {
                int fila = c.getY();
                for (int f : filasCompletas) {
                    if (fila < f) {
                        c.setY(c.getY() + 1);
                    }
                }
            }
        }
    }
}