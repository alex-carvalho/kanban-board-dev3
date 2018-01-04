package br.unisinos.kanban.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Base64;

/**
 * @author Alex Carvalho
 */
@Converter
public class PasswordEncryptConverter implements AttributeConverter<String, String> {
    @Override
    public String convertToDatabaseColumn(String attribute) {
        return Base64.getEncoder().encodeToString(attribute.getBytes());
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return new String(Base64.getDecoder().decode(dbData));
    }
}
