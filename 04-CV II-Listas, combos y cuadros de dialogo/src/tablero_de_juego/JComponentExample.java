package tablero_de_juego;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


@SuppressWarnings("serial")
public class JComponentExample extends JFrame {

	private BoardComponent board;
	private JTextField rows;
	private JTextField cols;

	public JComponentExample() {
		super("JComponent Example");
		initGUI();
	}

	private void initGUI() {
		
			JPanel mainPanel = new JPanel(new BorderLayout());
	
				board = new BoardComponent(10, 10);
				
			mainPanel.add(board, BorderLayout.CENTER);
			
				JPanel sizePabel = new JPanel();
				
					sizePabel.add(new JLabel("Rows"));
					
						rows = new JTextField(5);
						
					sizePabel.add(rows);
					
					sizePabel.add(new JLabel("Cols"));
					
						cols = new JTextField(5);
					
					sizePabel.add(cols);
					
						JButton setSize = new JButton("Set Size");
						
						setSize.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								try {
									int x = new Integer(rows.getText());
									int y = new Integer(cols.getText());
									board.setBoardSize(x, y);
								} catch (NumberFormatException _e) {
								}
							}
							
						});
						
					sizePabel.add(setSize);
					
			mainPanel.add(sizePabel, BorderLayout.PAGE_START);
	
			mainPanel.setOpaque(true);
			
		this.setContentPane(mainPanel);
		
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {
				
				new JComponentExample();
				
			}
			
		});
		
	}
	
}
