package com.example.p2.model;

import lombok.*;

import javax.persistence.*;

@Getter @Setter @ToString @NoArgsConstructor @RequiredArgsConstructor
@Entity @Table
public class Submission {

    public enum STATUS { temporary, submitted, in_review, accepted, rejected, archived };

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Column Long id;

    private @Column(nullable = false) @NonNull Long authorId;
    private @Column(nullable = false) @NonNull String title;
    private @Column(nullable = false) @NonNull String summary;
    private @Column(nullable = false) @NonNull String keywords;
    private @Column(nullable = false) @NonNull String lang = "pl";
    private @Column(nullable = false) @NonNull Integer pages;
    private @Column(nullable = false) @NonNull Integer status;
    private @Column(nullable = false) @NonNull Boolean archived = false;
}
