package com.gb.customer.factory;

import com.gb.customer.entity.Customer;

import java.util.Objects;

/**
 * 客户工厂类
 *
 * @author ljh
 * @date 2022/9/8 6:25 PM
 */
public class CustomerFactory {

    /**
     * 构建客户类
     *
     * @param customer
     * @return
     */
    public static Customer getBeansCustomer(Customer customer) {
        if (Objects.isNull(customer)) {
            customer = new Customer();
        }


        return customer;
    }
}
