package com.kdigital.spring7.service;

import org.springframework.stereotype.Service;

import com.kdigital.spring7.dto.UserDTO;
import com.kdigital.spring7.entity.UserEntity;
import com.kdigital.spring7.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	final UserRepository userRepository;
	/**
	 * 전달 받은 userDTO를 userEntity로 변경한 후 DB save();
	 * @param userDTO
	 */
	public boolean join(UserDTO userDTO) {
		// 가입하려는 id가 이미 사용 중이면 쓸 수 없음
		boolean isExistUser = userRepository.existsById(userDTO.getUserId());
		if(isExistUser) return false; // 이미 사용 중인 아이디이므로 가입 실패 
		
		UserEntity userEntity = UserEntity.toEntity(userDTO);
		userRepository.save(userEntity); // 가입 성공
		return true;
 	}
	public boolean existId(String userId) {
		return false;
	}

}
