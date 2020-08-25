package com.zhss.eshop.pay.service;

import com.zhss.eshop.order.domain.OrderInfoDTO;

public interface PayService {

    /**
     * 获取支付二维码
     * @param order
     * @return 支付二维码
     */
    String getQrCode(OrderInfoDTO order);
}
