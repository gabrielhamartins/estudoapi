package com.gft.estudoapi.dto.fipe;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RespostaFipeDTO {

    private String nome;
    private String codigo;
    @JsonProperty("Valor")
    private String Valor;
    private List<RespostaFipeDTO> modelos;
    private List<RespostaFipeDTO> anos;

}
