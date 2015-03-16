package br.com.caelum.jdbc.dao;

public class DAOException extends RuntimeException {
	
	 //Aqui o construtor default para a classe  
    public DAOException() {  
        super("Causa do Erro Desconhecida");  
    }  
  
    //Aqui a sobreescrita do construtor que recebe como parametro uma String   
    public DAOException(String msg) {  
        super(msg);  
    }  
    
}
