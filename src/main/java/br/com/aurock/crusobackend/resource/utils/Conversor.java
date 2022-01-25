package br.com.aurock.crusobackend.resource.utils;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Conversor {

    public static List<Integer> converteStringParaInteger(String valorString){
        return Arrays.stream(valorString.split(",")).map(Integer::parseInt).collect(Collectors.toList());
    }

    public static String decodificaString(String string){
        return URLDecoder.decode(string, StandardCharsets.UTF_8);
    }
}
