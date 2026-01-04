package oncall.vo.order;

import static oncall.common.ErrorMessage.NICKNAME_DIFFERENT;
import static oncall.common.ErrorMessage.SIZE_DIFFERENT;

import oncall.common.OncallException;

public record Orders(Order workday, Order dayOff) {

    public static Orders from(Order workday, Order dayOff) {
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
