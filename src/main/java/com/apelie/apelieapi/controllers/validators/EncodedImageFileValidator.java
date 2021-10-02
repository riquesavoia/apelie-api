package com.apelie.apelieapi.controllers.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EncodedImageFileValidator implements ConstraintValidator<EncodedImageFileConstraint, String> {

    @Override
    public void initialize(EncodedImageFileConstraint encodedFile) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        Long fileSize = (long)value.length() * (3/4);
        if (fileSize > (5 * 1048576)) {
            return false;
        }

        String[] encodedParts = value.split(",");
        Pattern pattern = Pattern.compile("(?<=/).*(?=;)");
        Matcher matcher = pattern.matcher(encodedParts[0]);
        if (!matcher.find()) {
            return false;
        }

        String imageType = matcher.group(0);

        List<String> acceptedTypes = Arrays.asList("png", "jpeg");

        if (!acceptedTypes.contains(imageType)) {
            return false;
        }

        return true;
    }
}
