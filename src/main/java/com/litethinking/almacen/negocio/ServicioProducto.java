package com.litethinking.almacen.negocio;

import java.util.List;

import com.litethinking.almacen.modelo.Producto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface ServicioProducto {
	
	
	
	 /**
     * Lista todos los Productos.
     * 
     * @return Un listado de todos los Productos.
     */
	List<Producto> listarTodosLosProductos();

	
	
	 /**
     * Busca un Producto por ID (idproducto).
     * 
     * @param id (idproducto) del Producto
     * @return El producto (DTO) encontrado
     */
	ProductoDTO buscarPorId(@Valid Integer id);
	
	
	 /**
     * Busca un Producto por CODIGO (codigo).
     * 
     * @param codigo (codigo) del Producto
     * @return El producto (DTO) encontrado
     */
	ProductoDTO buscarPorCodigo(@Valid Integer codigo);
		
	
	 /**
     * Crea un Producto nuevo.
     * 
     * @param ProductoDTO 
     * @return Un Entero del recurso (Producto) creado.
     */
	Integer crear(@Valid ProductoDTO _productoDTO);	
	
	
	
    /**
     * Actualiza un Producto.
     * 
     * @param ID (idproducto) del Producto a actualizar.
     * @param ProductoDTO a actualizar.
     */
    void actualizar(@NotNull Integer id, @Valid ProductoDTO _productoDTO);
    
    
    /**
     * Eliminar un Producto.
     * 
     * @param ID (idproducto) del Producto a eliminar.
     */
    void eliminar(@NotNull Integer id);    
    
    	
	
	

}
