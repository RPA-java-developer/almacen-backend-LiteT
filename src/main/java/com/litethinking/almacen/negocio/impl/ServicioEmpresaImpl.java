package com.litethinking.almacen.negocio.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.litethinking.almacen.general.excepciones.ResourceNotFoundException;
import com.litethinking.almacen.modelo.Empresa;
import com.litethinking.almacen.negocio.EmpresaDTO;
import com.litethinking.almacen.negocio.ServicioEmpresa;
import com.litethinking.almacen.repositorio.RepositorioEmpresa;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;


@Service
public class ServicioEmpresaImpl implements ServicioEmpresa {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(ServicioEmpresaImpl.class);
	
	
	private final RepositorioEmpresa _repositorioEmpresa;
	private final MapeadorEmpresa _mapeadorEmpresa;
	
	public ServicioEmpresaImpl (final RepositorioEmpresa _repositorioEmpresa, final MapeadorEmpresa _mapeadorEmpresa) {
		this._repositorioEmpresa = _repositorioEmpresa;
		this._mapeadorEmpresa = _mapeadorEmpresa;
	}
	
	
	@Override
	public List<Empresa> listarTodasLasEmpresas() {

		return _repositorioEmpresa.findAll();
	}
	
	
	@Override
	public EmpresaDTO buscarPorId(@Valid Integer id) {

			LOGGER.info("Buscando Empresa por ID {}", id);
			return _repositorioEmpresa.findById(id).map(_mapeadorEmpresa::mapearEmpresaADTO)
					.orElseThrow(() -> new ResourceNotFoundException("No existe la empresa con el ID: " + id));
	}
	
	
	@Override
	public Empresa buscarPorNit(@Valid Integer nit) {
		LOGGER.info("Buscando Empresa por NIT {}", nit);
		
		Empresa _empresa = _repositorioEmpresa.findByNit(nit);
		//EmpresaDTO _empresaDTO = _mapeadorEmpresa.mapearEmpresaADTO(nit);
		return _empresa;
	}
		
	
	@Override
	public Integer crear(@Valid EmpresaDTO _empresaDTO) {
		LOGGER.info("Creando Entidad Empresa - inicio {}", _empresaDTO);
		
		final Empresa _entidadEmpresa = _mapeadorEmpresa._mapeadorAEntidadEmpresa(_empresaDTO);
		
		Integer id = _repositorioEmpresa.save(_entidadEmpresa).getIdempresa();
		return id;
	}
	
	   
	
	@Override
	public void actualizar(@NotNull Integer id, @Valid EmpresaDTO _empresaDTO) {
		LOGGER.info("Actualizando la Empresa - inicio {}", _empresaDTO);
		
		Empresa _entidadEmpresa = _repositorioEmpresa.findById(id)
								.orElseThrow(() -> new ResourceNotFoundException("No existe la empresa con el ID: " + id));
		
		_entidadEmpresa.setDireccion(_empresaDTO.direccion());
		_entidadEmpresa.setNombre(_empresaDTO.nombre());
		_entidadEmpresa.setTelefono(_empresaDTO.telefono());

		_repositorioEmpresa.save(_entidadEmpresa);
		
		LOGGER.info("Actualizando Empresa - fin ");		
	}




	@Override
	public void eliminar(@NotNull Integer id) {

		_repositorioEmpresa.deleteById(id);
	}



	

}
