package com.challenge.simpleApi.domains.tasks.services.uploadTaskService;

import com.challenge.simpleApi.shared.providers.FileUploadProvider.IFileUploadProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadTaskService implements IUploadTaskService{

  @Autowired
  IFileUploadProvider fileUploadProvider;
  
  @Override
  public String execute(MultipartFile file) {
    return fileUploadProvider.uploadFile(file);
  }
}
