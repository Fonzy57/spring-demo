package com.site.cda_demo;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class InterceptorExceptionGlobal {

  // Permet de retourner un JSON pour dire quelle exception a été levé
  // Dans le cas où il y a une erreur lors d'un call API
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public Map<String, Object> intercepteurViolationContrainte(MethodArgumentNotValidException exception) {

    Map<String, Object> errors = new HashMap<>();
    exception.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });

    return errors;
  }

  // Ici le cas où il y ait une erreur 500
  @ExceptionHandler(DataIntegrityViolationException.class)
  @ResponseStatus(HttpStatus.CONFLICT) // Code de retour
  @ResponseBody // Assure que la réponse est envoyée au format JSON (dans le corp de la réponse)
  public Map<String, Object> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {

    ConstraintViolationException cause = (ConstraintViolationException) exception.getCause();

    if (cause.getKind() == ConstraintViolationException.ConstraintKind.UNIQUE) {
      return Map.of("message", "Le champ doit contenir une valeur unique");
    } else {
      return Map.of("message", "Une contrainte de clé étrangère a été violée");
    }


    // return Map.of("message", "Une violation de contrainte d'intégrité de données a été détectée");
  }


  // A décommenter uniquement pour pouvoir  voir les erreurs en prod/staging en l'absence de console
  // NE PAS UTILISER EN PROD
//  @ExceptionHandler(ConstraintViolationException.class)
//  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//  @ResponseBody
//  protected Map<String, Object> handleAllError(RuntimeException exception, WebRequest request) {
//    StringWriter sw = new StringWriter();
//    PrintWriter pw = new PrintWriter(sw);
//    exception.printStackTrace(pw);
//
//    String bodyOfResponse = sw.toString();
//
//    return Map.of("message", bodyOfResponse);
//  }


}
