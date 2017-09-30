package ejemplo2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;


public class Vista extends JFrame implements ObservadorModelo {
	private Controlador control;
	
	private  JTextField txtResultado;
	
	public Vista(Controlador c) {
		super("MVC mínimo");
		control = c;
		
		this.getContentPane().setLayout(new FlowLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel pnlPpal = new JPanel();
		pnlPpal.setBorder(new EmptyBorder(15,15,15,15));
		this.getContentPane().add(pnlPpal);
		pnlPpal.setLayout(new GridLayout(3,2,15,15));
		this.setSize(650, 200);
		
		JLabel lblNumero = new JLabel("Introduce un número");
		lblNumero.setFont(new Font("Dialog",Font.PLAIN,28));
		pnlPpal.add(lblNumero);
		
		final JTextField txtNumero = new JTextField("");
		txtNumero.setFont(new Font("Dialog",Font.PLAIN,28));
		pnlPpal.add(txtNumero);
		
		JLabel lblResultado = new JLabel("Su cuadrado es");
		lblResultado.setFont(new Font("Dialog",Font.PLAIN,28));
		pnlPpal.add(lblResultado);
		
		txtResultado = new JTextField();
		txtResultado.setFont(new Font("Dialog",Font.PLAIN,28));
		txtResultado.setEnabled(false);
		pnlPpal.add(txtResultado);
		

		JButton btnCalcular = new JButton("Calcula");
		btnCalcular.setFont(new Font("Dialog",Font.PLAIN,28));
		pnlPpal.add(btnCalcular);
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.calcular(txtNumero.getText());
			}
		});
		
		PanelMensajes pnlMsg = new PanelMensajes();
		pnlPpal.add(pnlMsg);
		
		control.addObservador(pnlMsg);
		control.addObservador(this);
	}

	@Override
	public void actualizar(double dato) {
		String str = Double.toString(dato);
		txtResultado.setText(str);
	}

	@Override
	public void notificarError(String err) {
	}
	
	public class PanelMensajes extends JPanel implements ObservadorModelo {
		private JLabel lblMensaje;

		public PanelMensajes() {
			super();
			lblMensaje = new JLabel("");
			lblMensaje.setFont(new Font("Dialog",Font.PLAIN,28));
			lblMensaje.setForeground(Color.red);
			this.add(lblMensaje);
		}

		@Override
		public void actualizar(double dato) {
		}

		@Override
		public void notificarError(String err) {
			lblMensaje.setText(err);
		}
		
	}
	
}
