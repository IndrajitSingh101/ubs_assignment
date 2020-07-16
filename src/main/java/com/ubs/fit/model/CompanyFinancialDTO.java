package com.ubs.fit.model;

import lombok.*;

import java.math.BigDecimal;

@Builder
@Data
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CompanyFinancialDTO {
    private String companyCode;
    private String account;
    private String city;
    private String country;
    private String creditRating;
    private String currency;
    private BigDecimal amount;
}
