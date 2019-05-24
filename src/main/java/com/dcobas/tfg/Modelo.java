package com.dcobas.tfg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.dcobas.tfg.base.Proyecto;
import com.dcobas.tfg.base.Empleado;
import com.dcobas.tfg.base.Usuario;

public class Modelo {
	
	private Connection conexion;
	
	public Modelo() {
		conectar();
	}
	
	@Override
	public void finalize() {
		desconectar();
	}
	
	public boolean iniciarSesion(String usuario, String contrasena) throws SQLException {
		Session sesion = HibernateUtil.getCurrentSession();
		Query query = HibernateUtil.getCurrentSession().
				  createQuery("FROM Usuario u WHERE u.usuario = :usuario AND u.contrasena = SHA1(:contrasena)");
				query.setParameter("usuario", usuario);
				query.setParameter("contrasena", contrasena);
				Usuario cliente = (Usuario) query.uniqueResult();
				return (cliente != null);
	}
	
	public void conectar() {
		HibernateUtil.buildSessionFactory();
	}
	
	private void desconectar() {
		HibernateUtil.closeSessionFactory();
	}
	
	public void guardar(Proyecto proyecto) {
		Session sesion = HibernateUtil.getCurrentSession();
		sesion.beginTransaction();
		sesion.save(proyecto);
		sesion.getTransaction().commit();
		sesion.close();
	}
	
	public void eliminar(Proyecto proyecto) {
		Session sesion = HibernateUtil.getCurrentSession();
		sesion.beginTransaction();
		sesion.delete(proyecto);
		sesion.getTransaction().commit();
		sesion.close();
	}
	
	public void eliminarProyectos() {
		Session sesion = HibernateUtil.getCurrentSession();
		sesion.beginTransaction();
		for (Proyecto proyecto : getProyectos()) {
			sesion.delete(proyecto);
		}
		sesion.getTransaction().commit();
		sesion.close();
	}
	
	public boolean existeProyecto(String nombre) {
		Session sesion = HibernateUtil.getCurrentSession();
		Query<Proyecto> query = sesion.createQuery("FROM Proyecto WHERE nombre = :nombre");
		query.setParameter("nombre", nombre);
		Proyecto proyecto= query.uniqueResult();

		return (proyecto != null);
	}
	
	public List<Proyecto> getProyectos() {
		Session sesion = HibernateUtil.getCurrentSession();
		ArrayList<Proyecto> proyectos = (ArrayList<Proyecto>) sesion.createQuery("FROM Proyecto").list();
		return proyectos;
	}
	
	public void guardar(Empleado empleado) {
		Session sesion = HibernateUtil.getCurrentSession();
		sesion.beginTransaction();
		sesion.save(empleado);
		sesion.getTransaction().commit();
		sesion.close();
	}
	
	public void eliminar(Empleado empleado) {
		Session sesion = HibernateUtil.getCurrentSession();
		sesion.beginTransaction();
		sesion.delete(empleado);
		sesion.getTransaction().commit();
		sesion.close();
	}
	
	public void eliminarEmpleados() {
		Session sesion = HibernateUtil.getCurrentSession();
		Query<Empleado> query = sesion.createQuery("DELETE * FROM Proyecto");
		query.executeUpdate();
		sesion.close();
	}
	
	public List<Empleado> getEmpleados() {
		Session sesion = HibernateUtil.getCurrentSession();
		ArrayList<Empleado> empleados = (ArrayList<Empleado>) sesion.createQuery("FROM Empleado").list();
		return empleados;
	}
}
