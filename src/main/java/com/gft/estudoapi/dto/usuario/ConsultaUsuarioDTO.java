package com.gft.estudoapi.dto.usuario;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gft.estudoapi.dto.veiculo.ConsultaVeiculoDTO;
import com.gft.estudoapi.entities.Veiculo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaUsuarioDTO {

    private Long id;
    private String nome;
    private String email;
    private String cpf;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date nascimento;
    private Set<ConsultaVeiculoDTO> veiculos;

}
