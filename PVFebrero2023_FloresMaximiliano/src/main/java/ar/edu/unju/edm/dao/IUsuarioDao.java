package ar.edu.unju.edm.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.edm.models.Usuario;

@Repository
public interface IUsuarioDao extends CrudRepository<Usuario, Long>{
	
	
}
