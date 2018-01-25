package exams.domain.statistics;
import java.util.List;

public interface IStatistic {
    void generateStatistic();
    List<StringTuple> getData();
}
