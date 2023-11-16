package cn.itcast.order.service;

import cn.itcast.feign.clients.UserClient;
import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import cn.itcast.feign.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;
  //  @Autowired
    //private RestTemplate restTemplate;

//    public Order queryOrderById(Long orderId) {
//        // 1.查询订单
//        Order order = orderMapper.findById(orderId);
//        //利用订单id查询用户信息
//
//        //2.1 url路径
//        String url ="http://userservice/user/"+  order.getUserId();
//        //2.2 发送http请求 ，实现远程调用
//        User user = restTemplate.getForObject(url, User.class);
//        // 封装user到order
//        order.setUser(user);
//        // 4.返回
//        return order;
//    }
    @Autowired
    private UserClient userClient;
public Order queryOrderById(Long orderId) {
    // 1.查询订单
    Order order = orderMapper.findById(orderId);
    //利用订单id查询用户信息
    //2. s使用feign实现远程调用
    User user = userClient.findById(order.getUserId());
    // 封装user到order
    order.setUser(user);
    // 4.返回
    return order;
}
}
