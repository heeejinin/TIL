package com.example.account.controller;

import com.example.account.domain.Account;
import com.example.account.service.AccountService;
import com.example.account.service.RedisTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/create-account")
    public String createAccount() {
        accountService.createAccount();

        return "success";
    }

    @GetMapping("/account/{id}")
    public Account getAccount(@PathVariable Long id) { // @PathVariable은 생략 가능
        return accountService.getAccount(id);
    }
}

// 레이어드 아키텍처는 외부에서는 컨트롤러로만 접속하고, 컨트롤러는 서비스로 접속하고
// 서비스는 레포지토리로 접속하는 순차적, 계층화 된 구조
// 그래서 컨트롤러는 서비스만 의존하도록 설정하면 됨
