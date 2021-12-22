package br.com.aurock.crusobackend.util;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
public class Log {

    private Logger logger;

    public Log(Object classe){
        logger = LoggerFactory.getLogger(classe.getClass());
    }
}
