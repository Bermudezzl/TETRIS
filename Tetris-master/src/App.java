//Librerias
import javax.swing.JFrame;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App extends JFrame implements Runnable {

    int WIDTH = 1000; // Ancho de la ventana
    int HEIGHT = 800; // Alto de la ventana
    int nfilas = 25; // Número de filas del tablero
    int ncolumnas = 11; // Número de columnas del tablero
    int tcelda = 25; // Tamaño de cada celda del tablero
    int desplazamiento = 100; // Desplazamiento del tablero (Pixeles)

    Contol control; // Objeto para controlar el juego
    BufferedImage bi = new BufferedImage(this.WIDTH, this.HEIGHT, BufferedImage.TYPE_4BYTE_ABGR); //
    Graphics gbi = bi.getGraphics(); // Gráficos de la imagen de fondo

    // Método para pintar las piezas del tablero
    public void pintarPiezasTablero(Graphics g) {
        for (Coordenadas c : control.getLpiezas()) {
            g.setColor(c.getC());
            g.fillRect((desplazamiento) + (c.getX() * tcelda), (desplazamiento) + (c.getY() * tcelda), tcelda, tcelda);
        }
    }

    // Método para pintar la pieza actual
    public void pintarPieza(Graphics g) {
        Pieza p = this.control.getActual();
        for (Coordenadas c : p.getBody()) {
            g.setColor(p.getColorpieza());
            g.fillRect((desplazamiento) + (c.getX() * tcelda), (desplazamiento) + (c.getY() * tcelda), tcelda, tcelda);
        }
    }

    // Método para pintar el fondo del juego
    public void pintarFondo(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(10, 10, this.WIDTH, this.HEIGHT);
    }

    // Método para pintar el tablero
    public void pintarTablero(Graphics g) {
        for (int i = 0; i < nfilas; i++) {
            for (int j = 0; j < ncolumnas; j++) {
                g.setColor(Color.WHITE);
                g.drawRect(desplazamiento + (j * tcelda), desplazamiento + (i * tcelda), tcelda, tcelda);
            }
        }
    }

    // Método para pintar los gráficos en la ventana
    public void paint(Graphics g) {
        this.pintarFondo(gbi);
        this.pintarTablero(gbi);
        this.pintarPieza(gbi);
        this.pintarPiezasTablero(gbi);
        g.drawImage(bi, 10, 10, this.WIDTH, this.HEIGHT, this);
    }

    public App() {
        this.setVisible(true); // Hace que la ventana sea visible
        this.setTitle("Clon Tetris"); // Establece el título de la ventana
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); // Configura la acción al cerrar la ventana
        control = new Contol();
        control.setPfinalx(ncolumnas);
        control.setPfinaly(nfilas);
        control.limitetabd = ncolumnas;
        control.limitetabi = 0;
        this.addKeyListener(control); // Agrega un KeyListener para controlar el juego
        this.setSize(this.WIDTH, this.HEIGHT); // Establece el tamaño de la ventana

        // Para que el juego no pare de ejecutarse, cuando se llame al método start(), se irá al método run()
        Thread hilo = new Thread(this);
        hilo.start();
    }

    public static void main(String[] args) throws Exception {
        App juego = new App();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(350); // Pausa el hilo durante 350 milisegundos
            } catch (InterruptedException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
            control.ejecutarFrame(); // Ejecuta el siguiente frame del juego
            repaint(); // Vuelve a pintar los gráficos en la ventana
        }
    }

    public Contol getControl() {
        return control;
    }

    public void setControl(Contol control) {
        this.control = control;
    }
}