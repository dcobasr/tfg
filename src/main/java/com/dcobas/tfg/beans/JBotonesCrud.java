package com.dcobas.tfg.beans;

import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class JBotonesCrud extends JPanel {
	
	public JButton btNuevo;
	public JButton btEliminarTodo;
	public JButton btCancelar;
	public JButton btAnadir;
	public JButton btEditar;
	public JButton btEliminar;
	
//	public final String RUTA = "C:\\Users\\AlumnoT\\eclipse-workspace\\Ev2\\tfg\\resources\\icons\\";
	public final String RUTA = "C:\\tfg\\tfg\\resources\\icons\\";
	
	public JBotonesCrud() {
		
		setLayout(null);
		
		btNuevo = new JButton("");
		btNuevo.setIcon(new ImageIcon(RUTA + "list-add.png"));
		btNuevo.setActionCommand("nuevo");
		btNuevo.setBounds(10, 10, 46, 36);
		add(btNuevo);
		
		btEliminarTodo = new JButton("");
		btEliminarTodo.setIcon(new ImageIcon(RUTA + "edit-delete-2.png"));
		btEliminarTodo.setActionCommand("eliminar");
		btEliminarTodo.setBounds(170, 70, 46, 36);
		add(btEliminarTodo);
		
		btCancelar = new JButton("");
		btCancelar.setIcon(new ImageIcon(RUTA + "dialog-cancel-7.png"));
		btCancelar.setEnabled(false);
		btCancelar.setActionCommand("cancelar");
		btCancelar.setBounds(90, 70, 46, 36);
		add(btCancelar);
		
		btAnadir = new JButton("");
		btAnadir.setIcon(new ImageIcon(RUTA + "dialog-accept.png"));
		btAnadir.setEnabled(false);
		btAnadir.setActionCommand("anadir");
		btAnadir.setBounds(10, 70, 46, 36);
		add(btAnadir);
		
		btEditar = new JButton("");
		btEditar.setIcon(new ImageIcon(RUTA + "edit-6.png"));
		btEditar.setEnabled(false);
		btEditar.setActionCommand("editar");
		btEditar.setBounds(90, 10, 46, 36);
		add(btEditar);
		
		btEliminar = new JButton("");
		btEliminar.setIcon(new ImageIcon(RUTA + "draw-eraser-2.png"));
		btEliminar.setEnabled(false);
		btEliminar.setActionCommand("eliminar");
		btEliminar.setBounds(170, 10, 46, 36);
		add(btEliminar);
		
	}
		
	public void addListeners(ActionListener listener) {
		btNuevo.addActionListener(listener);
		btAnadir.addActionListener(listener);
		btEditar.addActionListener(listener);
		btEliminar.addActionListener(listener);
		btCancelar.addActionListener(listener);
		btEliminarTodo.addActionListener(listener);
	}
	
	public void modoEdicion(boolean edicion) {
		btNuevo.setEnabled(!edicion);
		btAnadir.setEnabled(edicion);
		btEditar.setEnabled(!edicion);
		btEliminar.setEnabled(!edicion);
		btCancelar.setEnabled(edicion);
	}
}
