package ro.ubb.istudent.service;

import com.mongodb.util.JSON;
import ro.ubb.istudent.domain.Report;

public class ReportService  implements  IReportService{
    @Override
    public JSON generateJSONReport(Report raport) {
        return (JSON) JSON.parse(raport.toString());
    }

    @Override
    public void generatePDFReport(Report raport, String file) {
        return;
    }

    @Override
    public String generateHTMLReport(Report report) {
        return null;
    }
}
