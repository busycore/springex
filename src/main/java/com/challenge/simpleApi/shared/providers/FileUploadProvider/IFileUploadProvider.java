package com.challenge.simpleApi.shared.providers.FileUploadProvider;


import org.springframework.web.multipart.MultipartFile;

public interface IFileUploadProvider {

  String uploadFile(MultipartFile file);
}