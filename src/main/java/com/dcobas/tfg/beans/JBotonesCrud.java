package com.dcobas.tfg.beans;

import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;

public class JBotonesCrud extends JPanel {
	
	public JButton btNuevo;
	public JButton btEditar;
	public JButton btEliminar;
	public JButton btConfirmar;
	public JButton btCancelar;
	public JButton btEliminarTodo;
			
	public JBotonesCrud() {
		
		setLayout(null);
		
		btNuevo = new JButton("N");
		try {
//			btNuevo.setIcon(new ImageIcon(JBotonesCrud.class.getResource("/list-add.png")));
			btNuevo.setIcon(new ImageIcon("C:\\Users\\Usuario\\Desktop\\DAM2\\TFG\\resources\\icons\\list-add.png"));
			btNuevo.setText("");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		btNuevo.setActionCommand("nuevo");
		btNuevo.setBounds(10, 10, 46, 36);
		add(btNuevo);
		
		btEditar = new JButton("E");
		try {
//			btEditar.setIcon(new ImageIcon(JBotonesCrud.class.getResource("/edit-6.png")));
			btEditar.setIcon(new ImageIcon("C:\\Users\\Usuario\\Desktop\\DAM2\\TFG\\resources\\icons\\edit-6.png"));
			btEditar.setText("");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		btEditar.setEnabled(false);
		btEditar.setActionCommand("editar");
		btEditar.setBounds(90, 10, 46, 36);
		add(btEditar);
		
		btEliminar = new JButton("D");
		try {
//			btEliminar.setIcon(new ImageIcon(JBotonesCrud.class.getResource("/draw-eraser-2.png")));
			btEliminar.setIcon(new ImageIcon("C:\\Users\\Usuario\\Desktop\\DAM2\\TFG\\resources\\icons\\draw-eraser-2.png"));
			btEliminar.setText("");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		btEliminar.setEnabled(false);
		btEliminar.setActionCommand("eliminar");
		btEliminar.setBounds(170, 10, 46, 36);
		add(btEliminar);
		
		btConfirmar = new JButton("A");
		try {
//			btConfirmar.setIcon(new ImageIcon(JBotonesCrud.class.getResource("/dialog-accept.png")));
			btConfirmar.setIcon(new ImageIcon("C:\\Users\\Usuario\\Desktop\\DAM2\\TFG\\resources\\icons\\dialog-accept.png"));
			btConfirmar.setText("");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		btConfirmar.setEnabled(false);
		btConfirmar.setActionCommand("anadir");
		btConfirmar.setBounds(10, 70, 46, 36);
		add(btConfirmar);
		
		btCancelar = new JButton("C");
		try {
//			btCancelar.setIcon(new ImageIcon(JBotonesCrud.class.getResource("/dialog-cancel-7.png")));
			btCancelar.setIcon(new ImageIcon("C:\\Users\\Usuario\\Desktop\\DAM2\\TFG\\resources\\icons\\dialog-cancel-7.png"));
			btCancelar.setText("");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		btCancelar.setEnabled(false);
		btCancelar.setActionCommand("cancelar");
		btCancelar.setBounds(90, 70, 46, 36);
		add(btCancelar);
		
		btEliminarTodo = new JButton("X");
		try {
//			btEliminarTodo.setIcon(new ImageIcon(JBotonesCrud.class.getResource("/edit-delete-2.png")));
			btEliminarTodo.setIcon(new ImageIcon("C:\\Users\\Usuario\\Desktop\\DAM2\\TFG\\resources\\icons\\edit-delete-2.png"));
			btEliminarTodo.setText("");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		btEliminarTodo.setActionCommand("eliminar");
		btEliminarTodo.setBounds(170, 70, 46, 36);
		add(btEliminarTodo);
		
	}
		
	public void addListeners(ActionListener listener) {
		btNuevo.addActionListener(listener);
		btConfirmar.addActionListener(listener);
		btEditar.addActionListener(listener);
		btEliminar.addActionListener(listener);
		btCancelar.addActionListener(listener);
		btEliminarTodo.addActionListener(listener);
	}
	
	public void modoEdicion(boolean edicion) {
		btNuevo.setEnabled(!edicion);
		btConfirmar.setEnabled(edicion);
		btEditar.setEnabled(!edicion);
		btEliminar.setEnabled(!edicion);
		btCancelar.setEnabled(edicion);
	}
	
	public void modoInicio() {
		btNuevo.setEnabled(true);
		btConfirmar.setEnabled(false);
		btEditar.setEnabled(false);
		btEliminar.setEnabled(false);
		btCancelar.setEnabled(false);
	}
	
	public void modoEdicion() {
		btNuevo.setEnabled(false);
		btConfirmar.setEnabled(false);
		btEditar.setEnabled(false);
		btEliminar.setEnabled(false);
		btCancelar.setEnabled(false);
	}
	
	public void modoVista() {
		modoInicio();
		btEditar.setEnabled(true);
		btEliminar.setEnabled(true);
	}
	
	
}
