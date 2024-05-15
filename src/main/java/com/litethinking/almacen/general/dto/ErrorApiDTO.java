package com.litethinking.almacen.general.dto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Representa un error genérico retornado por un API.
 */
public record ErrorApiDTO (
		String ruta,
		String excepcion,
		String mensaje,
		int codigoEstadoHttp,
		LocalDateTime fechaHoraLocal,
		List<Object> erroresAtributos) {
}
