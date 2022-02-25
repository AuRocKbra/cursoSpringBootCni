package br.com.aurock.crusobackend.service;

import br.com.aurock.crusobackend.service.exceptions.OperacaoNaoRealizadaException;
import br.com.aurock.crusobackend.util.Mensagens;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class ImagenService {

    public BufferedImage getImagemJpgDeArquivo(MultipartFile multipartFile){
        String extencaoArquivo = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        if(!extencaoArquivo.equals("png") && extencaoArquivo.equals("jpg")){
            throw new OperacaoNaoRealizadaException("Arquivo de imagem com formato inválido!");
        }
        try{
            BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());
            if(extencaoArquivo.equals("png")){
                bufferedImage = convertePngParaJpg(bufferedImage);
            }
            return bufferedImage;
        }catch (IOException e){
            throw new OperacaoNaoRealizadaException(Mensagens.MSG_ERRO_UPLOAD.replace("{}", multipartFile.getName()),e.getCause());
        }
    }

    private BufferedImage convertePngParaJpg(BufferedImage bufferedImage){
        BufferedImage jpgImage = new BufferedImage(bufferedImage.getWidth(),bufferedImage.getHeight(),BufferedImage.TYPE_INT_BGR);
        jpgImage.createGraphics().drawImage(bufferedImage,0,0, Color.WHITE,null);
        return jpgImage;
    }

    public InputStream getImputStream(BufferedImage bufferedImage, String extensao){
        try{
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage,extensao,outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        }catch (IOException e){
            throw new OperacaoNaoRealizadaException("Erro ao abrir aquivo!");
        }
    }
}
