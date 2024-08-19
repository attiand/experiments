package org.acme;

import jakarta.validation.constraints.NotBlank;

public record Message(@NotBlank(message="message may not be blank") String message) {

}
