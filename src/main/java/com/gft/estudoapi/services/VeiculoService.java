package com.gft.estudoapi.services;

import com.gft.estudoapi.entities.Veiculo;
import com.gft.estudoapi.exceptions.BadRequestException;
import com.gft.estudoapi.repositories.VeiculoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;

    public VeiculoService(VeiculoRepository veiculoRepository){
        this.veiculoRepository = veiculoRepository;
    }

    public Veiculo salvarVeiculo(Veiculo veiculo){

        return veiculoRepository.save(veiculo);

    }

    public Page<Veiculo> listarTodosVeiculos(Pageable pageable){

        return veiculoRepository.findAll(pageable);

    }

    public Veiculo buscarVeiculo(Long id) {

        Optional<Veiculo> veiculoOptional = veiculoRepository.findById(id);

        return veiculoOptional.orElseThrow(() -> new BadRequestException("Veiculo não encontrado."));

    }

    public Veiculo atualizarVeiculo(Veiculo veiculo, Long id){

        Veiculo veiculoOriginal = this.buscarVeiculo(id);

        veiculo.setId(id);

        return veiculoRepository.save(veiculo);

    }

    public void excluirVeiculo(Long id){

        Veiculo veiculoOriginal = this.buscarVeiculo(id);

        veiculoRepository.deleteById(id);

    }

}
