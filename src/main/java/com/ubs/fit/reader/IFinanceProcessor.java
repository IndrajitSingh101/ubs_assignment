package com.ubs.fit.reader;

import com.ubs.fit.model.CompanyFinancialDTO;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IFinanceProcessor {
    // mapping all currency value with respect to USD
    default  Map<String,Double> getCurrencyValueMapping(){
        Map<String,Double> currencyValueMap=new HashMap<>();
        currencyValueMap.put("GBP",1.654);
        currencyValueMap.put("CHF",1.10);
        currencyValueMap.put("EUR",1.35);
        return currencyValueMap;
    }
    List<CompanyFinancialDTO> getCompanyDetailsList(String Path);
    Map<String,Map<String, BigDecimal>> getGroupingByCountryAndRating(List<CompanyFinancialDTO> companyDetailsList);
}
