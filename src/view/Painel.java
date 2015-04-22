package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Painel extends JPanel{

	private static final long serialVersionUID = 1L;

	public Painel() {
		setPreferredSize(new Dimension(0, 60));
		setBackground(Color.BLUE);
	}
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graficos = (Graphics2D) g;
		graficos.setColor(Color.WHITE);
		graficos.drawString("Primeiro Jogador :", 100, 30);
	}
}
