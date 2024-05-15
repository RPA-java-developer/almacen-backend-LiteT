package com.litethinking.almacen.negocio;

import java.util.List;

import com.litethinking.almacen.modelo.Empresa;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface ServicioEmpresa {

	
	
		 /**
	     * Lista todas las Empresas.
	     * 
	     * @return Un listado de todas las Empresas.
	     */
		List<Empresa> listarTodasLasEmpresas();	
		
	
		 /**
	     * Crea una Empresa nuevo.
	     * 
	     * @param EmpresaDTO 
	     * @return Un Entero del recurso (Empresa) creada.
	     */	
		Integer crear(@Valid EmpresaDTO _empresaDTO);

		
		
		 /**
	     * Busca una Empresa por ID (idempresa).
	     * 
	     * @param id (idempresa) de la Empresa
	     * @return La Empresa (DTO) encontrada.
	     */
		EmpresaDTO buscarPorId(@Valid Integer id);
		
		
		 /**
	     * Busca una Empresa por NIT (nit).
	     * 
	     * @param nit  de la Empresa
	     * @return La Empresa (DTO) encontrada.
	     */
		Empresa buscarPorNit(@Valid Integer nit);		
		
		
	    /**
	     * Actualiza una Empresa.
	     * 
	     * @param ID (idempresa) de la Empresa a actualizar.
	     * @param EmpresaDTO a actualizar.
	     */
	    void actualizar(@NotNull Integer id, @Valid EmpresaDTO _empresaDTO);
	    
	    
	    /**
	     * Eliminar una Empresa.
	     * 
	     * @param ID (idempresa) de la Empresa a eliminar.
	     */
	    void eliminar(@NotNull Integer id);   	    
	    
	
}
