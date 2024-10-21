package com.example.account.controller;

import com.example.account.domain.Account;
import com.example.account.domain.AccountStatus;
import com.example.account.service.AccountService;
import com.example.account.service.RedisTestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
public class AccountControllerTest {
    @MockBean
    private AccountService accountService;

    @MockBean
    private RedisTestService redisTestService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void successGetAccount() throws Exception {
        //given
        given(accountService.getAccount(anyLong()))
                .willReturn(Account.builder()
                        .accountNumber("3456")
                        .accountStatus(AccountStatus.IN_USE)
                        .build());
        //when
        //then
        mockMvc.perform(get("/account/876"))
                .andDo(print()) // get했을 때 응답값을 콘솔에 표여줌
                .andExpect(jsonPath("$.accountNumber").value("3456"))
                .andExpect(jsonPath("$.accountStatus").value("IN_USE"))
                .andExpect(status().isOk());
    }
}
//어카운트 서비스는 겟어카운트 호출받으면
// 어카운트를 3456이라는 number를 갖고 있고,
// IN_USE 사용중인 어카운트를 응답으로 준다

// => 컨트롤러를 테스트하는 방법
// WebMvcTest를 사용해서 특정 컨트롤러만 격리시켜서 Controller 테스트도 unit단위로 테스트 가능
// 다른 의존되고 있는 서비스들은 mockbean을 통해 가짜로 bean등록을 할 수 있고
// 등록된 bean을 mockito랑 비슷하게 mocking을 해두고
// 모킹된 상태에서 mockMvc를 통해 테스트하고자 하는 컨트롤러 안에 있는
// 특정 url을 호출해서 결과를 검증해볼 수 있다.

