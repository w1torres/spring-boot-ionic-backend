package com.wildessilva.cursomc.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.wildessilva.cursomc.services.exceptions.FileException;

@Component
public class S3Service {

    private Logger LOG = org.slf4j.LoggerFactory.getLogger(S3Service.class);

    @Autowired
    private AmazonS3Client s3client;

    @Value("${s3.bucket}")
    private String bucketName;

    public URI uploadFile(MultipartFile multipartFile) {
        try {
            String fileName = multipartFile.getOriginalFilename();
            InputStream is = multipartFile.getInputStream();
            String contenType = multipartFile.getContentType();
            return uploadFile(is, fileName, contenType);
        } catch (IOException e) {
            throw new FileException("Erro de IO:" + e.getMessage());
        }

    }

    public URI uploadFile(InputStream is, String fileName, String contentType) {
        try {
            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentType(contentType);
            LOG.info("Iniciando upload");
            s3client.putObject(bucketName, fileName, is, meta);
            LOG.info("Upload finalizado");
            return s3client.getUrl(bucketName, fileName).toURI();
        } catch (URISyntaxException e) {
            throw new FileException("Erro ao converter URL para URI");
        }
    }
}
