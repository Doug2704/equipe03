package com.codigocerto.backend.controllers;

import com.codigocerto.backend.exceptions.ResourceNotFoundException;
import com.codigocerto.backend.exceptions.ValidationErrors;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ApplicationControllerAdviceTest {

    private static final String NOT_FOUND_MESSAGE = "Recurso não encontrado";
    private static final String REQUEST_URI = "/api/v1/usuarios";
    private static final String FIELD_NAME_1 = "nome";
    private static final String FIELD_ERROR_MESSAGE_1 = "Nome é obrigatório";
    private static final String FIELD_NAME_2 = "email";
    private static final String FIELD_ERROR_MESSAGE_2 = "Email é inválido";

    @InjectMocks
    private ApplicationControllerAdvice applicationControllerAdvice;

    @Test
    void handleNotFoundException_DeveRetornarMensagemErroNotFound() {
        ResourceNotFoundException ex = new ResourceNotFoundException(NOT_FOUND_MESSAGE);
        String result = applicationControllerAdvice.handleNotFoundException(ex);

        assertEquals(NOT_FOUND_MESSAGE, result);
    }

    @Test
    void validationErrors_DeveRetornarValidationErrorsComStatus400() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI(REQUEST_URI);

        FieldError fieldError1 = new FieldError("usuarioRequestDto", FIELD_NAME_1, FIELD_ERROR_MESSAGE_1);
        FieldError fieldError2 = new FieldError("usuarioRequestDto", FIELD_NAME_2, FIELD_ERROR_MESSAGE_2);
        BindException bindException = new BindException(new Object(), "usuarioRequestDto");
        bindException.addError(fieldError1);
        bindException.addError(fieldError2);

        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, bindException);

        ValidationErrors validationErrors = applicationControllerAdvice.validationErrors(ex, request);

        assertEquals(HttpStatus.BAD_REQUEST.value(), validationErrors.getStatus());
        assertEquals("Validation Error", validationErrors.getError());
        assertEquals(REQUEST_URI, validationErrors.getPath());
        assertEquals(2, validationErrors.getErrors().size());
        assertEquals(FIELD_NAME_1, validationErrors.getErrors().get(0).getFieldName());
        assertEquals(FIELD_ERROR_MESSAGE_1, validationErrors.getErrors().get(0).getMessage());
        assertEquals(FIELD_NAME_2, validationErrors.getErrors().get(1).getFieldName());
        assertEquals(FIELD_ERROR_MESSAGE_2, validationErrors.getErrors().get(1).getMessage());
    }
}
