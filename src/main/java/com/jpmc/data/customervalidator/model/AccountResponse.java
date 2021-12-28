package com.jpmc.data.customervalidator.model;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.io.Serial;
import java.io.Serializable;

@Data
@ToString
@Validated
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = -5712903941477845620L;
    private String provider;
    private boolean isValid;
}
