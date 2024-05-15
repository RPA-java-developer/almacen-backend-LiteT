package com.litethinking.almacen.controlador;

import static com.litethinking.almacen.general.enumeracion.CodigosEstadoHttp.ESTADO_HTTP_201;
import static com.litethinking.almacen.general.enumeracion.CodigosEstadoHttp.ESTADO_HTTP_200;
import static com.litethinking.almacen.general.enumeracion.CodigosEstadoHttp.ESTADO_HTTP_404;
import static com.litethinking.almacen.general.enumeracion.MensajesGeneralesControladoresRest.RECURSO_CREADO;
import static com.litethinking.almacen.general.enumeracion.MensajesGeneralesControladoresRest.RECURSO_ACTUALIZADO;
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
import com.litethinking.almacen.modelo.Empresa;
import com.litethinking.almacen.negocio.EmpresaDTO;
import com.litethinking.almacen.negocio.ServicioEmpresa;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;



@Tag(name = "A L M A C E N --> Empresa", description = "API REST para la gestión de una Empresa")
@RestController
@RequestMapping(value = "/api/v1/", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:3000")
public class ControladorEmpresa {

	private static final Logger LOGGER = LoggerFactory.getLogger(ControladorEmpresa.class);
	
	private final ServicioEmpresa _servicioEmpresa;

	
	public ControladorEmpresa(final ServicioEmpresa _servicioEmpresa) {
		this._servicioEmpresa = _servicioEmpresa;
	}
	
	
	
	@Operation(summary = "Lista todas las Empresas")
	@ApiResponses(value = {@ApiResponse(responseCode = ESTADO_HTTP_200, description = RECURSO_ENCONTRADO)})
	@GetMapping("/empresa")
	public List<Empresa> listarTodasLasEmpresas() {
		return  _servicioEmpresa.listarTodasLasEmpresas();
	}
	
	
    @Operation(summary = "Busca una Empresa por ID")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = ESTADO_HTTP_200, description = RECURSO_ENCONTRADO),
    		@ApiResponse(responseCode = ESTADO_HTTP_404, description = RECURSO_NO_ENCONTRADO, content = {
					@Content(mediaType = APPLICATION_JSON_VALUE, 
							schema = @Schema(implementation = ErrorApiDTO.class))
					})
    		})
    @GetMapping("/empresa/{id}")
    public ResponseEntity<EmpresaDTO> buscarEmpresaPorId(@PathVariable(name = "id") final Integer id) {
        return ResponseEntity.ok(_servicioEmpresa.buscarPorId(id));
    }
    
	
    @Operation(summary = "Busca una Empresa por NIT")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = ESTADO_HTTP_200, description = RECURSO_ENCONTRADO),
    		@ApiResponse(responseCode = ESTADO_HTTP_404, description = RECURSO_NO_ENCONTRADO, content = {
					@Content(mediaType = APPLICATION_JSON_VALUE, 
							schema = @Schema(implementation = ErrorApiDTO.class))
					})
    		})
    @GetMapping("/empresa/nit/{nit}")
    public ResponseEntity<Empresa> buscarEmpresaPorNit(@PathVariable(name = "nit") final Integer nit) {
        return ResponseEntity.ok(_servicioEmpresa.buscarPorNit(nit));
    }
        
    
	
	@Operation(summary = "Crea una Empresa")
	@ApiResponses(value = {@ApiResponse(responseCode = ESTADO_HTTP_201, description = RECURSO_CREADO)})
	@PostMapping("/empresa")	
    public ResponseEntity<IdEnteroRecursoDTO> crearEmpresa(@RequestBody @Valid EmpresaDTO _empresaDTO) {
		
		LOGGER.info("C O N T R O L A D O R -----------------------------------------------------");
		LOGGER.info(_empresaDTO.toString());
    	
        final Integer idCreado = _servicioEmpresa.crear(_empresaDTO);        
        return new ResponseEntity<>(new IdEnteroRecursoDTO(idCreado), HttpStatus.CREATED);
    }	
	
	
    @Operation(summary = "Actualiza una Empresa")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = ESTADO_HTTP_200, description = RECURSO_ACTUALIZADO),
    		@ApiResponse(responseCode = ESTADO_HTTP_404, description = RECURSO_NO_ENCONTRADO, content = {
						@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorApiDTO.class))
					})
    		})        
    @PutMapping("/empresa/{id}")
    public ResponseEntity<IdEnteroRecursoDTO> actualizarEmpresa(@PathVariable(name = "id") final Integer id, @RequestBody @Valid EmpresaDTO _empresaDTO) {
    	   	
    	_servicioEmpresa.actualizar(id, _empresaDTO);
        return ResponseEntity.ok(new IdEnteroRecursoDTO(id));
    }    
    
    
    @Operation(summary = "Elimina una Empresa por ID")
    @ApiResponses(value = {@ApiResponse(responseCode = ESTADO_HTTP_200, description = "RECURSO_ELIMINADO")})
	@DeleteMapping("/empresa/{id}")
	public ResponseEntity<Map<String,Boolean>> eliminarEmpresa(@PathVariable Integer id){
		_servicioEmpresa.eliminar(id);
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminar",Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
    }	    
    
	
	
}
