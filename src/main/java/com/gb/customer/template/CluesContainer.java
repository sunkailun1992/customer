package com.gb.customer.template;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.gb.utils.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 容器控制
 *
 * @author ljh
 * @date 2022/3/25 3:12 下午
 */
@Slf4j
@Service
public class CluesContainer implements ApplicationContextAware {

    private final Map<String, AbstractCluesTemplate> beans = new ConcurrentHashMap<>();

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(@Nonnull ApplicationContext applicationContext) throws BeansException {
        CluesContainer.applicationContext = applicationContext;
    }

    @PostConstruct
    public void init() {
        Map<String, AbstractCluesTemplate> map = applicationContext.getBeansOfType(AbstractCluesTemplate.class);
        if (CollectionUtils.isEmpty(map)) {
            throw new RuntimeException("当前容器内无AbstractCluesTemplate对象");
        }
        for (AbstractCluesTemplate value : map.values()) {
            beans.put(value.priority(), value);
        }
        log.info("容器内已有对象,beans={}", beans);
    }


    public AbstractCluesTemplate getCluesSourceService(CluesPriorityEnum cluesPriorityEnum) {
        if (CollectionUtils.isEmpty(beans)) {
            log.info("容器对象为空,size={}，重新存放", beans.size());
            init();
        }
        return beans.get(cluesPriorityEnum.getValue());
    }
}
