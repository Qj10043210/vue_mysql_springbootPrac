package com.ajar.ajarprjmysql.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajar.ajarprjmysql.repository.MysqlRepository;
import com.ajar.ajarprjmysql.model.MysqlModel;
import com.ajar.ajarprjmysql.model.MysqlModelCom;
import com.ajar.ajarprjmysql.repository.MysqlComRepository;

@Service
public class MysqlService {
    
     @Autowired
    private MysqlRepository mr;
    
    @Autowired
    private MysqlComRepository mrc;
    
    public List<MysqlModel> findAll() {
        return mr.findAll();
    }
    
    public List<MysqlModelCom> findAllc() {
        return mrc.findAll();
    }
}
