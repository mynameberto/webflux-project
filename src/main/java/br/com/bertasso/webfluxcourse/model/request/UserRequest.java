package br.com.bertasso.webfluxcourse.model.request;

import br.com.bertasso.webfluxcourse.validator.TrimString;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(
        @TrimString
        @Size(min = 3, max = 75, message = "must be between 3 and 75 chars")
        @NotBlank(message = "must not be null or empty")
        String name,
        @TrimString
        @NotBlank(message = "must not be null or empty")
        @Email(message = "invalid email")
        String email,
        @TrimString
        @Size(min = 3, max = 75, message = "must be between 3 and 75 chars")
        @NotBlank(message = "must not be null or empty")
        String password
) {
}
