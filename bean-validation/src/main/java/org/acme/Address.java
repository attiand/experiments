package org.acme;

import jakarta.validation.constraints.NotNull;

public record Address(@NotNull String street, String country) {

}
