package listas_combos_y_cuadros_de_dialogo;

// Ejemplo de lista con modelo y combo con array de elementos (enumerado).
// Incluye cOdigo para selecciOn mUltiple en la lista.

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class EjemploListaYCombo3 extends JFrame implements ActionListener {
	
	public static final String COMMAND_ANNADIR  = "Anadir";
	public static final String COMMAND_ELIMINAR = "Eliminar";
	public static final String COMMAND_LIMPIAR  = "Limpiar lista";
	public static final String COMMAND_SALIR    = "Salir";
	
	JTextField txtTexto;
	JList<String> lstLista;
	JComboBox<Ferreteria> cboFerr;
	DefaultListModel<String> modelo;
	
	enum Ferreteria {		
		
		TORNILLOS,
		TUERCAS,
		ARANDELAS;
		
	}
	
	public EjemploListaYCombo3() {
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
	        JPanel pnl = new JPanel();
	        pnl.setLayout(new BorderLayout());
	    	this.getContentPane().add(pnl);
	    	
	    	JPanel pnlNorte = new JPanel();
	    	
	    		pnlNorte.setLayout(new GridLayout(2,1,5,5));
	    		pnlNorte.add(new JLabel("Lista de la compra"));
	    		
	    			cboFerr = new JComboBox<Ferreteria>(Ferreteria.values());		
	    			cboFerr.addActionListener(new ActionListener() {
	    				
	    				@Override
	    				public void actionPerformed(ActionEvent e) {
	    					aniadirItem(cboFerr.getSelectedItem().toString());
	    				}
	    				
	    			});
	    		
	    		pnlNorte.add(cboFerr);
	
	    	pnl.add(pnlNorte,BorderLayout.NORTH);
	    	
	    		JButton btnSalir = new JButton(COMMAND_SALIR);
	    		btnSalir.addActionListener(this);
	
	    	pnl.add(btnSalir,BorderLayout.SOUTH);      	
	
	    		JPanel pnlCentro = new JPanel();
	        	pnlCentro.setLayout(new BorderLayout());
	    		
		        	JPanel pnlTexto = new JPanel();
		        	pnlTexto.setLayout(new GridLayout(1,2,5,5));
		        	
		        		txtTexto = new JTextField("");
		        	
		        	pnlTexto.add(txtTexto);
		        	
		    			JButton btnAnadir = new JButton(COMMAND_ANNADIR);
		    			btnAnadir.setActionCommand(COMMAND_ANNADIR);  //no es necesario
		    			btnAnadir.addActionListener(this);  // en ventanas sencillas el listener puede ser el JFrame.
		        	
		        	pnlTexto.add(btnAnadir);
		        	
		        pnlCentro.add(pnlTexto,BorderLayout.NORTH);
		        
					modelo = new DefaultListModel<String>();
					lstLista = new JList<String>(modelo);
					lstLista.setVisibleRowCount(5);
		        
		        pnlCentro.add(lstLista,BorderLayout.CENTER);
		        
		        		JPanel pnlBotones = new JPanel();
		        		pnlBotones.setLayout(new GridLayout(1,2,5,5));
		        	
			        		JButton btnEliminar = new JButton(COMMAND_ELIMINAR);
			        		btnEliminar.addActionListener(this);  
		        		
		        		pnlBotones.add(btnEliminar);
		        		
			        		JButton btnLimpiar = new JButton(COMMAND_LIMPIAR);
			        		btnLimpiar.addActionListener(this);
		        		
		        		pnlBotones.add(btnLimpiar);
		        	
		        	pnlCentro.add(pnlBotones,BorderLayout.SOUTH);
	    		
	    	pnl.add(pnlCentro,BorderLayout.CENTER);
    	
    	this.getRootPane().setDefaultButton(btnAnadir);
        this.setSize(300, 200);
        this.setVisible(true);
    }
	
    @Override
	public void actionPerformed(ActionEvent e) {
    	
    	switch (e.getActionCommand()) {
    	
    	case COMMAND_ANNADIR:
    		
    		aniadirItem(txtTexto.getText());
    		txtTexto.setText("");
    		txtTexto.requestFocus();
    		break;
    		
    	case COMMAND_ELIMINAR:
    		
    		eliminarItems();
    		txtTexto.setText("");
    		txtTexto.requestFocus();
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

    private void aniadirItem(String s) {
    	
    	if (modelo.contains(s)) {
    		JOptionPane.showMessageDialog(this, "Ya esta en la lista.");
    	} else 
    		modelo.addElement(s);
    	
    }

    private void eliminarItems() {
    	
    	List<String> seleccion = lstLista.getSelectedValuesList();
    	
    	for (String elem : seleccion)
    		modelo.removeElement(elem);
    	
    }

	public static void main(String[] args) {
		
        //Schedule a job for the event-dispatching thread:
		
        final EjemploListaYCombo3 ej = new EjemploListaYCombo3();
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
        	
            public void run() {
                ej.setVisible(true);
            }
            
        });
        
    }

}
