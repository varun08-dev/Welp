package org.varun.welp.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Likes extends BaseModel{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id" , nullable = false)
    private Users users;

    @Column(nullable = false)
    private LikeType likeType;

    @Column(nullable = false)
    private UUID typeId;


}
