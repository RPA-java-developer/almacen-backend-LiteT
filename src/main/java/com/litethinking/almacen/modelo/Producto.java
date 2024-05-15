package com.litethinking.almacen.modelo;

import java.util.Optional;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "productos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Producto {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idproducto;	
	
    @Column(name = "codigo", nullable = false, precision = 5, scale = 0, unique = true)
    private Integer codigo;	
	
    @Column(name = "nombreproducto", nullable = false, length = 100)
    private String nombreProducto;
    
    @Column(name = "caracteristicas", nullable = false, length = 200)
    private String caracteristicas;    
    
    @Column(name = "nitempresa", nullable = false, precision = 5, scale = 0)
	private Integer nitempresa;
    
}
