package com.gb.job;

import com.alibaba.schedulerx.worker.domain.JobContext;
import com.alibaba.schedulerx.worker.processor.JavaProcessor;
import com.alibaba.schedulerx.worker.processor.ProcessResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 拉取线下单客户
 *
 * @author lijh
 */
@Component
@Slf4j
public class OfflineCastInsuranceJob extends JavaProcessor {

    @Override
    public ProcessResult process(JobContext jobContext) throws Exception {
        return new ProcessResult(true);
    }
}
