package ar.edu.unju.edm.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.edm.models.Usuario;

@Repository
public interface IUsuarioDao extends CrudRepository<Usuario, Long>{
	
	List<Usuario> findByDni(Long dni);
	List<Usuario> findByNacionalidad(String nacionalidad);
	List<Usuario> findByTipoUsuario(String tipoUsuario);
	 List<Usuario> findByFecha(LocalDate fecha);
	
	List<Usuario> findByNacionalidadAndDni(String nacionalidad, Long dni);
	List<Usuario> findByDniAndTipoUsuario(Long dni, String tipoUsuario);
	List<Usuario> findByDniAndFecha(Long dni, LocalDate fecha);//PROBAR
	
	List<Usuario> findByFechaAndNacionalidad(LocalDate fecha, String nacionalidad);//PROBAR
	List<Usuario> findByFechaAndTipoUsuario(LocalDate fecha, String tipoUsuario);//Probar

	List<Usuario> findByNacionalidadAndTipoUsuario(String nacionalidad, String tipoUsuario);
	
	List<Usuario> findByDniAndNacionalidadAndTipoUsuario(Long dni, String nacionalidad, String tipoUsuario);
	List<Usuario> findByDniAndFechaAndNacionalidad(Long dni, LocalDate fecha, String nacionalidad);
	List<Usuario> findByDniAndFechaAndTipoUsuario(Long dni, LocalDate fecha, String tipoUsuario);
	List<Usuario> findByFechaAndTipoUsuarioAndNacionalidad(LocalDate fecha, String tipoUsuario, String nacionalidad);
	
	List<Usuario> findByDniAndFechaAndNacionalidadAndTipoUsuario(Long dni, LocalDate fecha, String nacionalidad, String tipoUsuario);
	

}
