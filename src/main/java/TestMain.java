import com.ubs.fit.model.CompanyFinancialDTO;
import com.ubs.fit.reader.FinanceProcessor;
import com.ubs.fit.reader.IFinanceProcessor;
import java.util.List;

public class TestMain {
    
    public static void main(String[] args) {
        String path= "src/test/resources/FILE.DAT.txt";
        IFinanceProcessor companyDetailReader=new FinanceProcessor();
        List<CompanyFinancialDTO> companyDetailsList=companyDetailReader.getCompanyDetailsList(path);
        System.out.println(companyDetailReader.getGroupingByCountryAndRating(companyDetailsList));
    }
}