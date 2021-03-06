package com.dcobas.tfg.ui;

import java.awt.BorderLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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

public class Vista extends JFrame{

	public JPanel contentPane;
	public JTabbedPane tabbedPane;
	public PanelEmpleados panelEmpleados;
	public PanelProyectos panelProyectos;

	public JEstado jEstado;
	public Modelo modelo;
	
	public Vista() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Vista.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		
		setMinimumSize(new Dimension(600, 350));
		setPreferredSize(new Dimension(800, 400));
		
		setTitle("Prodeck");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 875, 484);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		jEstado = new JEstado();
		contentPane.add(jEstado, BorderLayout.SOUTH);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		modelo = new Modelo();
		panelProyectos= new PanelProyectos(modelo);
		panelEmpleados= new PanelEmpleados(modelo);
		tabbedPane.addTab("Proyectos", panelProyectos);
		tabbedPane.addTab("Empleados", panelEmpleados);
		
		
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
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
