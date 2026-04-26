package org.varun.welp.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Answers extends BaseModel{


    @Column(nullable = false)
    private String answer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Questions question;

    @ManyToOne(fetch = FetchType.LAZY)
    private Users user;

}
