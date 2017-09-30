package listas_combos_y_cuadros_de_dialogo;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class EjemploLista2 extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	public static final String COMMAND_ANNADIR  = "Anadir";
	public static final String COMMAND_ELIMINAR = "Eliminar";
	public static final String COMMAND_LIMPIAR  = "Limpiar lista";
	public static final String COMMAND_SALIR    = "Salir";
	
	JTextField txtTexto;
	JList<String> lstLista;
	DefaultListModel<String> modelo;
	
	public EjemploLista2() {
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
        	JPanel pnl = new JPanel();
        	pnl.setLayout(new BorderLayout());
        	this.getContentPane().add(pnl);
    	
        	pnl.add(new JLabel("Lista de la compra"),BorderLayout.NORTH);
    	
    			JButton btnSalir = new JButton(COMMAND_SALIR);
    			btnSalir.setActionCommand(COMMAND_SALIR);
    			btnSalir.addActionListener(this);
    	
    		pnl.add(btnSalir,BorderLayout.SOUTH);      	

    			JPanel pnlCentro = new JPanel();
    			pnlCentro.setLayout(new BorderLayout());
   
    		pnl.add(pnlCentro,BorderLayout.CENTER);
    		
    		// PANEL CENTRAL
    	
    				JPanel pnlTexto = new JPanel();
    				pnlTexto.setLayout(new GridLayout(1,2,5,5));
      		
      					txtTexto = new JTextField("");
      		
      				pnlTexto.add(txtTexto);
      		
      					JButton btnAnadir = new JButton(COMMAND_ANNADIR);
      					btnAnadir.setActionCommand(COMMAND_ANNADIR);
      					btnAnadir.addActionListener(this);  // en ventanas sencillas
      		
      				pnlTexto.add(btnAnadir);
      		
      			pnlCentro.add(pnlTexto,BorderLayout.NORTH);
      		
      				modelo = new DefaultListModel<String>();
      				lstLista = new JList<String>(modelo);
      				lstLista.setVisibleRowCount(5);
      		
      			pnlCentro.add(lstLista,BorderLayout.CENTER);
    	
      				JPanel pnlBotones = new JPanel();
      				pnlBotones.setLayout(new GridLayout(1,2,5,5));
    	
      					JButton btnEliminar = new JButton(COMMAND_ELIMINAR);
      					btnEliminar.setActionCommand(COMMAND_ELIMINAR);
      					btnEliminar.addActionListener(this);  // en ventanas sencillas
      		
      				pnlBotones.add(btnEliminar);
      			
      					JButton btnLimpiar = new JButton(COMMAND_LIMPIAR);
      					btnLimpiar.setActionCommand(COMMAND_LIMPIAR);
      					btnLimpiar.addActionListener(this);  // en ventanas sencillas
      			
      				pnlBotones.add(btnLimpiar);
      			
      			pnlCentro.add(pnlBotones,BorderLayout.SOUTH);
    	
        this.setSize(400, 300);
        this.setVisible(true);
        
    }
	
    @Override
	public void actionPerformed(ActionEvent e) {
    	
    	switch (e.getActionCommand()) {
    	
    	case COMMAND_ANNADIR:
    		
    		modelo.addElement(txtTexto.getText());
    		txtTexto.setText("");
    		txtTexto.requestFocus();
    		break;
    		
    	case COMMAND_ELIMINAR:
    		
    		modelo.removeElement(lstLista.getSelectedValue());
    		txtTexto.setText("");
    		break;
    		
    	case COMMAND_LIMPIAR:
    		
    		modelo.removeAllElements();
    		txtTexto.setText("");
    		break;
    		
    	case COMMAND_SALIR:
    		
    		this.dispose();
    		break;
    		
    	}
    	
	}

	public static void main(String[] args) {
		
        //Schedule a job for the event-dispatching thread:
		
        final EjemploLista2 ej = new EjemploLista2();
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
        	
            public void run() {
                ej.setVisible(true);
            }
            
        });
        
    }

}
