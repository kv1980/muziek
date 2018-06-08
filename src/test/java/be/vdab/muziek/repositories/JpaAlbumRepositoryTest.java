package be.vdab.muziek.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
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
import be.vdab.muziek.valueobjects.Track;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Sql("/insertArtiest.sql")
@Sql("/insertAlbum.sql")
@Sql("/insertTracks.sql")
@Import(JpaAlbumRepository.class)

public class JpaAlbumRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	private JpaAlbumRepository repository;
	@Autowired
	private EntityManager manager;
	
	private long idVanAlbumVlaamseSlagers() {
		return super.jdbcTemplate.queryForObject("select id from albums where naam ='Vlaamse slagers'",Long.class).longValue();
	}
	
	@Test
	public void read_leest_bestaand_album_in() {
		Album album = repository.read(idVanAlbumVlaamseSlagers()).get();
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
	
	@Test
	public void artiest_van_album_kan_worden_uitgelezen() {
		Album albumVlaamseSlagers = repository.read(idVanAlbumVlaamseSlagers()).get();
		assertEquals("Hans",albumVlaamseSlagers.getArtiest().getNaam());
	}
	
	@Test
	public void tracks_van_album_kunnen_worden_uitgelezen() {
		Album albumVlaamseSlagers = repository.read(idVanAlbumVlaamseSlagers()).get();
		assertEquals(3,albumVlaamseSlagers.getTracks().size());
		assertTrue(albumVlaamseSlagers.getTracks().contains(new Track("Eviva Espana",BigDecimal.valueOf(5))));
		assertTrue(albumVlaamseSlagers.getTracks().contains(new Track("J aime la vie",BigDecimal.valueOf(6))));
		assertTrue(albumVlaamseSlagers.getTracks().contains(new Track("Anne",BigDecimal.valueOf(4))));
		System.out.println(albumVlaamseSlagers.getNaam()+" heeft als tracks: \n");
		albumVlaamseSlagers.getTracks().stream()
									   .forEach(track -> System.out.println(track.getNaam()));
	}
}