package oncall.dto;

import oncall.vo.schedule.Schedule;

public record ScheduleDto(String content) {
    public static ScheduleDto from(Schedule schedule) {
        String content = "";

        for (int i = 0; i < schedule.dates().size(); i++) {
            String line = schedule.dates().get(i).toString() + " " + schedule.nicknames().get(i);
            content += line + "\n";
        }

        return new ScheduleDto(content);
    }
}
