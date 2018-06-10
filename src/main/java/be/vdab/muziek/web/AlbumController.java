package be.vdab.muziek.web;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import be.vdab.muziek.entities.Album;
import be.vdab.muziek.services.AlbumService;

@Controller
@RequestMapping("/album")
class AlbumController {
	private final AlbumService albumService;
	private final static String VIEW = "album";
	private final static String REDIRECT_ALBUM_NIET_GEVONDEN = "redirect:/";

	public AlbumController(AlbumService albumService) {
		this.albumService = albumService;
	}
	
	@GetMapping("{id}")
	ModelAndView album(@PathVariable long id, RedirectAttributes redirectAttributes) {
		Optional<Album> album = albumService.read(id);
		if (album.isPresent()) {
			return new ModelAndView(VIEW,"album",album.get());
		}
		redirectAttributes.addAttribute("fout","album niet gevonden");
		return new ModelAndView(REDIRECT_ALBUM_NIET_GEVONDEN);
	}
}
