package com.gft.estudoapi.dto.usuario;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistroUsuarioDTO {

    @NotEmpty(message = "O campo nome não pode ser vazio.")
    private String nome;
    @NotEmpty(message = "O campo email não pode ser vazio.")
    @Email(message = "O formato do email inserido é inválido.")
    private String email;
    @NotEmpty(message = "O campo cpf não pode ser vazio.")
    @CPF(message = "O CPF inserido é inválido.")
    private String cpf;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date nascimento;

}
