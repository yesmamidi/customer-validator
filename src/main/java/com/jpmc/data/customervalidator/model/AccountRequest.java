package com.jpmc.data.customervalidator.model;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@ToString
@Validated
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -6954874257188658967L;
    private String accountNumber;
    private List<String> providers;

}
