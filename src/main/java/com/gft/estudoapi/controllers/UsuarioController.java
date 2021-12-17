package com.gft.estudoapi.controllers;

import com.gft.estudoapi.dto.usuario.ConsultaUsuarioDTO;
import com.gft.estudoapi.dto.usuario.RegistroUsuarioDTO;
import com.gft.estudoapi.dto.veiculo.ConsultaVeiculoDTO;
import com.gft.estudoapi.entities.Usuario;
import com.gft.estudoapi.entities.Veiculo;
import com.gft.estudoapi.mapper.UsuarioMapper;
import com.gft.estudoapi.mapper.VeiculoMapper;
import com.gft.estudoapi.services.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/usuarios")
@Api(value = "API REST Usuários")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService usuarioService;

    private final VeiculoMapper veiculoMapper;

    private  final UsuarioMapper usuarioMapper;

    public UsuarioController(UsuarioService usuarioService, VeiculoMapper veiculoMapper, UsuarioMapper usuarioMapper) {
        this.usuarioService = usuarioService;
        this.veiculoMapper = veiculoMapper;
        this.usuarioMapper = usuarioMapper;
    }

    @GetMapping
    @ApiOperation("Retorna uma lista de usuários")
    public ResponseEntity<Page<ConsultaUsuarioDTO>> buscarTodosUsuarios(@PageableDefault Pageable pageable){

        return ResponseEntity.ok(usuarioService.listarTodosUsuarios(pageable).map(usuarioMapper::entidadeParaDto));

    }

    @PostMapping
    @Transactional
    @ApiOperation("Salva um usuário")
    public ResponseEntity<ConsultaUsuarioDTO> salvarUsuario(@RequestBody @Valid RegistroUsuarioDTO dto){

        Usuario usuarioSalvo = usuarioService.salvarUsuario(usuarioMapper.dtoParaEntidade(dto));

        return new ResponseEntity<ConsultaUsuarioDTO>(usuarioMapper.entidadeParaDto(usuarioSalvo), HttpStatus.CREATED);

    }

    @GetMapping("{id}")
    @ApiOperation("Retorna um usuário específico.")
    public ResponseEntity<ConsultaUsuarioDTO> buscarUsuario(@PathVariable Long id){

        Usuario usuario = usuarioService.buscarUsuario(id);

        return ResponseEntity.ok(usuarioMapper.entidadeParaDto(usuario));

    }

    @GetMapping("{id}/veiculos")
    @ApiOperation("Retorna apenas os veículos de um determinado usuário.")
    public ResponseEntity<Set<ConsultaVeiculoDTO>> buscarVeiculos(@PathVariable Long id){

        Set<Veiculo> veiculos = usuarioService.buscarVeiculos(id);

        return ResponseEntity.ok(veiculos.stream().map(veiculoMapper::entidadeParaDto).collect(Collectors.toSet()));

    }

    @PutMapping("{id}")
    @Transactional
    @ApiOperation("Atualiza um usuário")
    public ResponseEntity<ConsultaUsuarioDTO> alterarUsuario(@RequestBody RegistroUsuarioDTO dto, @PathVariable Long id){

        Usuario usuario = usuarioService.atualizarUsuario(usuarioMapper.dtoParaEntidade(dto), id);

        return ResponseEntity.ok(usuarioMapper.entidadeParaDto(usuario));

    }

    @Transactional
    @PutMapping("{usuarioId}/veiculos/{veiculoId}")
    @ApiOperation("Cadastrado um veículo para um usuário")
    public ResponseEntity<ConsultaUsuarioDTO> alterarUsuario(@PathVariable Long usuarioId, @PathVariable Long veiculoId){

        Usuario usuario = usuarioService.adicionarVeiculo(usuarioId, veiculoId);

        return ResponseEntity.ok(usuarioMapper.entidadeParaDto(usuario));

    }

    @DeleteMapping("{id}")
    @Transactional
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation("Deleta um usuário")
    public ResponseEntity<ConsultaUsuarioDTO> excluirUsuario(@PathVariable Long id){

        usuarioService.excluirUsuario(id);

        return ResponseEntity.ok().build();

    }

}
