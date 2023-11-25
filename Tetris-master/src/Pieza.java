import java.util.ArrayList;
import java.awt.Color;

public class Pieza {
   String[] lnombrepieza = {"I","L","IL","Z","IZ","T","S"};
   String id;
   Coordenadas centro;
   Color colorpieza;

   ArrayList<Coordenadas> body = new ArrayList<Coordenadas>();

   public Pieza(){
   }

   public Pieza(int n){
      switch(n){
         case 0 : {this.crearPiezaI();} break;
         case 1 : {this.crearPiezaL();} break;
         case 2 : {this.crearPiezaIL();} break;
         case 3 : {this.crearPiezaZ();} break;
         case 4 : {this.crearPiezaIZ();} break;
         case 5 : {this.crearPiezaT();} break;
         case 6 : {this.crearPiezaS();} break;
      }
   }

   //pieza I
   public ArrayList<Coordenadas> crearPiezaI(){
      ArrayList<Coordenadas> lcord = new ArrayList<Coordenadas>();

      Coordenadas p1 = new Coordenadas(0, 0);
      Coordenadas p2 = new Coordenadas(1, 0);
      Coordenadas p3 = new Coordenadas(2, 0);
      Coordenadas p4 = new Coordenadas(3, 0);

      lcord.add(p1);
      lcord.add(p2);
      lcord.add(p3);
      lcord.add(p4);

      body=lcord;
      id=lnombrepieza[0]; 
      this.colorpieza = Color.RED;
      p1.setC(colorpieza);
      p2.setC(colorpieza);
      p3.setC(colorpieza);
      p4.setC(colorpieza);

      //this.centro = p4;
      this.centro =this.getBody().get(3);

      return this.getBody();
   }

   //pieza L
   public ArrayList<Coordenadas> crearPiezaL(){
      ArrayList<Coordenadas> lcord = new ArrayList<Coordenadas>();

      Coordenadas p1 = new Coordenadas(0, 0);
      Coordenadas p2 = new Coordenadas(0, 1);
      Coordenadas p3 = new Coordenadas(1, 1);
      Coordenadas p4 = new Coordenadas(2, 1);

      lcord.add(p1);
      lcord.add(p2);
      lcord.add(p3);
      lcord.add(p4);

      body=lcord;
      id=lnombrepieza[1]; 
      this.colorpieza = Color.ORANGE;
      p1.setC(colorpieza);
      p2.setC(colorpieza);
      p3.setC(colorpieza);
      p4.setC(colorpieza);
      //this.centro = p4;
      this.centro =this.getBody().get(3);

      return this.getBody();
   }

   public String[] getLnombrepieza() {
      return lnombrepieza;
   }

   //pieza L invertida
   public ArrayList<Coordenadas> crearPiezaIL(){
      ArrayList<Coordenadas> lcord = new ArrayList<Coordenadas>();

      Coordenadas p1 = new Coordenadas(0, 0);
      Coordenadas p2 = new Coordenadas(0, 1);
      Coordenadas p3 = new Coordenadas(1, 0);
      Coordenadas p4 = new Coordenadas(2, 0);

      lcord.add(p1);
      lcord.add(p2);
      lcord.add(p3);
      lcord.add(p4);

      body=lcord;
      id=lnombrepieza[2]; 
      this.colorpieza = Color.GREEN;
      p1.setC(colorpieza);
      p2.setC(colorpieza);
      p3.setC(colorpieza);
      p4.setC(colorpieza);
      //this.centro = p4;
      this.centro =this.getBody().get(3);

      return this.getBody();
   }

   //pieza z
   public ArrayList<Coordenadas> crearPiezaZ(){
      ArrayList<Coordenadas> lcord = new ArrayList<Coordenadas>();

      Coordenadas p1 = new Coordenadas(0, 1);
      Coordenadas p2 = new Coordenadas(0, 2);
      Coordenadas p3 = new Coordenadas(1, 1);
      Coordenadas p4 = new Coordenadas(1, 0);

      lcord.add(p1);
      lcord.add(p2);
      lcord.add(p3);
      lcord.add(p4);

      body=lcord;
      id=lnombrepieza[3]; 
      this.colorpieza = Color.PINK;
      p1.setC(colorpieza);
      p2.setC(colorpieza);
      p3.setC(colorpieza);
      p4.setC(colorpieza);
      //this.centro = p4;
      this.centro =this.getBody().get(2);

      return this.getBody();
   }

