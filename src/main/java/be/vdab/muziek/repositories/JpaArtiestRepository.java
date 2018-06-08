package be.vdab.muziek.repositories;

import java.util.Optional;

import javax.persistence.EntityManager;

import be.vdab.muziek.entities.Artiest;

class JpaArtiestRepository implements ArtiestRepository {
	private EntityManager manager;

	protected JpaArtiestRepository(EntityManager manager) {
		this.manager = manager;
	}

	@Override
	public Optional<Artiest> read(long id) {
		return Optional.ofNullable(manager.find(Artiest.class,id));
	}
}
