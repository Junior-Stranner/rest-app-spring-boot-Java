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


    	public class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
         static PostgreSQLContainer<?> postgresql = new PostgreSQLContainer<>("postgresql: 15.5");

         private static void startContainers(){
            Startables.deepStart(Stream.of(postgresql)).join();
         }

         
         private Map createConnectionConfiguration() {
            return Map.of(
				"spring.datasource.url", postgresql.getJdbcUrl(),
				"spring.datasource.username", postgresql.getUsername(),
				"spring.datasource.password", postgresql.getPassword()
			);
        
         }

            @SuppressWarnings({"unchecked", "rawtypes"})
            @Override
            public void initialize(ConfigurableApplicationContext applicationContext) {
              startContainers();
              ConfigurableEnvironment environment = applicationContext.getEnvironment();
              MapPropertySource testcontainers = new MapPropertySource(
                "testcontainers", 
                (Map) createConnectionConfiguration());
                environment.getPropertySources().addFirst(testcontainers);
            }

        }
}
