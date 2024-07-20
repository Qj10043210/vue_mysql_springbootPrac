package com.ajar.ajarprjmysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ajar.ajarprjmysql.model.MysqlModel;
import java.util.List;


public interface MysqlRepository extends JpaRepository<MysqlModel, Long>{    
    
    List<MysqlModel> findByTitleContaining(String title);
    List<MysqlModel> findByContentsContaining(String contents);
}
