package com.dcobas.tfg;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import com.dcobas.tfg.base.Empleado;
import com.dcobas.tfg.base.Proyecto;
import com.dcobas.tfg.ui.Login;
import com.dcobas.tfg.ui.Vista;
import com.dcobas.tfg.util.Util;

public class Controlador implements ActionListener, MouseListener {
	
	private Modelo modelo;
	private Vista vista;
	public enum Accion {
		NUEVO, MODIFICAR, GUARDAR, CANCELAR, ELIMINAR
	}
	private Constantes constantes;
	
	public Controlador(Vista vista, Modelo modelo) {
		this.vista = vista;
		this.modelo = modelo;
		
		constantes= new Constantes();
//		iniciarSesion();
		inicializar();
		
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
		System.out.println(vista.jEstado);
		vista.jEstado.setMensajeConfirmacion("Bienvenido a "+constantes.nombreAplicacion+" "+constantes.versionAplicacion+" de "+constantes.creador);
	}
	
	private void inicializar() {
		//Panel Empleados
		vista.panelEmpleados.botonesCrud.addListeners(this);
		vista.panelEmpleados.panelBusqueda.addListener(this);
		vista.panelEmpleados.lImagen.addMouseListener(this);
		
		vista.panelEmpleados.inicializar();
		
		//Panel Proyectos
		vista.panelProyectos.botonesCrud.addListeners(this);
		vista.panelProyectos.panelBusqueda.addListener(this);
		vista.panelProyectos.lImagen.addMouseListener(this);
		
		vista.panelProyectos.inicializar();
		
		refrescarPaneles();
	}
	
