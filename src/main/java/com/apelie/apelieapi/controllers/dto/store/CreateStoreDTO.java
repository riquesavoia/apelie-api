package com.apelie.apelieapi.controllers.dto.store;

import com.apelie.apelieapi.controllers.validators.EncodedImageFileConstraint;
import com.apelie.apelieapi.models.enums.StoreCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CreateStoreDTO {
    @Size(max=15, message = "Twitter account must have at most 15 characters")
    private String twitterAccount;

    @NotEmpty(message = "Store must have at least one category.")
    private List<StoreCategory> categories;

    @Size(max=30, message = "Twitter account must have at most 30 characters")
    private String instagramAccount;

    @Size(max=2, message = "State must have at most 2 characters")
    private String state;

    @Size(max=50, message = "Twitter account must have at most 50 characters")
    private String facebookAccount;

    @Size(max=20, message = "Twitter account must have at most 20 characters")
    private String youtubeAccount;

    @EncodedImageFileConstraint
    private String bannerImage;

    @EncodedImageFileConstraint
    private String logoImage;

    @Size(max=20, message = "Twitter account must have at most 20 characters")

    @Pattern(regexp="^#(?:[0-9a-fA-F]{3}){1,2}$", message="Primary color is invalid")
    private String primaryColor;

    @Pattern(regexp="^#(?:[0-9a-fA-F]{3}){1,2}$", message="Secondary color is invalid")
    private String secondaryColor;

    @Size(max=50, message = "Street must have at most 50 characters")
    private String street;

    @Size(max=50, message = "City must have at most 50 characters")
    private String city;

    @Size(max=12, message = "CEP must have at most 12 characters")
    private String cep;

    @Size(max=30, message = "Name must have at most 30 characters")
    @Size(min=4, message = "Name must have at least 4 characters")
    private String name;

    @Email(message = "Email is invalid")
    private String email;

    @Size(max=13, message = "Phone must have at most 13 characters")
    private String phone;

    @Size(max=10, message = "Address number must have at most 10 characters")
    private String addressNumber;

    @Size(max=50, message = "Neighbourhood must have at most 10 characters")
    private String neighbourhood;

    @Size(max=400, message = "Description must have at most 400 characters")
    private String description;
}
