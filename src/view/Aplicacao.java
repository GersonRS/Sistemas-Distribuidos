package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JFrame;

import modelo.Controle;
import modelo.Logica;
import modelo.Personagem;
import controle.TratarTeclas;

public class Aplicacao extends JFrame {

	private static final long serialVersionUID = 1L;
	private Render render;
	// private Painel painel;
	// private Comandos comandos;
	private Logica logica;
	private Personagem p1, p2;

	static Dimension dimTela = Toolkit.getDefaultToolkit().getScreenSize();

	public Aplicacao(Socket s, int decisao) {
		super("3° VA de Sistemas Distribuidos");
		setSize(dimTela.width, dimTela.height);
		setPreferredSize(new Dimension(dimTela.width, dimTela.height));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setLayout(new BorderLayout());
		Mapa m = null;

		if (decisao == 1) {
			m = new Mapa();

			Controle c = new Controle(38, 39, 40, 37);

			ObjectOutputStream saida;

			p1 = nascer(1, c, m);
			p2 = nascer(2, c, m);

			try {
				saida = new ObjectOutputStream(s.getOutputStream());
				saida.writeObject(p2);
				saida.writeObject(p1);
				saida.writeObject(m);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (decisao == 2) {
			ObjectInputStream entrada;

			try {
				entrada = new ObjectInputStream(s.getInputStream());
				p1 = (Personagem) entrada.readObject();
				p2 = (Personagem) entrada.readObject();
				m = (Mapa) entrada.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			System.exit(0);
		}
		logica = new Logica(p1, s, m);
		logica.start();

		render = new Render(m, p1, p2, s);
		// painel = new Painel();
		// comandos = new Comandos();
		add(render, BorderLayout.CENTER);
		// add(painel,BorderLayout.NORTH);
		// add(comandos,BorderLayout.SOUTH);

		render.addKeyListener(new TratarTeclas());
		new Thread(render).start();

		setVisible(true);

	}

	private Personagem nascer(int a, Controle c, Mapa m) {
		int posX = 0;
		int posY = 0;

		int matriz[][] = m.getMatriz();

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				if (a == 1) {
					if (matriz[i][j] == 3) {
						return new Personagem(posX, posY, c);
					}
				} else if (a == 2) {
					if (matriz[i][j] == 2) {
						return new Personagem(posX, posY, c);
					}
				}
				posX += 16;
			}
			posY += 16;
			posX = 0;
		}
		return null;
	}
}
