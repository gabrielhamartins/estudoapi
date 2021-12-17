package com.gft.estudoapi.dto.veiculo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistroVeiculoDTO {

    @NotEmpty(message = "O campo marca não pode ser vazio.")
    private String marca;
    @NotEmpty(message = "O campo modelo não pode ser vazio.")
    private String modelo;
    @NotEmpty(message = "O campo ano não pode ser vazio.")
    private String ano;

}
