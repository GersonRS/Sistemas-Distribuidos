package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JPanel;

import modelo.Personagem;

public class Render extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	private BufferedImage tela = new BufferedImage(208, 208,
			BufferedImage.TYPE_4BYTE_ABGR);
	private Graphics2D graphics;
	private Mapa map;
	private Personagem p1;
	private Personagem p2;
	private DataInputStream entrada;
	private Graphics2D graficos;

	boolean run = true; 
	int p1x = 0, p1y = 0;
	int p2x = 0, p2y = 0;

	public Render(Mapa m, Personagem p1, Personagem p2, Socket s) {
		this.p1 = p1;
		this.p2 = p2;
		p1x = p1.getPosX();
		p1y = p1.getPosY();
		p2x = p2.getPosX();
		p2y = p2.getPosY();
		try {
			// this.entrada = new ObjectInputStream(conexao.getInputStream());
			entrada = new DataInputStream(s.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		graphics = (Graphics2D) tela.getGraphics();
		setSize(640, 480);
		setPreferredSize(new Dimension(640, 480));
		setDoubleBuffered(true);
		setFocusable(true);
		requestFocus();
		setBackground(Color.BLUE);

		this.map = m;
	}

	public void paint(Graphics g) {

		graficos = (Graphics2D) g;
		graficos.drawRect(0, 0, getWidth(), getHeight());
		graphics.drawImage(map.getMapa(), 0, 0, null);
		graphics.drawImage(p1.getImagem().getImage(), p1.getPosX(),
				p1.getPosY(), null);
		graphics.drawImage(p2.getImagem().getImage(), p2.getPosX(),
				p2.getPosY(), null);
		graphics.drawImage(map.getSombra().getImage(), p1.getPosX() - 354,
				p1.getPosY() - 294, null);
		graficos.drawImage(tela.getScaledInstance(Aplicacao.dimTela.width,
				Aplicacao.dimTela.height, Image.SCALE_DEFAULT), 0, 0, null);
		if(ganhou()==1){
			graficos.setColor(Color.BLACK);
			graficos.fillRect(0, 0, getWidth(), getHeight());
			graficos.setColor(Color.WHITE);
			graficos.drawString("Você Ganhou", 100, 100);
			run=false;
		}
		if(ganhou()==2){
			graficos.setColor(Color.BLACK);
			graficos.fillRect(0, 0, getWidth(), getHeight());
			graficos.setColor(Color.WHITE);
			graficos.drawString("Você Perdeu", 100, 100);
			run=false;
		}
	}
	
	public int ganhou(){
		if(p1.getPosX()==p2x && p1.getPosY()==p2y){
			return 1;
		}
		if(p2.getPosX()==p1x && p2.getPosY()==p1y){
			return 2;
		}
		return 0;
	}

	public void run() {
		map.montarMapa();
		while (run) {
			repaint();
			try {
				p2.setPosX(entrada.readInt());
				p2.setPosY(entrada.readInt());
				Thread.sleep(50);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			Thread.sleep(3000);
			Aplicacao aplic = (Aplicacao) getParent();
			aplic.dispose();
			Tela tela = new Tela();
			new Thread(tela).start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
