package ar.edu.unju.edm.service;

import java.util.List;

import ar.edu.unju.edm.models.Habitacion;
import ar.edu.unju.edm.models.Usuario;

public interface IHabitacionService {
	
	public List<Habitacion> findAll();
	public void save(Habitacion habitacion);
	public Habitacion buscarCodigo(Long codigo) throws Exception;
	public void eliminar(Long codigo);
	public List<Habitacion> findlibres();
	public boolean existeCodigo(Long codigo);
	

}
