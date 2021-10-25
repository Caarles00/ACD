package com.ieseljust.ad.figures;

// Llibreríes per a poder dibuixar 
import java.io.Serializable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

// Definim la classe rectangle a partir de la classe figura
// Heretarem per tant, la posició i el color
class Rectangle extends Figura  {

    // Té un nou atribut que serà el radi
    private Integer llarg;
    private Integer alt;

    // Constructors
    Rectangle() {
        // Sense paràmetres:
        super(); // Invoca al constructor del pare
        this.llarg = 0;
        this.llarg = 0;
    }

    Rectangle(int x, int y, int llarg, int alt, String color) {
        // Amb paràmetres:
        super(x, y, color); // Invoquem al constructor pare
        this.llarg = llarg; // Indiquem el valor de la llargària
        this.alt = alt; // Indiquem el valor de l'altura
    }

    public void renderText() {
        // Escriu les propietats de la figura
        System.out.println("Rectangle en (" + this.posicio.getX() + "," + this.posicio.getY() + ") de llarg " + this.llarg + ", altura " + this.alt + " i color " + this.color);
    }

    ;

    public void render(GraphicsContext gc) {
        // Dibuixem el rectangle amb el seu color, la posició i les dimensions
        Color color = Color.web(this.color);
        gc.setFill(color);

        gc.fillRect(this.posicio.getX(), this.posicio.getY(), this.llarg, this.alt);
        //gc.fillOval(this.posicio.getX(), this.posicio.getY(), this.radi*2, this.radi*2);
    }

    @Override
    public String getAsText() {
         return "Rectangle: " + this.posicio.getX() + ", " + this.posicio.getY() + ", " + this.llarg + ", " + this.alt + ", " + this.color;
    }

    @Override
    public Element getAsXML(Document doc) {
        Element e = doc.createElement("Rect");
        e.setAttribute("Fill", this.color);
        e.setAttribute("Height", String.valueOf(this.alt));
        e.setAttribute("Width", String.valueOf(this.llarg));
        e.setAttribute("PosicioX", String.valueOf(this.posicio.getX()));
        e.setAttribute("PosicioY", String.valueOf(this.posicio.getY()));

        return e;  
    }

    @Override
    public JSONObject getAsJSON() {
        JSONObject object = new JSONObject();
        object.put("PosicioX", this.posicio.getX());
        object.put("PosicioY", this.posicio.getY());
        object.put("Llarg", this.llarg);
        object.put("Alt", this.alt);
        object.put("Fill", this.color);

        JSONObject rectangle = new JSONObject();
        rectangle.put("Rectangle", object);
        return rectangle;    
    }

    
}
