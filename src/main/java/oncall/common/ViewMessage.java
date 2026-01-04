package oncall.common;

public enum ViewMessage {
    MONTH_AND_DAY_OF_WEEK("비상 근무를 배정할 월과 시작 요일을 입력하세요>"),
    WORKDAY_ORDER("평일 비상 근무 순번대로 사원 닉네임을 입력하세요>"),
    DAY_OFF_ORDER("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요>");

    private final String message;

    private ViewMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}