package com.ZahidHasanJamil.SBproject.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {
  private String firstName;
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
