package com.breakout;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

public class GameMain extends JFrame {

	private static final long serialVersionUID = 1L;
	Board boardGame;
	JPanel infoPanel;
	JLabel scoreLabel, levelLabel;
	public final JLabel score = new JLabel("0");
	public final JLabel level = new JLabel("1");

	public GameMain() {
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowDeactivated(WindowEvent e) {
				if (boardGame.gameIsRunning()) {
					setAlwaysOnTop(true);
				}else{
					setAlwaysOnTop(false);
				}
				super.windowDeactivated(e);
			}
		});
	}

	public void initGui() {

		setTitle("Breakout Game");
		setJMenuBar(getMenu());

		this.infoPanel = new JPanel();
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.X_AXIS));
		infoPanel.setBorder(BorderFactory.createEmptyBorder(1, 2, 1, 2));

		scoreLabel = new JLabel("Score: ");
		levelLabel = new JLabel("Level ");

		infoPanel.add(scoreLabel);
		infoPanel.add(score);
		infoPanel.add(Box.createGlue());
		infoPanel.add(levelLabel);
		infoPanel.add(level);

		this.boardGame = new Board();

		getContentPane().add(infoPanel, BorderLayout.NORTH);
		getContentPane().add(boardGame, BorderLayout.CENTER);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public JMenuBar getMenu() {

		JMenuBar bar = new JMenuBar();

		JMenu menu = new JMenu("Game");

		JMenuItem newGame = new JMenuItem("New...");
		newGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.ALT_MASK));
		newGame.addActionListener(e -> {
			int op = JOptionPane.showConfirmDialog(null, "Start a new Game?", "New Game", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE);

			if (op == JOptionPane.OK_OPTION) {
				boardGame.newGame();
			}
		});

		menu.add(newGame);
		bar.add(menu);

		return bar;
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(() -> {
			new GameMain().initGui();
		});
	}
}