   //pieza invertida
   public ArrayList<Coordenadas> crearPiezaIZ(){
      ArrayList<Coordenadas> lcord = new ArrayList<Coordenadas>();

      Coordenadas p1 = new Coordenadas(0, 0);
      Coordenadas p2 = new Coordenadas(0, 1);
      Coordenadas p3 = new Coordenadas(1, 1);
      Coordenadas p4 = new Coordenadas(1, 2);

      lcord.add(p1);
      lcord.add(p2);
      lcord.add(p3);
      lcord.add(p4);

      body=lcord;
      id=lnombrepieza[4]; 
      this.colorpieza = Color.YELLOW;
      p1.setC(colorpieza);
      p2.setC(colorpieza);
      p3.setC(colorpieza);
      p4.setC(colorpieza);
      //this.centro = p4;
      this.centro =this.getBody().get(2);

      return this.getBody();
   }

   //pieza T
   public ArrayList<Coordenadas> crearPiezaT(){
      ArrayList<Coordenadas> lcord = new ArrayList<Coordenadas>();

      Coordenadas p1 = new Coordenadas(0, 1);
      Coordenadas p2 = new Coordenadas(1, 0);
      Coordenadas p3 = new Coordenadas(1, 1);
      Coordenadas p4 = new Coordenadas(1, 2);

      lcord.add(p1);
      lcord.add(p2);
      lcord.add(p3);
      lcord.add(p4);

      body=lcord;
      id=lnombrepieza[5]; 
      this.colorpieza = Color.LIGHT_GRAY;
      p1.setC(colorpieza);
      p2.setC(colorpieza);
      p3.setC(colorpieza);
      p4.setC(colorpieza);
      //this.centro = p4;
      this.centro =this.getBody().get(2);

      return this.getBody();
   }

   //pieza cuadrado o square
   public ArrayList<Coordenadas> crearPiezaS(){
      ArrayList<Coordenadas> lcord = new ArrayList<Coordenadas>();

      Coordenadas p1 = new Coordenadas(0, 0);
      Coordenadas p2 = new Coordenadas(0, 1);
      Coordenadas p3 = new Coordenadas(1, 0);
      Coordenadas p4 = new Coordenadas(1, 1);

      lcord.add(p1);
      lcord.add(p2);
      lcord.add(p3);
      lcord.add(p4);

      body=lcord;
      id=lnombrepieza[6]; 
      this.colorpieza = Color.MAGENTA;
      p1.setC(colorpieza);
      p2.setC(colorpieza);
      p3.setC(colorpieza);
      p4.setC(colorpieza);
      //this.centro = p4;
      this.centro =this.getBody().get(2);

      return this.getBody();
   }

   public void rotarPieza(){
      Coordenadas centro = this.getCentro();//variable auxiliar para guardar las coordenadas del centro
      for(Coordenadas c : this.getBody()){//para recorrer las coordenadas de los puntos
         int cenx=centro.getX();
         int ceny=centro.getY();
         int pcx = c.getX()-cenx;
         int pyx = c.getY()-ceny;
         int finx = centro.getX()+pyx;
         int finy = centro.getY()-pcx;
         c.setX(finx);
         c.setY(finy);
      }
   }

   public void setLnombrepieza(String[] lnombrepieza) {
      this.lnombrepieza = lnombrepieza;
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public Coordenadas getCentro() {
      return centro;
   }

   public void setCentro(Coordenadas centro) {
      this.centro = centro;
   }

   public Color getColorpieza() {
      return colorpieza;
   }

   public void setColorpieza(Color colorpieza) {
      this.colorpieza = colorpieza;
   }

   public ArrayList<Coordenadas> getBody() {
      return body;
   }

   public void setBody(ArrayList<Coordenadas> body) {
      this.body = body;
   }


   
}
