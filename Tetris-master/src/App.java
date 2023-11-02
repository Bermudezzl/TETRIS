import javax.swing.JFrame;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App extends JFrame implements Runnable{

    int WIDTH = 1000;
    int HEIGHT = 800;
    int nfilas = 25;
    int ncolumnas = 11;
    int tcelda = 25;
    int desplazamiento = 100;

    Contol control;
    BufferedImage bi = new BufferedImage(this.WIDTH,this.HEIGHT,BufferedImage.TYPE_4BYTE_ABGR);
    Graphics gbi = bi.getGraphics();

    public void pintarPiezasTablero(Graphics g){
        for(Pieza p : control.getLpiezas()){
            for(Coordenadas c : p.getBody()){
                g.setColor(p.getColorpieza());
                g.fillRect((desplazamiento)+(c.getX()*tcelda), (desplazamiento)+(c.getY()*tcelda), tcelda, tcelda);
            }
        } 
    }

    public void pintarPieza(Graphics g){
        Pieza p = this.control.getActual();
        for(Coordenadas c : p.getBody()){
            g.setColor(p.getColorpieza());
            g.fillRect((desplazamiento)+(c.getX()*tcelda), (desplazamiento)+(c.getY()*tcelda), tcelda, tcelda);
        }
    }

    public void pintarFondo(Graphics g){
        g.setColor(Color.BLUE);
        g.fillRect(10, 10, this.WIDTH, this.HEIGHT);
    }

    //tablero
    public void pintarTablero(Graphics g){
        for(int i=0;i<nfilas;i++){
            for(int j=0;j<ncolumnas;j++){
                g.setColor(Color.WHITE);
                g.drawRect(desplazamiento+(j*tcelda),desplazamiento+(i*tcelda),tcelda,tcelda);
            }
        }
    }

    public void paint(Graphics g){
        this.pintarFondo(gbi);
        this.pintarTablero(gbi);
        this.pintarPieza(gbi);
        this.pintarPiezasTablero(gbi);
        g.drawImage(bi,10,10, this.WIDTH,this.HEIGHT,this);
    }

    public App(){
        this.setVisible(true);
        this.setTitle("Clon Tetris");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        control = new Contol(); ////faltava esta onda
        control.setPfinalx(ncolumnas);
        control.setPfinaly(nfilas);
        this.addKeyListener(control);
        this.setSize(this.WIDTH,this.HEIGHT);

        //para que el juego no pare de ejecutarse, cuyando yo ponga el metodo star() se ira al metodo run()
        Thread hilo = new Thread(this);
        hilo.start();
    }
    public static void main(String[] args) throws Exception {
        App juego = new App();
    }

    @Override
    public void run(){
         while(true){
            try {
                Thread.sleep(350);
            } catch (InterruptedException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
            control.ejecutarFrame();
            repaint();
        }
    }

    public Contol getControl() {
        return control;
    }

    public void setControl(Contol control) {
        this.control = control;
    }

    
}
