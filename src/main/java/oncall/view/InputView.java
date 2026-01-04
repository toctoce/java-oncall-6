package oncall.view;

import camp.nextstep.edu.missionutils.Console;
import oncall.vo.date.Date;
import oncall.vo.order.Order;

public class InputView {

    public String read() {
        return Console.readLine();
    }

    public Date readDate() {
        return Date.from(read());
    }

    public Order readOrder() {
        return Order.from(read());
    }
}
