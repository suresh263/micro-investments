package com.blackrock.micro.investments.micro_investments.service;

import com.blackrock.micro.investments.micro_investments.model.EnrichedExpenses;
import com.blackrock.micro.investments.micro_investments.model.Expenses;
import com.blackrock.micro.investments.micro_investments.model.ExpensesObj;
import com.blackrock.micro.investments.micro_investments.model.Metrics;

import java.util.List;

public interface Investments {

    public List<EnrichedExpenses> calculateCeilingAndRemanent(List<ExpensesObj> expensesObjList);

    public Expenses validateCeilingAndRemanent(List<ExpensesObj> expensesObjList);

    public Metrics getSystemMetrics();
}
