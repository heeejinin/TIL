package com.zerobase.convpay.service;

import com.zerobase.convpay.type.*;
import org.springframework.stereotype.Component;

@Component
public class MoneyAdapter implements PaymentInterface{
    public MoneyUseResult use(Integer payAmount) { // 편의점 정보를 알 필요X, 단순히 얼마를 사용했는지만 알면 됨
        System.out.println("MoneyAdapter.use : " + payAmount);

        //너무 큰 금액은 실패
        if (payAmount > 1000_000) {
            return MoneyUseResult.USE_FAIL;
        }

        return MoneyUseResult.USE_SUCCESS;
    }

    public MoneyUseCancelResult useCancel(Integer payCancelAmount) {
        System.out.println("MoneyAdapter.useCancel : " + payCancelAmount);

        //너무 큰 금액은 실패
        if (payCancelAmount < 100) {
            return MoneyUseCancelResult.MONEY_USE_CANCEL_FAIL;
        }

        return MoneyUseCancelResult.MONEY_USE_CANCEL_SUCCESS;
    }

    @Override
    public PayMethodType getPayMethodType() {
        return PayMethodType.MONEY;
    }

    @Override
    public PaymentResult payment(Integer payAmount) {
        MoneyUseResult moneyUseResult = use(payAmount);

        if (moneyUseResult == MoneyUseResult.USE_FAIL) {
            return PaymentResult.PAYMENT_FAIL;
        }
        return PaymentResult.PAYMENT_SUCCESS;
    }

    @Override
    public CancelPaymentResult cancelPayment(Integer cancelAmount) {
        MoneyUseCancelResult moneyUseCancelResult = useCancel(cancelAmount);

        if (moneyUseCancelResult == MoneyUseCancelResult.MONEY_USE_CANCEL_FAIL) {
            return CancelPaymentResult.CANCEL_PAYMENT_FAIL;
        }
        return CancelPaymentResult.CANCEL_PAYMENT_SUCCESS;
    }
}

