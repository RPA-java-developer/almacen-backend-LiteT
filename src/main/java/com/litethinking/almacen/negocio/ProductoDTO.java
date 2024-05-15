package com.litethinking.almacen.negocio;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProductoDTO(
	
		@NotNull
	    Integer codigo,
	    
	    @NotBlank
	    @Size(max = 100)
	    String nombreProducto,    
	    
	    @NotBlank
	    @Size(max = 200)
	    String caracteristicas,        
	    
		@NotNull
	    Integer nitempresa
    
    ) {}
