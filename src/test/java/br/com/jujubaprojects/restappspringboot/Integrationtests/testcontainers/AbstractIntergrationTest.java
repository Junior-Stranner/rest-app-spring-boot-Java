package br.com.jujubaprojects.restappspringboot.Integrationtests.testcontainers;

import java.util.Map;
import java.util.stream.Stream;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.lifecycle.Startables;

@ContextConfiguration(initializers = AbstractIntergrationTest.Initializer.class)
public class AbstractIntergrationTest {
    
// Inicializador para configurar e iniciar contêineres do Testcontainers
public class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    
    // Contêiner PostgreSQL para o banco de dados
    static PostgreSQLContainer<?> postgresql = new PostgreSQLContainer<>("postgresql:15.5");

    // Método para iniciar os contêineres
    private static void startContainers() {
        Startables.deepStart(Stream.of(postgresql)).join();
    }

    // Método para criar a configuração de conexão com o banco de dados
    private Map<String, String> createConnectionConfiguration() {
        return Map.of(
            "spring.datasource.url", postgresql.getJdbcUrl(),
            "spring.datasource.username", postgresql.getUsername(),
            "spring.datasource.password", postgresql.getPassword()
        );
    }

    // Método para inicializar o contexto da aplicação
    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        // Inicia os contêineres
        startContainers();
        // Obtém o ambiente configurável da aplicação
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        // Cria uma fonte de propriedades a partir da configuração de conexão com o banco de dados
        MapPropertySource testcontainers = new MapPropertySource(
            "testcontainers",
            (Map) createConnectionConfiguration());
        // Adiciona a fonte de propriedades ao ambiente
        environment.getPropertySources().addFirst(testcontainers);
    }
  }
}
