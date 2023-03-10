package ar.edu.unju.edm.models;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@NotNull
	private Long DNI;
	
	@NotEmpty
	private String nombre;
	
	@NotEmpty
	private String apellido;
	
	@Column(name = "fecha_nacimiento")
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate FechaDeNacimiento;
	
	@NotEmpty
	private String password;
	
	@NotEmpty
	private String nacionalidad;
	
	@Column(name = "tipo_usuario")
	@NotEmpty
	private String tipoUsuario;

	public Long getDNI() {
		return DNI;
	}

	public void setDNI(Long dNI) {
		DNI = dNI;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public LocalDate getFechaDeNacimiento() {
		return FechaDeNacimiento;
	}

	public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) {
		FechaDeNacimiento = fechaDeNacimiento;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	

	public Usuario(@NotNull Long dNI, @NotEmpty String nombre, @NotEmpty String apellido,
			@NotNull LocalDate fechaDeNacimiento, @NotEmpty String password, @NotEmpty String nacionalidad,
			@NotEmpty String tipoUsuario) {
		super();
		DNI = dNI;
		this.nombre = nombre;
		this.apellido = apellido;
		FechaDeNacimiento = fechaDeNacimiento;
		this.password = password;
		this.nacionalidad = nacionalidad;
		this.tipoUsuario = tipoUsuario;
	}

	public Usuario() {
		super();
	}

	@Override
	public String toString() {
		return nombre;
	}
	
	
	

}
