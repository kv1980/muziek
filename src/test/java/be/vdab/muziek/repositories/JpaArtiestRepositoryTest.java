package be.vdab.muziek.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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

import be.vdab.muziek.entities.Artiest;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Sql("/insertArtiest.sql")
@Sql("/insertAlbum.sql")
@Import(JpaArtiestRepository.class)
public class JpaArtiestRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	private JpaArtiestRepository repository;
	
	private long idVanArtiestHans() {
		return super.jdbcTemplate.queryForObject("select id from artiesten where naam ='Hans'",Long.class).longValue();
	}
	
	@Test
	public void read_leest_bestaand_artiest_in() {
		Artiest artiest = repository.read(idVanArtiestHans()).get();
		assertEquals("Hans",artiest.getNaam());
	}
	
	@Test
	public void read_leest_onbestaand_artiest_niet_in() {
		assertFalse(repository.read(-1L).isPresent());
	}
}