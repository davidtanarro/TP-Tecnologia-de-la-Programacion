// Ejemplo 3
package Anonimas;

import javax.swing.*;        

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class HelloWorldSwing {
	
	private static void createAndShowGUI() {
		
		//Create and set up the window.
		JFrame frame = new JFrame("HelloWorldSwing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Add the ubiquitous "Hello World" label.
		JButton btn = new JButton("Hola, pœlsame!");
		btn.setFont(new Font("Dialog",Font.BOLD,36));
		
		btn.addActionListener(
				
				new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						System.out.println("Ups! me has pulsado.");
					}
					
				} );
		
		frame.getContentPane().add(btn);

		//Display the window.
		frame.pack();
		frame.setVisible(true);
		
	}

	public static void main(String[] args) {
		
		//Schedule a job for the event-dispatching thread:
		//creating and showing this application's GUI.
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {
				createAndShowGUI();
			}
			
		});
		
	}
	
}
