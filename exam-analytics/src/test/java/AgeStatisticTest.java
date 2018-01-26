/**
 * Created by Carmen on 26-Jan-18.
 */
import exams.domain.statistics.AgeStatistic;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import exams.service.Service;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;



@RunWith(MockitoJUnitRunner.class)
public class AgeStatisticTest {
    @Mock
    Service service;

    @InjectMocks
    AgeStatistic ageStatistic;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void generateStatisticTest() {
        List statistics = ageStatistic.getData();
        assertThat(statistics, is(notNullValue()));

    }

}
