package com.challenge.simpleApi.shared.providers.FileUploadProvider.implementations;

import com.challenge.simpleApi.domains.users.controllers.UsersController;
import com.challenge.simpleApi.shared.errors.exceptions.FileStorageException;
import com.challenge.simpleApi.shared.providers.FileUploadProvider.IFileUploadProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Date;


@Component
@Primary
public class S3FileUploadProvider implements IFileUploadProvider {

  Logger logger = LoggerFactory.getLogger(S3FileUploadProvider.class);

  @Value("${s3.endpoint:}")
  public String s3_endpoint;

  public static final String UPLOAD_BUCKET_NAME = "fileplace";


  @Override
  public String uploadFile(MultipartFile file) {
    Region region = Region.US_EAST_1;
    URI localstack = URI.create(s3_endpoint);
    File tempFile = null;

    try 
    {
      logger.debug("Creating temp file");
      tempFile = File.createTempFile("tmp_",file.getOriginalFilename());
      tempFile.deleteOnExit();
      file.transferTo(tempFile);
      logger.debug("Creating S3 client");
      S3Client s3 = S3Client.builder().region(region).endpointOverride(localstack).build();

      logger.debug("Uploading to S3 bucket");
      PutObjectResponse s3Operation = s3.putObject(PutObjectRequest.builder().bucket(UPLOAD_BUCKET_NAME).key(file.getOriginalFilename()).build(),
        RequestBody.fromFile(tempFile));
      
      tempFile.delete();
      logger.info("File successfully uploaded to S3 Bucket");
      logger.info(s3Operation.toString());
      
      return s3Operation.toString();
    } catch (Exception e) {
      e.printStackTrace();
      logger.error(e.getMessage());
      throw new FileStorageException("Could not store file " + file.getOriginalFilename()
        + ". Please try again!");
    }
    
  }
}
