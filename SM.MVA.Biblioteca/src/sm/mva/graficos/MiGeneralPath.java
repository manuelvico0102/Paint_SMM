/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sm.mva.graficos;


import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;

/**
 * Esta clase define objetos de Path2D a la cual se le agrega un método
 * para facilitar mover los objetos.
 * 
 * @author Manuel Vico
 */
public class MiGeneralPath extends Path2D.Float implements MiShape{
    
    /**
     * Booleano que se encarga de controlar el estado del atributo Relleno
     */
    private boolean relleno = false;
    /**
     *  Booleano que se encarga de controlar el estado del atributo Alisar
     */
    private boolean alisar = false;
    /**
     *  Booleano que se encarga de controlar el estado del atributo Transparencia
     */
    private boolean transp = false;
    /**
     * Variable que indica el grosor de las formas, inicialmente 1
     */
    private int grosor = 1;
    /**
     * Color actual de la forma
     */
    private Color color;
    /**
     * Variable que indica el tipo de antialiasing
     */
    private final RenderingHints render = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
    /**
     * Variable que indica la transparencia que se le puede aplicar a las formas
     */
    private final Composite comp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
    /**
     * Variable que se utiliza para configurar el grosor
     */
    private Stroke stroke;
    
    
    /**
     * Constructor por defecto
     */
    public MiGeneralPath() {
        super();
    }
    
    /**
     * Método para obtener el último punto del objeto MiGeneralPath
     * 
     * @return un Point2D del último punto agregado al objeto
     */
    public Point2D getLocation(){
        return this.getCurrentPoint();
    }
    
    /**
     * Método para posicionar el objeto MiGeneralPath en la posicion dada
     * 
     * @param pos el punto de coordenadas donde posicionar el objeto
     */
    @Override
    public void setLocation(Point2D pos){
        //Point2D loc = this.getLocation();
        PathIterator iterador = this.getPathIterator(null);
        double[] coordenadas = new double[6];
        iterador.currentSegment(coordenadas);
        Point2D loc = new Point2D.Double(coordenadas[0], coordenadas[1]); //primer punto
        AffineTransform at;
        at = AffineTransform.getTranslateInstance(pos.getX()-loc.getX(), pos.getY()-loc.getY());
        this.transform(at);
    }
    
    /**
     * Método que devuelve el punto p1
     * @return El punto p1
     */
    @Override
    public Point2D getP1(){
        PathIterator iterador = this.getPathIterator(null);
        double[] coordenadas = new double[6];
        iterador.currentSegment(coordenadas);
        Point2D loc = new Point2D.Double(coordenadas[0], coordenadas[1]); //primer punto
        return loc;
    }
    
    /**
     * Método que devuelve el nombre de la forma
     * @return El string del nombre de la forma
     */
    @Override
    public String toString(){
        return "Trazo Libre";
    }
    
    /**
     * Método que devuelve el color de la forma
     * @return el color de la forma
     */
    @Override
    public Color getColor() {
        return this.color;
    }
    
    /**
     * Método que establece el clor de la forma
     * @param color el color que tomara la forma
     */
    @Override
    public void setColor(Color color) {
        this.color = color;
    }
    
    /**
     * Método que devuelve el grosor de la forma
     * @return el grosor de la forma
     */
    @Override
    public int getGrosor() {
        return this.grosor;
    }
    
    /**
     * Método que establece el grosor de la forma
     * @param grosor El entero que indica el grosor de la forma 
     */
    @Override
    public void setGrosor(int grosor) {
        this.grosor = grosor;
    }
    
    /**
     * Método que devuelve si la forma esta rellena o no
     * @return en caso de estar relleno true en caso contrario false
     */
    @Override
    public boolean getRelleno() {
        return this.relleno;
    }
    
    /**
     * Método que establece si rellenar o no la figura
     * @param relleno el booleano correspondiente
     */
    @Override
    public void setRelleno(boolean relleno) {
        this.relleno = relleno;
    }
    
    /**
     * Método que devuelve si la forma esta alisada o no
     * @return el booleano correspondiente
     */
    @Override
    public boolean getAlisado() {
        return this.alisar;
    }
    
    /**
     * Método que establece si la forma tiene que estar alisada o no
     * @param alisado el booleano correspondiente
     */
    @Override
    public void setAlisado(boolean alisado) {
        this.alisar = alisado;
    }
    
    /**
     * Método que devuelve si la forma es transparente o no
     * @return el booleano correspondiente
     */
    @Override
    public boolean getTransparencia() {
        return this.transp;
    }  
    
    /**
     * Método que establece si la forma es transparente o no
     * @param transparencia el booleano correspondiente
     */
    @Override
    public void setTransparencia(boolean transparencia) {
        this.transp = transparencia;
    }
    
    /**
     * Método que se encarga de pintar la figura correspondiente.
     * Así como aplicarle los diferentes atributos seleccionados.
     * 
     * @param g2d objeto Graphics2D utilizado para pintar las formas.
     */
    @Override
    public void paint(Graphics2D g2d) {
        g2d.setPaint(color);
        
        stroke = new BasicStroke(grosor);
        g2d.setStroke(stroke);
        
        if(alisar)
            g2d.setRenderingHints(render);
        
        if(transp)
            g2d.setComposite(comp);
        
        if(relleno)
            g2d.fill(this);
        g2d.draw(this);
    }
}