	private void refrescarPaneles() {
		vista.panelProyectos.refrescarPanel();
		vista.panelEmpleados.refrescarPanel();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		
		Proyecto proyecto = null;
		Empleado empleado = null;
		switch (actionCommand) {
			//Panel Proyectos
			case "nuevoProyecto":
				vista.panelProyectos.limpiar();
				vista.panelProyectos.accion = Accion.NUEVO;
				vista.panelProyectos.modoEdicion(true);
				break;
				
			case "editarProyecto":
				vista.panelProyectos.accion = Accion.MODIFICAR;
				vista.panelProyectos.modoEdicion(true);
				break;
				
			case "confirmarProyecto":
				if (vista.panelProyectos.tfNombre.getText().equals("")) {
					JOptionPane.showMessageDialog(null,
							"El campo nombre es obligatorio",
							"Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if (vista.panelProyectos.tfEmpleados.getText().equals("")) {
					vista.panelProyectos.tfEmpleados.setText("0");
				}
	
				String nombre = vista.panelProyectos.tfNombre.getText();
				String empresa = vista.panelProyectos.tfEmpresa.getText();
				String tipo = vista.panelProyectos.tfTipo.getText();
				String descripcion = vista.panelProyectos.taDescripcion.getText();
				String fechaInicio = vista.panelProyectos.tfFechaInicio.getText();
				int numeroEmpleados;
				try {
					numeroEmpleados = Integer.parseInt(vista.panelProyectos.tfEmpleados.getText());
				} catch (NumberFormatException nfe){
					JOptionPane.showMessageDialog(null,
							"El año debe ser un número",
							"Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				String nombreImagen = null;
				if (vista.panelProyectos.ficheroSeleccionado != null)
					nombreImagen = vista.panelProyectos.ficheroSeleccionado.getName();
				else
					if(vista.panelProyectos.editar==true) {
						proyecto = vista.panelProyectos.panelBusqueda.lista.getSelectedValue();
						nombreImagen= proyecto.getNombreImagen();
					}
					else
						nombreImagen = "nopicture.png";
	
				
				switch (vista.panelProyectos.accion) {
					case NUEVO:
						proyecto = new Proyecto();
						break;
					case MODIFICAR:
						proyecto = vista.panelProyectos.panelBusqueda.getSeleccionado();
						nombreImagen= proyecto.getNombreImagen();
						break;
					default:
						Util.mensajeError("Operación desconocida");
						break;
				}
				
				proyecto.setNombre(nombre);
				proyecto.setEmpresa(empresa);
				proyecto.setTipo(tipo);
				proyecto.setDescripcion(descripcion);
				proyecto.setFechaInicio(fechaInicio);
				proyecto.setNumeroEmpleados(numeroEmpleados);
				proyecto.setNombreImagen(nombreImagen);
				proyecto.setEmpleados(vista.panelProyectos.panelAnadirEmpleado.getListadoEmpleados());
				
				modelo.guardar(proyecto);
				
				try {
					if (vista.panelProyectos.ficheroSeleccionado != null)
					Util.copiarImagen(vista.panelProyectos.ficheroSeleccionado.getAbsolutePath(),nombreImagen);
				} catch (IOException ioe) {
					System.out.println("Error al guardar la imagen (puede que esté repetida).");
				}
				
				vista.panelProyectos.limpiar();
				refrescarPaneles();
				break;
			case "eliminarProyecto":
				proyecto = vista.panelProyectos.panelBusqueda.getSeleccionado();
				modelo.eliminar(proyecto);
				vista.panelProyectos.limpiar();
				refrescarPaneles();
				break;
			case "cancelarProyecto":
				vista.panelProyectos.limpiar();
				vista.panelProyectos.modoInicio(true);
				break;
			case "eliminarProyectos":
				try {
					int n=JOptionPane.showConfirmDialog(null,
							"¿Estás seguro de que quieres eliminar todos los proyectos?",
							"   ATENCIÓN",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.WARNING_MESSAGE);
					if(n==0) {
						modelo.eliminarProyectos();
						vista.panelProyectos.limpiar();
						vista.panelProyectos.modoInicio(true);
						refrescarPaneles();
						break;
					}
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
				break;
				
			//Panel Empleados
			case "nuevoEmpleado":
				vista.panelEmpleados.limpiar();
				vista.panelEmpleados.modoEdicion(true);
				vista.panelEmpleados.accion = Accion.NUEVO;
				break;
			case "editarEmpleado":
				vista.panelEmpleados.modoEdicion(true);
				vista.panelEmpleados.accion = Accion.MODIFICAR;
				break;
			case "confirmarEmpleado":
				
				if (vista.panelEmpleados.tfNombre.getText().equals("")) {
					JOptionPane.showMessageDialog(null,
							"El campo nombre es obligatorio",
							"Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				String nombreEmpleado = vista.panelEmpleados.tfNombre.getText();
				String apellidos = vista.panelEmpleados.tfApellidos.getText();
				
				int edad;
				try {
					if (vista.panelEmpleados.tfEdad.getText().equals("")) {
						vista.panelEmpleados.tfEdad.setText("0");
					}
					edad = Integer.parseInt(vista.panelEmpleados.tfEdad.getText());
				} catch (NumberFormatException nfe){
					JOptionPane.showMessageDialog(null,
							"La edad debe ser un número",
							"Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				String email = vista.panelEmpleados.tfEmail.getText();
				String sector = vista.panelEmpleados.tfSector.getText();
				String documentacion = vista.panelEmpleados.tfDocumentacion.getText();
				
				String nombreImagenEmpleado = null;
				if (vista.panelEmpleados.ficheroSeleccionado != null)
					nombreImagenEmpleado = vista.panelEmpleados.ficheroSeleccionado.getName();
				else
					if(vista.panelEmpleados.editar==true) {
						empleado = vista.panelEmpleados.panelBusqueda.lista.getSelectedValue();
						nombreImagenEmpleado= empleado.getNombreImagen();
					}
					else
						nombreImagenEmpleado = "nopicture.png";
				
				switch (vista.panelEmpleados.accion) {
					case NUEVO:
						empleado = new Empleado();
						break;
					case MODIFICAR:
						empleado = vista.panelEmpleados.panelBusqueda.getSeleccionado();
						nombreImagenEmpleado= empleado.getNombreImagen();
						break;
					default:
						Util.mensajeError("Operación desconocida");
						break;
				}
				
				empleado.setNombre(nombreEmpleado);
				empleado.setApellidos(apellidos);
				empleado.setEdad(edad);
				empleado.setEmail(email);
				empleado.setSector(sector);
				empleado.setNombreImagen(nombreImagenEmpleado);
				empleado.setDocumentacion(documentacion);
				
				modelo.guardar(empleado);
				
				try {
					if (vista.panelEmpleados.ficheroSeleccionado != null)
					Util.copiarImagen(vista.panelEmpleados.ficheroSeleccionado.getAbsolutePath(),nombreImagenEmpleado);
				} catch (IOException ioe) {
					System.out.println("Error al guardar la imagen (puede que esté repetida).");
				}
				
				vista.panelEmpleados.limpiar();
				refrescarPaneles();
				break;
			case "eliminarEmpleado":
				empleado = vista.panelEmpleados.panelBusqueda.getSeleccionado();
				modelo.eliminar(empleado);
				vista.panelEmpleados.limpiar();
				refrescarPaneles();
				break;
			case "cancelarEmpleado":
				vista.panelEmpleados.limpiar();
				vista.panelEmpleados.modoInicio(true);
				break;
				
			case "eliminarEmpleados":
				try {
					int n=JOptionPane.showConfirmDialog(null,
							"¿Estás seguro de que quieres eliminar todos los empleados?",
							"   ATENCIÓN",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.WARNING_MESSAGE);
					if(n==0) {
						modelo.eliminarEmpleados();
						vista.panelEmpleados.limpiar();
						vista.panelEmpleados.modoInicio(true);
						refrescarPaneles();
						break;
					}
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
				break;
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//Elegimos una imagen para el proyecto
		if(e.getSource()==vista.panelProyectos.lImagen) {
			if(vista.panelProyectos.activarJFileChooser) {
				vista.panelProyectos.lanzarFileChooser();
			}
		}
		//Elegimos una imagen para el empleado
		if(e.getSource()==vista.panelEmpleados.lImagen) {
			if(vista.panelEmpleados.activarJFileChooser) {
				vista.panelEmpleados.lanzarFileChooser();
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		//Lista de proyectos
		if(e.getSource()==vista.panelProyectos.panelBusqueda.lista) {
			Proyecto proyecto = vista.panelProyectos.panelBusqueda.getSeleccionado();
			if (proyecto == null)
				return;
			
			vista.panelProyectos.modoEdicion(false);
			vista.panelProyectos.cargar(proyecto);
		}
		
		//Lista de empleados
		if(e.getSource()==vista.panelEmpleados.panelBusqueda.lista) {
			Empleado empleadoSeleccionado = vista.panelEmpleados.panelBusqueda.getSeleccionado();
			if (empleadoSeleccionado == null)
				return;
			
			vista.panelEmpleados.modoEdicion(false);
			vista.panelEmpleados.cargar(empleadoSeleccionado);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}
