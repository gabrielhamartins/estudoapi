package com.gft.estudoapi.services;

import com.gft.estudoapi.entities.Usuario;
import com.gft.estudoapi.entities.Veiculo;
import com.gft.estudoapi.exceptions.BadRequestException;
import com.gft.estudoapi.exceptions.FieldAlreadyExistsException;
import com.gft.estudoapi.repositories.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final VeiculoService veiculoService;

    public UsuarioService(UsuarioRepository usuarioRepository, VeiculoService veiculoService) {
        this.usuarioRepository = usuarioRepository;
        this.veiculoService = veiculoService;
    }

    public Usuario salvarUsuario(Usuario usuario){

        if(verificarSeEmailExiste(usuario)){
            throw new FieldAlreadyExistsException("email", "Este email já foi cadastrado.");
        }

        if(verificarSeCpfExiste(usuario)){
            throw new FieldAlreadyExistsException("cpf", "Este cpf já foi cadastrado.");
        }

        return usuarioRepository.save(usuario);

    }

    public Page<Usuario> listarTodosUsuarios(Pageable pageable){

        return usuarioRepository.findAll(pageable);

    }

    public Set<Veiculo> buscarVeiculos(Long id){

        Usuario usuario = buscarUsuario(id);

        return usuario.getVeiculos();

    }

    public Usuario buscarUsuario(Long id) {

        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        return usuarioOptional.orElseThrow(() -> new BadRequestException("Usuario não encontrado."));

    }

    public Usuario atualizarUsuario(Usuario usuario, Long id){

        Usuario usuarioOriginal = this.buscarUsuario(id);

        usuario.setId(id);

        return usuarioRepository.save(usuario);

    }

    public Usuario adicionarVeiculo(Long usuarioId, Long VeiculoId){

        Usuario usuario = this.buscarUsuario(usuarioId);
        Veiculo veiculo = veiculoService.buscarVeiculo(VeiculoId);

        usuario.getVeiculos().add(veiculo);

        return usuarioRepository.save(usuario);

    }

    public void excluirUsuario(Long id){

        Usuario usuarioOriginal = this.buscarUsuario(id);

        usuarioRepository.deleteById(id);

    }

    private boolean verificarSeEmailExiste(Usuario usuario){

        Usuario usuarioOptional = usuarioRepository.findByEmail(usuario.getEmail());

        return (usuarioOptional != null) ? true : false;

    }

    private boolean verificarSeCpfExiste(Usuario usuario){

        Usuario usuarioOptional = usuarioRepository.findByCpf(usuario.getCpf());

        return (usuarioOptional != null) ? true : false;

    }

}
