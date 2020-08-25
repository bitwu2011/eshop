package com.zhss.eshop.order.state;

import com.zhss.eshop.common.util.DateProvider;
import com.zhss.eshop.order.constant.OrderStatus;
import com.zhss.eshop.order.dao.OrderInfoDAO;
import com.zhss.eshop.order.domain.OrderInfoDO;
import com.zhss.eshop.order.domain.OrderInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;

public class WaitForDeliveryOrderState implements OrderState {

    @Autowired
    DateProvider dateProvider;

    @Autowired
    OrderInfoDAO orderInfoDAO;

    @Override
    public void doTransition(OrderInfoDTO order) throws Exception {
        order.setOrderStatus(OrderStatus.WAIT_FOR_DELIVERY);
        order.setGmtModified(dateProvider.getCurrentTime());
        orderInfoDAO.updateStatus(order.clone(OrderInfoDO.class));
    }

    @Override
    public Boolean canCancel(OrderInfoDTO order) throws Exception {
        return false;
    }

    @Override
    public Boolean canPay(OrderInfoDTO order) throws Exception {
        return false;
    }

}
