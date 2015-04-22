package modelo;

import java.awt.Rectangle;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Parede implements Serializable{
	
	private int x,y,tamX,tamY;
	
	public Parede(int x, int y, int tamX, int tamY) {
		super();
		this.x = x;
		this.y = y;
		this.tamX = tamX;
		this.tamY = tamY;
	}

	public Rectangle getColisao(){
		return new Rectangle(x, y, tamX, tamY);
	}
}
