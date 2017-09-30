package listas_combos_y_cuadros_de_dialogo;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EjemploLista1 extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	JLabel lblTexto;
	JList<String> lstLista;
	
	// Lista basada en un array de elementos.

	public EjemploLista1() {
		
		super("Mi toolbox");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
		String[] valores = {"tornillos", "arandelas", "tuercas", "copetes", "martillos"};
		
		lblTexto = new JLabel("");
		lstLista = new JList<String>(valores);
		lstLista.setVisibleRowCount(5);
		
		lstLista.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				lblTexto.setText(lstLista.getSelectedValue());
			}
			
		});
		
		JButton btnSalir = new JButton("Salir");
		
		btnSalir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				EjemploLista1.this.dispose();
			}
			
		});
		
    	JPanel pnl = new JPanel();
        pnl.setLayout(new GridLayout(3,2,5,5));
        
       	pnl.add(new JLabel("Lista:"));
       	pnl.add(lstLista);
       	
       	pnl.add(new JLabel("Elemento seleccionado:"));
    	pnl.add(lblTexto);
      	
       	pnl.add(new JLabel(""));
    	pnl.add(btnSalir);
    	
    	this.add(pnl);
    		
        //Display the window.
    	
        this.setSize(400, 300);
        this.pack();
        this.setVisible(true);
        
    }

    public static void main(String[] args) {
    	
        //Schedule a job for the event-dispatching thread:
    	
        final EjemploLista1 ej = new EjemploLista1();
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
        	
            public void run() {
                ej.setVisible(true);
            }
            
        });
        
    }

}
