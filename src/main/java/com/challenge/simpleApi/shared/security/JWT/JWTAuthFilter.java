package com.challenge.simpleApi.shared.security.JWT;

import com.challenge.simpleApi.domains.users.services.UsersService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthFilter extends OncePerRequestFilter {

  private JWTService jwtService;
  private UsersService usersService;

  public JWTAuthFilter(JWTService jwtService, UsersService usersService) {
    this.jwtService = jwtService;
    this.usersService = usersService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, 
                                  HttpServletResponse response,
                                  FilterChain filterChain) 
    throws ServletException, IOException {

    String authorization = request.getHeader("Authorization");
    //If is not authenticated already and the authorization header starts with bearer

    if(authorization!=null && authorization.startsWith("Bearer")){

      String token = this.getTokenFromHeader(authorization);

      boolean isValid = jwtService.ValidateToken(token);

      if(isValid){
        //Get the username in JWT Payload
        String username = jwtService.getLogin(token);

        //Load the userDetails using the implemented UserDetails service
        UserDetails usuario = usersService.loadUserByUsername(username);

        UsernamePasswordAuthenticationToken user = 
          new UsernamePasswordAuthenticationToken(usuario,null,usuario.getAuthorities());

        user.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(user);
      }
      
    }
    //After the token and authorization was made,inject it into the request and go on
    filterChain.doFilter(request,response);
  }

  private String getTokenFromHeader(String authorization) {
    return authorization.split(" ")[1];
  }
}
