package br.com.jujubaprojects.restappspringboot.mapper;
/*package br.com.jujubaprojects.restappspringboot.mapper;

import java.util.ArrayList;
import java.util.List;

//import com.github.dozermapper.core.DozerBeanMapperBuilder;
//import com.github.dozermapper.core.Mapper;

public class DozerMapper {
  //  private static final Mapper MAPPER = DozerBeanMapperBuilder.buildDefault();
    private static  ModelMapper MAPPER = new ModelMapper();

    private DozerMapper() {
        // Garante que a classe não seja instanciada
    }

    public static <O, D> D mapObject(O origin, Class<D> destination) {
        try {
            return MAPPER.map(origin, destination);
        } catch (Exception e) {
            // Trate a exceção de mapeamento aqui, se necessário
            throw new RuntimeException("Erro ao mapear objeto", e);
        }
    }

    public static <O, D> List<D> mapListObjects(List<O> originList, Class<D> destination) {
        List<D> destinationList = new ArrayList<>();
        for (O origin : originList) {
            destinationList.add(mapObject(origin, destination));
        }
        return destinationList;
    }
}*/
