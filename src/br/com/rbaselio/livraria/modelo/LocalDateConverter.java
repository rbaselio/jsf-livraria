package br.com.rbaselio.livraria.modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "localDateConverter")
public class LocalDateConverter implements Converter<LocalDate> {

	@Override
	public LocalDate getAsObject(FacesContext context, UIComponent component, String value) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate localDate = LocalDate.parse(value, formatter);
		System.out.println("LocalDateConverter - Return Object: " + localDate);
		return localDate;

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, LocalDate value) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String s = value.format(formatter);
		System.out.println("LocalDateConverter - Return String: " + s);
		return s;
	}

}
