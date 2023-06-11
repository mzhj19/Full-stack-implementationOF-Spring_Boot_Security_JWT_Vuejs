package com.ZahidHasanJamil.SBproject.controller;

import com.ZahidHasanJamil.SBproject.exception.NotValidException;
import com.ZahidHasanJamil.SBproject.model.AuthResponseDto;
import com.ZahidHasanJamil.SBproject.model.LoginRequestDto;
import com.ZahidHasanJamil.SBproject.model.RegisterRequestDto;
import com.ZahidHasanJamil.SBproject.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    if(bindingResult.hasErrors()) {
      throw new NotValidException("Email in required. Please give a valid email.");
    }
      return ResponseEntity.ok(authService.register(registerRequestDto));
  }
}
