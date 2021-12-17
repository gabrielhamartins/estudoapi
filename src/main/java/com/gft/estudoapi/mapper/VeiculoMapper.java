package com.gft.estudoapi.mapper;

import com.gft.estudoapi.dto.fipe.RespostaFipeDTO;
import com.gft.estudoapi.dto.veiculo.ConsultaVeiculoDTO;
import com.gft.estudoapi.dto.veiculo.RegistroVeiculoDTO;
import com.gft.estudoapi.entities.Veiculo;
import com.gft.estudoapi.feign.FipeClient;
import lombok.Getter;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.List;

@Mapper(componentModel = "spring", imports = VeiculoMapper.ValoresDeRetorno.class)
public abstract class VeiculoMapper {

    @Autowired
    protected FipeClient fipeClient;

    public abstract Veiculo dtoParaEntidade(RegistroVeiculoDTO registroVeiculoDTO);

    @Mapping(target = "valor", expression = "java(obterValor(veiculo))")
    @Mapping(target = "estaRodizio", expression = "java(checarRodizio(veiculo).isRodizio())")
    @Mapping(target = "diaDeRodizio", expression = "java(checarRodizio(veiculo).getDiaDaSemana())")
    public abstract ConsultaVeiculoDTO entidadeParaDto(Veiculo veiculo);

    ValoresDeRetorno checarRodizio(Veiculo veiculo){

        int ultimoDigitoAno = Integer.parseInt(String.valueOf(veiculo.getAno().charAt(3)));

        Calendar calendar = Calendar.getInstance();

        int hoje = calendar.get(Calendar.DAY_OF_WEEK);

        String diaDeRozidio = null;
        boolean rodizio = false;


        if((ultimoDigitoAno == 0 || ultimoDigitoAno == 1)){
            diaDeRozidio = "Segunda-Feira";
            if(hoje == 2){
                rodizio = true;
            }
        }
        if((ultimoDigitoAno == 2 || ultimoDigitoAno == 3)){
            diaDeRozidio = "Terça-Feira";
            if(hoje == 3){
                rodizio = true;
            }
        }
        if((ultimoDigitoAno == 4 || ultimoDigitoAno == 5)){
            diaDeRozidio = "Quarta-Feira";
            if(hoje == 4){
                rodizio = true;
            }
        }
        if((ultimoDigitoAno == 6 || ultimoDigitoAno == 7)){
            diaDeRozidio = "Quinta-Feira";
            if(hoje == 5){
                rodizio = true;
            }
        }
        if((ultimoDigitoAno == 8 || ultimoDigitoAno == 9)){
            diaDeRozidio = "Sexta-Feira";
            if(hoje == 6){
                rodizio = true;
            }
        }

        return new ValoresDeRetorno(diaDeRozidio, rodizio);

    }

    String obterValor(Veiculo veiculo){

        String marcaId = null;
        String modeloId = null;

        List<RespostaFipeDTO> marcas = fipeClient.getBrands();


        for(RespostaFipeDTO marca : marcas){
            if(marca.getNome().equals(veiculo.getMarca())){
                marcaId = marca.getCodigo();
                break;
            }
        }

        RespostaFipeDTO modelos = fipeClient.getModels(marcaId);

        for(RespostaFipeDTO modelo : modelos.getModelos()){
            if(modelo.getNome().equals(veiculo.getModelo())){
                modeloId = modelo.getCodigo();
                break;
            }
        }

        RespostaFipeDTO retorno = fipeClient.getValue(marcaId, modeloId, veiculo.getAno());

        return retorno.getValor();

    }

    @Getter
    @Setter
    public class ValoresDeRetorno{

        public final String diaDaSemana;
        public final boolean rodizio;

        public ValoresDeRetorno(String diaDaSemana, boolean rodizio) {
            this.diaDaSemana = diaDaSemana;
            this.rodizio = rodizio;
        }
    }


}
