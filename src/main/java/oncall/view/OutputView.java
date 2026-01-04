package oncall.view;

import static oncall.common.ViewMessage.DAY_OFF_ORDER;
import static oncall.common.ViewMessage.MONTH_AND_DAY_OF_WEEK;
import static oncall.common.ViewMessage.WORKDAY_ORDER;

public class OutputView {

    public void write(String message) {
        System.out.println(message);
    }

    public void writeMonthAndDayOfWeek() {
        write(MONTH_AND_DAY_OF_WEEK.getMessage());
    }

    public void writeWorkdayOrder() {
        write(WORKDAY_ORDER.getMessage());
    }

    public void writeDayOffOrder() {
        write(DAY_OFF_ORDER.getMessage());
    }
}
