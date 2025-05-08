package org.acme;


import java.util.List;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record Person(@NotNull String firstName, @NotNull String lastName, @NotNull @Valid List<Address> address, String middleName) {

}
