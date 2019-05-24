package com.dcobas.tfg.beans;

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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import com.dcobas.tfg.Controlador.Accion;
import com.dcobas.tfg.Modelo;
import com.dcobas.tfg.base.Empleado;
import com.dcobas.tfg.util.Util;

public class PanelEmpleados extends JPanel implements ActionListener, MouseListener {
	public JBotonesCrud botonesCrud;
	public JLabel lImagen;
	public JLabel lblNombre;
	public JLabel lblApellidos;
	public JLabel lblEdad;
	public JLabel lblEmail;
	public JLabel lblSectores;
	public JLabel lblDni;
	public PanelBusqueda<Empleado> panelBusqueda;
	public JTextField tfNombre;
	public JTextField tfApellidos;
	public JTextField tfEdad;
	public JTextField tfEmail;
	public JTextField tfSector;
	public JTextField tfDocumentacion;
	
	private File ficheroSeleccionado;
	private boolean activarJFileChooser=false;
	private boolean editar=false;
	
	private Modelo modelo;
	private Accion accion;
	
	public PanelEmpleados(Modelo modelo) {
		this.modelo=modelo;
		
		setLayout(null);
		
		botonesCrud = new JBotonesCrud();
		botonesCrud.btNuevo.setActionCommand("nuevoEmpleado");
		botonesCrud.btEditar.setActionCommand("editarEmpleado");
		botonesCrud.btEliminar.setActionCommand("eliminarEmpleado");
		botonesCrud.btConfirmar.setActionCommand("confirmarEmpleado");
		botonesCrud.btCancelar.setActionCommand("cancelarEmpleado");
		botonesCrud.btEliminarTodo.setActionCommand("eliminarEmpleados");
		botonesCrud.setBounds(387, 273, 225, 109);
		add(botonesCrud);
		
		lImagen = new JLabel("");
		lImagen.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lImagen.setBounds(10, 11, 350, 350);
		add(lImagen);
		
		lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(387, 31, 58, 14);
		add(lblNombre);
		
		lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setBounds(387, 75, 58, 14);
		add(lblApellidos);

		lblEdad = new JLabel("Edad:");
		lblEdad.setBounds(387, 122, 58, 14);
		add(lblEdad);
		
		lblEmail = new JLabel("Email:");
		lblEmail.setBounds(387, 164, 46, 14);
		add(lblEmail);
		
		lblSectores = new JLabel("Sector/es:");
		lblSectores.setBounds(387, 207, 58, 14);
		add(lblSectores);
		
		lblDni = new JLabel("DNI/NIF:");
		lblDni.setBounds(387, 248, 46, 14);
		add(lblDni);
		
		panelBusqueda = new PanelBusqueda<Empleado>();
		panelBusqueda.setBounds(657, 11, 166, 367);
		add(panelBusqueda);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(455, 28, 179, 20);
		add(tfNombre);
		tfNombre.setColumns(10);
		
		tfApellidos = new JTextField();
		tfApellidos.setBounds(455, 72, 179, 20);
		add(tfApellidos);
		tfApellidos.setColumns(10);
		
		tfEdad = new JTextField();
		tfEdad.setBounds(455, 119, 179, 20);
		add(tfEdad);
		tfEdad.setColumns(10);
		
		tfEmail = new JTextField();
		tfEmail.setBounds(455, 161, 179, 20);
		add(tfEmail);
		tfEmail.setColumns(10);
		
		tfSector = new JTextField();
		tfSector.setBounds(455, 204, 179, 20);
		add(tfSector);
		tfSector.setColumns(10);
		
		tfDocumentacion = new JTextField();
		tfDocumentacion.setBounds(455, 245, 179, 20);
		add(tfDocumentacion);
		tfDocumentacion.setColumns(10);
		
		inicializar();

	}
	
	private void inicializar() {
		botonesCrud.addListeners(this);
		panelBusqueda.addListener(this);
		lImagen.addMouseListener(this);
		
		panelBusqueda.inicializar(modelo.getEmpleados());
		modoInicio(true);
	}
	
	private void modoInicio(boolean edicion) {
		modoEdicion(!edicion);
		
		botonesCrud.btEditar.setEnabled(!edicion);
		botonesCrud.btEliminar.setEnabled(!edicion);
	}
	
