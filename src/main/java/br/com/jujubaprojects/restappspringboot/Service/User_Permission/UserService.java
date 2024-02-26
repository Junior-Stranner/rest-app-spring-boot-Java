/*package br.com.jujubaprojects.restappspringboot.Service.User_Permission;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.jujubaprojects.restappspringboot.Model.User_Permission.User;
import br.com.jujubaprojects.restappspringboot.Repositories.UserRepository;

@Service
public class UserService implements UserDetailsService{

    private Logger logger = Logger.getLogger(UserService.class.getName());
    
    @Autowired
    UserRepository userRepository;

    /* Quando injetado através do construtor, significa que esse atributo é required
     * ou seja , seu eu não setar essa car durante a inicialização da aplicação 
     * , vai ocorrer erro .
     * Já quando eu coloco através  de atributos , vai subir a aplicação depois, quando precisar
     * é  isso vai ser injetado
     * 
     * o risco que pode occorer é quando acontece um nullpointException
     * quando agente chamar ele e não existir a instancia dele , e esse é o risco 
     * que ocorre
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        logger.info("Finding one user by name "+username+" !");
        User user = userRepository.findByUserName(username);

        if(user != null){
            return user;
        }else{
            throw new UsernameNotFoundException("Username "+username+ "not found !");
        }
    }

    public List<User> findAll(){

        List<User> entities = userRepository.findAll();

        return entities;
    }
}*/
