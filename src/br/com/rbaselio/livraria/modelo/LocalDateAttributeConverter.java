package br.com.rbaselio.livraria.modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, Date> {
	
	@Override
    public Date convertToDatabaseColumn(LocalDate locDate) {
		System.out.println("LocalDate " + locDate);
    	try {
			return (locDate == null ? null : new SimpleDateFormat("yyyy-MM-dd").parse(locDate.toString()));
		} catch (ParseException e) {			
			e.printStackTrace();
			return null;
		}
    }

    @Override
    public LocalDate convertToEntityAttribute(Date date) {
    	System.out.println("Date " + date);
    	return (date == null ? null : Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
    }
}