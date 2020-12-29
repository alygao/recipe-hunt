package controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Recipe Not Found")
public class NoRecipeFoundException extends RuntimeException {

}
