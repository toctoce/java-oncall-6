package oncall.vo.order;

import static oncall.common.ErrorMessage.NICKNAME_DIFFERENT;
import static oncall.common.ErrorMessage.SIZE_DIFFERENT;

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

    public int size() {
        if (workday.size() != dayOff.size()) {
            throw new OncallException(SIZE_DIFFERENT.getMessage());
        }
        return workday.size();
    }
}
