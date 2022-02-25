package br.com.aurock.crusobackend.service;

import br.com.aurock.crusobackend.service.exceptions.AmazonException;
import br.com.aurock.crusobackend.service.exceptions.OperacaoNaoRealizadaException;
import br.com.aurock.crusobackend.util.Log;
import br.com.aurock.crusobackend.util.Mensagens;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

@Service
public class S3Service {

    @Autowired
    private AmazonS3 clienteS3;

    @Value("${s3.bucket}")
    private String nomeBucket;

    private final Log logger = new Log(this);

    public URI uploadArquivo(MultipartFile arquivo) {
        logger.getLogger().info(Mensagens.MSG_SERVICE_CRIA_OBJETO,getClass().getSimpleName());
        try {
            String nomeArquivo = arquivo.getName();
            InputStream inputStream = arquivo.getInputStream();
            String contentType = arquivo.getContentType();
            return uploadArquivo(inputStream,nomeArquivo,contentType);
        } catch (IOException e) {
            logger.getLogger().error(Mensagens.MSG_ERRO_UPLOAD.replace("{}",arquivo.getName()),e.getLocalizedMessage());
            throw new OperacaoNaoRealizadaException("Erro ao processar imagem enviada!");
        }
    }

    public URI uploadArquivo(InputStream inputStream, String nomeArquivo, String contentType){
        try{
            logger.getLogger().info(Mensagens.MSG_UPLOAD_ARQUIVO,"Iniciando",nomeArquivo);
            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentType(contentType);
            clienteS3.putObject(nomeBucket,nomeArquivo,inputStream,meta);
            logger.getLogger().info(Mensagens.MSG_UPLOAD_ARQUIVO,"Finalizado",nomeArquivo);
            return clienteS3.getUrl(nomeArquivo,nomeArquivo).toURI();
        } catch (URISyntaxException e) {
            logger.getLogger().error(Mensagens.MSG_ERRO_UPLOAD.replace("{}",nomeArquivo),e.getLocalizedMessage());
            throw new AmazonException("Erro ao enviar arquivo para o servidor aws!");
        } catch (AmazonServiceException e){
            logger.getLogger().error(Mensagens.MSG_ERRO_UPLOAD.replace("{}",nomeArquivo),e.getLocalizedMessage());
            throw new AmazonException("Erro no processo de upload do arquivo");
        }
    }
}
