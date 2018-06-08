package be.vdab.muziek.repositories;

import java.util.Optional;

import be.vdab.muziek.entities.Artiest;

public interface ArtiestRepository {
	Optional<Artiest> read(long id);
}
