package org.varun.welp.Models;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Comments extends BaseModel{

    @Column(nullable = false)
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "answer_id",nullable = false)
    private Answers answer;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private Comments parentComment;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "users_id" , nullable = false)
    private Users users;



}
