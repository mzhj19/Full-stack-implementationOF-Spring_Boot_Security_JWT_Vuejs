package com.ZahidHasanJamil.SBproject.controller;

import com.ZahidHasanJamil.SBproject.exception.NotValidException;
import com.ZahidHasanJamil.SBproject.model.AuthResponseDto;
import com.ZahidHasanJamil.SBproject.model.LoginRequestDto;
import com.ZahidHasanJamil.SBproject.model.RegisterRequestDto;
import com.ZahidHasanJamil.SBproject.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping
  public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto registerRequestDto) {
    //return ResponseEntity.ok(authService.login(registerRequestDto));

    try {
      System.out.println(registerRequestDto.toString() +'\n');
      AuthResponseDto response = authService.login(registerRequestDto);
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      // Handle the exception and return an appropriate response or error message
      System.out.println(e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
  
  @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@Valid @RequestBody RegisterRequestDto registerRequestDto, BindingResult bindingResult) throws NotValidException {
//    if(bindingResult.hasErrors()) {
//      System.out.println(bindingResult.getAllErrors().);
//      throw new NotValidException(bindingResult.getFieldError().getDefaultMessage());
//    }
    if (bindingResult.hasErrors()) {
      Map<String, String> errors = new HashMap<>();
      for (FieldError error : bindingResult.getFieldErrors()) {
        errors.put(error.getField(), error.getDefaultMessage() + '\n');
      }
      //return ResponseEntity.badRequest().body(errorMessage);
      throw new NotValidException(errors.toString());
    }
      return ResponseEntity.ok(authService.register(registerRequestDto));
  }
}
