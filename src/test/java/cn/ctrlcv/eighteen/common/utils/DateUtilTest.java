package cn.ctrlcv.eighteen.common.utils;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DateUtilTest {

    @Test
    void testStringToDate1() throws Exception {
        assertThat(DateUtil.stringToDate("dateString"))
                .isEqualTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        assertThatThrownBy(() -> DateUtil.stringToDate("dateString")).isInstanceOf(ParseException.class);
    }

    @Test
    void testStringToDate2() throws Exception {
        assertThat(DateUtil.stringToDate("dateString", "pattern"))
                .isEqualTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        assertThatThrownBy(() -> DateUtil.stringToDate("dateString", "pattern")).isInstanceOf(ParseException.class);
    }

    @Test
    void testDateStringToIntArray() throws Exception {
        assertThat(DateUtil.dateStringToIntArray("dateString")).isEqualTo(new int[]{0});
        assertThat(DateUtil.dateStringToIntArray("dateString")).isEqualTo(new int[]{});
        assertThatThrownBy(() -> DateUtil.dateStringToIntArray("dateString")).isInstanceOf(ParseException.class);
    }

    @Test
    void testGetCurrentDateString() {
        System.out.println(DateUtil.getCurrentDateString("yyyy年MM月dd日"));
    }
}
