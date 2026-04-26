package org.varun.welp.Models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Questions extends BaseModel{

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String body;

    @ElementCollection
    @CollectionTable(name = "question_tag" , joinColumns = @JoinColumn(name = "question_id"))
    @Column(name = "tags")
    private List<String> topicTags = new ArrayList<>();


    @OneToMany(mappedBy = "question",fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private List<Answers> answers = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="users_id",nullable = false )
    private Users users;



}
