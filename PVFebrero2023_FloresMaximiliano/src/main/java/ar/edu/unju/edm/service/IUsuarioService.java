package ar.edu.unju.edm.service;

import java.time.LocalDate;
import java.util.List;

import ar.edu.unju.edm.models.Usuario;

public interface IUsuarioService {
	
	public List<Usuario> findAll();
	public List<Usuario> findByFechaNacimiento(LocalDate date);
	public List<Usuario> findByNacionalidad(String palabraClave);
	public List<Usuario> findByDNI(Long dni);
	public List<Usuario> findByDNIandFecha(Long dni, LocalDate fecha);
	public List<Usuario> findByDNIandFechaandNacionalidad(Long dni, LocalDate fecha, String palabraClave);
	public List<Usuario> findByDNIandNacionalidad(Long dni, String palabraClave);
	public List<Usuario> findByFechaAndNacionalidad(LocalDate fecha, String palabraClave);
	
	public void save(Usuario usuario);
	public Usuario buscarDni(Long dni);
	public void eliminar(Long DNI);
	public List<Usuario> findHuesped();
	

}
