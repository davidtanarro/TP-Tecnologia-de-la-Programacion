package ejemplo1;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;


public class Vista extends JFrame implements ObservadorModelo {
	private Controlador control;
	
	private  JTextField txtResultado;
	
	public Vista(Controlador c) {
		super("MVC mínimo");
		control = c;
		
		this.getContentPane().setLayout(new FlowLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(200, 200);
		
		final JTextField txtNumero = new JTextField("");
		txtNumero.setFont(new Font("Dialog",Font.PLAIN,28));
		txtNumero.setPreferredSize(new Dimension(150,50));
		this.getContentPane().add(txtNumero);
		
		txtResultado = new JTextField();
		txtResultado.setFont(new Font("Dialog",Font.PLAIN,28));
		txtResultado.setPreferredSize(new Dimension(150,50));
		txtResultado.setEnabled(false);
		this.getContentPane().add(txtResultado);
		
		JButton btnCalcular = new JButton("Calcula");
		btnCalcular.setFont(new Font("Dialog",Font.PLAIN,28));
		this.getContentPane().add(btnCalcular);
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.calcular(txtNumero.getText());
			}
		});
		control.addObservador(this);
	}

	@Override
	public void actualizar(double dato) {
		String str = Double.toString(dato);
		txtResultado.setText(str);
	}
}
