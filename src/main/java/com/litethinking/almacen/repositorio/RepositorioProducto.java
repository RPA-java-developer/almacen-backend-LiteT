package com.litethinking.almacen.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.litethinking.almacen.modelo.Producto;


public interface RepositorioProducto extends JpaRepository<Producto, Integer> {
	
	Producto findByCodigo(Integer codigo);

}