	private void modoEdicion(boolean edicion) {
		tfNombre.setEditable(edicion);
		tfApellidos.setEditable(edicion);
		tfEdad.setEditable(edicion);
		tfEmail.setEditable(edicion);
		tfSector.setEditable(edicion);
		tfDocumentacion.setEditable(edicion);
		
		activarJFileChooser=edicion;
		botonesCrud.modoEdicion(edicion);
	}
	
	private void cargar(Empleado empleado) {
		tfNombre.setText(empleado.getNombre());
		tfApellidos.setText(empleado.getApellidos());
		tfEdad.setText(String.valueOf(empleado.getEdad()));
		tfEmail.setText(empleado.getEmail());
		tfSector.setText(empleado.getSector());
		tfDocumentacion.setText(empleado.getDocumentacion());
		ImageIcon imageIcon = new ImageIcon(
				new ImageIcon(Util.rutaImagenes+empleado.getNombreImagen()).
				getImage().getScaledInstance(350, 350, Image.SCALE_DEFAULT));
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
				getImage().getScaledInstance(350, 350, Image.SCALE_DEFAULT));
		lImagen.setIcon(imageIcon);
	}
	
	public void limpiar() {
		tfNombre.setText("");
		tfApellidos.setText("");
		tfEdad.setText("");
		tfEmail.setText("");
		tfSector.setText("");
		tfDocumentacion.setText("");
		lImagen.setIcon(null);
		ficheroSeleccionado=null;
		panelBusqueda.lista.clearSelection();
	}

	public void refrescarPanel() {
		panelBusqueda.lista.clearSelection();
		panelBusqueda.setDatos(modelo.getEmpleados());
		panelBusqueda.refrescar();
		modoInicio(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		
		Empleado empleado = null;
		switch (actionCommand) {
			case "nuevoEmpleado":
				limpiar();
				modoEdicion(true);
				accion = Accion.NUEVO;
				break;
			case "editarEmpleado":
				modoEdicion(true);
				accion = Accion.MODIFICAR;
				break;
			case "confirmarEmpleado":
				
				if (tfNombre.getText().equals("")) {
					JOptionPane.showMessageDialog(null,
							"El campo nombre es obligatorio",
							"Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				String nombre = tfNombre.getText();
				String apellidos = tfApellidos.getText();
				
				int edad;
				try {
					if (tfEdad.getText().equals("")) {
						tfEdad.setText("0");
					}
					edad = Integer.parseInt(tfEdad.getText());
				} catch (NumberFormatException nfe){
					JOptionPane.showMessageDialog(null,
							"La edad debe ser un número",
							"Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				String email = tfEmail.getText();
				String sector = tfSector.getText();
				String documentacion = tfDocumentacion.getText();
				
				String nombreImagen = null;
				if (ficheroSeleccionado != null)
					nombreImagen = ficheroSeleccionado.getName();
				else
					if(editar==true) {
						empleado = panelBusqueda.lista.getSelectedValue();
						nombreImagen= empleado.getNombreImagen();
					}
					else
						nombreImagen = "nopicture.png";
				
				switch (accion) {
					case NUEVO:
						empleado = new Empleado();
						break;
					case MODIFICAR:
						empleado = panelBusqueda.getSeleccionado();
						nombreImagen= empleado.getNombreImagen();
						break;
					default:
						Util.mensajeError("Operación desconocida");
						break;
				}
				
				empleado.setNombre(nombre);
				empleado.setApellidos(apellidos);
				empleado.setEdad(edad);
				empleado.setEmail(email);
				empleado.setSector(sector);
				empleado.setNombreImagen(nombreImagen);
				empleado.setDocumentacion(documentacion);
				
				modelo.guardar(empleado);
				
				try {
					if (ficheroSeleccionado != null)
					Util.copiarImagen(ficheroSeleccionado.getAbsolutePath(),nombreImagen);
				} catch (IOException ioe) {
					System.out.println("Error al guardar la imagen (puede que esté repetida).");
				}
				
				limpiar();
				refrescarPanel();
				break;
			case "eliminarEmpleado":
				empleado = panelBusqueda.getSeleccionado();
				modelo.eliminar(empleado);
				limpiar();
				refrescarPanel();
				break;
			case "cancelarEmpleado":
				limpiar();
				modoInicio(true);
				break;
				
			case "eliminarEmpleados":
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
			Empleado empleadoSeleccionado = panelBusqueda.getSeleccionado();
			if (empleadoSeleccionado == null)
				return;
			
			modoEdicion(false);
			cargar(empleadoSeleccionado);
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
