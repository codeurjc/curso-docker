package es.urjc.code.daw.tablonanuncios;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TablonController {

	@Autowired
	private Usuario usuario;

	private ConcurrentMap<Integer,Anuncio> anuncios = new ConcurrentHashMap<>();
	private AtomicInteger lastId = new AtomicInteger();

	@PostConstruct
	public void init(){
		
		Anuncio a1 = new Anuncio("Pepe", "Hola caracola", "XXXX");
		a1.setId(lastId.getAndIncrement());
		anuncios.put(a1.getId(),a1);
		
		Anuncio a2 = new Anuncio("Juan", "Hola caracola", "XXXX");
		a2.setId(lastId.getAndIncrement());
		anuncios.put(a2.getId(),a2);
	}

	@RequestMapping("/")
	public String tablon(Model model, HttpSession session) {

		model.addAttribute("anuncios", anuncios.values());
		model.addAttribute("bienvenida", session.isNew());

		return "tablon";
	}

	@RequestMapping("/anuncio/nuevo")
	public String nuevoAnuncio(Model model, Anuncio anuncio) {

		anuncio.setId(lastId.getAndIncrement());
		anuncios.put(anuncio.getId(), anuncio);

		usuario.setNombre(anuncio.getNombre());
		usuario.incAnuncios();

		return "anuncio_guardado";

	}

	@RequestMapping("/anuncio/nuevo_form")
	public String nuevoAnuncioForm(Model model) {

		model.addAttribute("nombre", usuario.getNombre());
		model.addAttribute("num_anuncios", usuario.getNumAnuncios());

		return "nuevo_anuncio";
	}

	@RequestMapping("/anuncio/{id}")
	public String nuevoAnuncio(Model model, @PathVariable int id) {

		Anuncio anuncio = anuncios.get(id);

		model.addAttribute("anuncio", anuncio);

		return "ver_anuncio";
	}
}