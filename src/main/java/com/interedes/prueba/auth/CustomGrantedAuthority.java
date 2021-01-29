package com.interedes.prueba.auth;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonCreator;

public class CustomGrantedAuthority implements GrantedAuthority{

	private static final long serialVersionUID = 1L;

    private String authority;

    
    //Indica el constructor por defecto cuando se crean objetos apartir de JSON
    //aunque por defecto busca el constructor vacio
    @JsonCreator
    public CustomGrantedAuthority(){
        
    }

    public CustomGrantedAuthority(String authority){
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

}
