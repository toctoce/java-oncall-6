package oncall.vo.order;

import static oncall.common.ErrorMessage.NICKNAME_DIFFERENT;

import oncall.common.OncallException;

public record Orders(Order workday, Order dayOff) {

    public static Orders from(String workdayInput, String dayOffInput) {
        Order workday = Order.from(workdayInput);
        Order dayOff = Order.from(dayOffInput);

        if (!workday.equals(dayOff)) {
            throw new OncallException(NICKNAME_DIFFERENT.getMessage());
        }
        return new Orders(workday, dayOff);
    }
}
