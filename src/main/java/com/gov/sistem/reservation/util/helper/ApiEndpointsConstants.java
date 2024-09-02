package com.gov.sistem.reservation.util.helper;

public class ApiEndpointsConstants {

    private ApiEndpointsConstants(){}

    public static final String BASE_INPUT = "/input";
    public static final String BASE_OUTPUT = "/output";
    public static final String BASE_SERVICIO = "/servicio";
    public static final String BASE_AFILIADO = "/afiliado";

    public static final String INPUT_CREAR = "/crear";
    public static final String INPUT_MODIFICAR = "/modificar";
    public static final String INPUT_CANCELAR = "/cancelar/{codigoReserva}";

    public static final String OUTPUT_CONSULTA_RESERVAS_FILTRO = "/consultarReservasFiltro";

    public static final String SERVICIO_CONSULTA_TIPO_SERVICIO = "/consultaTipoServicio/{tipoServicio}";
    public static final String SERVICIO_CONSULTA_CODIGO_RESERVA = "/consultaCodigoReserva/{codigoReserva}";
    public static final String SERVICIO_CONSULTA_FILTROS = "/consultaFiltros";


}
