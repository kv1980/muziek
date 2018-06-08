package be.vdab.muziek.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.muziek.entities.Album;
import be.vdab.muziek.repositories.AlbumRepository;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
class DefaultAlbumService implements AlbumService {
	private AlbumRepository repository;

	public DefaultAlbumService(AlbumRepository repository) {
		this.repository = repository;
	}

	@Override
	public Optional<Album> read(long id) {
		return repository.read(id);
	}

	@Override
	public List<Album> findAll() {
		return repository.findAll();
	}
}