package com.dcobas.tfg.beans;

import javax.swing.JPanel;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;

import com.dcobas.tfg.Controlador.Accion;
import com.dcobas.tfg.Modelo;
import com.dcobas.tfg.base.Proyecto;
import com.dcobas.tfg.util.Util;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class PanelProyectos extends JPanel implements ActionListener, MouseListener {
	public JLabel lImagen;
	public JLabel lblNombre;
	public JLabel lblEmpresa;
	public JLabel lblTipo;
	public JLabel lblDescripcin;
	public JTextField tfNombre;
	public JTextField tfEmpresa;
	public JTextField tfTipo;
	public JTextArea taDescripcion;
	public JLabel lblFechaInicio;
	public JLabel lblEmpleadosAsignados;
	public JTextField tfFechaInicio;
	public JTextField tfEmpleados;
	public PanelBusqueda<Proyecto> panelBusqueda;
	public JBotonesCrud botonesCrud;
	public PanelAnadirEmpleado panelAnadirEmpleado;
	private Accion accion;
	
	private File ficheroSeleccionado;
	private boolean editar=false;
	private boolean activarJFileChooser=false;
	
	private Modelo modelo;
	
	public PanelProyectos(Modelo modelo) {
		this.modelo = modelo;
		setLayout(null);
		
		lImagen = new JLabel("");
		lImagen.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lImagen.setBounds(10, 11, 250, 250);
		add(lImagen);
		
		lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(280, 14, 66, 14);
		add(lblNombre);
		
		lblEmpresa = new JLabel("Empresa:");
		lblEmpresa.setBounds(280, 52, 66, 14);
		add(lblEmpresa);
		
		lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(280, 95, 66, 14);
		add(lblTipo);
		
		lblDescripcin = new JLabel("Descripción:");
		lblDescripcin.setBounds(280, 132, 86, 14);
		add(lblDescripcin);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(356, 11, 200, 20);
		add(tfNombre);
		tfNombre.setColumns(10);
		
		tfEmpresa = new JTextField();
		tfEmpresa.setColumns(10);
		tfEmpresa.setBounds(356, 49, 200, 20);
		add(tfEmpresa);
		
		tfTipo = new JTextField();
		tfTipo.setColumns(10);
		tfTipo.setBounds(356, 92, 200, 20);
		add(tfTipo);
		
		taDescripcion = new JTextArea();
		taDescripcion.setBounds(280, 157, 276, 148);
		add(taDescripcion);
		
		lblFechaInicio = new JLabel("Fecha inicio:");
		lblFechaInicio.setBounds(280, 316, 75, 14);
		add(lblFechaInicio);
		
		lblEmpleadosAsignados = new JLabel("Empleados asignados:");
		lblEmpleadosAsignados.setBounds(280, 348, 138, 14);
		add(lblEmpleadosAsignados);
		
		tfFechaInicio = new JTextField();
		tfFechaInicio.setBounds(365, 313, 111, 20);
		add(tfFechaInicio);
		tfFechaInicio.setColumns(10);
		
		tfEmpleados = new JTextField();
		tfEmpleados.setBounds(415, 345, 61, 20);
		add(tfEmpleados);
		tfEmpleados.setColumns(10);
		
		panelBusqueda = new PanelBusqueda<Proyecto>();
		panelBusqueda.setBounds(566, 211, 260, 151);
		add(panelBusqueda);
		
		botonesCrud = new JBotonesCrud();
		botonesCrud.btEliminarTodo.setActionCommand("eliminarProyectos");
		botonesCrud.btCancelar.setActionCommand("cancelarProyecto");
		botonesCrud.btConfirmar.setActionCommand("confirmarProyecto");
		botonesCrud.btEliminar.setActionCommand("eliminarProyecto");
		botonesCrud.btEditar.setActionCommand("editarProyecto");
		botonesCrud.btNuevo.setActionCommand("nuevoProyecto");
		botonesCrud.setBounds(20, 261, 231, 112);
		add(botonesCrud);
		
		panelAnadirEmpleado = new PanelAnadirEmpleado();
		panelAnadirEmpleado.setBounds(566, 11, 260, 185);
		add(panelAnadirEmpleado);
		
		inicializar();

	}
	
	private void inicializar() {
		botonesCrud.addListeners(this);
		panelBusqueda.addListener(this);
		lImagen.addMouseListener(this);
		
		panelBusqueda.inicializar(modelo.getProyectos());
		modoInicio(true);
	}
	
	private void modoEdicion(boolean edicion) {
		tfNombre.setEditable(edicion);
		tfEmpresa.setEditable(edicion);
		tfTipo.setEditable(edicion);
		taDescripcion.setEditable(edicion);
		tfFechaInicio.setEditable(edicion);
		tfEmpleados.setEditable(edicion);
		
		activarJFileChooser=edicion;
		panelAnadirEmpleado.modoEdicion(edicion);
		botonesCrud.modoEdicion(edicion);
	}
	
	private void modoInicio(boolean edicion) {
		modoEdicion(!edicion);
		
		panelBusqueda.lista.clearSelection();
		botonesCrud.modoEdicion(!edicion);
		botonesCrud.btEditar.setEnabled(!edicion);
		botonesCrud.btEliminar.setEnabled(!edicion);
	}
	
	private void cargar(Proyecto proyecto) {
		tfNombre.setText(proyecto.getNombre());
		tfEmpresa.setText(proyecto.getEmpresa());
		tfTipo.setText(proyecto.getTipo());
		taDescripcion.setText(proyecto.getDescripcion());
		tfFechaInicio.setText(proyecto.getFechaInicio());
		tfEmpleados.setText(proyecto.getDescripcion());
		panelAnadirEmpleado.anadirEmpleados((proyecto.getEmpleados()));
		ImageIcon imageIcon = new ImageIcon(
				new ImageIcon(Util.rutaImagenes+proyecto.getNombreImagen()).
				getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT));
		lImagen.setIcon(imageIcon);
	}
	
	public void lanzarFileChooser() {
		JFileChooser jfc= new JFileChooser();
		if(jfc.showOpenDialog(null)==JFileChooser.CANCEL_OPTION) {
			return;
		}
		ficheroSeleccionado = jfc.getSelectedFile();
		ImageIcon imageIcon = new ImageIcon(
				new ImageIcon(ficheroSeleccionado.getAbsolutePath()).
				getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT));
		lImagen.setIcon(imageIcon);
	}
	
	public void refrescarPanel() {
		panelBusqueda.lista.clearSelection();
		panelBusqueda.setDatos(modelo.getProyectos());
		panelBusqueda.refrescar();
		panelAnadirEmpleado.refrescarCb();
		panelAnadirEmpleado.limpiarEmpleados();
		
		modoInicio(true);
	}
	
	public void limpiar() {
		tfNombre.setText("");
		tfEmpresa.setText("");
		tfTipo.setText("");
		taDescripcion.setText("");
		tfFechaInicio.setText("");
		tfEmpleados.setText("");
		lImagen.setIcon(null);
		ficheroSeleccionado=null;
		panelBusqueda.lista.clearSelection();
		panelAnadirEmpleado.limpiarEmpleados();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		
		Proyecto proyecto= null;
		switch (actionCommand) {
		
			case "nuevoProyecto":
				limpiar();
				refrescarPanel();
				accion = Accion.NUEVO;
				modoEdicion(true);
				break;
				
			case "editarProyecto":
				accion = Accion.MODIFICAR;
				modoEdicion(true);
				break;
				
			case "confirmarProyecto":
				if (tfNombre.getText().equals("")) {
					JOptionPane.showMessageDialog(null,
							"El campo nombre es obligatorio",
							"Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if (tfEmpleados.getText().equals("")) {
					tfEmpleados.setText("0");
				}

				String nombre = tfNombre.getText();
				String empresa = tfEmpresa.getText();
				String tipo = tfTipo.getText();
				String descripcion = taDescripcion.getText();
				String fechaInicio = tfFechaInicio.getText();
				int numeroEmpleados;
				try {
					numeroEmpleados = Integer.parseInt(tfEmpleados.getText());
				} catch (NumberFormatException nfe){
					JOptionPane.showMessageDialog(null,
							"El año debe ser un número",
							"Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				String nombreImagen = null;
				if (ficheroSeleccionado != null)
					nombreImagen = ficheroSeleccionado.getName();
				else
					if(editar==true) {
						proyecto = panelBusqueda.lista.getSelectedValue();
						nombreImagen= proyecto.getNombreImagen();
					}
					else
						nombreImagen = "nopicture.png";

				
				switch (accion) {
					case NUEVO:
						proyecto = new Proyecto();
						break;
					case MODIFICAR:
						proyecto = panelBusqueda.getSeleccionado();
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
				proyecto.setEmpleados(panelAnadirEmpleado.getListadoEmpleados());
				
				modelo.guardar(proyecto);
				
				try {
					if (ficheroSeleccionado != null)
					Util.copiarImagen(ficheroSeleccionado.getAbsolutePath(),nombreImagen);
				} catch (IOException ioe) {
					System.out.println("Error al guardar la imagen (puede que esté repetida).");
				}
				
				limpiar();
				refrescarPanel();
				break;
			case "eliminarProyecto":
				proyecto = panelBusqueda.getSeleccionado();
				modelo.eliminar(proyecto);
				limpiar();
				refrescarPanel();
				break;
			case "cancelarProyecto":
				limpiar();
				modoInicio(true);
				break;
			case "eliminarProyectos":
				break;
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//Elegimos una imagen
		if(e.getSource()==lImagen) {
			if(activarJFileChooser) {
				lanzarFileChooser();
			}
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getSource()==panelBusqueda.lista) {
			Proyecto proyecto = panelBusqueda.getSeleccionado();
			if (proyecto == null)
				return;
			
			modoEdicion(false);
			cargar(proyecto);
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
