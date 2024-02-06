package br.com.jujubaprojects.restappspringboot.configs;


public class TestConfigs {
    
   // Porta do servidor utilizada nos testes
public static final int SERVER_PORT = 8888;

// Parâmetros de cabeçalho comuns
public static final String HEADER_PARAM_AUTHORIZATION = "Authorization"; // Cabeçalho de autorização
public static final String HEADER_PARAM_ORIGIN = "Origin"; // Cabeçalho de origem

// Tipos de cabeçalho comuns
public static final String HEADER_TYPE_JSON = "application/json"; // Tipo de conteúdo JSON
public static final String HEADER_TYPE_XML = "application/xml"; // Tipo de conteúdo XML
public static final String HEADER_TYPE_YML = "application/x-yaml"; // Tipo de conteúdo YAML

// Origens permitidas para CORS
public static final String ORIGIN_JUJUBAPROJECTS = "https://jujubaprojects.com.br"; // Origem do Jujuba Projects
public static final String ORIGIN_SEMERU = "https://semeru.com.br"; // Origem do Semeru


}

