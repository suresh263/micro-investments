package com.blackrock.micro.investments.micro_investments.controllers;

import com.blackrock.micro.investments.micro_investments.model.*;
import com.blackrock.micro.investments.micro_investments.service.Investments;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class MicroInvestments {

    private final Investments investments;

    @PostMapping("/blackrock/challenge/v1/transactions:parse")
    public ResponseEntity<List<EnrichedExpenses>> calculateCeilingAndRemanent(@RequestBody List<ExpensesObj> expensesObjList) {

        return new ResponseEntity<>(investments.calculateCeilingAndRemanent(expensesObjList), HttpStatus.OK);
    }

    @PostMapping("blackrock/challenge/v1/transactions:validator")
    public ResponseEntity<Expenses> validateCeilingAndRemanent(@RequestBody validateAmounts expensesObjList) {

        return new ResponseEntity<>(investments.validateCeilingAndRemanent(expensesObjList.getTransactions()), HttpStatus.OK);

    }

    @GetMapping("/blackrock/challenge/v1/performance")
    public ResponseEntity<Metrics> validateCeilingAndRemanent() {

        return new ResponseEntity<>(investments.getSystemMetrics(), HttpStatus.OK);

    }

}
