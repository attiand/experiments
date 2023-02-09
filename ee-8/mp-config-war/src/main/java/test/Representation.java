package test;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

public class Representation {

    private String message;

    private LocalDate interruptDate;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getInterruptDate() {
        return interruptDate;
    }

    public void setInterruptDate(LocalDate interruptDate) {
        this.interruptDate = interruptDate;
    }

}
