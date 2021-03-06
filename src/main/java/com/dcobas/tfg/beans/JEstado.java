package com.dcobas.tfg.beans;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Barra de estado para mostrar mensajes de la aplicación
 */

public class JEstado extends JPanel {
	public JLabel lbEstado;
	
	public JEstado() {
		setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		lbEstado = new JLabel("");
		add(lbEstado);
	}
	
	public void setMensajeConfirmacion(String mensaje) {
		lbEstado.setForeground(new Color(31, 106, 218));
		lbEstado.setText(mensaje);
	}
	
	public void setMensajeError(String mensaje) {
		lbEstado.setForeground(Color.RED);
		lbEstado.setText(mensaje);
	}

}
