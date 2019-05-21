package com.dcobas.tfg.base;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="empleados")
public class Empleado {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	@Column(name="nombre")
	private String nombre;
	@Column(name="apellidos")
	private String apellidos;
	@Column(name="edad")
	private int edad;
	@Column(name="email")
	private String email;
	@Column(name="sector")
	private String sector;
	@Column(name="documentacion")
	private String documentacion;
	@Column(name="nombre_imagen")
	private String nombreImagen;
	
	@ManyToMany(cascade = CascadeType.DETACH, mappedBy = "empleados")
	private List<Proyecto> proyectos;
	
	public List<Proyecto> getProyectos() {
		return proyectos;
	}

	
	public Empleado() {}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	public String getDocumentacion() {
		return documentacion;
	}
	public void setDocumentacion(String documentacion) {
		this.documentacion = documentacion;
	}

	public String getNombreImagen() {
		return nombreImagen;
	}

	public void setNombreImagen(String nombreImagen) {
		this.nombreImagen = nombreImagen;
	}

	@Override
	public String toString() {
		return nombre +" "+ apellidos;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Empleado))
			return false;
		
		Empleado empleado = (Empleado) o;
		
		if (nombre.equals(empleado.getNombre()))
			return true;
		
		return false;
	}
}
