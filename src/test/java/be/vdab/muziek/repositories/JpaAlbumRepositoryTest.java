package be.vdab.muziek.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import be.vdab.muziek.entities.Album;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Sql("/insertArtiest.sql")
@Sql("/insertAlbum.sql")
@Import(JpaAlbumRepository.class)

public class JpaAlbumRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	private JpaAlbumRepository repository;
	@Autowired
	private EntityManager manager;
	
	private long idVanAlbumVlaamseSlager() {
		return super.jdbcTemplate.queryForObject("select id from albums where naam ='Vlaamse slagers'",Long.class).longValue();
	}
	
	@Test
	public void read_leest_bestaand_album_in() {
		Album album = repository.read(idVanAlbumVlaamseSlager()).get();
		assertEquals("Vlaamse slagers",album.getNaam());
	}
	
	@Test
	public void read_leest_onbestaand_album_niet_in() {
		assertFalse(repository.read(-1L).isPresent());
	}
	
	@Test
	public void findAll() {
		List<Album> albums = repository.findAll();
		manager.clear();
		assertEquals(super.countRowsInTable("albums"), albums.size());
		String vorigeAlbumNaam = "";
		for (Album album : albums) {
			assertTrue(album.getNaam().compareTo(vorigeAlbumNaam) > 0);
			vorigeAlbumNaam = album.getNaam();
			System.out.println(album.getNaam()+" werd uitgevoerd door "+album.getArtiest().getNaam());
		}
	}
}