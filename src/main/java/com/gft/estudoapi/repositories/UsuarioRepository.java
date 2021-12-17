package com.gft.estudoapi.repositories;

import com.gft.estudoapi.entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Page<Usuario> findAll(Pageable pageable);
    Usuario findByEmail(String nome);
    Usuario findByCpf(String cpf);

}
