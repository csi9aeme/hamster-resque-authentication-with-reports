package hamsterresqueauth.enums;

import lombok.Getter;

@Getter
public enum Role {

    USER, ADMIN, GUEST;

    private String role;
}
