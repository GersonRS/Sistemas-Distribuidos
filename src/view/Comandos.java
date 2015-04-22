package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Comandos extends JPanel{

	private static final long serialVersionUID = 1L;

	public Comandos() {
		setSize(new Dimension(240, 100));
		setPreferredSize(new Dimension(0, 100));
		setBackground(Color.GREEN);
	}
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graficos = (Graphics2D) g;
		graficos.setColor(Color.WHITE);
		graficos.drawString("Comandos do Jogador :", 100, 30);
	}
}
