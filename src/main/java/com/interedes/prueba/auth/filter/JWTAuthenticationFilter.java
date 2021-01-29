package com.interedes.prueba.auth.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interedes.prueba.auth.service.JWTServiceImpl;
import com.interedes.prueba.models.entity.Usuario;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JWTServiceImpl jwtServiceImpl;
	
	
	
	public JWTAuthenticationFilter(AuthenticationManager authenticacionManager) {
		this.authenticationManager = authenticacionManager;
		setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/user"));
		
	}




	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		//return super.attemptAuthentication(request, response);
		
		String username = obtainUsername(request);
		String password = obtainPassword(request);
		
		if (username == null && password == null) {
			
			Usuario usuario = null;

			try {	
				usuario = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
				username = usuario.getUsername();
				password = usuario.getPassword();

				logger.info("Username desde request (InputStream) en filtro JWTAuth: " + username);
				logger.info("Password desde request (InputStream) en filtro JWTAuth: " + password);
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}

		username = username.trim();

		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
		return authenticationManager.authenticate(authToken);
	}




	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		String token = jwtServiceImpl.createToken(authResult);

		response.addHeader("Authorization", "Bearer " + token);

		Map<String, Object> body = new HashMap<String, Object>();
		body.put("token", token);
		body.put("User", (Usuario) authResult.getPrincipal());
		body.put("mensaje", String.format("Hola %s has iniciado sesion con exito", authResult.getName()));

		ObjectMapper formateadorJson = new ObjectMapper();
		String json = formateadorJson.writeValueAsString(body);

		response.getWriter().write(json);
		response.setStatus(200);
		response.setContentType("application/json");
		
	}




	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		Map<String, String> body = new HashMap<>();
		body.put("mensaje", "Credenciales incorrectas");
		body.put("error", failed.getMessage());
		
		response.getWriter().write(new ObjectMapper().writeValueAsString(body));
		response.setStatus(401);
		response.setContentType("application/json");
		
		
		
	}
	
	
	
	
	
	
	
	

}
