package be.vdab.muziek.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.muziek.services.AlbumService;

@Controller
@RequestMapping("/")
class IndexController {
	private final AlbumService albumService;
		
	IndexController(AlbumService albumService) {
		this.albumService = albumService;
	}

	@GetMapping
	ModelAndView index() {
		return new ModelAndView("index","albums",albumService.findAll());
	}
}