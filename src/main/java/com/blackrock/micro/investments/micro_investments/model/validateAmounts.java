package com.blackrock.micro.investments.micro_investments.model;

import lombok.Data;

import java.util.List;

@Data
public class validateAmounts {
    private Double wage;
    private List<ExpensesObj> transactions;

}
