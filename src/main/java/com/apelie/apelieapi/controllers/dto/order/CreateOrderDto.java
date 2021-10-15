package com.apelie.apelieapi.controllers.dto.order;

import com.apelie.apelieapi.models.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class CreateOrderDto {
    @NotNull(message = "Payment method cannot be null")
    private PaymentMethod paymentMethod;

    @NotNull(message = "Address cannot be null")
    private Long addressId;
}
