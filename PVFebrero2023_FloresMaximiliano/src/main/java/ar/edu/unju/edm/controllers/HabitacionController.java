
package ar.edu.unju.edm.controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.unju.edm.dao.IUsuarioDao;
import ar.edu.unju.edm.models.Habitacion;
import ar.edu.unju.edm.models.Usuario;
import ar.edu.unju.edm.service.IHabitacionService;


@Controller
public class HabitacionController {

	
	protected final Log logger = LogFactory.getLog(this.getClass());
	
	
	@Autowired
	private IHabitacionService habitacionService;
	
	
	@RequestMapping(value = "/listarHabitacion", method = RequestMethod.GET)
	public String listarNivel(Model model, Authentication authentication) {
		
		if(authentication != null) {
			logger.info("Hola usuario autenticado, tu username es: ".concat(authentication.getName()));
		}		
		model.addAttribute("titulo", "Elije una Habitación:");
		model.addAttribute("habitaciones", habitacionService.findlibres());
		return "listarHabitacion";
	}	

	@RequestMapping(value = "/formHabitacion")
	public String crear(Model model) {
		Habitacion habitacion = new Habitacion();
		model.addAttribute("habitacion", habitacion);
		model.addAttribute("titulo", "Formulario de Habitación");
		return "formHabitacion";
	}

	@RequestMapping(value = "/formHabitacion", method = RequestMethod.POST)
	public String guardar(@Valid Habitacion habitacion, BindingResult result, Model model, RedirectAttributes flash, Authentication authentication) {
		
		
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Habitación");
			return "formHabitacion";
		} else {
			try {
				habitacionService.buscarCodigo(habitacion.getCodigo());
				
				model.addAttribute("mensajeError", "El codigo que intenta ingresar ya esta ocupado!");
				model.addAttribute("titulo", "Formulario de Usuario");
				return "formHabitacion";
			} catch (Exception e) {
				// TODO: handle exception
				habitacionService.save(habitacion);
				flash.addFlashAttribute("success", "Habitación creada con exito!");
				return "redirect:/listarTodas";
			}
					
							
		}
	}
	
	@RequestMapping(value = "/listarTodas", method = RequestMethod.GET)
	public String listar(Model model , Authentication authentication) {
		
		if(authentication != null) {
			logger.info("Hola usuario autenticado, tu username es: ".concat(authentication.getName()));
		}
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(hasRole("Administrador")) {
			logger.info("Hola ".concat(auth.getName()).concat(" tienes acceso!"));
		}
		else {
			logger.info("Hola ".concat(auth.getName()).concat(" NO tienes acceso!"));
		}
		
		
		model.addAttribute("titulo", "Listado de Habitaciones completo:");
		model.addAttribute("habitaciones", habitacionService.findAll());
		return "listarTodas";
	}
	
	@RequestMapping(value = "/mostrarHabitacion", method = RequestMethod.GET)
	public String mostrar(Model model , Authentication authentication) {
		
		if(authentication != null) {
			logger.info("Hola usuario autenticado, tu username es: ".concat(authentication.getName()));
		}
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(hasRole("Huesped")) {
			logger.info("Hola ".concat(auth.getName()).concat(" tienes acceso!"));
		}
		else {
			logger.info("Hola ".concat(auth.getName()).concat(" NO tienes acceso!"));
		}
		
		
		model.addAttribute("titulo", "Listado de Habitaciones completo:");
		model.addAttribute("habitaciones", habitacionService.findlibres());
		return "mostrarHabitacion";
	}

	@RequestMapping(value = "/formHabitacion/{codigo}")
	public String modificar(@PathVariable(value = "codigo") Long codigo, Model model, RedirectAttributes flash){
		Habitacion habitacion = null;
		try {
			habitacion = habitacionService.buscarCodigo(codigo);
			model.addAttribute("habitacion", habitacion);
			model.addAttribute("titulo", "Editar Habitacion");
			return "habitacionModificar";
		} catch (Exception e) {
			// TODO: handle exception
			flash.addFlashAttribute("error", "El Habitacion que intenta modficar NO existe!");
			return "redirect:/listarTodas";
		}
	}
	@RequestMapping(value = "/habitacionModificar", method = RequestMethod.POST)
	public String modificar(@Valid Habitacion habitacion, BindingResult result, Model model, RedirectAttributes flash, Authentication authentication) {
		
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Editar Habitación");
			return "habitacionModificar";
		} else {
			habitacionService.save(habitacion);
				flash.addFlashAttribute("success", "Habitación editado con exito!");
				return "redirect:/listarTodas";
			}
			
	}

	@RequestMapping(value = "/eliminarHabitacion/{codigo}")
	public String eliminar(@PathVariable(value = "codigo") Long codigo, RedirectAttributes flash) {
		try {
			habitacionService.buscarCodigo(codigo);
			habitacionService.eliminar(codigo);
			flash.addFlashAttribute("error", "La Habitacion ha sido eliminada con exito!");
			return "redirect:/listarTodas";
		} catch (Exception e) {
			// TODO: handle exception
			flash.addFlashAttribute("error", "La Habitacion que intenta eliminar NO existe!");
			return "redirect:/listarTodas";
		}
	}
	
	
public boolean hasRole(String role) {
		
		SecurityContext context = SecurityContextHolder.getContext();
		
		if(context == null) {
			return false;
		}
		
		Authentication auth = context.getAuthentication();
		
		if(auth == null) {
			
			return false;
		}
		
		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
		
		for(GrantedAuthority authority: authorities) {
			if(role.equals(authority.getAuthority())) {
				logger.info("Hola usuario ".concat(auth.getName()).concat(" tu rol es: ".concat(authority.getAuthority())));
				return true;
			}
		}
		
		return false;
	}

}
