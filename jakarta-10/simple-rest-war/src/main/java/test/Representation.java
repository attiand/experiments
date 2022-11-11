package test;

import java.time.LocalDate;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Representation {

	@XmlElement(name = "msg")
	private String message;

	@XmlElement
	@XmlJavaTypeAdapter(BasicIsoLocalDateAdapter.class)
	private LocalDate interruptDate;

	public LocalDate getInterruptDate() {
		return interruptDate;
	}

	public void setInterruptDate(LocalDate interruptDate) {
		this.interruptDate = interruptDate;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
