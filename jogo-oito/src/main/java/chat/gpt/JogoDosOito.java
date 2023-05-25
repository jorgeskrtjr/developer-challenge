package chat.gpt;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


import org.example.classes.Peca;
import org.example.classes.Tabuleiro;

public class JogoDosOito extends JFrame implements KeyListener {

	private JButton botaoReiniciar;
	private Tabuleiro tabuleiro = new Tabuleiro();
	private JButton[][] botoes = new JButton[3][3];
	private List<Peca> listaPecas;

	public JogoDosOito() {

		this.listaPecas = new ArrayList<Peca>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 300);
		setLayout(new GridLayout(4, 3));

		int count = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				JButton botao = new JButton();
				botao.setFont(new Font("Arial", Font.BOLD, 36));
				botoes[i][j] = botao;
				add(botao);
				if (count < 8) {
					count++;
					listaPecas.add(new Peca(tabuleiro.getValue(i, j), i, j));
				}
				botoes[i][j].addActionListener((ActionEvent e) -> {
					listaPecas.forEach(peca -> {
						if (e.getActionCommand().equals(String.valueOf(peca.getnumero()))){
							movimentarPeca(peca.getLinha(), peca.getColuna());
						}
					});
				});
			}
		}

		botaoReiniciar = new JButton("Reiniciar");
		botaoReiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reiniciarJogo();
			}
		});
		add(new JLabel(""));
		add(botaoReiniciar);
		add(new JLabel(""));

		addKeyListener(this);
		setFocusable(true);
		atualizarTabuleiro();
		setVisible(true);
	}

	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		switch (keyCode) {
		case KeyEvent.VK_UP:
			mover(1, 0);
			break;
		case KeyEvent.VK_DOWN:
			mover(-1, 0);
			break;
		case KeyEvent.VK_LEFT:
			mover(0, 1);
			break;
		case KeyEvent.VK_RIGHT:
			mover(0, -1);
			break;
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

	private void mover(int linha, int coluna) {
		int linhaVazia = -1;
		int colunaVazia = -1;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (tabuleiro[i][j] == 0) {
					linhaVazia = i;
					colunaVazia = j;
				}
			}
		}
		int novaLinha = linhaVazia + linha;
		int novaColuna = colunaVazia + coluna;
		if (novaLinha < 0 || novaLinha > 2 || novaColuna < 0 || novaColuna > 2) {
			// movimento inválido
			return;
		}
		tabuleiro[linhaVazia][colunaVazia] = tabuleiro[novaLinha][novaColuna];
		tabuleiro[novaLinha][novaColuna] = 0;
		atualizarTabuleiro();
		if (jogoConcluido()) {
			JOptionPane.showMessageDialog(this, "Parabéns, você venceu!");
			reiniciarJogo();
		}
	}

	public static void main(String[] args) {
		new JogoDosOito();
	}

	private boolean jogoConcluido() {
		int count = 1;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (tabuleiro[i][j] != count % 9) {
					return false;
				}
				count++;
			}
		}
		return true;
	}

	private boolean movimentarPeca(int linha, int coluna) {
		if (linha > 0 && tabuleiro[linha - 1][coluna] == 0) {
			tabuleiro[linha - 1][coluna] = tabuleiro[linha][coluna];
			tabuleiro[linha][coluna] = 0;
			return true;
		} else if (linha < 2 && tabuleiro[linha + 1][coluna] == 0) {
			tabuleiro[linha + 1][coluna] = tabuleiro[linha][coluna];
			tabuleiro[linha][coluna] = 0;
			return true;
		} else if (coluna > 0 && tabuleiro[linha][coluna - 1] == 0) {
			tabuleiro[linha][coluna - 1] = tabuleiro[linha][coluna];
			tabuleiro[linha][coluna] = 0;
			return true;
		} else if (coluna < 2 && tabuleiro[linha][coluna + 1] == 0) {
			tabuleiro[linha][coluna + 1] = tabuleiro[linha][coluna];
			tabuleiro[linha][coluna] = 0;
			return true;
		}
		return false;
	}

	private void atualizarTabuleiro() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				JButton botao = botoes[i][j];
				int valor = tabuleiro[i][j];
				if (valor == 0) {
					botao.setText("");
				} else {
					botao.setText(String.valueOf(valor));
				}
			}
		}
	}

	private void reiniciarJogo() {
		tabuleiro = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
		atualizarTabuleiro();
	}
}
