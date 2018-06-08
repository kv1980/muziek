package be.vdab.muziek.valueobjects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class TrackTest {
	private Track track1, track1kopie, track2;

	@Before
	public void before() {
		track1 = new Track("Mooi, t leven is mooi", BigDecimal.valueOf(5, 2));
		track1kopie = new Track("Mooi, t leven is mooi", BigDecimal.valueOf(5, 2));
		track2 = new Track("De noorderwind", BigDecimal.valueOf(4, 5));
	}

	@Test
	public void tracks_met_dezelfde_naam_hebben_dezelfde_hashCode() {
		assertEquals(track1.hashCode(), track1kopie.hashCode());
	}

	@Test
	public void tracks_met_verschillende_namen_hebben_verschillende_hashCode() {
		assertNotEquals(track1.hashCode(), track2.hashCode());
	}

	@Test
	public void tracks_met_dezelfde_naam_zijn_gelijk() {
		assertEquals(track1, track1kopie);
	}

	@Test
	public void tracks_met_verschillende_namen_zijn_verschillend() {
		assertNotEquals(track1, track2);
	}

	@Test
	public void track_verschilt_van_null() {
		assertNotEquals(track1, null);
	}

	@Test
	public void track_verschilt_van_een_ander_object() {
		assertNotEquals(track1, "een string");
	}
}
