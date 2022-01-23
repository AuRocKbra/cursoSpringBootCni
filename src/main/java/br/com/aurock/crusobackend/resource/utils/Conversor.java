package br.com.aurock.crusobackend.resource.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Conversor {

    public static List<Integer> ConverteStringParaInteger(String valorString){
        return Arrays.stream(valorString.split(",")).map(Integer::parseInt).collect(Collectors.toList());
    }
}
