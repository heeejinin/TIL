package com.zerobase.convpay.service;

import com.zerobase.convpay.dto.PayRequest;

public interface DiscountInterface {
    // 할인된 가격을 받는 메소드
    Integer getDiscountedAmount(PayRequest payRequest);
}
