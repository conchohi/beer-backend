package com.zipbeer.beerbackend.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "board_tbl")
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardNo;

    @Column(length = 100)
    private String title;

    @Column(length = 500)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity writer;

    @Column
    @CreatedDate
    private LocalDate regDate;

    @Column
    @LastModifiedDate
    private LocalDate modifyDate;

    @Column
    private int count;

    @OneToMany(mappedBy = "board",orphanRemoval = true,
    cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList;
}