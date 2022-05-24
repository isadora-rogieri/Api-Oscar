package com.isadora.oscarjpa.controller;

import com.isadora.oscarjpa.exception.AtorJaCadastradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class AtorAdvice {

    @ExceptionHandler
    public ResponseEntity tratarValidacoes(MethodArgumentNotValidException e){
        Map<String, String> erros = new HashMap<>();
        for (int i =0; i < e.getBindingResult().getAllErrors().size(); i++){

            String campo = ((FieldError) e.getBindingResult().getAllErrors().get(i)).getField();
            String erro = ((FieldError) e.getBindingResult().getAllErrors().get(i)).getDefaultMessage();
            String erroFormatado = String.format("Erro no campo:  " + campo + " ERRO: " + erro);
            erros.put(String.format("Erro: " + i), String.format("Erro no campo: " + campo + " ERRO: " + erro));

        }
        return  new ResponseEntity(erros, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler
    public ResponseEntity tratarAtorExistente(AtorJaCadastradoException e){
        ResponseEntity response = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        return  response;
    }
}
