package com.andy.common.utils;

import com.andy.common.entity.Goods;
import com.andy.common.entity.Order;
import com.andy.common.entity.OrderItem;
import com.andy.common.entity.User;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class EntityFactory {

    private static Random random = new Random();

    private static List<User> userList = new LinkedList<>();

    private static List<Order> orderList = new LinkedList<>();

    private static List<Goods> goodsList = new ArrayList<>();

    private static List<OrderItem> orderItemList = new ArrayList<>();

    static {
        for (long i = 0; i < 100; i++) {
            Date date = new Date(new Date().getTime() - (random.nextInt(1000000) + 1000000));
            userList.add(new User(i, RandomValue.randomUsername(), RandomValue.randomStr(16), RandomValue.randomMessage(), RandomValue.random.nextInt(50) + 10, date, false));
            orderList.add(new Order(i, i, random.nextInt(1000) + 200, "Chicken and fish", 1 + RandomValue.randomNum(15), date, date, false));
            orderList.add(new Order(100 + i, i, random.nextInt(100) + 200, "some apple and orange", 1 + RandomValue.randomNum(15), date, date, false));
            goodsList.add(new Goods(i, RandomValue.randomGoods(), random.nextInt(10000), "http://image.taobao.com/picture/"+UUID.randomUUID().toString().replace("-","")+ ".jpg", random.nextInt(1000), date, false));

            orderItemList.add(new OrderItem(i, i, i, RandomValue.randomGoods(), random.nextInt(1000), "http://image.taobao.com/picture/"+UUID.randomUUID().toString().replace("-","")+ ".jpg", random.nextInt(10) + 1, date));
            orderItemList.add(new OrderItem(100 + i, i, i, RandomValue.randomGoods(), random.nextInt(1000), "http://image.taobao.com/picture/"+UUID.randomUUID().toString().replace("-","")+ ".jpg", random.nextInt(10) + 1, date));
            orderItemList.add(new OrderItem(200 + i, i, 100 + i, RandomValue.randomGoods(), random.nextInt(1000), "http://image.taobao.com/picture/"+UUID.randomUUID().toString().replace("-","")+ ".jpg", random.nextInt(10) + 1, date));
        }
    }

    public static final String JSON_USER = "{\"username\":\"james\",\"password\":\"admin\",\"email\":\"andy@163.com\",\"token\":\"token:12idkail8lld\",\"salary\":10004,\"birthday\":\"2018-04-02\"}";

    /**
     * 获取object数据格式数据
     *
     * @param count
     * @return
     */
    public static List<User> getUsers(Integer count) {
        List<User> userList = new ArrayList<>();
        if (count < 1) {
            return Collections.emptyList();
        }
        for (long i = 0; i < count; i++) {
            userList.add(new User(i, RandomValue.randomUsername(), RandomValue.randomStr(16), RandomValue.randomMessage(), RandomValue.random.nextInt(50) + 10, new Date(), false));
        }
        return userList;
    }


    /**
     * @return
     */
    public static User getUser() {
        return new User(RandomValue.random.nextLong(), RandomValue.randomUsername(), RandomValue.randomStr(16), RandomValue.randomMessage(), RandomValue.random.nextInt(50) + 10, new Date(), false);
    }

    /**
     * @param userId
     * @return
     */
    public static User getUser(Long userId) {
        return userList.stream().filter(e -> e.getUserId().equals(userId)).collect(Collectors.toList()).get(0);
    }

    /**
     * @param userId
     */
    public static void remove(Long userId) {
        userList.removeIf(next -> next.getUserId().equals(userId));
    }


    public static List<Order> getOrderList(Long userId) {
        return orderList.stream().filter(e -> e.getUserId().equals(userId)).collect(Collectors.toList());
    }


    public static Order getOrder(Long orderId) {
        return orderList.stream().filter(e -> e.getUserId().equals(orderId)).collect(Collectors.toList()).get(0);
    }

    public static List<Goods> getGoodsList() {
        return goodsList;
    }


    public static Goods getGoods(Long goodsId) {
        return goodsList.stream().filter(e -> e.getGoodsId().equals(goodsId)).collect(Collectors.toList()).get(0);
    }


    public static List<OrderItem> getOrderItemList(Long orderItemId) {
        return orderItemList.stream().filter(e -> e.getGoodsId().equals(orderItemId)).collect(Collectors.toList());
    }


    public static OrderItem getOrderItem(Long orderItemId) {
        return orderItemList.stream().filter(e -> e.getGoodsId().equals(orderItemId)).collect(Collectors.toList()).get(0);
    }

}