package com.dcobas.tfg;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.dcobas.tfg.base.Empleado;
import com.dcobas.tfg.base.Proyecto;
import com.dcobas.tfg.ui.Login;
import com.dcobas.tfg.ui.Vista;
import com.dcobas.tfg.util.Util;

public class Controlador {
	
	private Modelo modelo;
	private Vista vista;
	private File ficheroSeleccionado;
	public enum Accion {
		NUEVO, MODIFICAR, GUARDAR, CANCELAR, ELIMINAR
	}
	private Accion accion;
	private Proyecto proyectoActual;
	private Constantes constantes;
	
	public Controlador(Vista vista, Modelo modelo) {
		this.vista = vista;
		this.modelo = modelo;
		
//		iniciarSesion();
//		vista.jEstado.setMensajeError("Bienvenido a Proyectos "+constantes.versionAplicacion);
		
//		refrescarProyectos();
//		refrescarEmpleados();
	}
	
	private void iniciarSesion() {
		
		boolean autenticado = false;
		Login login = new Login();
		int intentos = 1;
		
		do {
			login.mostrarDialogo();
			String usuario = login.getUsuario();
			String contrasena = login.getContrasena();
			
			try {
				autenticado = modelo.iniciarSesion(usuario, contrasena);
				if (!autenticado) {
					if (intentos > 2) {
						Util.mensajeError("Limite de intentos superado");
						System.exit(0);
					}
					login.limpiarContrasena();
					login.setMensaje("Usuario/Contraseña incorrectos");
					intentos++;
					continue;
				}
					
			} catch (SQLException sqle) {
				sqle.printStackTrace();
				Util.mensajeError("No se ha podido conectar por algo");
			}
		} while (!autenticado);
	}

	private void refrescarProyectos() {
		List<Proyecto> proyectos = modelo.getProyectos();
		vista.mProyectos.removeAllElements();
		for (Proyecto proyecto : proyectos) 
			vista.mProyectos.addElement(proyecto);
	}
	
	private void refrescarEmpleados() {
		List<Empleado> empleados = modelo.getEmpleados();
		vista.mEmpleados.removeAllElements();
		for (Empleado empleado : empleados) 
			vista.mEmpleados.addElement(empleado);
	}
	
	/*
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		
		case "nuevoProyecto":
			nuevo();
			break;
			
		case "anadirProyecto":
			anadir();
			break;
			
		case "cancelarProyecto":
			cancelar();
			break;
			
		case "eliminarProyecto":
			eliminar();
			break;
			
		case "editarProyecto":
			editar();
			break;

		case "eliminarProyectos":
			eliminarTodo();
			break;

		default:
			break;

		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}*/


}
