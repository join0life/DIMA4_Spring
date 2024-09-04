package com.kdigital.ajaxtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdigital.ajaxtest.entity.GuestbookEntity;

public interface GuestbookRepository extends JpaRepository<GuestbookEntity, Long> {

}
