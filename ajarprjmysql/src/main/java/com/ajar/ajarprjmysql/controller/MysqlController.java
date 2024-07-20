package com.ajar.ajarprjmysql.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ajar.ajarprjmysql.model.MysqlModel;
import com.ajar.ajarprjmysql.model.MysqlModelCom;
import com.ajar.ajarprjmysql.repository.MysqlComRepository;
import com.ajar.ajarprjmysql.repository.MysqlRepository;
import com.ajar.ajarprjmysql.service.MysqlService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin(origins = "http://localhost:8888")
@RestController
@RequestMapping("/asks")
public class MysqlController {
    // C R U D
    @Autowired
    MysqlRepository mr;

    @Autowired
    MysqlComRepository mrc;

    // C {{R}} U D
    @GetMapping("/allthings")
    public ResponseEntity<List<MysqlModel>> getAllThings(@RequestParam(required = false) String param) {
        try {
            System.out.println("dowiell");
            List<MysqlModel> new_list = new ArrayList<MysqlModel>();
            System.out.println(param);
            System.out.println(new_list.size());
            if (param == null) {
                System.out.println("we null");
                mr.findAll().forEach(new_list::add);
                System.out.println("test2");
            } else {
                System.out.println("test3");
                List<MysqlModel> result = mr.findByTitleContaining(param);
                if (!result.isEmpty()) {
                    result.forEach(new_list::add);
                } // mr.findAll().forEach((element) -> new_list.add(element)); ::메서드 레퍼런스 사용하여 람다
                  // 표현식을 단순화
            }
            System.out.println("test T");
            System.out.println(new_list.isEmpty());
            if (new_list.isEmpty())
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(new_list, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // 이렇게 요청시, get(/allthings)와 get(/allthings?asfd=${fff})을 두 개로 나누어 본다.
        // required=false로 했기에, 두 개 다 ok.
    }
    @GetMapping("/allthings/{id}")
    public  ResponseEntity<MysqlModel> getThings(@PathVariable("id") Long param) {
        Optional<MysqlModel> new_list = mr.findById(param);
        if(new_list.isPresent()){
            return new ResponseEntity<>(new_list.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    // {{C}} R U D
    @PostMapping("/allthings")
    public ResponseEntity<MysqlModel> postNewContent(@RequestBody MysqlModel entity) {
        try {
            System.out.println(entity.getTitle());
            System.out.println(entity.getContents());
            MysqlModel newContent = new MysqlModel(entity.getTitle(), entity.getContents());
            mr.save(newContent);
            System.out.println(newContent.getTitle());
            System.out.println(newContent.getContents());
            return new ResponseEntity<>(newContent, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    // C R {{U}} D
    @PutMapping("/allthings/{id}")
    public ResponseEntity<MysqlModel> putNewContent(@PathVariable("id") Long id, @RequestBody MysqlModel entity) {
        Optional<MysqlModel> oldContent = mr.findById(id);
        try {
            if (oldContent.isPresent()) {
                MysqlModel newContent = oldContent.get();
                newContent.setTitle(entity.getTitle());
                newContent.setContents(entity.getContents());
                newContent.setLikes(entity.getLikes());
                newContent.setCreatedAt(LocalDateTime.now());
                return new ResponseEntity<>(mr.save(newContent), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    // C R U {{D}}
    @DeleteMapping("/allthings/{id}")
    public ResponseEntity<MysqlModel> deleteOldContent(@PathVariable("id") Long id) {
        try {
            mr.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
