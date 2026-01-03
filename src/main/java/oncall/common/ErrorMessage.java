package oncall.common;

public enum ErrorMessage {
    INVALID_NICKNAME("닉네임은 1~5글자이어야 합니다. (%s)", true),
    NICKNAME_DUPLICATED("닉네임이 중복되었습니다."),
    INVALID_NUMBER_OF_PEOPLE("인원 수는 5~35명이어야 합니다.");

    private final String message;
    private final boolean isFormatted;

    private ErrorMessage(String message) {
        this.message = message;
        this.isFormatted = false;
    }

    private ErrorMessage(String message, boolean isFormatted) {
        this.message = message;
        this.isFormatted = isFormatted;
    }

    public String getMessage() {
        return this.message;
    }

    public String getMessage(String... arguments) {
        return this.isFormatted ? String.format(this.message, arguments) : this.message;
    }
}