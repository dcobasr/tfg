package com.dcobas.tfg.beans;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * Panel de listado y búsqueda de elementos de la aplicación
 */
public class PanelBusqueda<T> extends JPanel implements KeyListener {
	public JTextField tfBusqueda;
	public JScrollPane scrollPane;
	public JList<T> lista;
	public DefaultListModel<T> modeloLista;
	
	private List<T> datos;

	public PanelBusqueda() {
		setLayout(new BorderLayout(0, 0));
		
		tfBusqueda = new JTextField();
		add(tfBusqueda, BorderLayout.SOUTH);
		tfBusqueda.setColumns(10);
		
		scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		lista = new JList<>();
		scrollPane.setViewportView(lista);
		modeloLista = new DefaultListModel<>();
		lista.setModel(modeloLista);
	}
	
	public void inicializar(List<T> datos) {
		this.datos = datos;
		
		modeloLista = new DefaultListModel<>();
		lista.setModel(modeloLista);
		
		tfBusqueda.addKeyListener(this);
		
		listar();
	}
	
	public void setDatos(List<T> datos) {
		this.datos = datos;
	}
	
	public void listar() {
		if (datos == null)
			return;
		
		for (T dato : datos) 
			modeloLista.addElement(dato);
	}
	
	public void limpiar() {
		modeloLista.removeAllElements();
	}
	
	public void refrescar() {
		limpiar();
		listar();
	}
	
	public T getSeleccionado() {
		return lista.getSelectedValue();
	}
	
	private void buscar() {
		if(!(tfBusqueda.getText().equals(""))) {
			modeloLista.removeAllElements();
			for (T dato : datos) {
				String buscar =  tfBusqueda.getText();	//palabra buscada
				String texto = dato.toString();			//texto
	
				Pattern regex = Pattern.compile("\\b" + Pattern.quote(buscar) + "\\b", Pattern.CASE_INSENSITIVE);
				Matcher match = regex.matcher(texto);
	
				while (match.find()) { 
					modeloLista.addElement(dato);
				}
			}
		}
		else {
			refrescar();
		}

	}
	
	public void addListener(MouseListener listener) {
		lista.addMouseListener(listener);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			buscar();
		}
	}
}
