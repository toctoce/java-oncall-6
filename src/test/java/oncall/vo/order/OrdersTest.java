package oncall.vo.order;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class OrdersTest {

    @Test
    void from() {
        Order workday = Order.from("봉구스,찹쌀,킹콩,킹킹콩,킹킹킹콩");
        Order dayOff = Order.from("찹쌀,봉구스,킹킹콩,킹콩,킹킹킹콩");
        Orders orders = Orders.from(workday, dayOff);
        assertThat(orders.workday()).isEqualTo(workday);
        assertThat(orders.dayOff()).isEqualTo(dayOff);
    }
}