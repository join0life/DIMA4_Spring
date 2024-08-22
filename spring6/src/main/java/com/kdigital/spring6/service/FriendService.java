package com.kdigital.spring6.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kdigital.spring6.dto.Friend;
import com.kdigital.spring6.entity.FriendEntity;
import com.kdigital.spring6.repository.FriendRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
// Spring Container에서 생성하고 lifecycle을 관리해주는 객체 ==> 빈(bean)
// @Controller, @Service : 객체 생성해주는 코드

@Service 
@Slf4j
@RequiredArgsConstructor

public class FriendService {
   
   final FriendRepository repository;

   /**
    * DB에 데이터를 저장하기 위한 메소드
    * @param friend
    */
   public void insert(Friend friend) {
      log.info("service에 도착함");
      log.info("{}", friend.toString());
      // DTO를 Entity로 변환
      FriendEntity friendEntity = FriendEntity.toEntity(friend);
      repository.save(friendEntity);
   }
   
   /**
    * 
    *  DB에서 Friend 목록을 조회
    * 
    */
   public List<Friend> list() {
      List<Friend> list = new ArrayList<>();
      
      List<FriendEntity> entityList = repository.findAll();
      
      // entity들을 dto로 변환한다!
      entityList.forEach((entity)-> list.add(Friend.toDTO(entity)));
      
      log.info("{}", list.get(0).toString());
      
   return list;
   }
}
