package com.blackrock.micro.investments.micro_investments.model;

import lombok.Data;

import java.util.List;

@Data
public class Expenses {
    List<EnrichedExpenses> valid;
    List<EnrichedExpenses> invalid;
}
