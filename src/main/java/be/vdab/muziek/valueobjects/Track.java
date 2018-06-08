package be.vdab.muziek.valueobjects;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Embeddable;

@Embeddable
public class Track implements Serializable {
	private static final long serialVersionUID = 1L;
	private String naam;
	private BigDecimal tijd;
	
	protected Track() {
	}

	public Track(String naam, BigDecimal tijd) {
		this.naam = naam;
		this.tijd = tijd;
	}

	public String getNaam() {
		return naam;
	}

	public BigDecimal getTijd() {
		return tijd;
	}

	@Override
	public int hashCode() {
		return naam.hashCode();
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Track)) {
			return false;
		}
		Track andereTrack = (Track) object;
		return naam.equals(andereTrack.naam) ? true : false;
	}
}