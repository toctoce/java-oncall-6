package oncall.vo.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import oncall.common.OncallException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderTest {

    @Test
    void from() {
        Order order = Order.from("봉구스,찹쌀,킹콩,킹킹콩,킹킹킹콩");
        assertThat(order.nicknames().size()).isEqualTo(5);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "봉구스,찹쌀,킹콩,킹킹콩,킹킹킹킹킹콩",
            "봉구스,봉구스,찹쌀,킹콩,킹킹콩",
            "봉구스,봉봉구스",
            "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36"
    })
    void from_예외(String input) {
        assertThatThrownBy(() -> Order.from(input)).isInstanceOf(OncallException.class);
    }

    @Test
    void testEquals() {
        Order order1 = Order.from("봉구스,찹쌀,킹콩,킹킹콩,킹킹킹콩");
        Order order2 = Order.from("찹쌀,봉구스,킹킹콩,킹콩,킹킹킹콩");
        assertThat(order1).isEqualTo(order2);
    }
}