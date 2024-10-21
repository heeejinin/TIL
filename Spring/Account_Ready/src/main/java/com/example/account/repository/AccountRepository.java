package com.example.account.repository;

import com.example.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    // 엔티티를 DB에 저장하기 위해서는 JPA에서 제공하는 Repository가 필요함
    // 인터페이스로 생성 JpaRepository를 확장해서 만드는 Repository임
    // <Account, Long> : Account는 Repository가 허용하게 될 Entity, Long은 Entity의 PK 타입
}
