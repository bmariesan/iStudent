package ro.ubb.config;

import ro.ubb.dummy_data.DummyDataProvider;
import ro.ubb.dummy_data.DummyDataReader;
import ro.ubb.utils.DateFormatterUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

@Configuration
public class UtilBeans {

    @Bean
    public SimpleDateFormat initSimpleDateFormat() {
        return new SimpleDateFormat("dd/MM/yyyy");
    }

    @Bean
    public DateFormatterUtil initDateUtil() {
        return new DateFormatterUtil();
    }

    @Bean
    public DummyDataProvider initDummyDataProvider() {
        return new DummyDataReader();
    }

}
