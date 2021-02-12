package com.challenge.simpleApi.shared.http;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class APIResponse<T> {
  
  @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy hh:mm:ss")
  private LocalDateTime timestamp;
  private String path;
  private String method;
  private Integer status_code=200;
  private String message="OK";
  private T data;
  

  public APIResponse(T data){

    this.data = data;
    this.path=getEndpoint().getPath();
    this.method=getCurrentHttpRequest().get().getMethod();
    this.timestamp = LocalDateTime.now();
  }
  
  public APIResponse(T data,HttpStatus code){
    this(data);
    this.status_code=code.value();
    this.message=code.getReasonPhrase();
  }
  
  
  private URI getEndpoint(){
    ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequestUri();
    builder.scheme("https");
    //builder.replaceQueryParam("someBoolean", false);
    URI newUri = builder.build().toUri();
    return newUri;
  }

  public static Optional<HttpServletRequest> getCurrentHttpRequest() {
    return Optional.ofNullable(RequestContextHolder.getRequestAttributes())
      .filter(requestAttributes -> ServletRequestAttributes.class.isAssignableFrom(requestAttributes.getClass()))
      .map(requestAttributes -> ((ServletRequestAttributes) requestAttributes))
      .map(ServletRequestAttributes::getRequest);
  }
  
  private HttpServletResponse getCurrentHttpResponse(){
    RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
    return ((ServletRequestAttributes)requestAttributes).getResponse();
  }
}
