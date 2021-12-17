package com.gft.estudoapi.controllers;

import com.gft.estudoapi.dto.veiculo.ConsultaVeiculoDTO;
import com.gft.estudoapi.dto.veiculo.RegistroVeiculoDTO;
import com.gft.estudoapi.entities.Veiculo;
import com.gft.estudoapi.mapper.VeiculoMapper;
import com.gft.estudoapi.services.VeiculoService;
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

@RestController
@RequestMapping("v1/veiculos")
@Api(value = "API REST Veículos")
@CrossOrigin(origins = "*")
public class VeiculoController {

    private final VeiculoService veiculoService;

    private final VeiculoMapper veiculoMapper;

    public VeiculoController(VeiculoService veiculoService, VeiculoMapper veiculoMapper) {
        this.veiculoService = veiculoService;
        this.veiculoMapper = veiculoMapper;
    }

    @GetMapping
    @ApiOperation("Retorna todos os veículos.")
    public ResponseEntity<Page<ConsultaVeiculoDTO>> buscarTodosVeiculos(@PageableDefault Pageable pageable){

        return ResponseEntity.ok(veiculoService.listarTodosVeiculos(pageable).map(veiculoMapper::entidadeParaDto));

    }

    @PostMapping
    @Transactional
    @ApiOperation("Salva um veículo.")
    public ResponseEntity<ConsultaVeiculoDTO> salvarVeiculo(@RequestBody @Valid RegistroVeiculoDTO dto){

        Veiculo veiculoSalvo = veiculoService.salvarVeiculo(veiculoMapper.dtoParaEntidade(dto));

        return new ResponseEntity<ConsultaVeiculoDTO>(veiculoMapper.entidadeParaDto(veiculoSalvo), HttpStatus.CREATED);

    }

    @GetMapping("{id}")
    @ApiOperation("Retorna um veículo específico.")
    public ResponseEntity<ConsultaVeiculoDTO> buscarVeiculo(@PathVariable Long id){

        Veiculo veiculo = veiculoService.buscarVeiculo(id);

        return ResponseEntity.ok(veiculoMapper.entidadeParaDto(veiculo));

    }

    @PutMapping("{id}")
    @Transactional
    @ApiOperation("Atualiza um veículo.")
    public ResponseEntity<ConsultaVeiculoDTO> alterarVeiculo(@RequestBody RegistroVeiculoDTO dto, @PathVariable Long id){

        Veiculo veiculo = veiculoService.atualizarVeiculo(veiculoMapper.dtoParaEntidade(dto), id);

        return ResponseEntity.ok(veiculoMapper.entidadeParaDto(veiculo));

    }

    @DeleteMapping("{id}")
    @Transactional
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation("Deleta um veículo.")
    public ResponseEntity<ConsultaVeiculoDTO> excluirVeiculo(@PathVariable Long id){

        veiculoService.excluirVeiculo(id);

        return ResponseEntity.ok().build();

    }

}
