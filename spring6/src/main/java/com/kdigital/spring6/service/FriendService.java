package com.kdigital.spring6.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kdigital.spring6.dto.Friend;
import com.kdigital.spring6.entity.FriendEntity;
import com.kdigital.spring6.repository.FriendRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
		log.info("{}", friend.toString());

		FriendEntity friendEntity = FriendEntity.toEntity(friend);
		
		repository.save(friendEntity); // persist()
	}

	/**
	 * DB에서 Friend 목록을 조회
	 * @return
	 */
	public List<Friend> list() {
		List<Friend> list = new ArrayList<>();
		
		List<FriendEntity> entityList = repository.findAll();
		
		// entity들을 dto로 변환한다! (데이터가 하나도 없을 경우에는 처리를 하면 안됨)
		if(!entityList.isEmpty()) {
			entityList.forEach((entity) -> list.add(Friend.toDTO(entity) ));
			log.info("{}", list.get(0).toString());
		}
		
		return list;
	}
	
	/**
	 * DB에 fseq번호에 해당하는 데이터를 삭제함.
	 * @param fseq
	 */
	public void deleteOne(Integer fseq) {
		log.info("삭제할 번호 ==> {}", fseq);
		
		repository.deleteById(fseq);
		
	}

	/**
	 * DB에서 fseq 번호에 해당하는 데이터를 조회
	 * @param fseq
	 * @return
	 */
	public Friend selectOne(Integer fseq) {
		// Null로 인한 오류로 인해 FriendEntity를 반환하지 않고
		// Optional이라는 Wrapper 클래스로 감싸서 반환한다.
		
		Optional<FriendEntity> entity =  repository.findById(fseq);
		
		if(entity.isPresent()) {
			FriendEntity friendEntity = entity.get();
			return Friend.toDTO(friendEntity);
		}
		return null;
	}

	@Transactional
	public void updateOne(Friend friend) {
		// dto를 entity로 수정하는 작업함.
		// set을 하면 바뀜
		log.info("updateOne ========> {} ", friend.toString());
		
		// 1) PK를 이용해서 그 데이터가 있는지 조회한다. --> findById()
		Optional<FriendEntity> entity =  repository.findById(friend.getFseq());
		
		// 2) 조회된 데이터에 수정할 정보를 setting한다.
		if(entity.isPresent()) {
			FriendEntity friendEntity = entity.get(); // 바뀌기 전 데이터
			
			// 나이, 전화번호, 성향, 생년월일을 setting하면 DB값이 바뀐다
			friendEntity.setAge(friend.getAge());
			friendEntity.setPhone(friend.getPhone());
			friendEntity.setActive(friend.isActive());
			friendEntity.setBirthday(friend.getBirthday());
		}
	}
}






