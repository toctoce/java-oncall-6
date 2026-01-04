package oncall.vo.date;

import static oncall.common.ErrorMessage.INVALID_INPUT;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Pattern;
import oncall.common.OncallException;

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

    public static Date from(String input) {
        validate(input);

        String[] split = input.split(",");
        int month = Integer.parseInt(split[0]);
        String dayOfWeek = split[1];

        return Date.from(month, dayOfWeek);
    }

    private static void validate(String input) {
        validateFormat(input);

        String[] split = input.split(",");
        int month = Integer.parseInt(split[0]);
        validateMonth(month);

        String dayOfWeek = split[1];
        validateDayOfWeek(dayOfWeek);
    }

    private static void validateDayOfWeek(String dayOfWeek) {
        List<String> dayOfWeeks = List.of("월", "화", "수", "목", "금", "토", "일");
        if (!List.of("월", "화", "수", "목", "금", "토", "일").contains(dayOfWeek)) {
            throw new OncallException("올바른 요일을 입력하세요.");
        }
    }

    private static void validateMonth(int month) {
        if (month < 1 || month > 12) {
            throw new OncallException("올바른 월을 입력하세요.");
        }
    }


    private static void validateFormat(String input) {
        if (!Pattern.matches("[0-9]{1,2},[ㄱ-ㅎ가-힣]{1}", input)) {
            throw new OncallException(INVALID_INPUT.getMessage());
        }
    }

    private static Date from(int month, String dayOfWeek) {
        List<String> dayOfWeeks = List.of("", "월", "화", "수", "목", "금", "토", "일");
        int dayOfWeekIndex = dayOfWeeks.indexOf(dayOfWeek);

        return new Date(month, 1, DayOfWeek.of(dayOfWeekIndex));
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
        String str = String.format("%d월 %d일 %s", month, dayOfMonth,
                dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.KOREAN));
        if (dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY && isHoliday) {
            str += "(휴일)";
        }
        return str;
    }
}
