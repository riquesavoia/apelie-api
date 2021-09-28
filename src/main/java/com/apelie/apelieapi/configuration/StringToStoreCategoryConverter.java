package com.apelie.apelieapi.configuration;

import com.apelie.apelieapi.models.enums.StoreCategory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToStoreCategoryConverter implements Converter<String, StoreCategory> {

    @Override
    public StoreCategory convert(String source) {
        return StoreCategory.valueOf(source.toUpperCase());
    }
}
