package com.dcobas.tfg.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.JOptionPane;

public class Util {
	
	public static void mensajeInformacion(String titulo, String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje, titulo, 
				JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void mensajeInformacion(String mensaje) {
		mensajeInformacion("Información", mensaje);
	}
	
	public static int mensajeConfirmacion(String mensaje) {
		return JOptionPane.showConfirmDialog(null, mensaje);
	}
	
	public static void mensajeError(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje, "Error", 
				JOptionPane.ERROR_MESSAGE);
	}
	
	public static void mensajeAviso(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje, "Aviso", 
				JOptionPane.WARNING_MESSAGE);
	}

	
	
	public final static String rutaPredefinida=System.getProperty("user.home") + 
						File.separator + "Pictures" + File.separator;
	
	//Ruta donde se creará la carpeta con las imagenes que guarda la app
	public static String rutaImagenes= rutaPredefinida+"AppImages"+File.separator;
	/**
	 * Copia una imagen desde la ruta original a la carpeta de imagenes
	 * con el nombre de fichero que se le pasa
	 * @param rutaOrigen La ruta absoluta a la imagen
	 * @param nombreDestino El nombre del fichero en la carpeta imagenes del programa
	 * @throws IOException 
	 */
	public static void copiarImagen(String rutaOrigen, String nombreDestino) 
			throws IOException {
		
		File carpetaImagenes = new File(rutaImagenes);
		carpetaImagenes.mkdir();
		
		
		Path origen = FileSystems.getDefault().getPath(rutaOrigen);
		
		FileOutputStream destino = new FileOutputStream(
				new File(rutaImagenes+nombreDestino));
		Files.copy(origen, destino);
	}
}