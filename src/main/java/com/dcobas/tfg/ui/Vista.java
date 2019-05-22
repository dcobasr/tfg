package com.dcobas.tfg.ui;

import java.awt.BorderLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.dcobas.tfg.Constantes;
import com.dcobas.tfg.Modelo;
import com.dcobas.tfg.base.Empleado;
import com.dcobas.tfg.base.Proyecto;
import com.dcobas.tfg.beans.JEstado;
import com.dcobas.tfg.beans.PanelEmpleados;
import com.dcobas.tfg.beans.PanelProyectos;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import com.dcobas.tfg.beans.PanelAnadirEmpleado;

public class Vista extends JFrame {

	public JPanel contentPane;
	
	public DefaultListModel<Proyecto> mProyectos;
	public DefaultListModel<Empleado> mEmpleados;
	
	public JTabbedPane tabbedPane;
	public PanelEmpleados panelEmpleados;
	public PanelProyectos panelProyectos;

	public JEstado jEstado;
	public Constantes constantes;
	public Modelo modelo;
	
	public Vista() {
		
		setMinimumSize(new Dimension(600, 350));
		setPreferredSize(new Dimension(800, 400));
		setTitle("Prodeck");
		Image icon = new ImageIcon(Proyecto.class.getResource("icons\\list-add.png")).getImage();
        setIconImage(icon);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 875, 484);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		jEstado = new JEstado();
		constantes= new Constantes();
		jEstado.setMensajeConfirmacion("Bienvenido a Proyectos "+constantes.versionAplicacion+" de "+constantes.creador);
		contentPane.add(jEstado, BorderLayout.SOUTH);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		modelo = new Modelo();
		panelProyectos= new PanelProyectos(modelo);
		panelEmpleados= new PanelEmpleados(modelo);
		tabbedPane.addTab("Proyectos", panelProyectos);
		tabbedPane.addTab("Empleados", panelEmpleados);
		
		
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		mProyectos = new DefaultListModel<>();
		mEmpleados = new DefaultListModel<>();
		
		try {
	        UIManager.setLookAndFeel(
	            UIManager.getSystemLookAndFeelClassName());
	    } 
	    catch (Exception ex) {}
		
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
