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
	public List<Usuario> findByDNIFechaNacionalidadTipo(Long dni, LocalDate fecha, String palabraClave, String tipo);
	public List<Usuario> findByDNITipo(Long dni, String tipo);
	public List<Usuario> findByFechaTipo(LocalDate fecha, String tipo);
	public List<Usuario> findByDNIandNacionalidad(Long dni, String palabraClave);
	public List<Usuario> findByFechaAndNacionalidad(LocalDate fecha, String palabraClave);
	public List<Usuario> findByFechaNacionalidadTipo(LocalDate fecha, String palabraClave, String tipo);
	public List<Usuario> findByNacionalidadTipo(String palabraClave, String tipo);
	public List<Usuario> findByTipo(String tipo);
	
	public void save(Usuario usuario);
	public Usuario buscarDni(Long dni);
	public void eliminar(Long DNI);
	public List<Usuario> findHuesped();

}
