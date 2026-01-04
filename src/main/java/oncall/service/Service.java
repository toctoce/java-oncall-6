package oncall.service;

import oncall.dto.ScheduleDto;
import oncall.vo.date.Date;
import oncall.vo.order.Orders;
import oncall.vo.schedule.Schedule;

public class Service {

    public ScheduleDto createSchedule(Date firstDate, Orders orders) {
        Schedule schedule = Schedule.from(firstDate, orders);
        return ScheduleDto.from(schedule);
    }
}
