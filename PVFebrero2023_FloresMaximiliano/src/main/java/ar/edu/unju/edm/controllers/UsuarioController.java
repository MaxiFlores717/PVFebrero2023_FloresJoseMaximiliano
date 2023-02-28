package ar.edu.unju.edm.controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.unju.edm.dao.IUsuarioDao;
import ar.edu.unju.edm.models.Usuario;
import ar.edu.unju.edm.service.IUsuarioService;


@Controller
public class UsuarioController {

	
	protected final Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private IUsuarioService usuarioService;
	
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(Model model /*, Authentication authentication*/) {
		
//		if(authentication != null) {
//			logger.info("Hola usuario autenticado, tu username es: ".concat(authentication.getName()));
//		}
//		
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
//		if(hasRole("DOCENTE")) {
//			logger.info("Hola ".concat(auth.getName()).concat(" tienes acceso!"));
//		}
//		else {
//			logger.info("Hola ".concat(auth.getName()).concat(" NO tienes acceso!"));
//		}
		
		
		model.addAttribute("titulo", "Listado de usuarios");
		model.addAttribute("usuarios", usuarioService.findAll());
		return "listar";
	}

	@RequestMapping(value = "/form")
	public String crear(Model model) {
		Usuario usuario = new Usuario();
		model.addAttribute("usuario", usuario);
		model.addAttribute("titulo", "Formulario de Usuario");
		return "form";
	}

	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Usuario usuario, BindingResult result, Model model, RedirectAttributes flash) {
		
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Usuario");
			return "form";
		} else {
			usuarioService.save(usuario);
			flash.addFlashAttribute("success", "Usuario creado con exito!");
			return "redirect:/listar";
		}
	}

	@RequestMapping(value = "/form/{dni}")
	public String modificar(@PathVariable(value = "dni") Long dni, Model model) {
		Usuario usuario = null;
		usuario = usuarioService.buscarDni(dni);
		model.addAttribute("usuario", usuario);
		model.addAttribute("titulo", "Editar Usuario");
		

		return "form";
	}

	@RequestMapping(value = "/eliminar/{dni}")
	public String eliminar(@PathVariable(value = "dni") Long dni, RedirectAttributes flash) {
		usuarioService.eliminar(dni);
		flash.addFlashAttribute("error", "Usuario ha sido eliminado con exito!");
		return "redirect:/listar";
	}

}
