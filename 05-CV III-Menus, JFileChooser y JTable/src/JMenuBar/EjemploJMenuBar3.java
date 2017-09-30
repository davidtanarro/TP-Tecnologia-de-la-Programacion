package JMenuBar;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;


public class EjemploJMenuBar3 extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public EjemploJMenuBar3() {
		
		super("Ejemplo de barra de menu");
		this.setSize(300, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
			JMenuBar menuBar = new JMenuBar();
			
			MiMenuListener menuLst = new MiMenuListener();
			
				JMenu menuArchivo = new JMenu("Archivo");
				menuArchivo.setMnemonic(KeyEvent.VK_A);
					
						JMenuItem menuItemAbrir = new JMenuItem("Abrir",KeyEvent.VK_A);
						menuItemAbrir.addActionListener(menuLst);
						menuArchivo.add(menuItemAbrir);
							
						JMenuItem menuItemCerrar = new JMenuItem("Cerrar",KeyEvent.VK_C);
						menuItemCerrar.addActionListener(menuLst);
						menuArchivo.add(menuItemCerrar);
						
						menuArchivo.add(new JSeparator());
						
						JMenuItem menuItemSalir = new JMenuItem("Salir",KeyEvent.VK_S);
						menuItemSalir.addActionListener(menuLst);
						menuArchivo.add(menuItemSalir);
					
			menuBar.add(menuArchivo);
			
				JMenu menuEditar = new JMenu("Editar");
				menuEditar.setMnemonic(KeyEvent.VK_E);
				
						JMenuItem menuItemCortar = new JMenuItem("Cortar",KeyEvent.VK_O);
						menuItemCortar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
						menuItemCortar.addActionListener(menuLst);
						menuEditar.add(menuItemCortar);
						
						JMenuItem menuItemCopiar = new JMenuItem("Copiar",KeyEvent.VK_P);
						menuItemCopiar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK +ActionEvent.ALT_MASK));
						menuItemCopiar.addActionListener(menuLst);
						menuEditar.add(menuItemCopiar);
						
						JMenuItem menuItemPegar = new JMenuItem("Pegar",KeyEvent.VK_G);
						menuItemPegar.addActionListener(menuLst);
						menuEditar.add(menuItemPegar);
						
						
							JMenu menuZoom = new JMenu("Zoom");
							menuZoom.setMnemonic(KeyEvent.VK_Z);
							
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
			
			JOptionPane.showMessageDialog( EjemploJMenuBar3.this, 
										   "Se ha pulsado en la opcion de menu: " + e.getActionCommand(), 
										   "Informacion del menu", 
										   JOptionPane.DEFAULT_OPTION );
			
		}
		
	}
	
	public static void main(String[] args) {
		
		final EjemploJMenuBar3 v = new EjemploJMenuBar3();
		
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				v.setVisible(true);
			}
			
		});
		
	}
	
}
