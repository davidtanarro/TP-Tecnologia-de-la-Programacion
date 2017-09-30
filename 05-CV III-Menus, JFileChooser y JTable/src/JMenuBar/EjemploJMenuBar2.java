package JMenuBar;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


public class EjemploJMenuBar2 extends JFrame {
	
	private static final long serialVersionUID = 1L;

	public EjemploJMenuBar2() {
		
		super("Ejemplo de barra de menu");
		this.setSize(300, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
			JMenuBar menuBar = new JMenuBar();
			
			MiMenuListener menuLst = new MiMenuListener();
			
				JMenu menuCheckBoxes = new JMenu("Check Boxes");
			
					JCheckBoxMenuItem menuItemContinuo = new JCheckBoxMenuItem("Vista continua");
					menuItemContinuo.addActionListener(menuLst);
					
				menuCheckBoxes.add(menuItemContinuo);
					
					JCheckBoxMenuItem menuItemPresentacion = new JCheckBoxMenuItem("Modo presentacion");
					menuItemPresentacion.addActionListener(menuLst);
			
				menuCheckBoxes.add(menuItemPresentacion);
			
			menuBar.add(menuCheckBoxes);		
			
				JMenu menuRadioButtons = new JMenu("Radio Buttons");
				
					ButtonGroup bgEditar = new ButtonGroup();
					
						JRadioButtonMenuItem menuItemCortar = new JRadioButtonMenuItem("Cortar");
						menuItemCortar.addItemListener(menuLst);
						
					bgEditar.add(menuItemCortar);
						
						JRadioButtonMenuItem menuItemCopiar = new JRadioButtonMenuItem("Copiar");
						menuItemCopiar.addItemListener(menuLst);
						
					bgEditar.add(menuItemCopiar);
					
				menuRadioButtons.add(menuItemCortar);
						
						JRadioButtonMenuItem menuItemPegar = new JRadioButtonMenuItem("Pegar");
						menuItemPegar.addItemListener(menuLst);
	
					bgEditar.add(menuItemPegar);
					
				menuRadioButtons.add(menuItemPegar);
				
				menuRadioButtons.add(new JSeparator());
			
					ButtonGroup bgZoom = new ButtonGroup();
					
						JRadioButtonMenuItem menuItemAgrandar = new JRadioButtonMenuItem("Agrandar");
						menuItemAgrandar.addItemListener(menuLst);
						
					bgZoom.add(menuItemAgrandar);
						
				menuRadioButtons.add(menuItemAgrandar);
						
						JRadioButtonMenuItem menuItemDisminuir = new JRadioButtonMenuItem("Disminuir");
						menuItemDisminuir.addItemListener(menuLst);
						
					bgZoom.add(menuItemDisminuir);
					
				menuRadioButtons.add(menuItemDisminuir);
						
						JRadioButtonMenuItem menuItem100 = new JRadioButtonMenuItem("100%");
						menuItem100.addItemListener(menuLst);
						
					bgZoom.add(menuItem100);
					
				menuRadioButtons.add(menuItem100);
						
						JRadioButtonMenuItem menuItem75 = new JRadioButtonMenuItem("75%");
						menuItem75.addItemListener(menuLst);
						
					bgZoom.add(menuItem75);
					
				menuRadioButtons.add(menuItem75);
						
						JRadioButtonMenuItem menuItem50 = new JRadioButtonMenuItem("50%");
						menuItem50.addItemListener(menuLst);
						
					bgZoom.add(menuItem50);
					
				menuRadioButtons.add(menuItem50);
						
						JRadioButtonMenuItem menuItem25 = new JRadioButtonMenuItem("25%");
						menuItem25.addItemListener(menuLst);
						
					bgZoom.add(menuItem25);
					
				menuRadioButtons.add(menuItem25);
				
			
			menuBar.add(menuRadioButtons);
		
		this.setJMenuBar(menuBar);
		
	}

	private class MiMenuListener implements ActionListener, ItemListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			JOptionPane.showMessageDialog( EjemploJMenuBar2.this, 
					                       "Se ha pulsado en la opcion de menu: " + e.getActionCommand(), 
					                       "Informacion del menu", 
					                       JOptionPane.DEFAULT_OPTION );
			
		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			
			AbstractButton b = (AbstractButton)e.getSource();
			
			if (b.isSelected()) {
				
				JOptionPane.showMessageDialog( EjemploJMenuBar2.this, 
											   "La opcion de menu: " + b.getActionCommand() + " esta seleccionada." );	
				
			}	
			
		}
		
	}
	
	public static void main(String[] args) {
		
		final EjemploJMenuBar2 v = new EjemploJMenuBar2();
		
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				v.setVisible(true);
			}
			
		});
		
	}
	
}
