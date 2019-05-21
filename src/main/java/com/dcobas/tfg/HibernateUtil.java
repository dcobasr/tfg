package com.dcobas.tfg;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.dcobas.tfg.base.Empleado;
import com.dcobas.tfg.base.Proyecto;
import com.dcobas.tfg.base.Usuario;

public class HibernateUtil {

  private static SessionFactory sessionFactory;
  private static Session session;

  public static void buildSessionFactory() {

	Configuration configuration = new Configuration();
	configuration.configure();
	
	// CLASES DE LA APP
	// Se registran las clases que hay que mapear con cada tabla de la base de datos
	configuration.addAnnotatedClass(Usuario.class);
	configuration.addAnnotatedClass(Empleado.class);
	configuration.addAnnotatedClass(Proyecto.class);
	
	ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
	  configuration.getProperties()).build();
	sessionFactory = configuration.buildSessionFactory(serviceRegistry);
  }
	
  public static void openSession() {
    session = sessionFactory.openSession();
  }
	
  public static Session getCurrentSession() {
	
    if ((session == null) || (!session.isOpen()))
      openSession();
			
    return session;
  }
	
  public static void closeSessionFactory() {
	
    if (session != null)
      session.close();
		
    if (sessionFactory != null)
      sessionFactory.close();
  }
}