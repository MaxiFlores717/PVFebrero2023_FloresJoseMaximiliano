package ar.edu.unju.edm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unju.edm.dao.IHabitacionDao;
import ar.edu.unju.edm.models.Habitacion;

@Service
public class HabitacionServiceIMP implements IHabitacionService {
	
	@Autowired
	private IHabitacionDao habitacionDao;

	@Override
	@Transactional(readOnly = true)
	public List<Habitacion> findAll() {
		// TODO Auto-generated method stub
		return (List<Habitacion>) habitacionDao.findAll();
	}

	@Override
	public void save(Habitacion habitacion) {
		// TODO Auto-generated method stub
		habitacionDao.save(habitacion);
	}

	@Override
	public Habitacion buscarCodigo(Long codigo) {
		// TODO Auto-generated method stub
		
		return habitacionDao.findById(codigo).orElse(null);
	}

	@Override
	public void eliminar(Long codigo) {
		// TODO Auto-generated method stub
		habitacionDao.deleteById(codigo);
	}

	@Override
	public List<Habitacion> findlibres() {
		// TODO Auto-generated method stub
		List<Habitacion> habitacion = new ArrayList<>();
		List<Habitacion> habitacion2 = new ArrayList<>();
		habitacion = (List<Habitacion>) habitacionDao.findAll();
		
		for(int i=0; i<habitacion.size(); i++) {
			if(habitacion.get(i).getEstado().equals("Libre")) {
				habitacion2.add(habitacion.get(i));
			}
		}
		return habitacion2;
	}
	

}
