package com.challenge.simpleApi.shared.providers.FileUploadProvider.implementations;

import com.challenge.simpleApi.shared.errors.exceptions.FileStorageException;
import com.challenge.simpleApi.shared.providers.FileUploadProvider.IFileUploadProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
class LocalFileUploadProvider implements IFileUploadProvider {

  @Value("${app.upload.dir:${user.home}}")
  public String uploadDir;

  @Override
  public String uploadFile(MultipartFile file) {

    try {
      Path copyLocation = Paths.get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
      Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
      return copyLocation.toString();
    } catch (Exception e) {
      e.printStackTrace();
      throw new FileStorageException("Could not store file " + file.getOriginalFilename()
        + ". Please try again!");
    }
  }

}
