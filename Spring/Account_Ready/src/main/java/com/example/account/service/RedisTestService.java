package com.example.account.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisTestService {
    private final RedissonClient redissonClient;

    public String getLock() {
        RLock lock = redissonClient.getLock("sampleLock");

        try {
            boolean isLock = lock.tryLock(1, 5, TimeUnit.SECONDS);
            if (!isLock) {
                log.error("=========Lock acquisition failed=========");
                return "Lock failed";
            }
        } catch (Exception e) {
            log.error("Redis lock failed");
        }

        return "Lock success";
    }
}
// 레디스 클라이언트에 sampleLock이라는 lock 변수를 가져오고
// 이 lock으로 spinLock 시도 => try, catch문
// 1초 동안 기다리면서 락을 시도해보고 lock 획득에 실패하면 if문으로 들어감
// lock 획득에 성공하면 최대 5초 시간 후에 자동으로 풀어주게(lease) 됨
// 명시적으로 unlock을 하고 있지 않기 때문에 다른 녀석이 lock을 획득하려고 하면 5초 동안은 실패가 뜨게 될 것임

