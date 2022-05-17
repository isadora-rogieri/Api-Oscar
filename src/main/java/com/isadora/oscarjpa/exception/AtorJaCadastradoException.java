package com.isadora.oscarjpa.exception;

public class AtorJaCadastradoException extends RuntimeException{
    public AtorJaCadastradoException() {
        super("Ator já cadastrado");
    }
}
