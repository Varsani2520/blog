package com.blog.blog.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "post_title", nullable = false)
    private String title;

    @Column(name = "post_content")
    private String content;

    @Column(name = "post_Image")
    private String ImageName;

    @Column(name = "post_date")
    private java.util.Date added_Date;

    // multiple post belongs to 1 category
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // multiple post are written by single user
    @ManyToOne
    private User user;

}
