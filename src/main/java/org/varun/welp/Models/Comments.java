package org.varun.welp.Models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
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
    private Answers answers;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private Comments parentComment;

    @ManyToOne
    @Fetch(FetchMode.SUBSELECT)
    private Users users;



}
