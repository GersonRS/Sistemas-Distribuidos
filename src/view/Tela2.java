package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class Tela2 extends JFrame implements Runnable {

	private JPanel contentPane;
	private JTextField textField;
	private String nome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela2 frame = new Tela2();
					frame.setVisible(true);
					new Thread(frame).start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the frame.
	 */
	public Tela2() {
		nome = JOptionPane.showInputDialog(null, "Digite seu Nome:");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 339, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Connectar:",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 303, 66);
		contentPane.add(panel);
		panel.setLayout(null);

		textField = new JTextField();
		textField.setBounds(10, 24, 166, 20);
		panel.add(textField);
		textField.setColumns(10);

		JButton btnConnectar = new JButton("Connectar");
		btnConnectar.setBounds(204, 23, 89, 23);
		panel.add(btnConnectar);
		btnConnectar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String endereco = textField.getText();
					int porta = 33333;
					Socket conexao = new Socket(endereco, porta);
					DataInputStream entrada = new DataInputStream(conexao
							.getInputStream());
					DataOutputStream saida = new DataOutputStream(conexao
							.getOutputStream());
					saida.writeUTF(nome);
					int decisao = entrada.readInt();
					System.out.println(decisao);
					if (decisao == 0) {
						dispose();
						new Aplicacao(conexao, 1);
					} else {
						entrada.close();
						saida.close();
						conexao.close();
					}
				} catch (UnknownHostException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
				null, null));
		panel_1.setBounds(10, 88, 303, 117);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblEsperandoConexao = new JLabel("Esperando Conexao");
		lblEsperandoConexao.setBounds(97, 46, 153, 23);
		panel_1.add(lblEsperandoConexao);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Meu Ip",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 216, 303, 35);
		contentPane.add(panel_2);

		String ip = null;
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (Exception e) {
		}
		panel_2.setLayout(null);

		JLabel lblAlgumacoisa = new JLabel(ip + "");
		lblAlgumacoisa.setBounds(118, 11, 100, 14);
		panel_2.add(lblAlgumacoisa);
	}

	@Override
	public void run() {

		try {
			ServerSocket serverSocket = new ServerSocket(36598);
			while (true) {
				Socket socket = serverSocket.accept();
				DataInputStream entrada = new DataInputStream(
						socket.getInputStream());
				DataOutputStream saida = new DataOutputStream(
						socket.getOutputStream());
				int decisao = JOptionPane
						.showConfirmDialog(
								null,
								entrada.readUTF()
										+ " esta lhe desafiando. Você aceita o desafio?",
								"Desafio", JOptionPane.OK_OPTION);
				if (decisao == 0) {
					saida.writeInt(decisao);
					dispose();
					new Aplicacao(socket, 2);
					break;
				} else {
					entrada.close();
					saida.close();
					socket.close();
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
