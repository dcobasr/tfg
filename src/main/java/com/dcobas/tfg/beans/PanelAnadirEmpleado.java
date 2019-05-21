package com.dcobas.tfg.beans;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.dcobas.tfg.Modelo;
import com.dcobas.tfg.base.Empleado;

/**
 * Panel para añadir y gestionar las empleados de un empleado
 */

public class PanelAnadirEmpleado extends JPanel implements ActionListener {
	public JPanel panel;
	public JComboGenerico<Empleado> cbEmpleados;
	public JButton btAnadir;
	public DefaultListModel<Empleado> modeloLista;
	public JButton btEliminar;
	public Modelo modelo;
	public PanelBusqueda<Empleado> panelBusqueda;
	
	public PanelAnadirEmpleado() {
		setLayout(new BorderLayout(0, 0));
		modeloLista = new DefaultListModel<>();
		
		panelBusqueda = new PanelBusqueda<Empleado>();
		add(panelBusqueda, BorderLayout.CENTER);
		panelBusqueda.lista.setModel(modeloLista);
		
		panel = new JPanel();
		panelBusqueda.scrollPane.setColumnHeaderView(panel);
		
		cbEmpleados = new JComboGenerico<>();
		cbEmpleados.setPreferredSize(new Dimension(100, 20));
		panel.add(cbEmpleados);
		
		btAnadir = new JButton("+");
		panel.add(btAnadir);
		
		btEliminar = new JButton("-");
		panel.add(btEliminar);

		inicializar();
	}
	
	public void inicializar() {
		modelo = new Modelo();
		List<Empleado> empleados = modelo.getEmpleados();
		cbEmpleados.inicializar(empleados);
		
		btAnadir.addActionListener(this);
		btEliminar.addActionListener(this);
	}
	
	public List<Empleado> getListadoEmpleados() {
		List<Empleado> empleados = new ArrayList<>();
		for (int i = 0; i < modeloLista.size(); i++) {
			empleados.add(modeloLista.getElementAt(i));
		}
		
		return empleados;
	}
	
	public void anadirEmpleados(List<Empleado> empleados) {
		modeloLista.removeAllElements();
		for (Empleado empleado : empleados)
			modeloLista.addElement(empleado);
	}

	public void limpiarEmpleados() {
		modeloLista.removeAllElements();
	}
	
	public void refrescarCb() {
		cbEmpleados.limpiar();
		cbEmpleados.inicializar(modelo.getEmpleados());
	}
	
	public void refrescar() {
		panelBusqueda.refrescar();
		refrescarCb();
	}
	
	public void modoEdicion(boolean edicion) {
		setEnabled(edicion);
		cbEmpleados.setEnabled(edicion);
		btEliminar.setEnabled(edicion);
		btAnadir.setEnabled(edicion);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String actionCommand = e.getActionCommand();
		
		switch (actionCommand) {
		case "+":
			Empleado empleadoSeleccionado = cbEmpleados.getDatoSeleccionado();
			if (empleadoSeleccionado == null)
				return;
			
			if (modeloLista.contains(empleadoSeleccionado))
				return;
			
			modeloLista.addElement(empleadoSeleccionado);
			break;
		case "-":
			if (panelBusqueda.lista.getSelectedIndex() == -1)
				return;
			
				modeloLista.remove(panelBusqueda.lista.getSelectedIndex());
			break;
		}
	}

}
