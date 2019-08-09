package ru.abr.dit.Enums;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class EnumCountryConverter implements AttributeConverter<EnumCountry, String> {

    @Override
    public String convertToDatabaseColumn(EnumCountry attribute) {
        if (attribute == null) {
            throw new IllegalArgumentException("Country = null");
        }
        return attribute.getDbValue();
    }

    @Override
    public EnumCountry convertToEntityAttribute(String dbData) {
        for (EnumCountry kind : EnumCountry.values()) {
            if (kind.getDbValue().equals(dbData)) return kind;
        }

        throw new IllegalArgumentException("Unable to map value " + dbData + " to Country");
    }

}
