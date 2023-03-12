package ar.edu.unju.edm.service;

import java.time.LocalDate;
import java.util.List;

import ar.edu.unju.edm.models.Usuario;

public interface IUsuarioService {
	
	public List<Usuario> findAll();
	//
	public List<Usuario> findByDni(Long dni);
	public List<Usuario> findByNacionalidad(String nacionalidad);
	public List<Usuario> findByTipoUsuario(String tipoUsuario);
	public List<Usuario> findByFecha(LocalDate fecha);
	
	 public List<Usuario> findByNacionalidadAndDni(String nacionalidad, Long dni);
	public List<Usuario> findByDniAndTipoUsuario(Long dni, String tipoUsuario);
	public List<Usuario> findByDniAndFecha(Long dni, LocalDate fecha);
	
	public List<Usuario> findByFechaAndNacionalidad(LocalDate fecha, String nacionalidad);
	public List<Usuario> findByFechaAndTipoUsuario(LocalDate fecha, String tipoUsuario);

	public List<Usuario> findByNacionalidadAndTipoUsuario(String nacionalidad, String tipoUsuario);
	
	public List<Usuario> findByDniAndNacionalidadAndTipoUsuario(Long dni, String nacionalidad, String tipoUsuario);
	public List<Usuario> findByDniAndFechaAndNacionalidad(Long dni, LocalDate fecha, String nacionalidad);
	public List<Usuario> findByDniAndFechaAndTipoUsuario(Long dni, LocalDate fecha, String tipoUsuario);
	public List<Usuario> findByFechaAndTipoUsuarioAndNacionalidad(LocalDate fecha, String tipoUsuario, String nacionalidad);
	
	public List<Usuario> findByDniAndFechaAndNacionalidadAndTipoUsuario(Long dni, LocalDate fecha, String nacionalidad, String tipoUsuario);	public void save(Usuario usuario);
	public Usuario buscarDni(Long dni);
	public boolean existeDni(Long dni);
	public void eliminar(Long DNI);
	public List<Usuario> findHuesped();

}
