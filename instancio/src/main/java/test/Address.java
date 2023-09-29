package test;

import jakarta.validation.constraints.Positive;

public record Address(String street, @Positive int number, String areaCode) {
}
