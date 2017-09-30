package listas_combos_y_cuadros_de_dialogo;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class EjemploListaScroll extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	public static final String COMMAND_ANNADIR  = "Anadir";
	public static final String COMMAND_ELIMINAR = "Eliminar";
	public static final String COMMAND_LIMPIAR  = "Limpiar lista";
	public static final String COMMAND_SALIR    = "Salir";
	
	JTextField txtTexto;
	JList<String> lstLista;
	DefaultListModel<String> modelo;
	
	public EjemploListaScroll() {
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
			JPanel pnl = new JPanel();
			pnl.setLayout(new BorderLayout());
		
				pnl.add(new JLabel("Lista de la compra"),BorderLayout.NORTH);
		
					JButton btnSalir = new JButton(COMMAND_SALIR);
					btnSalir.setActionCommand(COMMAND_SALIR);
					btnSalir.addActionListener(this);
		
				pnl.add(btnSalir,BorderLayout.SOUTH);  
				
					JPanel pnlCentro = new JPanel();
					pnlCentro.setLayout(new BorderLayout());
					
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
						
						JScrollPane sclLista = new JScrollPane(lstLista);
						sclLista.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
					 // sclLista.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
						sclLista.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
					 //	sclLista.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
					
					pnlCentro.add(sclLista,BorderLayout.CENTER);
					
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
		
				pnl.add(pnlCentro,BorderLayout.CENTER);
		
		this.getContentPane().add(pnl);

	  //this.getRootPane().setDefaultButton(btnAnadir);
		
	  //Display the window.
		
		this.setSize(300, 300);
		this.setVisible(true);
		
    }
	
    @Override
	public void actionPerformed(ActionEvent e) {
    	
    	switch (e.getActionCommand()) {
    	
    	case COMMAND_ANNADIR:
    		
			if (!txtTexto.getText().trim().equals(""))
				aniadirItem(txtTexto.getText());
	    		txtTexto.setText("");
	    		txtTexto.requestFocus();
	    		break;
	    		
    	case COMMAND_ELIMINAR:
    		
    		eliminarItems();
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
		
        final EjemploListaScroll ej = new EjemploListaScroll();
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
        	
            public void run() {
                ej.setVisible(true);
            }
            
        });
        
    }

}
