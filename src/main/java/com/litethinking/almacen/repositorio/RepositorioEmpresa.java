package com.litethinking.almacen.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.litethinking.almacen.modelo.Empresa;


public interface RepositorioEmpresa extends JpaRepository<Empresa, Integer> {

	Empresa findByNit(Integer nit);
}
