package com.litethinking.almacen.negocio.impl;

import org.springframework.stereotype.Service;

import com.litethinking.almacen.modelo.Producto;
import com.litethinking.almacen.negocio.ProductoDTO;

import jakarta.validation.constraints.NotNull;

@Service
public class MapeadorProducto {
	
	
    /**
     * Convierte de un record DTO a una entidad JPA.
     * 
     * @param ProductoDTO
     * @return Una entidad Producto JPA.
     */	
	public Producto _mapeadorAEntidadProducto(final @NotNull ProductoDTO _productoDTO) {
		final Producto _entidadProducto = new Producto();
		
		_entidadProducto.setCodigo(_productoDTO.codigo());
		_entidadProducto.setNombreProducto(_productoDTO.nombreProducto());
		_entidadProducto.setCaracteristicas(_productoDTO.caracteristicas());
		_entidadProducto.setNitempresa(_productoDTO.nitempresa());
		
		return _entidadProducto;
	} 
	
	
    /**
     * Convierte de entidad JPA a DTO.
     * 
     * @param Producto Entidad JPA
     * @return Un DTO de Producto
     */
	public ProductoDTO mapearProductoADTO(final @NotNull Producto _entidadProductoDTO) {
		
    	return new ProductoDTO(  
    			_entidadProductoDTO.getCodigo(),
    			_entidadProductoDTO.getNombreProducto(),
    			_entidadProductoDTO.getCaracteristicas(),
    			_entidadProductoDTO.getNitempresa()		
    		);
	}
		

}
