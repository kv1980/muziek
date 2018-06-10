package be.vdab.muziek.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.muziek.services.AlbumService;

@Controller
@RequestMapping("/album")
class AlbumController {
	private final AlbumService albumService;
	private final String VIEW = "album";

	public AlbumController(AlbumService albumService) {
		this.albumService = albumService;
	}
	
	@GetMapping("{id}")
	ModelAndView album(@PathVariable long id) {
		return new ModelAndView(VIEW,"album",albumService.read(id).get());
	}
}
