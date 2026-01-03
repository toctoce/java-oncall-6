package oncall.vo.date;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public record Date(int month, int dayOfMonth, DayOfWeek dayOfWeek, boolean isHoliday) {
    static final int[] MAX_DAY_OF_MONTH = new int[]{0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    static final Set<List<Integer>> HOLIDAYS = Set.of(
            List.of(1, 1),
            List.of(3, 1),
            List.of(5, 5),
            List.of(6, 6),
            List.of(8, 15),
            List.of(10, 3),
            List.of(10, 9),
            List.of(12, 25)
    );

    public Date(int month, int dayOfMonth, DayOfWeek dayOfWeek) {
        this(month, dayOfMonth, dayOfWeek, HOLIDAYS.contains(List.of(month, dayOfMonth)));
    }

    public Date nextDate() {
        if (dayOfMonth == MAX_DAY_OF_MONTH[month]) {
            return null;
        }
        return new Date(month, dayOfMonth + 1, dayOfWeek.plus(1));
    }

    public boolean isDayOff() {
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY || isHoliday;
    }

    @Override
    public String toString() {
        return String.format("%d월 %d일 %s", month, dayOfMonth, dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.KOREAN));
    }
}
