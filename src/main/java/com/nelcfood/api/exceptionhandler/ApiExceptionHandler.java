package com.nelcfood.api.exceptionhandler;

import com.nelcfood.model.exception.EntidadeEmUsoException;
import com.nelcfood.model.exception.NegocioException;
import com.nelcfood.model.exception.naoEncontrada.EntidadeNaoEncontradaException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Locale;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String MSG_ERRO_GENERICA_USUARIO_FINAL = "Ocorreu um erro interno inesperado no sistema. Tente novamente e se o problema persistir," +
            " entre em contato com o admistrador do sistema.";

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> tratarEntidadeNaoEncontradaException
            (EntidadeNaoEncontradaException e, WebRequest request) {

        Problema problema = fabricaDeProblema(HttpStatus.NOT_FOUND,
                TipoProblema.ENTIDADE_NAO_ENCONTRADA,
                e.getMessage()
        )
                .mensagemUsuario(e.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return handleExceptionInternal(e, problema, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> tratarEntidadeEmUsoException(EntidadeEmUsoException e, WebRequest request) {

        Problema problema = fabricaDeProblema(HttpStatus.CONFLICT,
                TipoProblema.ENTIDADE_EM_USO,
                e.getMessage()
        )
                .mensagemUsuario( e.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return handleExceptionInternal(e, problema, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> tratarNegocioException(NegocioException e, WebRequest request) {
        Problema problema = fabricaDeProblema(HttpStatus.BAD_REQUEST,
                TipoProblema.ERRO_NEGOCIO,
                e.getMessage()
        )
                .mensagemUsuario(e.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return handleExceptionInternal(e, problema, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> tratarException(Exception e, WebRequest request) {
        Problema problema = fabricaDeProblema(HttpStatus.INTERNAL_SERVER_ERROR,
                TipoProblema.ERRO_DE_SISTEMA,
                MSG_ERRO_GENERICA_USUARIO_FINAL
        )
                .mensagemUsuario(MSG_ERRO_GENERICA_USUARIO_FINAL)
                .timestamp(LocalDateTime.now())
                .build();
        return handleExceptionInternal(e, problema, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal
            (Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (body == null) {
            body = Problema.builder()
                    .titulo(status.getReasonPhrase())
                    .status(status.value())
                    .timestamp(LocalDateTime.now())
                    .mensagemUsuario(MSG_ERRO_GENERICA_USUARIO_FINAL)
                    .build();
        } else if (body instanceof String) {
            body = Problema.builder()
                    .titulo((String) body)
                    .status(status.value())
                    .timestamp(LocalDateTime.now())
                    .mensagemUsuario(MSG_ERRO_GENERICA_USUARIO_FINAL)
                    .build();
        }
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable
            (HttpMessageNotReadableException ex,
             HttpHeaders headers, HttpStatus status, WebRequest request) {

        Problema problema = fabricaDeProblema(
                status,
                TipoProblema.ENTIDADE_NAO_ENCONTRADA,
                "O corpo da requisição está inválido. Verifique erro de sintaxe."
        )
                .mensagemUsuario("O corpo da requisição está inválido. Verifique erro de sintaxe.")
                .timestamp(LocalDateTime.now())
                .build();

        return super.handleHttpMessageNotReadable(ex, headers, status, request);
    }

    private Problema.ProblemaBuilder fabricaDeProblema(HttpStatus status, TipoProblema tipoProblema, String detalhes) {
        return Problema.builder()
                .status(status.value())
                .tipo(tipoProblema.getTipo())
                .titulo(tipoProblema.getTitulo())
                .detalhes(detalhes);
    }
}
