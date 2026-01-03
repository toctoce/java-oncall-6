package oncall.vo;

import java.time.DayOfWeek;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class DateTest {

    @Test
    void nextDate() {
        Date date1 = new Date(10, 1, DayOfWeek.MONDAY, false);
        Date date2 = date1.nextDate();
        Date date3 = date2.nextDate();

        Assertions.assertThat(date2.month()).isEqualTo(10);
        Assertions.assertThat(date2.dayOfMonth()).isEqualTo(2);
        Assertions.assertThat(date2.dayOfWeek()).isEqualTo(DayOfWeek.TUESDAY);
        Assertions.assertThat(date2.isHoliday()).isFalse();

        Assertions.assertThat(date3.month()).isEqualTo(10);
        Assertions.assertThat(date3.dayOfMonth()).isEqualTo(3);
        Assertions.assertThat(date3.dayOfWeek()).isEqualTo(DayOfWeek.WEDNESDAY);
        Assertions.assertThat(date3.isHoliday()).isTrue();
    }
}