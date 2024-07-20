package com.ajar.ajarprjmysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ajar.ajarprjmysql.model.MysqlModel;
import com.ajar.ajarprjmysql.model.MysqlModelCom;
import java.util.List;


public interface MysqlComRepository extends JpaRepository<MysqlModelCom, Long>{
    
    List<MysqlModelCom> findByWewereone(MysqlModel wewereone); 
    List<MysqlModelCom> findByComContentsContaining(String comContents);
}
