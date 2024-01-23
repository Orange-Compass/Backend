package OrangeCompass.OrangeCompass.user.domain;

import lombok.Getter;

@Getter
public enum UserStatus {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE");

    private final String status;

    UserStatus(String status) {
        this.status = status;
    }
}
