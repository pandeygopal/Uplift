package com.hack.UpLift.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long postId;
    String postDesc;
    long postLike;
    long postComments;
    private String imageName;
    private String imageType;

    @Lob
    private byte[] imageData;

//    @ManyToOne
//    @JoinColumn(name = "alumni_email", nullable = false)
//    private alumni alumni;

}