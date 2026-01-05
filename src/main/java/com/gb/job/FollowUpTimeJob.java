package com.gb.job;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.alibaba.schedulerx.worker.domain.JobContext;
import com.alibaba.schedulerx.worker.processor.JavaProcessor;
import com.alibaba.schedulerx.worker.processor.ProcessResult;
import com.alibaba.ttl.TransmittableThreadLocal;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gb.aliyun.sms.SmsUtils;
import com.gb.customer.entity.Customer;
import com.gb.customer.entity.CustomerCommunication;
import com.gb.customer.service.CustomerCommunicationService;
import com.gb.customer.service.CustomerService;
import com.gb.utils.JsonUtil;
import com.gb.utils.enumeration.SmsEnum;
import com.google.common.collect.Maps;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * 跟进记录时间
 *
 * @author sunkailun
 * @DateTime 2021/1/4  下午2:09
 * @email 376253703@qq.com
 * @phone 13777579028
 * @explain
 */
@Component
@Setter(onMethod_ = {@Autowired})
public class FollowUpTimeJob extends JavaProcessor {


    /**
     * 客户表
     */
    private CustomerService customerService;

    /**
     * 客户沟通表
     */
    private CustomerCommunicationService customerCommunicationService;
    public static TransmittableThreadLocal<String> dataSourceContext = new TransmittableThreadLocal();


    @Override
    public ProcessResult process(JobContext context) throws Exception {
        // 取出客户
        List<Customer> customerList = customerService.listEnhance(new Customer() {{
            setConnectionState(true);
            setCommunication(true);
        }});
        // 循环
        customerList.forEach(customer -> {
            Customer up = new Customer();
            up.setId(customer.getId());
            //取出最新一条沟通记录
            List<CustomerCommunication> list = customerCommunicationService.pageEnhance(new Page(1, 1), new CustomerCommunication() {{
                setCustomerId(customer.getId());
            }}).getRecords();
            //为空判断
            if (list.size() != 0) {
                CustomerCommunication c = list.get(0);
                if (c.getNextDataTime() != null) {
                    //时间比较，最新的下次跟进时间，是否大于当前时间
                    Duration between = LocalDateTimeUtil.between(LocalDateTimeUtil.beginOfDay(LocalDateTime.now()), c.getNextDataTime());
                    //取出时间差值
                    Long x = between.toDays();
                    if (x <= 0) {
                        up.setConnectionState(false);
                    } else {
                        up.setConnectionState(true);
                    }
                } else {
                    up.setConnectionState(false);
                }
            } else {
                up.setConnectionState(false);
            }
            //修改客户
            customerService.updateEnhance(up);
        });
        return new ProcessResult(true);
    }
}
