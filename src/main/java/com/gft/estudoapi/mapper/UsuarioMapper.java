package com.gft.estudoapi.mapper;

import com.gft.estudoapi.dto.usuario.ConsultaUsuarioDTO;
import com.gft.estudoapi.dto.usuario.RegistroUsuarioDTO;
import com.gft.estudoapi.entities.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = Collectors.class)
public abstract class UsuarioMapper {

    @Autowired
    protected VeiculoMapper veiculoMapper;

    public abstract Usuario dtoParaEntidade(RegistroUsuarioDTO registroUsuarioDTO);

    @Mapping(target = "veiculos", expression = "java((usuario.getVeiculos() != null) ? (usuario.getVeiculos().stream().map(veiculoMapper::entidadeParaDto).collect(Collectors.toSet())) : null)")
    public abstract ConsultaUsuarioDTO entidadeParaDto(Usuario usuario);

}
