package com.ubs.fit.reader;

import com.ubs.fit.model.CompanyFinancialDTO;
import com.ubs.fit.util.FileReaderUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;


public class FinanceProcessor implements IFinanceProcessor {

    @Override
    public List<CompanyFinancialDTO> getCompanyDetailsList(String path) {
        List<CompanyFinancialDTO> companyDetailsList=new ArrayList<CompanyFinancialDTO>();
        try  {
            companyDetailsList= FileReaderUtil.getStreamOfData(path).get().skip(1).map(line->line.split("\t")).map(financialData->CompanyFinancialDTO.builder()
                    .companyCode(financialData[0])
                    .account(financialData[1])
                    .city(financialData[2])
                    .country(financialData[3].trim().isEmpty()?financialData[2]:financialData[3]) // if country name is empty replace it with city name
                    .creditRating(financialData[4])
                    .currency(financialData[5])
                    .amount(getConvertedValueInCurrency(new BigDecimal(financialData[6]),financialData[5],"EUR").setScale(2, BigDecimal.ROUND_HALF_EVEN))
                            //!financialData[6].isEmpty()?getConvertedValueinCurrency(new BigDecimal(financialData[6]),financialData[5],"EUR"):BigDecimal.ZERO)
                    .build()).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return companyDetailsList;
    }

    // group by country/Rating return average amount
    @Override
    public Map<String, Map<String, BigDecimal>> getGroupingByCountryAndRating(List<CompanyFinancialDTO> companyDetailsList) {
        Map<String, Map<String, BigDecimal>> map = companyDetailsList.stream()
                .collect(Collectors.groupingBy(CompanyFinancialDTO::getCountry,
                        Collectors.groupingBy(CompanyFinancialDTO::getCreditRating,Collectors.mapping(CompanyFinancialDTO::getAmount, Collector.of(IntermediateResult::new, IntermediateResult::add, IntermediateResult::combine, IntermediateResult::finish)))));
        return map;
    }

    //converting to standardised currency which in this case is Euro
    private BigDecimal getConvertedValueInCurrency(BigDecimal value,String currency,String standardisedCurrency) {
        Double conversionFactor=1/getCurrencyValueMapping().get(standardisedCurrency);
        return value.multiply(new BigDecimal(getCurrencyValueMapping().get(currency))).multiply(new BigDecimal(conversionFactor));
    }


}
