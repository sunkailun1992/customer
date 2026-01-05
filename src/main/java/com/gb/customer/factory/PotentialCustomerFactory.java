package com.gb.customer.factory;

import com.gb.customer.entity.PotentialCustomer;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

/**
 * 潜在客户工厂类
 *
 * @author ljh
 * @date 2022/9/8 6:31 PM
 */
public class PotentialCustomerFactory {

    /**
     * 构建潜在客户类
     *
     * @param potentialCustomer
     * @return
     */
    public static PotentialCustomer getBeanPotentialCustomer(PotentialCustomer potentialCustomer) {
        if (Objects.isNull(potentialCustomer)) {
            potentialCustomer = new PotentialCustomer();
        }
        //基础配置
        LocalDateTime now = LocalDateTime.now();
        potentialCustomer.setProcessingTime(now);
        potentialCustomer.setCreateDateTime(now);
        potentialCustomer.setModifyDateTime(now);
        potentialCustomer.setSubmitTime(now);
        if (Objects.isNull(potentialCustomer.getAppointmentContactDateTime())) {
            potentialCustomer.setAppointmentContactDateTime(new Date());
        }
        return potentialCustomer;

    }
}
