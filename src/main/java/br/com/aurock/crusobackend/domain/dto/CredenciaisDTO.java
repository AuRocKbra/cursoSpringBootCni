package br.com.aurock.crusobackend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CredenciaisDTO implements Serializable {

    private static final long serialVersionUID = 1l;

    private String email;
    private String senha;

}
