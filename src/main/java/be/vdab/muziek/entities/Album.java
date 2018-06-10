package be.vdab.muziek.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;

import be.vdab.muziek.valueobjects.Track;

@Entity
@Table(name = "albums")
@NamedEntityGraph(name= Album.MET_ARTIEST,
                  attributeNodes =  @NamedAttributeNode("artiest"))
public class Album implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String MET_ARTIEST = "Album.metArtiest";
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String naam;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "artiestid")
	private Artiest artiest;
	@ElementCollection
	@CollectionTable(name = "tracks", joinColumns = @JoinColumn(name = "albumid"))
	private Set<Track> tracks;

	protected Album() {
	}

	public Album(String naam, Artiest artiest) {
		this.naam = naam;
		this.artiest = artiest;
		this.tracks = new LinkedHashSet();
	}

	public long getId() {
		return id;
	}

	public String getNaam() {
		return naam;
	}

	public Artiest getArtiest() {
		return artiest;
	}
	
	public BigDecimal getTotaleDuur() {
		BigDecimal totaleDuur = BigDecimal.ZERO;
		for (Track track : tracks) {
			totaleDuur = totaleDuur.add(track.getTijd());
		}
		return totaleDuur;
	}
	
	public Set<Track> getTracks(){
		return Collections.unmodifiableSet(tracks);
	}
}