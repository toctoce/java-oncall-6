package oncall.controller;

import oncall.common.OncallException;
import oncall.dto.ScheduleDto;
import oncall.service.Service;
import oncall.view.InputView;
import oncall.view.OutputView;
import oncall.vo.date.Date;
import oncall.vo.order.Order;
import oncall.vo.order.Orders;

public class Controller {

    OutputView outputView = new OutputView();
    InputView inputView = new InputView();
    Service service = new Service();

    public void run() {
        Date firstDate = createFirstDate();
        Orders orders = createOrders();

        ScheduleDto dto = service.createSchedule(firstDate, orders);
        outputView.write(dto.content());
    }

    private Orders createOrders() {
        try {
            outputView.writeWorkdayOrder();
            Order workday = inputView.readOrder();

            outputView.writeDayOffOrder();
            Order dayOff = inputView.readOrder();

            return Orders.from(workday, dayOff);
        } catch (OncallException e) {
            outputView.write(e.getMessage());
            return createOrders();
        }
    }

    private Date createFirstDate() {
        try {
            outputView.writeMonthAndDayOfWeek();
            return inputView.readDate();
        } catch (OncallException e) {
            outputView.write(e.getMessage());
            return createFirstDate();
        }
    }
}
