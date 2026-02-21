package com.blackrock.micro.investments.micro_investments.service;

import com.blackrock.micro.investments.micro_investments.model.EnrichedExpenses;
import com.blackrock.micro.investments.micro_investments.model.Expenses;
import com.blackrock.micro.investments.micro_investments.model.ExpensesObj;
import com.blackrock.micro.investments.micro_investments.model.Metrics;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class InvestmentsImpl implements Investments {
    @Override
    public List<EnrichedExpenses> calculateCeilingAndRemanent(List<ExpensesObj> expensesObjList) {
        return expensesObjList.stream().map(this::calculateEnrichedResponse).toList();
    }

    @Override
    public Expenses validateCeilingAndRemanent(List<ExpensesObj> expensesObjList) {
        return validateAmountsValid(expensesObjList);
    }

    @Override
    public Metrics getSystemMetrics() {
        Metrics metrics = new Metrics();
        Runtime runtime = Runtime.getRuntime();
        ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
        metrics.setMemory(String.valueOf((runtime.totalMemory() - runtime.freeMemory()) / (1024 * 1024)));
        metrics.setTime(LocalDateTime.now());
        metrics.setThread(threadBean.getThreadCount());
        return metrics;
    }

    private EnrichedExpenses calculateEnrichedResponse(ExpensesObj expensesObj) {
        EnrichedExpenses enrichedExpenses = new EnrichedExpenses();
        enrichedExpenses.setDate(expensesObj.getDate());
        enrichedExpenses.setAmount(expensesObj.getAmount());
        Double ceilingValue = Math.ceil(expensesObj.getAmount() / 100.0) * 100;
        enrichedExpenses.setCeiling(ceilingValue);
        enrichedExpenses.setRemanent(ceilingValue - expensesObj.getAmount());
        return enrichedExpenses;
    }

    private Expenses validateAmountsValid(List<ExpensesObj> expensesObjList) {
        List<EnrichedExpenses> valid = new ArrayList<>();
        List<EnrichedExpenses> invalid = new ArrayList<>();
        Set<LocalDateTime> frequencyCount = new HashSet<>();
        Expenses expenses = new Expenses();
        expensesObjList.forEach(expensesObj -> {
            boolean isDuplicate = frequencyCount.add(expensesObj.getDate());
            if (expensesObj.getAmount() < 0 && isDuplicate) {
                invalid.add(populateEnrichedResponse(expensesObj, "Amount cannot be Negative"));
            } else {
                valid.add(populateEnrichedResponse(expensesObj, null));
            }
            if (!isDuplicate) {
                invalid.add(populateEnrichedResponse(expensesObj, "Duplicate transaction"));
            }
        });
        expenses.setValid(valid);
        expenses.setInvalid(invalid);
        return expenses;
    }

    private EnrichedExpenses populateEnrichedResponse(ExpensesObj expensesObj, String message) {
        EnrichedExpenses enrichedExpenses = new EnrichedExpenses();
        enrichedExpenses.setDate(expensesObj.getDate());
        if (StringUtils.isNotBlank(message)) {
            enrichedExpenses.setMessage(message);
        }
        enrichedExpenses.setAmount(expensesObj.getAmount());
        enrichedExpenses.setCeiling(expensesObj.getCeiling());
        enrichedExpenses.setRemanent(expensesObj.getRemanent());
        return enrichedExpenses;
    }


}
