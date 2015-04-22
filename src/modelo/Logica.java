package modelo;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

import view.Mapa;

public class Logica extends Thread {

	private static Personagem p1;
	private List<Parede> paredes;
	public static Controle controle;
	private DataOutputStream saida;

	enum direcao {
		CIMA, DIREITA, BAIXO, ESQUERDA
	};

	public Logica(Personagem p,Socket s,Mapa m) {
		controle = p.getControle();
		p1 = p;
		this.paredes = m.getColisoes();
		try {
//			saida = new ObjectOutputStream(socket.getOutputStream());
			saida = new DataOutputStream(s.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while (true) {			
			tratarJogo();
			try {
				saida.writeInt(p1.getPosX());
				saida.writeInt(p1.getPosY());
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	private void tratarJogo() {
		moverJogador();
		
	}

	private void moverJogador() {
		int posXAnterior = p1.getPosX();
		int posYAnterior = p1.getPosY();

		Controle controle = p1.getControle();

		if (controle.isBotaoCima()) {
			p1.andar(direcao.CIMA.ordinal());
		}
		if (controle.isBotaoDireita()) {
			p1.andar(direcao.DIREITA.ordinal());
		}
		if (controle.isBotaoBaixo()) {
			p1.andar(direcao.BAIXO.ordinal());
		}
		if (controle.isBotaoEsquerda()) {
			p1.andar(direcao.ESQUERDA.ordinal());
		}

		tratarSairTela(posXAnterior, posYAnterior);
		tratarColisao(posXAnterior, posYAnterior);

	}

	private void tratarColisao(int posXAnterior, int posYAnterior) {

		for (Parede r : paredes) {
			if (p1.colisao().intersects(r.getColisao())) {
				p1.setPosX(posXAnterior);
				p1.setPosY(posYAnterior);
			}
		}
	}

	private void tratarSairTela(int posX, int posY) {

		if ((p1.getPosX() < 0) || ((p1.getPosX() + 16) > 208)) {
			p1.setPosX(posX);
		}
		if ((p1.getPosY() < 0) || ((p1.getPosY() + 16) > 208)) {
			p1.setPosY(posY);
		}
	}

	public static Personagem getP1() {
		return p1;
	}
	
}
