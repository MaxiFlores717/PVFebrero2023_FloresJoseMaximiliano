package ar.edu.unju.edm.controllers;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.unju.edm.dao.IUsuarioDao;
import ar.edu.unju.edm.models.Habitacion;
import ar.edu.unju.edm.models.Usuario;
import ar.edu.unju.edm.service.IUsuarioService;


@Controller
public class UsuarioController {

	
	protected final Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private IUsuarioService usuarioService;
	
	
	@RequestMapping(value = {"/paginaInicio", "/"})
	public String Inicio() {
		return "paginaInicio";
	}
	
	@RequestMapping("/filtrar")
	public String filtrar(Model model) {
		model.addAttribute("titulo", "Filtro:");
		return "filtrar";
	}
	
	@RequestMapping("/bienvenida")
	public String bienvenida() {
		return "bienvenida";
	}
	
	@RequestMapping(value = "/listarHuesped", method = RequestMethod.GET)
	public String listarNivel(Model model, Authentication authentication) {
		
		if(authentication != null) {
			logger.info("Hola usuario autenticado, tu username es: ".concat(authentication.getName()));
		}		
		model.addAttribute("titulo", "Huespedes registrados:");
		model.addAttribute("usuarios", usuarioService.findHuesped());
		return "listarHuesped";
	}
	
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(Model model , Authentication authentication, @Param("dni") Long dni,
			@Param("fecha") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fecha, 
			@Param("nacionalidad") String nacionalidad,
			@Param("tipo") String tipo) throws Exception {
				
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
		
		
		model.addAttribute("titulo", "Listado de usuarios");
		
		if(nacionalidad==null) {
			nacionalidad="";
		}
		if(tipo==null) {
			tipo="";
		}
		
		//listar todos
		if(dni==null && fecha==null && nacionalidad=="") {
			logger.info("Hola "+ usuarioService.buscarDni(Long.parseLong(auth.getName())).getNombre().concat(" listando TODOS los usuarios!"));
			model.addAttribute("usuarios", usuarioService.findAll());
		}

		
		
		if(dni!=null && fecha==null && nacionalidad=="" && tipo=="") {	
			logger.info("Hola "+ usuarioService.buscarDni(Long.parseLong(auth.getName())).getNombre().concat(" listando por DNI:".concat(dni.toString())+ "!"));
			model.addAttribute("usuarios", usuarioService.findByDni(dni));
		}
		if(dni==null && fecha!=null && nacionalidad=="" && tipo=="") {
			logger.info("Hola "+ usuarioService.buscarDni(Long.parseLong(auth.getName())).getNombre().concat(", listando por FECHA:".concat(fecha.toString())+"!"));
			model.addAttribute("usuarios", usuarioService.findByFecha(fecha));
		}
		if(dni==null && fecha==null && nacionalidad!="" && tipo=="") {
			logger.info("Hola "+ usuarioService.buscarDni(Long.parseLong(auth.getName())).getNombre().concat(", listando por NACIONALIDAD:".concat(nacionalidad.toString())+"!"));
			model.addAttribute("usuarios", usuarioService.findByNacionalidad(nacionalidad));
		}
		if(dni==null && fecha==null && nacionalidad=="" && tipo!="") {
			logger.info("Hola "+ usuarioService.buscarDni(Long.parseLong(auth.getName())).getNombre().concat(" Y listando por TIPO:").concat(tipo).toString()+"!");
			model.addAttribute("usuarios", usuarioService.findByTipoUsuario(tipo));
		}
		
		
		
		
		
		if(dni!=null && fecha!=null && nacionalidad=="" && tipo=="") {
			logger.info("Hola "+ usuarioService.buscarDni(Long.parseLong(auth.getName())).getNombre().concat(" listando por DNI:".concat(dni.toString()).concat(" Y listando por FECHA:".concat(fecha.toString())+"!")));
			model.addAttribute("usuarios", usuarioService.findByDniAndFecha(dni, fecha));
		}
		if(dni!=null && fecha==null && nacionalidad!="" && tipo=="") {
			logger.info("Hola "+ usuarioService.buscarDni(Long.parseLong(auth.getName())).getNombre().concat(" listando por DNI:".concat(dni.toString()).concat(" Y listando por NACIONALIDAD:".concat(nacionalidad.toString())+"!")));
			model.addAttribute("usuarios", usuarioService.findByNacionalidadAndDni(nacionalidad, dni));
		}
		if(dni!=null && fecha==null && nacionalidad=="" && tipo!="") {
			logger.info("Hola "+ usuarioService.buscarDni(Long.parseLong(auth.getName())).getNombre().concat(" listando por DNI:".concat(dni.toString()).concat(" Y listando por TIPO:").concat(tipo).toString()+"!"));
			model.addAttribute("usuarios", usuarioService.findByDniAndTipoUsuario(dni, tipo));
		}
		
		
		
		if(dni==null && fecha!=null && nacionalidad!="" && tipo=="") {
			logger.info("Hola "+ usuarioService.buscarDni(Long.parseLong(auth.getName())).getNombre().concat(", listando por FECHA:".concat(fecha.toString()).concat(", listando por NACIONALIDAD:".concat(nacionalidad.toString())+"!")));
			model.addAttribute("usuarios", usuarioService.findByFechaAndNacionalidad(fecha, nacionalidad));
		}
		if(dni==null && fecha!=null && nacionalidad=="" && tipo!="") {
			logger.info("Hola "+ usuarioService.buscarDni(Long.parseLong(auth.getName())).getNombre().concat(", listando por FECHA:".concat(fecha.toString()).concat(" Y listando por TIPO:").concat(tipo).toString()+"!"));
			model.addAttribute("usuarios", usuarioService.findByFechaAndTipoUsuario(fecha, tipo));
		}
		
		
		
		
		if(dni==null && fecha==null && nacionalidad!="" && tipo!="") {
			logger.info("Hola "+ usuarioService.buscarDni(Long.parseLong(auth.getName())).getNombre().concat(", listando por NACIONALIDAD:".concat(nacionalidad.toString()).concat(" Y listando por TIPO:").concat(tipo).toString()+"!"));
			model.addAttribute("usuarios", usuarioService.findByNacionalidadAndTipoUsuario(nacionalidad, tipo));
		}
		
		
		
		if(dni!=null && fecha==null && nacionalidad!="" && tipo!="") {
			model.addAttribute("usuarios", usuarioService.findByDniAndNacionalidadAndTipoUsuario(dni, nacionalidad, tipo));
		}
		if(dni!=null && fecha!=null && nacionalidad!="" && tipo=="") {	
			logger.info("Hola ".concat(auth.getName()).concat(" listando por DNI:".concat(dni.toString()).concat(", listando por FECHA:".concat(fecha.toString()).concat(" Y listando por NACIONALIDAD:".concat(nacionalidad.toString())+"!"))));
			model.addAttribute("usuarios", usuarioService.findByDniAndFechaAndNacionalidad(dni, fecha, nacionalidad));
		}
		if(dni!=null && fecha!=null && nacionalidad=="" && tipo!="") {
			logger.info("Hola "+ usuarioService.buscarDni(Long.parseLong(auth.getName())).getNombre().concat(" listando por DNI:".concat(dni.toString()).concat(", listando por FECHA:".concat(fecha.toString()).concat(" Y listando por TIPO:").concat(tipo).toString()+"!")));
			model.addAttribute("usuarios", usuarioService.findByDniAndFechaAndTipoUsuario(dni, fecha, tipo));
		}
		if(dni==null && fecha!=null && nacionalidad!="" && tipo!="") {
			logger.info("Hola "+ usuarioService.buscarDni(Long.parseLong(auth.getName())).getNombre().concat(" listando por FECHA:".concat(fecha.toString()).concat(", listando por NACIONALIDAD").concat(nacionalidad.toString()).concat(" Y listando por TIPO:").concat(tipo).toString()+"!"));
			model.addAttribute("usuarios", usuarioService.findByFechaAndTipoUsuarioAndNacionalidad(fecha, tipo, nacionalidad));
		}
		
		
		
		
		
		
		
		if(dni!=null && fecha!=null && nacionalidad!="" && tipo!="") {
			logger.info("Hola "+ usuarioService.buscarDni(Long.parseLong(auth.getName())).getNombre().concat(" listando por DNI:".concat(dni.toString()).concat(", listando por FECHA:".concat(fecha.toString()).concat(", listando por NACIONALIDAD:".concat(nacionalidad.toString()).concat(" Y listando por TIPO:").concat(tipo).toString()+"!"))));
			model.addAttribute("usuarios", usuarioService.findByDniAndFechaAndNacionalidadAndTipoUsuario(dni, fecha, nacionalidad, tipo));
		}
		
		
		
		
		model.addAttribute("usuarioLogeado", usuarioService.buscarDni(Long.parseLong(auth.getName())));
		
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
	public String guardar(@Valid Usuario usuario, BindingResult result, Model model, RedirectAttributes flash, Authentication authentication) {
		
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Usuario");
			return "form";
		} else {
			try {
				usuarioService.buscarDni(usuario.getDni());
				model.addAttribute("mensajeError", "El DNI que intenta ingresar ya esta ocupado!");
				model.addAttribute("titulo", "Formulario de Usuario");
				return "form";
			} catch (Exception e) {
				// TODO: handle exception
				usuarioService.save(usuario);
				
				if(hasRole("Administrador")) {
					flash.addFlashAttribute("success", "Usuario creado con exito!");
					return "redirect:/listar";
				}
				else {
					flash.addFlashAttribute("success", "Usuario creado con exito!");
					return "redirect:/paginaInicio";
				}
			}
			}
			
	}

