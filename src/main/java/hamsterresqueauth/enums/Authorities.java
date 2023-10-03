package hamsterresqueauth.enums;

import lombok.Getter;

@Getter
public enum Authorities {

    USER, ADMIN;

    private String authority;
}
