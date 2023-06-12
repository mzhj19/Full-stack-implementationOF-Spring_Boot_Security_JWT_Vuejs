package com.ZahidHasanJamil.SBproject.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {

  @Email(message = "Enter a valid email.")
  @NotNull
  private String email;

  @NotNull
  private String password;
}
