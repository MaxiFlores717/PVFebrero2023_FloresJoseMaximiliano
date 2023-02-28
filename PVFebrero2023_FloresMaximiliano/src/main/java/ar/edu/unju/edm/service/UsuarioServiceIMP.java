package ar.edu.unju.edm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unju.edm.dao.IUsuarioDao;
import ar.edu.unju.edm.models.Usuario;

@Service
public class UsuarioServiceIMP implements IUsuarioService {
	
	@Autowired
	private IUsuarioDao usuarioDao;

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return (List<Usuario>) usuarioDao.findAll();
	}

	@Override
//	@Transactional
	public void save(Usuario usuario) {
		// TODO Auto-generated method stub
		String pw = usuario.getPassword();
		BCryptPasswordEncoder coder = new BCryptPasswordEncoder(4);
		usuario.setPassword(coder.encode(pw));
		
		usuarioDao.save(usuario);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario buscarDni(Long dni) {
		// TODO Auto-generated method stub
		
		return usuarioDao.findById(dni).orElse(null);
	}

	@Override
	@Transactional
	public void eliminar(Long DNI) {
		// TODO Auto-generated method stub
		usuarioDao.deleteById(DNI);
	}

}
