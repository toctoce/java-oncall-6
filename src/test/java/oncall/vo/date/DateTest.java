package oncall.vo.date;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.DayOfWeek;
import org.junit.jupiter.api.Test;

class DateTest {

    @Test
    void nextDate() {
        Date date1 = new Date(10, 1, DayOfWeek.MONDAY, false);
        Date date2 = date1.nextDate();
        Date date3 = date2.nextDate();

        assertThat(date2.month()).isEqualTo(10);
        assertThat(date2.dayOfMonth()).isEqualTo(2);
        assertThat(date2.dayOfWeek()).isEqualTo(DayOfWeek.TUESDAY);
        assertThat(date2.isHoliday()).isFalse();

        assertThat(date3.month()).isEqualTo(10);
        assertThat(date3.dayOfMonth()).isEqualTo(3);
        assertThat(date3.dayOfWeek()).isEqualTo(DayOfWeek.WEDNESDAY);
        assertThat(date3.isHoliday()).isTrue();
    }

    @Test
    void testToString() {
        Date date1 = new Date(10, 1, DayOfWeek.MONDAY, false);
        assertThat(date1.toString()).isEqualTo("10월 1일 월");
    }
}