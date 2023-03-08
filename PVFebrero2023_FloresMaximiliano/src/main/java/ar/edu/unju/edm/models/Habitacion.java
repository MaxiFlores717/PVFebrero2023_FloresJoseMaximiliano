package ar.edu.unju.edm.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Habitacion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@NotNull
	private Long codigo;
	
	@NotEmpty
	private String caracteristicas;
	
	@NotEmpty
	private String tipo;
	
	@NotEmpty
	private String camas;
	
	@NotEmpty
	private String estado;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCamas() {
		return camas;
	}

	public void setCamas(String camas) {
		this.camas = camas;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
		

}
