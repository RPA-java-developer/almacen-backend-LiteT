package com.litethinking.almacen.negocio;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EmpresaDTO (
		
		@NotNull
	    Integer nit,
		
	    @NotBlank
	    @Size(max = 100)
	    String nombre,
	    
	    @NotBlank
	    @Size(max = 100)
	    String direccion,    
	
	    @NotBlank
	    @Size(max = 30)
	    String telefono
	    
	) {}
