package com.ubs.fit.reader;

import java.math.BigDecimal;

public class IntermediateResult {
    private int count = 0;
    private BigDecimal sum = BigDecimal.ZERO;

    IntermediateResult() {
    }

    void add(BigDecimal value) {
        this.sum = this.sum.add(value);
        this.count++;
    }

    IntermediateResult combine(IntermediateResult r) {
        this.sum = this.sum.add(r.sum);
        this.count += r.count;
        return this;
    }

    BigDecimal finish() {
        return sum.divide(BigDecimal.valueOf(count), 2, BigDecimal.ROUND_HALF_UP);
    }
}
