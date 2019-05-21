package com.dcobas.tfg;

import com.dcobas.tfg.ui.Vista;

public class Aplicacion {
	public static void main(String args[]) {
		
		Modelo modelo = new Modelo();
		Vista vista = new Vista();
		Controlador controlador = new Controlador(vista, modelo);
	}
}
