package com.example.veeez.feature.financial;

import java.util.List;

public class FinancialResponse {
    private List<FinancialItem> financialItems;

    public FinancialResponse(List<FinancialItem> items) {
        financialItems = items;
    }

    public List<FinancialItem> getFinancialItems() {
        return financialItems;
    }
}
