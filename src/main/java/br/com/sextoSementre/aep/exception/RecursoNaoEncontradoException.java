package br.com.sextoSementre.aep.exception;

public class RecursoNaoEncontradoException extends RuntimeException {

    public RecursoNaoEncontradoException(String recurso, String id) {
        super(recurso + " não encontrado(a) para o ID " + id);
    }
}
