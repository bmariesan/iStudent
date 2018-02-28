package ro.ubb.istudent.domain;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.data.mongodb.core.mapping.Document;
import ro.ubb.istudent.dto.Dto;

import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by usr on 22.11.2017.
 */

public class Report implements Dto {

    private ArrayList<Statistics> statistics;
    private String title;


    public Report(ArrayList<Statistics> statistics, String title) {
        this.statistics = statistics;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Report(){
        this.statistics = new ArrayList<Statistics>();
        this.title = "";

    }

    public ArrayList<Statistics> getStatistics() {
        return statistics;
    }

    public void setStatistics(ArrayList<Statistics> statistics) {
        this.statistics = statistics;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Report report = (Report) o;

        return statistics.equals(report.statistics);
    }

    @Override
    public int hashCode() {
        return statistics.hashCode();
    }

    public String ToHTML(){
        String s = "<h3>" + this.title + "</h3><ul>";
        for (Statistics entry : this.statistics) {
           s+= "<li" + entry.ToHTML() + "</li>";
        }
        s+="</ul><br/>";
        return s;
    }

    public  void generatePDF() {
        try {
            com.itextpdf.text.Document document = new com.itextpdf.text.Document();
            PdfWriter.getInstance(document, new FileOutputStream("C:/Bogdan/raport.pdf"));
            document.open();
            String reportToString = this.toPDF();
            int index = 0;
            for (String line: reportToString.split("\n")
                    ) {
                if(index == 0){
                    Paragraph paragraph = new Paragraph(line);
                    paragraph.setAlignment(Element.ALIGN_CENTER);
                    Font font = new Font();
                    font.setColor(BaseColor.CYAN);
                    font.setSize(24);
                    paragraph.setFont(font);
                    document.add(paragraph);
                    index = 1;
                }else if(index == 1){
                    Paragraph paragraph = new Paragraph(line);
                    paragraph.setAlignment(Element.ALIGN_CENTER);
                    document.add(paragraph);
                    index = 2;
                }
                else{
                    Paragraph paragraph = new Paragraph(line);
                    paragraph.setAlignment(Element.ALIGN_LEFT);
                    Font font = new Font();
                    font.setColor(BaseColor.MAGENTA);
                    font.setSize(15);
                    paragraph.setFont(font);
                    document.add(paragraph);
                }
            }
            document.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private String toPDF(){
        String s = "Report\n";
        for (Statistics entry : this.statistics) {
            s+=entry.toPDF();
        }
        return s;
    }
}
