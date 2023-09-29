package test;

import javax.validation.constraints.NotNull;

public record Person(@NotNull String firstName) {

}
