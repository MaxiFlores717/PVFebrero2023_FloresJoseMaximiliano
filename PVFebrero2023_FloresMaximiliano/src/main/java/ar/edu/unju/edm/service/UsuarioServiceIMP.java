package ar.edu.unju.edm.service;

import java.time.LocalDate;
import java.util.ArrayList;
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

	@Override
	public List<Usuario> findHuesped() {
		// TODO Auto-generated method stub
		List<Usuario> usuario = new ArrayList<>();
		List<Usuario> usuario2 = new ArrayList<>();
		usuario = (List<Usuario>) usuarioDao.findAll();
		for (int i = 0; i < usuario.size(); i++) {
			if (usuario.get(i).getTipoUsuario().equals("Huesped")) {
				usuario2.add(usuario.get(i));
			}
		}
		return usuario2;
	}

	@Override
	public List<Usuario> findByDni(Long dni) {
		// TODO Auto-generated method stub
		return (List<Usuario>) usuarioDao.findByDni(dni);
	}

	@Override
	public List<Usuario> findByNacionalidad(String nacionalidad) {
		// TODO Auto-generated method stub
		return (List<Usuario>) usuarioDao.findByNacionalidad(nacionalidad);
	}

	@Override
	public List<Usuario> findByTipoUsuario(String tipoUsuario) {
		// TODO Auto-generated method stub
		return (List<Usuario>) usuarioDao.findByTipoUsuario(tipoUsuario);
	}

	@Override
	public List<Usuario> findByFecha(LocalDate fecha) {
		// TODO Auto-generated method stub
		return (List<Usuario>) usuarioDao.findByFecha(fecha);
	}

	@Override
	public List<Usuario> findByNacionalidadAndDni(String nacionalidad, Long dni) {
		// TODO Auto-generated method stub
		return (List<Usuario>) usuarioDao.findByNacionalidadAndDni(nacionalidad, dni);
	}

	@Override
	public List<Usuario> findByDniAndTipoUsuario(Long dni, String tipoUsuario) {
		// TODO Auto-generated method stub
		return (List<Usuario>) usuarioDao.findByDniAndTipoUsuario(dni, tipoUsuario);
	}

	@Override
	public List<Usuario> findByDniAndFecha(Long dni, LocalDate fecha) {
		// TODO Auto-generated method stub
		return (List<Usuario>) usuarioDao.findByDniAndFecha(dni, fecha);
	}

	@Override
	public List<Usuario> findByFechaAndNacionalidad(LocalDate fecha, String nacionalidad) {
		// TODO Auto-generated method stub
		return (List<Usuario>) usuarioDao.findByFechaAndNacionalidad(fecha, nacionalidad);
	}

	@Override
	public List<Usuario> findByFechaAndTipoUsuario(LocalDate fecha, String tipoUsuario) {
		// TODO Auto-generated method stub
		return (List<Usuario>) usuarioDao.findByFechaAndTipoUsuario(fecha, tipoUsuario);
	}

	@Override
	public List<Usuario> findByNacionalidadAndTipoUsuario(String nacionalidad, String tipoUsuario) {
		// TODO Auto-generated method stub
		return (List<Usuario>) usuarioDao.findByNacionalidadAndTipoUsuario(nacionalidad, tipoUsuario);
	}

	@Override
	public List<Usuario> findByDniAndNacionalidadAndTipoUsuario(Long dni, String nacionalidad, String tipoUsuario) {
		// TODO Auto-generated method stub
		return (List<Usuario>) usuarioDao.findByDniAndNacionalidadAndTipoUsuario(dni, nacionalidad, tipoUsuario);
	}

	@Override
	public List<Usuario> findByDniAndFechaAndNacionalidad(Long dni, LocalDate fecha, String nacionalidad) {
		// TODO Auto-generated method stub
		return (List<Usuario>) usuarioDao.findByDniAndFechaAndNacionalidad(dni, fecha, nacionalidad);
	}

	@Override
	public List<Usuario> findByDniAndFechaAndTipoUsuario(Long dni, LocalDate fecha, String tipoUsuario) {
		// TODO Auto-generated method stub
		return (List<Usuario>) usuarioDao.findByDniAndFechaAndTipoUsuario(dni, fecha, tipoUsuario);
	}

	@Override
	public List<Usuario> findByFechaAndTipoUsuarioAndNacionalidad(LocalDate fecha, String tipoUsuario,
			String nacionalidad) {
		// TODO Auto-generated method stub
		return (List<Usuario>) usuarioDao.findByFechaAndTipoUsuario(fecha, tipoUsuario);
	}

	@Override
	public List<Usuario> findByDniAndFechaAndNacionalidadAndTipoUsuario(Long dni, LocalDate fecha, String nacionalidad,
			String tipoUsuario) {
		// TODO Auto-generated method stub
		return (List<Usuario>) usuarioDao.findByDniAndFechaAndNacionalidadAndTipoUsuario(dni, fecha, nacionalidad, tipoUsuario);
	}

	@Override
	public boolean existeDni(Long dni) {
		// TODO Auto-generated method stub
		return usuarioDao.existsById(dni);
	}


}
