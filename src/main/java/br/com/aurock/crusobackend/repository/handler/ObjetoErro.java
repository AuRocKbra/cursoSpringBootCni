package br.com.aurock.crusobackend.repository.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
public class ObjetoErro implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer statusErro;
    private String msg;
    private Long timeStamp;


}
