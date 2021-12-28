package com.jpmc.data.customervalidator.model;

import lombok.*;

import java.util.List;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    List<AccountResponse> result;
}
