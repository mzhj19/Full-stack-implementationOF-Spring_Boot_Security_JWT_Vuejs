package com.ZahidHasanJamil.SBproject.model;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {

  @NotNull(message = "Give your real first name")
  private String firstName;

  @NotNull(message = "Give your real last name")
  private String lastName;

  @NotEmpty
  @Email(message = "Give a valid email")
  private String email;

  @NotEmpty(message = "Give a more secure password.")
  @Size(min = 5, max = 30)
  private String password;

  @NotEmpty(message = "Give a valid mobile number.")
  private String mobile;
}
