package com.dcobas.tfg.base;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="proyectos")
public class Proyecto {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	@Column(name="nombre")
	private String nombre;
	@Column(name="empresa")
	private String empresa;
	@Column(name="tipo")
	private String tipo;
	@Column(name="descripcion")
	private String descripcion;
	@Column(name="fecha_inicio")
	private String fechaInicio;
	@Column(name="numero_empleados")
	private int numeroEmpleados;
	@Column(name="nombre_imagen")
	private String nombreImagen;
	
	@ManyToMany(cascade = CascadeType.DETACH)
	  @JoinTable(name="proyecto_empleado",
	    joinColumns={@JoinColumn(name="id_proyecto")},
	    inverseJoinColumns={@JoinColumn(name="id_empleado")})
	private List<Empleado> empleados;
	
	public List<Empleado> getEmpleados(){
		return empleados;
	}
	
	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}

	public Proyecto() {
	}
	
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
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public int getNumeroEmpleados() {
		return numeroEmpleados;
	}
	public void setNumeroEmpleados(int numeroEmpleados) {
		this.numeroEmpleados = numeroEmpleados;
	}
	public String getNombreImagen() {
		return nombreImagen;
	}
	public void setNombreImagen(String nombreImagen) {
		this.nombreImagen = nombreImagen;
	}

	@Override
	public String toString() {
		return nombre;
	}
	
}
