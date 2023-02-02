package com.example.p2.model;

import com.ns.util.En;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Getter @Setter @ToString @NoArgsConstructor @RequiredArgsConstructor
@Entity @Table
public class Author {

    public enum SALUTATION { none, mr, ms; }
    public enum STATUS { unknown, regular, returning, reviewer, archived }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Column Long id;
    private @Column Integer salutation = SALUTATION.none.ordinal(); // ENUM stored as Int4 in the DB
    private @Column(nullable = false) @NonNull String firstName;
    private @Column(nullable = false) @NonNull String lastName;
    private @Column Date birthdate;
    private @Column(nullable = false) @NonNull String email;
    private @Column String phone;
    private @Column(unique = true) String orcid;
    private @Column String status = STATUS.unknown.name();        // ENUM stored as VARCHAR in the DB

    public static String getSalutationLabel(Integer salutation) {
        return En.enumName(salutation, SALUTATION.class);
    }
    public static Integer getStatusValue(String status) { return En.enumOrdinal(status, STATUS.class); }
}
