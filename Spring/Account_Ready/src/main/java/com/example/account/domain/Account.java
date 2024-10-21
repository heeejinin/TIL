package com.example.account.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
// 상위 5개의 어노테이션은 엔티티나 Dto에 세트처럼 사용하신다고 함
@Entity // 일종의 설정 파일
public class Account { // 엔티티를 DB에 저장하기 위해서는 JPA에서 제공하는 Repository가 필요함
    @Id // Account의 PK를 id로 지정
    @GeneratedValue
    private Long id;

    private String accountNumber;

    @Enumerated(EnumType.STRING) // Enum 값에 0,1,2..이 아니라 실제 문자값을 DB에 저장
    private AccountStatus accountStatus;
}
