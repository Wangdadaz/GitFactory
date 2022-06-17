package com.wang.hm_takeout.dao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.hm_takeout.dao.domain.OrderDetail;
import com.wang.hm_takeout.dao.mapper.OrderDetailMapper;
import com.wang.hm_takeout.dao.service.OrderDetailService;
import org.springframework.stereotype.Service;

/**
* @author 86182
* @description 针对表【order_detail(订单明细表)】的数据库操作Service实现
* @createDate 2022-06-16 22:26:06
*/
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail>
    implements OrderDetailService {

}




