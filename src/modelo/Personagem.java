package modelo;

import java.awt.Rectangle;
import java.io.Serializable;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class Personagem implements Serializable{

	private int posX;
	private int posY;
	private final int velocidade=2;
//	private BufferedImage imagem;
	private ImageIcon imagem;
	private Controle controle;	
	
	public Personagem(int posX, int posY,Controle controle) {
		this.posX = posX;
		this.posY = posY;
		this.controle = controle;
		this.imagem = new ImageIcon(getClass().getClassLoader().getResource("personagem.png"));
//		try {
//			imagem = ImageIO.read(this.getClass().getClassLoader()
//					.getResource("personagem.png"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	
	
	public void andar(int novaDirecao) {
		switch (novaDirecao) {
		case 0:
			posY -= (velocidade);
			break;
		case 1:
			posX += (velocidade);
			break;
		case 2:
			posY += (velocidade);
			break;
		case 3:
			posX -= (velocidade);
			break;
		}
	}
	
	public Rectangle colisao(){
		return new Rectangle(posX+1, posY+1, 14, 14);	
	}
	
	public int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public int getPosY() {
		return posY;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}
//	public BufferedImage getImagem() {
//		return imagem;
//	}

	public Controle getControle() {
		return controle;
	}


	public ImageIcon getImagem() {
		return imagem;
	}


	public void setControle(Controle controle) {
		this.controle = controle;
	}
	
}