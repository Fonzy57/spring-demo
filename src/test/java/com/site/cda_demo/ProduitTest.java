package com.site.cda_demo;

import com.site.cda_demo.model.Produit;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProduitTest {

  private Validator validator;

  @BeforeEach
  public void setUp() {
    ValidatorFactory factory =
        Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  void createValidProduit_shouldNotThrowException() {
    Produit produitTest = new Produit();

    produitTest.setNom("ProduitTest");
    produitTest.setPrix(100);
    produitTest.setCode("57>54");

    Set<ConstraintViolation<Produit>> violations = validator.validate(produitTest);

    assertTrue(violations.isEmpty());

  }

  @Test
  void createValidProduitWithoutName_shouldNotBeValid() {
    Produit produitTest = new Produit();

    produitTest.setPrix(100);
    produitTest.setCode("Test non bon");

    Set<ConstraintViolation<Object>> violations = validator.validate(produitTest);

    boolean notBlankViolationExist = constraintExist(
        violations,
        "nom",
        "NotBlank"
    );

    assertTrue(notBlankViolationExist);
  }

  @Test
  void createValidProduitWithNegativPrice_shouldNotBeValid() {
    Produit produitTest = new Produit();

    produitTest.setNom("ProduitTest");
    produitTest.setPrix(-5);

    Set<ConstraintViolation<Object>> violations = validator.validate(produitTest);

    boolean notBlankViolationExist = constraintExist(
        violations,
        "prix",
        "DecimalMin"
    );

    assertTrue(notBlankViolationExist);
  }

  // Méthode pour vérifier si une contrainte Jakarta existe
  private boolean constraintExist(Set<ConstraintViolation<Object>> violations, String filedName,
      String constraintName) {
    return violations.stream()
        .filter(v -> v.getPropertyPath().toString().equals(filedName))
        .map(v -> v.getConstraintDescriptor().getAnnotation().annotationType().getName())
        .anyMatch(s -> s.equals("jakarta.validation.constraints." + constraintName));
  }

}
