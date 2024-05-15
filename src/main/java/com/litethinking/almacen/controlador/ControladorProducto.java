package com.litethinking.almacen.controlador;

import static com.litethinking.almacen.general.enumeracion.CodigosEstadoHttp.ESTADO_HTTP_200;
import static com.litethinking.almacen.general.enumeracion.CodigosEstadoHttp.ESTADO_HTTP_201;
import static com.litethinking.almacen.general.enumeracion.CodigosEstadoHttp.ESTADO_HTTP_404;
import static com.litethinking.almacen.general.enumeracion.MensajesGeneralesControladoresRest.RECURSO_ACTUALIZADO;
import static com.litethinking.almacen.general.enumeracion.MensajesGeneralesControladoresRest.RECURSO_CREADO;
import static com.litethinking.almacen.general.enumeracion.MensajesGeneralesControladoresRest.RECURSO_ENCONTRADO;
import static com.litethinking.almacen.general.enumeracion.MensajesGeneralesControladoresRest.RECURSO_NO_ENCONTRADO;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.litethinking.almacen.general.dto.ErrorApiDTO;
import com.litethinking.almacen.general.dto.IdEnteroRecursoDTO;
import com.litethinking.almacen.general.excepciones.ResourceNotFoundException;
import com.litethinking.almacen.modelo.Empresa;
import com.litethinking.almacen.modelo.Producto;
import com.litethinking.almacen.negocio.ProductoDTO;
import com.litethinking.almacen.negocio.ServicioProducto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "A L M A C E N --> Producto", description = "API REST para la gestión de un Producto")
@RestController
@RequestMapping(value = "/api/v1/", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:3000")
public class ControladorProducto {

	private static final Logger LOGGER = LoggerFactory.getLogger(ControladorProducto.class);
	
	private final ServicioProducto _servicioProducto;

	
	public ControladorProducto(final ServicioProducto _servicioProducto) {
		this._servicioProducto = _servicioProducto;
	}	
	
	
	@Operation(summary = "Lista todos los Productos")
	@ApiResponses(value = {@ApiResponse(responseCode = ESTADO_HTTP_200, description = RECURSO_ENCONTRADO)})
	@GetMapping("/producto")
	public List<Producto> listarTodosLosProductos() {
		
		List<Producto> control = _servicioProducto.listarTodosLosProductos();
		System.out.println(" ===============================================");
		System.out.println(control.get(0).getIdproducto());
		System.out.println(control.get(0).getNombreProducto());
		System.out.println(control.get(0).getCaracteristicas());
				
		return  control;
		
	}
	
    @Operation(summary = "Busca un Producto por ID")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = ESTADO_HTTP_200, description = RECURSO_ENCONTRADO),
    		@ApiResponse(responseCode = ESTADO_HTTP_404, description = RECURSO_NO_ENCONTRADO, content = {
					@Content(mediaType = APPLICATION_JSON_VALUE, 
							schema = @Schema(implementation = ErrorApiDTO.class))
					})
    		})
    @GetMapping("/producto/{id}")
    public ResponseEntity<ProductoDTO> buscarProductoPorId(@PathVariable(name = "id") final Integer id) {
        return ResponseEntity.ok(_servicioProducto.buscarPorId(id));
    }
    	
    @Operation(summary = "Busca un Producto por CODIGO")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = ESTADO_HTTP_200, description = RECURSO_ENCONTRADO),
    		@ApiResponse(responseCode = ESTADO_HTTP_404, description = RECURSO_NO_ENCONTRADO, content = {
					@Content(mediaType = APPLICATION_JSON_VALUE, 
							schema = @Schema(implementation = ErrorApiDTO.class))
					})
    		})
    @GetMapping("/producto/codigo/{codigo}")
    public ResponseEntity<ProductoDTO> buscarProductoPorCodigo(@PathVariable(name = "codigo") final Integer codigo) {
        return ResponseEntity.ok(_servicioProducto.buscarPorCodigo(codigo));
    }    
	
    
	@Operation(summary = "Crea un Producto")
	@ApiResponses(value = {@ApiResponse(responseCode = ESTADO_HTTP_201, description = RECURSO_CREADO)})
	@PostMapping("/producto")	
    public ResponseEntity<IdEnteroRecursoDTO> crearProducto(@RequestBody @Valid ProductoDTO _productoDTO) {
		
		LOGGER.info("C O N T R O L A D O R Producto -----------------------------------------------------");
		LOGGER.info(_productoDTO.toString());    	
		
		boolean empresaValida = validarExistenciaEmpresa(_productoDTO.nitempresa());
		System.out.println("Empresa: " + empresaValida);
				
		if (!empresaValida) {
			throw new ResourceNotFoundException("No existe la Empresa con el NIT: " + _productoDTO.nitempresa());
		}
		
        final Integer idCreado = _servicioProducto.crear(_productoDTO);        
        return new ResponseEntity<>(new IdEnteroRecursoDTO(idCreado), HttpStatus.CREATED);
    }	
	
	
    @Operation(summary = "Actualiza un Producto")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = ESTADO_HTTP_200, description = RECURSO_ACTUALIZADO),
    		@ApiResponse(responseCode = ESTADO_HTTP_404, description = RECURSO_NO_ENCONTRADO, content = {
						@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorApiDTO.class))
					})
    		})        
    @PutMapping("/producto/{id}")
    public ResponseEntity<IdEnteroRecursoDTO> actualizarProducto(@PathVariable(name = "id") final Integer id, @RequestBody @Valid ProductoDTO _productoDTO) {
    	   	
    	_servicioProducto.actualizar(id, _productoDTO);
        return ResponseEntity.ok(new IdEnteroRecursoDTO(id));
    }   
    
	
    @Operation(summary = "Elimina un Producto")
    @ApiResponses(value = {@ApiResponse(responseCode = ESTADO_HTTP_200, description = "RECURSO_ELIMINADO")})
	@DeleteMapping("/producto/{id}")
	public ResponseEntity<Map<String,Boolean>> eliminarProducto(@PathVariable Integer id){
    	_servicioProducto.eliminar(id);
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminar",Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
    }	
	
    
    private boolean validarExistenciaEmpresa(Integer nit) {
    	
    	boolean valido = false;
    	final String apiUrl = "http://localhost:9191/api/v1/empresa";
    	RestTemplate restTemplate = new RestTemplate();
    	String url = apiUrl + "/nit/" + nit;
    	Empresa respusetaEmpresa = restTemplate.getForObject(url, Empresa.class);

    	if (respusetaEmpresa != null) {
    		System.out.println("Entra por if");
    		valido = true;
    	} else {
    		System.out.println("Entra por else");
    		valido = false;
    	}
    	System.out.println("-----------------------------------------------");
    	System.out.println(respusetaEmpresa);
   	
    	return valido;
    }
    
    
    
}
