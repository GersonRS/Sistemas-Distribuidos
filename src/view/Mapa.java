package view;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import modelo.Maze;
import modelo.Parede;

public class Mapa implements Serializable{

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//	private BufferedImage tileSet;
	private BufferedImage mapa;
//	private BufferedImage sombra;

	private ImageIcon tileSet;
//	private ImageIcon mapa;
	private ImageIcon sombra;
	
	private final static int tamanhoTileX = 16;
	private final static int tamanhoTileY = 16;
	private int matriz[][];

	private List<Parede> colisoes;

	public Mapa() {
		matriz = Maze.createMaze(6);
		colisoes  = colisao();
		tileSet = new ImageIcon(this.getClass().getClassLoader()
				.getResource("TileSet.png"));
		sombra = new ImageIcon(this.getClass().getClassLoader()
				.getResource("sombra.png"));
		
//		try {
//			tileSet = ImageIO.read(this.getClass().getClassLoader()
//					.getResource("TileSet.png"));
//			sombra = ImageIO.read(this.getClass().getClassLoader()
//					.getResource("sombra.png"));
//		} catch (IOException e) {
//			System.out
//					.println("Erro ao buscar imagem do mapa.\nEncerrando aplicação");
//			System.exit(0);
//		}
	}
	
	private List<Parede> colisao(){
		colisoes = new ArrayList<Parede>();
		int posX = 0;
		int posY = 0;
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				if (matriz[i][j] == 1) {
					colisoes.add(new Parede(posX, posY, 16,16));
				}
				posX += tamanhoTileX;
			}
			posY += tamanhoTileY;
			posX = 0;
		}
		return colisoes;
	}

	public void montarMapa() {

		mapa = new BufferedImage(matriz[0].length * tamanhoTileX, matriz.length
				* tamanhoTileY, BufferedImage.TYPE_4BYTE_ABGR);

		int posX = 0;
		int posY = 0;
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				mapa.getGraphics().drawImage(tileSet.getImage(), posX, posY,
						posX + tamanhoTileX, posY + tamanhoTileY,
						(matriz[i][j] % 3) * tamanhoTileX, 0,
						(matriz[i][j] % 3) * tamanhoTileX + tamanhoTileX,
						tamanhoTileY, null);
				posX += tamanhoTileX;
			}
			posY += tamanhoTileY;
			posX = 0;
		}
	}

	public BufferedImage getMapa() {
		return mapa;
	}

//	public BufferedImage getSombra() {
//		return sombra;
//	}

	public int[][] getMatriz() {
		return matriz;
	}

	public ImageIcon getSombra() {
		return sombra;
	}

	public List<Parede> getColisoes() {
		return colisoes;
	}

}
