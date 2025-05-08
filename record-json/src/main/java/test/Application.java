package test;

import java.time.LocalDate;

public record Application(LocalDate startDate, String name, MyTest test) {
}
