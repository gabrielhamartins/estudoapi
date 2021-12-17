package com.gft.estudoapi.services;


import com.gft.estudoapi.entities.User;
import com.gft.estudoapi.entities.Usuario;
import com.gft.estudoapi.repositories.UserRepository;
import com.gft.estudoapi.repositories.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository usuarioRepository;

    public UserService(UserRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public User buscarUsuarioPorEmail(String email){

        Optional<User> optional = usuarioRepository.findByEmail(email);

        if(optional.isPresent()){

            return optional.get();

        }else{

            throw new UsernameNotFoundException("Usuário não encontrado.");

        }

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return buscarUsuarioPorEmail(username);

    }

    public User buscarUsuarioPorId(Long id) {

        Optional<User> usuario = usuarioRepository.findById(id);

        if(usuario.isPresent()){
            return usuario.get();
        }

        throw new RuntimeException("Usuário não encontrado.");

    }

    public User salvarUsuario(User usuario){

        return usuarioRepository.save(usuario);

    }
}

