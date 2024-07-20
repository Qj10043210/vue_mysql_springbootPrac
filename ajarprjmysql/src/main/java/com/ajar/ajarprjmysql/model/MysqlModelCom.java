package com.ajar.ajarprjmysql.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "wewereone_com")
public class MysqlModelCom {

    // 내가 여기서 입력한 추가 주석들은 필요한 것임. 특히 auto나 default 값은 필수며, type을 굳이 선언해주는 것도 안전한 데이터
    // 호환&정확성을 위함임.
    // 정석적으로 입력하였음 이걸 따라하면 됨
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "com_id")
    private Long comId;

    @Column(name = "com_contents", columnDefinition = "LONGTEXT")
    private String comContents;

    @ManyToOne
    @JoinColumn(name = "wewereone_id", nullable = false)
    private MysqlModel wewereone;

    @Column(name = "com_created_at", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime comCreatedAt;

    public MysqlModelCom() {
    }

    public Long getComId() {
        return comId;
    }

    public void setComId(Long comId) {
        this.comId = comId;
    }

    public String getComContents() {
        return comContents;
    }

    public void setComContents(String comContents) {
        this.comContents = comContents;
    }

    public MysqlModel getWewereone() {
        return wewereone;
    }

    public void setWewereone(MysqlModel wewereone) {
        this.wewereone = wewereone;
    }

    public LocalDateTime getComCreatedAt() {
        return comCreatedAt;
    }

    public void setComCreatedAt(LocalDateTime comCreatedAt) {
        this.comCreatedAt = comCreatedAt;
    }

}
