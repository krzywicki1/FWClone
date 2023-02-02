package com.example.p2.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table
public class Movies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Column Long id;
    private @Column(nullable = false) @NonNull String name;
    private @Column(nullable = false) @NonNull int production_date;
    private @Column(nullable = false) @NonNull String director;
    private @Column int budget;
    private @Column int boxoffice;
    private @Column int score;
    //name, production_date, director, budget, boxoffice, score
}
