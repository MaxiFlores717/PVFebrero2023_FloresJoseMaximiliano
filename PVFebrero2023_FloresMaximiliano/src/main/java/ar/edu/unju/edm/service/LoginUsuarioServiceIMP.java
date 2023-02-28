package ar.edu.unju.edm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.dao.IUsuarioDao;
import ar.edu.unju.edm.models.Usuario;

@Service
public class LoginUsuarioServiceIMP implements UserDetailsService{

	
	@Autowired
	IUsuarioDao iUsuario;
	
	@Override
	public UserDetails loadUserByUsername(String dni) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
//		Usuario usuarioEncontrado = iUsuario.findByDni(Long.parseLong(dni)).orElseThrow(()-> new UsernameNotFoundException("Login invalido"));
		Usuario usuarioEncontrado = iUsuario.findById(Long.parseLong(dni)).orElseThrow(()->new UsernameNotFoundException("Login Invalido"));
		
		
		List<GrantedAuthority> tipos = new ArrayList<>();
		GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(usuarioEncontrado.getTipoUsuario());
		tipos.add(grantedAuthority);
		
		UserDetails user = new User(dni,usuarioEncontrado.getPassword(),tipos);
		
		return user;
	}

}