	@RequestMapping(value = "/form/{dni}")
	public String modificar(@PathVariable(value = "dni") Long dni, Model model, RedirectAttributes flash) throws Exception {
		Usuario usuario = null;
		try {
			usuario = usuarioService.buscarDni(dni);
			model.addAttribute("usuario", usuario);
			model.addAttribute("titulo", "Editar Usuario");		
			return "formModificar";
			
		} catch (Exception e) {
			// TODO: handle exception
			flash.addFlashAttribute("error", "El Usuario que intenta modficar NO existe!");
			return "redirect:/listar";
		}
	}

	@RequestMapping(value = "/formModificar", method = RequestMethod.POST)
	public String modificar(@Valid Usuario usuario, BindingResult result, Model model, RedirectAttributes flash, Authentication authentication) {
		
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Editar Usuario");
			return "formModificar";
		} else {
			usuarioService.save(usuario);
				
				flash.addFlashAttribute("success", "Usuario editado con exito!");
				return "redirect:/listar";
			}
			
	}


	@RequestMapping(value = "/eliminar/{dni}")
	public String eliminar(@PathVariable(value = "dni") Long dni, RedirectAttributes flash)	{
		
		try {
			usuarioService.buscarDni(dni);
			usuarioService.eliminar(dni);
			flash.addFlashAttribute("error", "Usuario ha sido eliminado con exito!");
			return "redirect:/listar";
		} catch (Exception e) {
			// TODO: handle exception
			flash.addFlashAttribute("error", "El Usuario que intenta eliminar NO existe!");
			return "redirect:/listar";
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
