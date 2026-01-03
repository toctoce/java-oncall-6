package oncall.vo.schedule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import oncall.vo.date.Date;
import oncall.vo.order.Orders;

public record Schedule(List<Date> dates, List<String> nicknames) {

    public static Schedule from(Date firstDate, Orders orders) {
        List<Date> dates = createDates(firstDate);
        List<String> nicknames = createNicknames(dates, orders);
        return new Schedule(dates, nicknames);
    }

    private static List<String> createNicknames(List<Date> dates, Orders orders) {
        List<String> nicknames = initNicknames(dates, orders);
        nicknames = rearrange(dates, nicknames, orders);
        return nicknames;
    }

    private static List<String> rearrange(List<Date> dates, List<String> nicknames, Orders orders) {
        for (int index = 0; index < dates.size() - 1; index++) {
            String currentNickname = nicknames.get(index);
            String nextNickname = nicknames.get(index + 1);

            if (currentNickname.equals(nextNickname)) {
                int nextDateIndex = getNextDateIndex(dates, index + 1);
                if (nextDateIndex != 0) {
                    Collections.swap(nicknames, index + 1, nextDateIndex);
                    continue;
                }
                if (dates.get(index + 1).isDayOff()) {
                    nicknames.set(index + 1, orders.dayOff().next(nextNickname));
                }
                if (!dates.get(index + 1).isDayOff()) {
                    nicknames.set(index + 1, orders.workday().next(nextNickname));
                }
            }
        }
        return nicknames;
    }

    private static int getNextDateIndex(List<Date> dates, int index) {
        Date curDate = dates.get(index);
        Date nextDate = dates.stream()
                .filter(date -> (date.dayOfMonth() > curDate.dayOfMonth()) && (date.isDayOff() == curDate.isDayOff()))
                .findFirst()
                .orElse(null);
        if (nextDate == null) {
            return 0;
        }
        return dates.indexOf(nextDate);
    }

    private static List<String> initNicknames(List<Date> dates, Orders orders) {
        List<String> nicknames = new ArrayList<>();
        int weekdayIndex = 0;
        int dayOffIndex = 0;
        int size = orders.size();
        for (Date date : dates) {
            if (date.isDayOff()) {
                nicknames.add(orders.dayOff().get(dayOffIndex));
                dayOffIndex = (dayOffIndex + 1) % size;
                continue;
            }
            nicknames.add(orders.workday().get(weekdayIndex));
            weekdayIndex = (weekdayIndex + 1) % size;
        }

        return nicknames;
    }

    private static List<Date> createDates(Date firstDate) {
        List<Date> dates = new ArrayList<>();
        dates.add(firstDate);
        Date nextDate = firstDate;
        while ((nextDate = nextDate.nextDate()) != null) {
            dates.add(nextDate);
        }
        return dates;
    }
}
