package com.litethinking.almacen.negocio.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.litethinking.almacen.general.excepciones.ResourceNotFoundException;
import com.litethinking.almacen.modelo.Producto;
import com.litethinking.almacen.negocio.ProductoDTO;
import com.litethinking.almacen.negocio.ServicioProducto;
import com.litethinking.almacen.repositorio.RepositorioProducto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;



@Service
public class ServicioProductoImpl implements ServicioProducto {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(ServicioProductoImpl.class);
	
	
	private final RepositorioProducto _repositorioProducto;
	private final MapeadorProducto _mapeadorProducto;	
	
	
	public ServicioProductoImpl (final RepositorioProducto _repositorioProducto, final MapeadorProducto _mapeadorProducto) {
		this._repositorioProducto = _repositorioProducto;
		this._mapeadorProducto = _mapeadorProducto;
	}
		
	
	@Override
	public List<Producto> listarTodosLosProductos() {

		List<Producto> listaProductos= _repositorioProducto.findAll();
		if (listaProductos.isEmpty()) {
			throw  new ResourceNotFoundException("No existen productos");
		} 
		return listaProductos;
	}

	@Override
	public Integer crear(@Valid ProductoDTO _productoDTO) {
		LOGGER.info("Creando Entidad Producto - inicio {}", _productoDTO);
		
		final Producto _entidadProducto = _mapeadorProducto._mapeadorAEntidadProducto(_productoDTO);
		Integer id = _repositorioProducto.save(_entidadProducto).getIdproducto();
		return id;
	}

	@Override
	public ProductoDTO buscarPorId(@Valid Integer id) {

		LOGGER.info("Buscando Producto por ID {}", id);
		return _repositorioProducto.findById(id).map(_mapeadorProducto::mapearProductoADTO)
				.orElseThrow(() -> new ResourceNotFoundException("No existe el Producto con el ID: " + id));
	}

	@Override
	public ProductoDTO buscarPorCodigo(@Valid Integer codigo) {
		LOGGER.info("Buscando Producto por CODIGO {}", codigo);
		
		Producto _producto = _repositorioProducto.findByCodigo(codigo);
		ProductoDTO _productoDTO = _mapeadorProducto.mapearProductoADTO(_producto);
		return _productoDTO;
	}
	
	
	@Override
	public void actualizar(@NotNull Integer id, @Valid ProductoDTO _productoDTO) {
		LOGGER.info("Actualizando el Producto - inicio {}", _productoDTO);
		
		Producto _entidadProducto = _repositorioProducto.findById(id)
								.orElseThrow(() -> new ResourceNotFoundException("No existe el Producto con el ID: " + id));
		
		_entidadProducto.setCodigo(_productoDTO.codigo());
		_entidadProducto.setNombreProducto(_productoDTO.nombreProducto());
		_entidadProducto.setCaracteristicas(_productoDTO.caracteristicas());
		_entidadProducto.setNitempresa(_productoDTO.nitempresa());
		_repositorioProducto.save(_entidadProducto);
		
		LOGGER.info("Actualizando Producto - fin ");	
	}

	@Override
	public void eliminar(@NotNull Integer id) {
		
		_repositorioProducto.deleteById(id);
	}
	
	

}
