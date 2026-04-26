package org.varun.welp.Models;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Users extends BaseModel{

    @Column(nullable = false)
    protected String name;

    @Column(nullable = false)
    protected String email;

    @Column
    protected String bio;

    @OneToMany(fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    List<Questions> questions= new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    List<Answers> answers = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    List<Comments> comments= new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    List<Likes> likes = new ArrayList<>();
}
