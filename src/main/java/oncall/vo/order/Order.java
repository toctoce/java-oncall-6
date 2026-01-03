package oncall.vo.order;

import static oncall.common.ErrorMessage.INVALID_NICKNAME;
import static oncall.common.ErrorMessage.INVALID_NUMBER_OF_PEOPLE;
import static oncall.common.ErrorMessage.NICKNAME_DUPLICATED;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import oncall.common.OncallException;

public record Order(List<String> nicknames) {

    public static Order from(String nicknamesInput) {
        List<String> nicknames = Arrays.asList(nicknamesInput.split(","));
        validate(nicknames);
        return new Order(nicknames);
    }

    public String get(int index) {
        return nicknames.get(index);
    }

    public int indexOf(String nickname) {
        return nicknames.indexOf(nickname);
    }

    public String next(String nickname) {
        int curIndex = indexOf(nickname);
        return get((curIndex + 1) % size());
    }

    private static void validate(List<String> nicknames) {
        for (String nickname : nicknames) {
            validateNickname(nickname);
        }
        validateDuplicate(nicknames);
        validateSize(nicknames);

    }

    private static void validateSize(List<String> nicknames) {
        if (nicknames.size() < 5 || nicknames.size() > 35) {
            throw new OncallException(INVALID_NUMBER_OF_PEOPLE.getMessage());
        }
    }

    private static void validateDuplicate(List<String> nicknames) {
        Set<String> nicknameSet = Set.copyOf(nicknames);
        if (nicknameSet.size() != nicknames.size()) {
            throw new OncallException(NICKNAME_DUPLICATED.getMessage());
        }
    }

    private static void validateNickname(String nickname) {
        if (nickname.isEmpty() || nickname.length() > 5) {
            throw new OncallException(INVALID_NICKNAME.getMessage(nickname));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        return Objects.equals(getNicknameSet(), order.getNicknameSet());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nicknames());
    }


    private Set<String> getNicknameSet() {
        return Set.copyOf(nicknames);
    }

    public int size() {
        return nicknames.size();
    }
}
