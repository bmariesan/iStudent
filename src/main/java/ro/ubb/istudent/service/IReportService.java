package ro.ubb.istudent.service;

import com.mongodb.util.JSON;
import ro.ubb.istudent.domain.Report;

/**
 * Created by Guramulta Daniel on 11/22/2017.
 */
public interface IReportService {
    JSON generateJSONReport(Report raport);
    void generatePDFReport(Report raport, String file);
    String generateHTMLReport(Report report);
}
