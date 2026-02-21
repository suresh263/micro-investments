package com.blackrock.micro.investments.micro_investments.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnrichedExpenses extends ExpensesObj {
    private Double ceiling;
    private Double remanent;
    private String message;
}
