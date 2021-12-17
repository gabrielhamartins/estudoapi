package com.gft.estudoapi.dto.veiculo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaVeiculoDTO {

    private Long id;
    private String marca;
    private String modelo;
    private String ano;
    private String valor;
    private String diaDeRodizio;
    private Boolean estaRodizio;

}
