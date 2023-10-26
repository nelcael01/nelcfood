package com.nelcfood.api.exceptionhandler;

import com.nelcfood.model.exception.EntidadeEmUsoException;
import com.nelcfood.model.exception.NegocioException;
import com.nelcfood.model.exception.naoEncontrada.EntidadeNaoEncontradaException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> tratarEntidadeNaoEncontradaException
            (EntidadeNaoEncontradaException e, WebRequest request) {

        Problema problema = fabricaDeProblema(HttpStatus.NOT_FOUND,
                TipoProblema.ENTIDADE_NAO_ENCONTRADA,
                e.getMessage());

        return handleExceptionInternal(e, problema, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> tratarEntidadeEmUsoException(EntidadeEmUsoException e, WebRequest request) {

        Problema problema = fabricaDeProblema(HttpStatus.CONFLICT,
                TipoProblema.ENTIDADE_EM_USO,
                e.getMessage());
        return handleExceptionInternal(e, problema, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> tratarNegocioException(NegocioException e, WebRequest request) {
        Problema problema = fabricaDeProblema(HttpStatus.BAD_REQUEST,
                TipoProblema.ENTIDADE_NAO_ENCONTRADA,
                e.getMessage());
        return handleExceptionInternal(e, problema, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal
            (Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (body == null) {
            body = Problema.builder()
                    .titulo(status.getReasonPhrase())
                    .status(status.value())
                    .build();
        } else if (body instanceof String) {
            body = Problema.builder()
                    .titulo((String) body)
                    .status(status.value())
                    .build();
        }
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private Problema fabricaDeProblema(HttpStatus status, TipoProblema tipoProblema, String detalhes) {
        return Problema.builder()
                .status(status.value())
                .tipo(tipoProblema.getTipo())
                .titulo(tipoProblema.getTitulo())
                .detalhes(detalhes)
                .build();
    }
}
