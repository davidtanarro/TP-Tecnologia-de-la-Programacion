package listas_combos_y_cuadros_de_dialogo;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class EjemploCombo1 extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	JLabel lblTexto;
	JComboBox<String> lstLista;
	
	public EjemploCombo1() {
		
		super("Mi toolbox");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
		String[] valores = {"tornillos", "arandelas", "tuercas", "copetes", "martillos"};
		
		lblTexto = new JLabel("");
		lstLista = new JComboBox<String>(valores);
		// lstLista.setVisibleRowCount(5);  // no es aplicable
		
		lstLista.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				lblTexto.setText((String)lstLista.getSelectedItem());// El metodo get cambia.
			}
			
		});
		
		JButton btnSalir = new JButton("Salir");
		
		btnSalir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				EjemploCombo1.this.dispose();
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
    	
        final EjemploCombo1 ej = new EjemploCombo1();
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
        	
            public void run() {
                ej.setVisible(true);
            }
            
        });
        
    }

}
