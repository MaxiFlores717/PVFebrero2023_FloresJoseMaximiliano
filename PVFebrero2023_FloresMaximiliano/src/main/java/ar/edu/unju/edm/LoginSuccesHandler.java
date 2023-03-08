package ar.edu.unju.edm;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.SessionFlashMapManager;
 
@Component
public class LoginSuccesHandler extends SimpleUrlAuthenticationSuccessHandler{

	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		boolean tipoAdministrador = false;
		boolean tipoHuesped = false;
		
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for(GrantedAuthority grantedAuthorities : authorities) {
			if(grantedAuthorities.getAuthority().equals("Administrador")) {
				tipoAdministrador=true;
				break;
			}
			else {
				if(grantedAuthorities.getAuthority().equals("Huesped")) {
					tipoHuesped = true;
				}
			}
			
		}
		if(tipoAdministrador==true) {
			redirectStrategy.sendRedirect(request, response, "/listar");
		}
		if(tipoHuesped==true) {
			redirectStrategy.sendRedirect(request, response, "/listarHabitacion");
		}
		
		
		SessionFlashMapManager flashMapManager = new SessionFlashMapManager();
		
		FlashMap flashMap = new FlashMap();
		
		flashMap.put("success", "Hola " + authentication.getName()+", Ha iniciado sesión con éxito!");
		
		flashMapManager.saveOutputFlashMap(flashMap, request, response);
		
		if(authentication != null) {
			logger.info("El usuario '"+ authentication.getName() +"' ha iniciado sesión con éxito");
		}
		
		super.onAuthenticationSuccess(request, response, authentication);
		
		
	}

	
}
