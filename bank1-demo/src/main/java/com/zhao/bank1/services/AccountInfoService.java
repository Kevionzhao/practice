package com.zhao.bank1.services;

/**
 * Created by Administrator.
 */
public interface AccountInfoService {

    //张三扣减金额
    public void updateAccountBalance(String accountNo, Double amount);
}
