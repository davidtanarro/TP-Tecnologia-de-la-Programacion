package JMenuBar;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EjemploJMenuBar1 extends JFrame {
	
	private static final long serialVersionUID = 1L;

	public EjemploJMenuBar1() {
		
		super("Ejemplo de barra de menu");
		this.setSize(300, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
			JMenuBar menuBar = new JMenuBar();
			
			MiMenuListener menuLst = new MiMenuListener();
			
				JMenu menuArchivo = new JMenu("Archivo");
					
						JMenuItem menuItemAbrir  = new JMenuItem("Abrir");
						menuItemAbrir.addActionListener(menuLst);
						menuArchivo.add(menuItemAbrir);
							
						JMenuItem menuItemCerrar = new JMenuItem("Cerrar");
						menuItemCerrar.addActionListener(menuLst);
						menuArchivo.add(menuItemCerrar);
						
						menuArchivo.add(new JSeparator());
						
						JMenuItem menuItemSalir  = new JMenuItem("Salir");
						menuItemSalir.addActionListener(menuLst);
						menuArchivo.add(menuItemSalir);
					
			menuBar.add(menuArchivo);
			
				JMenu menuEditar = new JMenu("Editar");
				
						JMenuItem menuItemCortar = new JMenuItem("Cortar");
						menuItemCortar.addActionListener(menuLst);
						menuEditar.add(menuItemCortar);
						
						JMenuItem menuItemCopiar = new JMenuItem("Copiar");
						menuItemCopiar.addActionListener(menuLst);
						menuEditar.add(menuItemCopiar);
						
						JMenuItem menuItemPegar = new JMenuItem("Pegar");
						menuItemPegar.addActionListener(menuLst);
						menuEditar.add(menuItemPegar);
						
						
							JMenu menuZoom = new JMenu("Zoom");
							
								JMenuItem menuItemAgrandar = new JMenuItem("+");
								menuItemAgrandar.addActionListener(menuLst);
								menuZoom.add(menuItemAgrandar);						
							
								JMenuItem menuItemDisminuir = new JMenuItem("-");
								menuItemDisminuir.addActionListener(menuLst);
								menuZoom.add(menuItemDisminuir);
	
				menuEditar.add(menuZoom);
	
			
			menuBar.add(menuEditar);
		
		this.setJMenuBar(menuBar);
		
	}

	private class MiMenuListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			JOptionPane.showMessageDialog( EjemploJMenuBar1.this, 
					                       "Se ha pulsado en la opcion de menu: " + e.getActionCommand(), 
					                       "Informacion del menu", 
					                       JOptionPane.DEFAULT_OPTION );		
			
		}
		
	}
	
	public static void main(String[] args) {
		
		final EjemploJMenuBar1 v = new EjemploJMenuBar1();
		
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				v.setVisible(true);
			}
			
		});
		
	}
	
}
