package be.vdab.muziek.entities;

import java.io.Serializable;

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

	protected Album() {
	}

	public Album(String naam, Artiest artiest) {
		this.naam = naam;
		this.artiest = artiest;
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
}