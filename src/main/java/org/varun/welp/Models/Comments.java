package org.varun.welp.Models;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

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
    @JoinColumn(name = "answer_id",nullable = false)
    private Answers answer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_comment_id")
    private Comments parentComment;

    @OneToMany(mappedBy = "parentComment")
    @Fetch(FetchMode.SUBSELECT)
    private List<Comments> replies;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id" , nullable = false)
    private Users users;



}
