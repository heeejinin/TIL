package com.example.account.service;

import com.example.account.domain.Account;
import com.example.account.domain.AccountStatus;
import com.example.account.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
    @Mock // Mockito 확장 팩
    private AccountRepository accountRepository;

    @InjectMocks // 가짜(mock)로 만든 accountRepository accountService에 주입
    private AccountService accountService;

    @Test
    @DisplayName("계좌 조회 실패 - 음수로 조회")
    void testFailedToSerachAccount() {
        //given
        //when
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> accountService.getAccount(-10L));// 결과값 X => 해당 동작 실행 시 RuntimeException이 실행될 것이라는 의미

        //then
        assertEquals("Minus", exception.getMessage());
    }

    @Test
    @DisplayName("계좌 조회 성공")
    void testxxx() {
        //given
        given(accountRepository.findById(anyLong()))
                .willReturn(Optional.of(Account.builder()
                        .accountStatus(AccountStatus.UNREGISTERED)
                        .accountNumber("65789").build()));
        ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);

        //when
        Account account = accountService.getAccount(4555L);

        //then
        // verify
        verify(accountRepository, times(1)).findById(anyLong()); // when 실행 시, 1번 호출했다를 검증할 때
        verify(accountRepository, times(1)).save(any()); // when 실행 시, save 되지 않아야 하는 것을 검증할 때 => 왜냠 getAccount를 실행하는 것이기 때문에 save되면 안되니까

        // captor
        verify(accountRepository, times(1)).findById(captor.capture()); // when 실행 시, 1번 호출했다를 검증할 때
        assertEquals(4555L, captor.getValue());
        assertNotEquals(45555L, captor.getValue());
        assertTrue(45555L == captor.getValue());

        assertEquals("65789", account.getAccountNumber());
        assertEquals(AccountStatus.UNREGISTERED, account.getAccountStatus());
    }

    @BeforeEach
        // test 실행 전에 @BeforeEach를 실행하고 test하도록 설정
    void init() {
        accountService.createAccount();
    }

    @Test
    @DisplayName("Test 이름 변경")
        // 테스트의 이름을 변경해줌
    void testAccount() {
        accountService.createAccount();
        Account account = accountService.getAccount(1L);

        assertEquals("40000", account.getAccountNumber());
        assertEquals(AccountStatus.IN_USE, account.getAccountStatus());

    }

    @Test
    void testAccount2() {
        accountService.createAccount();
        Account account = accountService.getAccount(2L);

        assertEquals("40000", account.getAccountNumber());
        assertEquals(AccountStatus.IN_USE, account.getAccountStatus());

    }

}