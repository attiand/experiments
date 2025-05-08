package org.acme;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.validation.constraints.NotBlank;
import jdk.jfr.Registered;

@RegisterForReflection
public record Message(@NotBlank(message="message may not be blank") String message) {

}
