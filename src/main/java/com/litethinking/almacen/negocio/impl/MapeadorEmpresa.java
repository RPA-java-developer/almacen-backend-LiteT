package com.litethinking.almacen.negocio.impl;

import org.springframework.stereotype.Service;

import com.litethinking.almacen.modelo.Empresa;
import com.litethinking.almacen.negocio.EmpresaDTO;

import jakarta.validation.constraints.NotNull;

@Service
public class MapeadorEmpresa {
	
    /**
     * Convierte de un record DTO a Entidad JPA.
     * 
     * @param EmpresaDTO
     * @return Una Entidad JPA.
     */
	public Empresa _mapeadorAEntidadEmpresa(final @NotNull EmpresaDTO _empresaDTO) {
		
		final Empresa _entidadEmpresa = new Empresa();
		
		_entidadEmpresa.setNit(_empresaDTO.nit());
		_entidadEmpresa.setNombre(_empresaDTO.nombre());
		_entidadEmpresa.setDireccion(_empresaDTO.direccion());
		_entidadEmpresa.setTelefono(_empresaDTO.telefono());
		
		return _entidadEmpresa;
	} 
	
	
    /**
     * Convierte de entidad JPA a DTO.
     * 
     * @param Empresa Entidad JPA
     * @return Un DTO
     */
	public EmpresaDTO mapearEmpresaADTO(final @NotNull Empresa _entidadEmpresa) {
		
    	return new EmpresaDTO(    			
       			_entidadEmpresa.getNit(),
       			_entidadEmpresa.getNombre(),
    			_entidadEmpresa.getDireccion(),    			
    			_entidadEmpresa.getTelefono()		
    		);
	}
	
	

}
