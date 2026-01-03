package oncall.vo.schedule;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.DayOfWeek;
import oncall.vo.date.Date;
import oncall.vo.order.Orders;
import org.junit.jupiter.api.Test;

class ScheduleTest {

    @Test
    void from() {
        Orders orders = Orders.from("A,B,C,D,E", "C,D,B,E,A");
        Schedule schedule = Schedule.from(new Date(10, 1, DayOfWeek.MONDAY), orders);
        assertThat(schedule.nicknames().toString()).isEqualTo(
                "[A, B, C, D, C, D, B, E, A, B, A, C, E, C, D, E, A, B, C, D, B, D, E, A, B, C, E, A, D, E, A]");
    }
}