package br.com.aurock.crusobackend.service;

import br.com.aurock.crusobackend.domain.Cliente;
import br.com.aurock.crusobackend.repository.ClienteRepository;
import br.com.aurock.crusobackend.service.exceptions.ObjetoNaoEncontradoException;
import br.com.aurock.crusobackend.util.Log;
import br.com.aurock.crusobackend.util.Mensagens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EmailService emailService;

    private Random random = new Random();

    private final Log logger = new Log(this);

    public void envidaNovaSenha(String email){
        logger.getLogger().info(Mensagens.MSG_SERVICO_ESQUECI_SENHA);
        logger.getLogger().info(Mensagens.MSG_VALIDACAO_DADOS,getClass().getSimpleName(),email);
        Cliente cliente = clienteRepository.findByEmail(email);
        if(cliente == null){
            throw new ObjetoNaoEncontradoException(Mensagens.MSG_OBJETO_NAO_ENCONTRADO.replace("{}", "O email "+email));
        }
        logger.getLogger().info(Mensagens.MSG_SERVICE_CRIA_OBJETO,"senha");
        String novaSenha = geradorDeSenha();
        cliente.setSenha(bCryptPasswordEncoder.encode(novaSenha));
        clienteRepository.save(cliente);
        logger.getLogger().info(Mensagens.MSG_ENVIANDO_EMAIL_NOVA_SENHA,email);
        emailService.enviaEmailNovaSenha(cliente,novaSenha);
    }

    private String geradorDeSenha(){
        logger.getLogger().info(Mensagens.MSG_GERANDO_SENHA,"Gerando");
        char [] vet = new char[10];
        for(int i=0; i<10; i++){
            vet[i] = gerarValorAleatorio();
        }
        return new String(vet);
    }

    private char gerarValorAleatorio(){
        logger.getLogger().info(Mensagens.MSG_GERANDO_SENHA,"Calculando");
        int semente = random.nextInt(3);
        char valor = 0;
        switch (semente) {
            case 0:
                valor = (char) (random.nextInt(10) + 48);
                break;
            case 1:
                valor = (char) (random.nextInt(26) + 65);
                break;
            case 2:
                valor = (char) (random.nextInt(26) + 97);
                break;
            default:
                break;
        }
        return valor;
    }
}
