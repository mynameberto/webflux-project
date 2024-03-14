package br.com.bertasso.webfluxcourse.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(
        @Size(min = 3, max = 75, message = "must be between 3 and 75 chars")
        @NotBlank(message = "must not be null or empty")
        String name,
        @NotBlank(message = "must not be null or empty")
        @Email(message = "invalid email")
        String email,
        @Size(min = 3, max = 75, message = "must be between 3 and 75 chars")
        @NotBlank(message = "must not be null or empty")
        String password
) {
}
