package com.zhss.eshop.order.state;

import com.zhss.eshop.order.domain.OrderInfoDTO;

public class WaitForDeliveryOrderState implements OrderState {
    @Override
    public void doTransition(OrderInfoDTO order) throws Exception {

    }

    @Override
    public Boolean canCancel(OrderInfoDTO order) throws Exception {
        return false;
    }

    @Override
    public Boolean canPay(OrderInfoDTO order) throws Exception {
        return false;
    }

    @Override
    public void Pay(OrderInfoDTO order) throws Exception {

    }
}
