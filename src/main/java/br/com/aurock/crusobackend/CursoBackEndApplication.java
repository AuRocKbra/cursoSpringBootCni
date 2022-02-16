package br.com.aurock.crusobackend;

import br.com.aurock.crusobackend.domain.*;
import br.com.aurock.crusobackend.domain.enuns.EstadoPagamento;
import br.com.aurock.crusobackend.domain.enuns.Perfil;
import br.com.aurock.crusobackend.domain.enuns.TipoCliente;
import br.com.aurock.crusobackend.repository.*;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.CancellationException;

@SpringBootApplication
public class CursoBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(CursoBackEndApplication.class, args);
	}
}
