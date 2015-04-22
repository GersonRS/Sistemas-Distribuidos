package modelo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Controle implements Serializable{

	private  int cima;
	private  int direita;
	private  int baixo;
	private  int esquerda;

	private boolean botaoCima = false;
	private boolean botaoDireita = false;
	private boolean botaoBaixo = false;
	private boolean botaoEsquerda = false;

	public Controle(int cima, int direita, int baixo, int esquerda) {
		this.cima = cima;
		this.direita = direita;
		this.baixo = baixo;
		this.esquerda = esquerda;
	}

	public int getDireita() {
		return direita;
	}

	public int getCima() {
		return cima;
	}

	public int getBaixo() {
		return baixo;
	}

	public int getEsquerda() {
		return esquerda;
	}

	public void setCima(int cima) {
		this.cima = cima;
	}

	public void setDireita(int direita) {
		this.direita = direita;
	}

	public void setBaixo(int baixo) {
		this.baixo = baixo;
	}

	public void setEsquerda(int esquerda) {
		this.esquerda = esquerda;
	}

	public boolean isBotaoCima() {
		return botaoCima;
	}

	public void setBotaoCima(boolean botaoCima) {
		this.botaoCima = botaoCima;
	}

	public boolean isBotaoBaixo() {
		return botaoBaixo;
	}

	public void setBotaoBaixo(boolean botaoBaixo) {
		this.botaoBaixo = botaoBaixo;
	}

	public boolean isBotaoDireita() {
		return botaoDireita;
	}

	public void setBotaoDireita(boolean botaoDireita) {
		this.botaoDireita = botaoDireita;
	}

	public boolean isBotaoEsquerda() {
		return botaoEsquerda;
	}

	public void setBotaoEsquerda(boolean botaoEsquerda) {
		this.botaoEsquerda = botaoEsquerda;
	}
}
