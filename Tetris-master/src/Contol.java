import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class Contol implements KeyListener {

    Pieza actual;
    public enum Accion {LEFT, RIGHT, SPACE, NOTHING};
    Accion accion;
    int pinix = 5;
    int piniy = 0;
    int pfinalx;
    int pfinaly;
    int limitetabd;
    int limitetabi;

    ArrayList<Coordenadas> lpiezas = new ArrayList<Coordenadas>();
    
    public Contol() {
        actual = new Pieza();
        crearPieza();
        accion = Accion.NOTHING;
    }

    public void crearPieza() {
        Random r = new Random();
        int npieza = r.nextInt(actual.lnombrepieza.length);
        actual = new Pieza(npieza);
        moverPiezaaInicio();
    }

    public void moverPiezaaInicio() {
        for (Coordenadas c : actual.getBody()) {
            int px = c.getX();
            int py = c.getY();
            int cx = px + pinix;
            int cy = py + piniy;
            c.setX(cx);
            c.setY(cy);
        }
    }

    public void bajarPieza() {
        for (Coordenadas c : actual.getBody()) {
            int py = c.getY();
            int cy = py + 1;
            c.setY(cy);
        }
    }

    public void rotarPieza() {
        actual.rotarPieza();
    }

    public void moverDerecha() {
        for (Coordenadas c : actual.getBody()) {
            int x = c.getX();
            int y = c.getY();
            x = x + 1;
            c.setX(x);
        }
    }

    public void moverIzquierda() {
        for (Coordenadas c : actual.getBody()) {
            int x = c.getX();
            int y = c.getY();
            x = x - 1;
            c.setX(x);
        }
    }

    public boolean hayFinalTablero() {
        boolean condicion = false;
        for (Coordenadas c : actual.getBody()) {
            if (c.getY() + 1 == this.pfinaly) {
                return true;
            }
        }
        return condicion;
    }

    public boolean hayColisioncontraPieza() {
        boolean condicion = false;
        for (Coordenadas ct : this.lpiezas) {
            for (Coordenadas cp : actual.getBody()) {
                if ((cp.getY() + 1 == ct.getY()) && (cp.getX() == ct.getX())) {
                    condicion = true;
                }
            }
        }
        return condicion;
    }

    public boolean hayMover() {
        boolean condicion = true;
        for (Coordenadas c : actual.getBody()) {
            if (accion == Accion.RIGHT) {
                if (c.getX() + 1 == this.limitetabd) {
                    condicion = false;
                }
            }
            if (accion == Accion.LEFT) {
                if (c.getX() == this.limitetabi) {
                    condicion = false;
                }
            }
        }
        return condicion;
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        char tecla = e.getKeyChar();
        System.out.println(tecla);
        switch (tecla) {
            case 'a':
                accion = Accion.LEFT;
                break;
            case 'd':
                accion = Accion.RIGHT;
                break;
            case ' ':
                accion = Accion.SPACE;
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
        if (this.hayMover()) {
            if (accion == Accion.RIGHT) {
                this.moverDerecha();
            }
            if (accion == Accion.LEFT) {
                this.moverIzquierda();
            }
            if (accion == Accion.SPACE) {
                this.rotarPieza();
            }
            accion = Accion.NOTHING;
        } else {
            accion = Accion.NOTHING;
        }

        if ((!this.hayFinalTablero()) && (!this.hayColisioncontraPieza())) {
            this.bajarPieza();
        } else {
            this.getLpiezas().addAll(actual.getBody());
            eliminarFilasCompletas();
            this.crearPieza();
        }
    }

    public void eliminarFilasCompletas() {
        ArrayList<Integer> filasCompletas = new ArrayList<>();

        for (Coordenadas c : lpiezas) {
            int fila = c.getY();
            boolean filaCompleta = true;

            for (int x = 0; x < pfinalx; x++) {
                boolean celdaOcupada = false;
                for (Coordenadas cp : lpiezas) {
                    if (cp.getX() == x && cp.getY() == fila) {
                        celdaOcupada = true;
                        break;
                    }
                }
                if (!celdaOcupada) {
                    filaCompleta = false;
                    break;
                }
            }

            if (filaCompleta && !filasCompletas.contains(fila)) {
                filasCompletas.add(fila);
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