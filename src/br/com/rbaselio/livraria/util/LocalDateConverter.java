package br.com.rbaselio.livraria.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "localDateConverter")
public class LocalDateConverter implements Converter {

	public LocalDate getAsObject(FacesContext context, UIComponent component, String value) {

		return LocalDate.parse(value, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

	public String getAsString(FacesContext context, UIComponent component, Object value) {
		LocalDate ld = (LocalDate) value;

		return ld.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

	

}
