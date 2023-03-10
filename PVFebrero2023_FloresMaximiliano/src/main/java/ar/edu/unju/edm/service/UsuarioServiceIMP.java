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
	public List<Usuario> findByFechaNacimiento(LocalDate date) {
		// TODO Auto-generated method stub
		List<Usuario> usuario = new ArrayList<>();
		List<Usuario> usuario2 = new ArrayList<>();
		usuario = (List<Usuario>) usuarioDao.findAll();
		for (int i = 0; i < usuario.size(); i++) {
			if (usuario.get(i).getFechaDeNacimiento().compareTo(date) == 0) {
				usuario2.add(usuario.get(i));
			}
		}
		return usuario2;
	}

	@Override
	public List<Usuario> findByNacionalidad(String palabraClave) {
		// TODO Auto-generated method stub
		List<Usuario> usuario = new ArrayList<>();
		List<Usuario> usuario2 = new ArrayList<>();
		usuario = (List<Usuario>) usuarioDao.findAll();
		for (int i = 0; i < usuario.size(); i++) {
			if (usuario.get(i).getNacionalidad().equals(palabraClave)) {
				usuario2.add(usuario.get(i));
			}
		}
		return usuario2;
	}

	@Override
	public List<Usuario> findByDNI(Long dni) {
		// TODO Auto-generated method stub
		List<Usuario> usuario = new ArrayList<>();
		List<Usuario> usuario2 = new ArrayList<>();
		usuario = (List<Usuario>) usuarioDao.findAll();
		for (int i = 0; i < usuario.size(); i++) {
			if (usuario.get(i).getDNI() == dni) {
				usuario2.add(usuario.get(i));
			}
		}
		return usuario2;
	}

	@Override
	public List<Usuario> findByDNIandFecha(Long dni, LocalDate fecha) {
		// TODO Auto-generated method stub
		List<Usuario> usuario = new ArrayList<>();
		List<Usuario> usuario2 = new ArrayList<>();
		usuario = (List<Usuario>) usuarioDao.findAll();
		for (int i = 0; i < usuario.size(); i++) {
			if (usuario.get(i).getDNI() == dni) {
				if (usuario.get(i).getFechaDeNacimiento().compareTo(fecha) == 0) {
					usuario2.add(usuario.get(i));
				}
			}
		}
		return usuario2;
	}

	@Override
	public List<Usuario> findByDNIandFechaandNacionalidad(Long dni, LocalDate fecha, String palabraClave) {
		// TODO Auto-generated method stub
		List<Usuario> usuario = new ArrayList<>();
		List<Usuario> usuario2 = new ArrayList<>();
		usuario = (List<Usuario>) usuarioDao.findAll();
		for (int i = 0; i < usuario.size(); i++) {
			if (usuario.get(i).getDNI() == dni) {
				if (usuario.get(i).getFechaDeNacimiento().compareTo(fecha) == 0) {
					if (usuario.get(i).getNacionalidad().equals(palabraClave)) {
						usuario2.add(usuario.get(i));
					}
				}
			}
		}
		return usuario2;
	}

	@Override
	public List<Usuario> findByDNIandNacionalidad(Long dni, String palabraClave) {
		// TODO Auto-generated method stub
		List<Usuario> usuario = new ArrayList<>();
		List<Usuario> usuario2 = new ArrayList<>();
		usuario = (List<Usuario>) usuarioDao.findAll();
		for (int i = 0; i < usuario.size(); i++) {
			if (usuario.get(i).getDNI() == dni) {
				if (usuario.get(i).getNacionalidad().equals(palabraClave)) {
					usuario2.add(usuario.get(i));
				}
			}
		}
		return usuario2;
	}

	@Override
	public List<Usuario> findByFechaAndNacionalidad(LocalDate fecha, String palabraClave) {
		// TODO Auto-generated method stub

		List<Usuario> usuario = new ArrayList<>();
		List<Usuario> usuario2 = new ArrayList<>();
		usuario = (List<Usuario>) usuarioDao.findAll();
		for (int i = 0; i < usuario.size(); i++) {
			if (usuario.get(i).getFechaDeNacimiento().compareTo(fecha) == 0) {
				if (usuario.get(i).getNacionalidad().equals(palabraClave)) {
					usuario2.add(usuario.get(i));
				}
			}
		}
		return usuario2;
	}

	@Override
	public List<Usuario> findByDNIFechaNacionalidadTipo(Long dni, LocalDate fecha, String palabraClave, String tipo) {
		// TODO Auto-generated method stub
		List<Usuario> usuario = new ArrayList<>();
		List<Usuario> usuario2 = new ArrayList<>();
		usuario = (List<Usuario>) usuarioDao.findAll();
		for (int i = 0; i < usuario.size(); i++) {
			if (usuario.get(i).getDNI() == dni) {
				if (usuario.get(i).getFechaDeNacimiento().compareTo(fecha) == 0) {
					if (usuario.get(i).getNacionalidad().equals(palabraClave)) {
						if (usuario.get(i).getTipoUsuario().equals(tipo)) {
							usuario2.add(usuario.get(i));
						}
					}
				}
			}
		}
		return usuario2;
	}

	@Override
	public List<Usuario> findByDNITipo(Long dni, String tipo) {
		// TODO Auto-generated method stub
		List<Usuario> usuario = new ArrayList<>();
		List<Usuario> usuario2 = new ArrayList<>();
		usuario = (List<Usuario>) usuarioDao.findAll();
		for (int i = 0; i < usuario.size(); i++) {
			if (usuario.get(i).getDNI() == dni) {
				if (usuario.get(i).getTipoUsuario().equals(tipo)) {
					usuario2.add(usuario.get(i));
				}

			}
		}
		return usuario2;
	}

	@Override
	public List<Usuario> findByFechaTipo(LocalDate fecha, String tipo) {
		// TODO Auto-generated method stub
		List<Usuario> usuario = new ArrayList<>();
		List<Usuario> usuario2 = new ArrayList<>();
		usuario = (List<Usuario>) usuarioDao.findAll();
		for (int i = 0; i < usuario.size(); i++) {

			if (usuario.get(i).getFechaDeNacimiento().compareTo(fecha) == 0) {
				if (usuario.get(i).getTipoUsuario().equals(tipo)) {
					usuario2.add(usuario.get(i));
				}
			}
		}
		return usuario2;
	}

	@Override
	public List<Usuario> findByNacionalidadTipo(String palabraClave, String tipo) {
		// TODO Auto-generated method stub
		List<Usuario> usuario = new ArrayList<>();
		List<Usuario> usuario2 = new ArrayList<>();
		usuario = (List<Usuario>) usuarioDao.findAll();
		for (int i = 0; i < usuario.size(); i++) {
			if (usuario.get(i).getNacionalidad().equals(palabraClave)) {
				if (usuario.get(i).getTipoUsuario().equals(tipo)) {
					usuario2.add(usuario.get(i));
				}
			}
		}
		return usuario2;
	}

	@Override
	public List<Usuario> findByTipo(String tipo) {
		// TODO Auto-generated method stub
		List<Usuario> usuario = new ArrayList<>();
		List<Usuario> usuario2 = new ArrayList<>();
		usuario = (List<Usuario>) usuarioDao.findAll();
		for (int i = 0; i < usuario.size(); i++) {
			if (usuario.get(i).getTipoUsuario().equals(tipo)) {
				usuario2.add(usuario.get(i));
			}
		}
		return usuario2;
	}

	@Override
	public List<Usuario> findByFechaNacionalidadTipo(LocalDate fecha, String palabraClave, String tipo) {
		// TODO Auto-generated method stub
		List<Usuario> usuario = new ArrayList<>();
		List<Usuario> usuario2 = new ArrayList<>();
		usuario = (List<Usuario>) usuarioDao.findAll();
		for (int i = 0; i < usuario.size(); i++) {
				if (usuario.get(i).getFechaDeNacimiento().compareTo(fecha) == 0) {
					if (usuario.get(i).getNacionalidad().equals(palabraClave)) {
						if (usuario.get(i).getTipoUsuario().equals(tipo)) {
							usuario2.add(usuario.get(i));
						}
					}
				}
		}
		return usuario2;
	}
}
