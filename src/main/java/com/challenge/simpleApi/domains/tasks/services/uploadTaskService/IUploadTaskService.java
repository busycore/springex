package com.challenge.simpleApi.domains.tasks.services.uploadTaskService;

import org.springframework.web.multipart.MultipartFile;

public interface IUploadTaskService {
  public String execute(MultipartFile file);
}
