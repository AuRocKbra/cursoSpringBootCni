package br.com.aurock.crusobackend.service;

import br.com.aurock.crusobackend.service.exceptions.AmazonException;
import br.com.aurock.crusobackend.util.Log;
import br.com.aurock.crusobackend.util.Mensagens;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class S3Service {

    @Autowired
    private AmazonS3 clienteS3;

    @Value("${s3.bucket}")
    private String nomeBucket;

    private final Log logger = new Log(this);

    public void uploadDeArquivo(String pathArquivo) {
        logger.getLogger().info(Mensagens.MSG_SERVICE_CRIA_OBJETO,getClass().getSimpleName());
        try {
            File arquivo = new File(pathArquivo);
            logger.getLogger().info(Mensagens.MSG_UPLOAD_ARQUIVO,"Iniciando",arquivo.getName());
            clienteS3.putObject(new PutObjectRequest(nomeBucket, "teste", arquivo));
            logger.getLogger().info(Mensagens.MSG_UPLOAD_ARQUIVO,"Concluído",arquivo.getName());
        }catch (AmazonServiceException e){
            throw new AmazonException("Falha no serviço da amazon");
        }catch (AmazonClientException e){
            throw new AmazonException("Falha na autenticação no servidor da amazon");
        }
    }
}
