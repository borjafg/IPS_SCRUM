package persistence;

import javax.persistence.NoResultException;

import model.Almacenero;

import persistence.util.Jpa;

public class AlmaceneroFinder {

	public static Almacenero findByNombreUsuario(String nombreUsuario) {
		try {
			return Jpa.getManager().createNamedQuery("Almacenero.findByLogin", Almacenero.class)
					.setParameter("login", nombreUsuario).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}