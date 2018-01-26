package ro.ubb.utils;

import ro.ubb.model.AverageDto;

import java.util.Comparator;

public class SortByDateComparator implements Comparator<AverageDto> {

    @Override
    public int compare(AverageDto o1, AverageDto o2) {
        if (o1.getDate().getTime() < o2.getDate().getTime()) {
            return -1;
        } else if (o1.getDate().getTime() > o2.getDate().getTime()) {
            return 1;
        }
        return 0;
    }
}
