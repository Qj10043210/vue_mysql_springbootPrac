package com.ajar.ajarprjmysql.model;

import java.time.LocalDateTime;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;


@Entity
@Table(name = "wewereone")
public class MysqlModel {

    // 내가 여기서 입력한 추가 주석들은 필요한 것임. 특히 auto나 default 값은 필수며, type을 굳이 선언해주는 것도 안전한 데이터
    // 호환&정확성을 위함임.
    // 정석적으로 입력하였음 이걸 따라하면 됨
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")    
    private Long id;

    @Column(name = "title", columnDefinition = "VARCHAR(100)")
    private String title;

    @Column(name = "likes", columnDefinition = "INT DEFAULT 0")
    private int likes;

    @Column(name = "contents", columnDefinition = "LONGTEXT DEFAULT NULL")
    private String contents;

    @Column(name = "comments", columnDefinition = "INT DEFAULT 0")
    private int comments;

    //@Column(name = "created_at", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    @Column(name = "created_at", columnDefinition = "'TIMESTAMP DEFAULT CURRENT_TIMESTAMP'")
    private LocalDateTime createdAt;
    
    public MysqlModel() {
    }

    // 별도의 constructor를 만들었음 기본도 만들어라!!! 안 그럼 데이터 읽을 때 에러 나온다
    public MysqlModel(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
