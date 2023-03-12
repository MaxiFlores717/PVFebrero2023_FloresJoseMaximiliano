package ar.edu.unju.edm.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.edm.models.Habitacion;

@Repository
public interface IHabitacionDao extends CrudRepository<Habitacion, Long>{
	
	public List<Habitacion> findByEstado(String estado);
	
}
