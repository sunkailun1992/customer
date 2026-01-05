package com.gb.customer.template;

import com.gb.customer.entity.PotentialCustomer;
import com.gb.customer.entity.PotentialCustomerSource;
import com.gb.customer.enums.SourceTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

/**
 * 线索模板方法
 *
 * @author ljh
 * @date 2022/3/10 9:34 上午
 */
@Slf4j
public abstract class AbstractCluesTemplate<T> {

    /**
     * 生成线索
     *
     * @return
     */
    public final Object generateClues(T clues, HttpServletRequest httpServletRequest) {
        //1-- 数据校验
        if (checkScreening(clues)) {
            return true;
        }

        //2-- 邀请
        Boolean beInvited = isBeInvited(clues);

        //3-- 分配管家（不是被邀请，自动分配管家）
        if (!beInvited) {
            allotSteward(clues);
        }
        //4-- 保存
        return saveCluesData(clues, httpServletRequest);
    }

    /**
     * 校验权限
     *
     * @param clues
     * @return
     */
    public abstract Boolean checkScreening(T clues);

    /**
     * 是否被邀请
     *
     * @param clues 线索体
     * @return 是否
     */
    public abstract Boolean isBeInvited(T clues);


    /**
     * 分配管家
     *
     * @param clues 线索体
     */
    public abstract void allotSteward(T clues);

    /**
     * 保存数据
     *
     * @param clues 线索体
     * @param httpServletRequest 请求头
     * @return 保存结果
     */
    public abstract Object saveCluesData(T clues, HttpServletRequest httpServletRequest);

    /**
     * 获取子类
     *
     * @return 子类的key
     */
    public abstract String priority();


    /**
     * 根据状态判断  获取来源id
     *
     * @param pc         判断目标
     * @param sourceList 所有来源
     * @return 来源id
     */
    public String getCluesSourceId(PotentialCustomer pc, List<PotentialCustomerSource> sourceList) {
        String name = pc.getSourceQuery();
        String status = "status15";
        if (status.equals(pc.getStatusQuery())) {
            name = "总部业务合作";
        }
        //SourceQuery="工保科技官网"，StatusQuery="status15" 分配至商务管家
        String sourceId = SourceTypeEnum.SOURCE_TYPE_15.getCode();
        if (StringUtils.isNotBlank(name)) {
            String finalName = name;
            //根据状态名匹配数据库所有来源获取对应id
            Optional<PotentialCustomerSource> first = sourceList.stream().filter(item -> item.getName().contains(finalName)).findFirst();
            if (first.isPresent()) {
                PotentialCustomerSource potentialCustomerSource = first.get();
                sourceId = potentialCustomerSource.getId();
            }
        }
        return sourceId;
    }

}
