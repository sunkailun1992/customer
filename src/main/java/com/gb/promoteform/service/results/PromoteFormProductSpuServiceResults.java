package com.gb.promoteform.service.results;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.gb.promoteform.entity.PromoteFormProductSpu;
import com.gb.promoteform.entity.vo.PromoteFormProductSpuVO;
import com.gb.promoteform.entity.bo.PromoteFormProductSpuBO;
import com.gb.rpc.entity.ProductSpu;
import com.gb.rpc.entity.UserAgentCertification;
import com.gb.rpc.entity.enums.RpcTypeEnum;
import com.gb.rpc.impl.RpcComponent;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import com.gb.utils.GeneralConvertor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * TODO 推广表单产品id,Service返回实现
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormProductSpuServiceResults
 * @time 2022-10-28 03:09:32
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class PromoteFormProductSpuServiceResults {

    private RpcComponent rpcComponent;


    /**
     * TODO 单条，增强返回参数追加
     *
     * @param promoteFormProductSpuVO 推广表单产品id
     * @return PromoteFormProductSpuVO
     * @author lijh
     * @methodName assignment
     * @time 2022-10-28 03:09:32
     */
    public PromoteFormProductSpuVO assignment(PromoteFormProductSpuVO promoteFormProductSpuVO) {
        if (promoteFormProductSpuVO != null) {
            return promoteFormProductSpuVO;
        } else {
            return promoteFormProductSpuVO;
        }
    }


    /**
     * TODO 分页，增强返回参数追加
     *
     * @param promoteFormProductSpuVOList 推广表单产品id
     * @return Page<PromoteFormProductSpuVO>
     * @author lijh
     * @methodName assignment
     * @time 2022-10-28 03:09:32
     */
    public Page<PromoteFormProductSpuVO> assignment(Page<PromoteFormProductSpuVO> promoteFormProductSpuVOList) {
        promoteFormProductSpuVOList.getRecords().forEach(promoteFormProductSpuVO -> {
        });
        return promoteFormProductSpuVOList;
    }


    /**
     * TODO 集合，增强返回参数追加
     *
     * @param promoteFormProductSpuVOList 推广表单产品id
     * @return List<PromoteFormProductSpuVO>
     * @author lijh
     * @methodName assignment
     * @time 2022-10-28 03:09:32
     */
    public List<PromoteFormProductSpuVO> assignment(List<PromoteFormProductSpuVO> promoteFormProductSpuVOList) {
        if (CollectionUtils.isEmpty(promoteFormProductSpuVOList)) {
            return promoteFormProductSpuVOList;
        }
        promoteFormProductSpuVOList.forEach(promoteFormProductSpuVO -> {
        });
        return promoteFormProductSpuVOList;
    }

    /**
     * TODO 集合，增强返回参数追加  返回实体对象
     *
     * @param promoteFormProductSpuList
     * @return List<PromoteFormProductSpu>
     * @author lijh
     * @methodName assignment
     * @time 2022-10-28 03:09:32
     */
    public List<PromoteFormProductSpu> assignmentDo(List<PromoteFormProductSpu> promoteFormProductSpuList) {
        if (CollectionUtils.isEmpty(promoteFormProductSpuList)) {
            return promoteFormProductSpuList;
        }
        promoteFormProductSpuList.forEach(promoteFormProductSpuVO -> {
            Map<String, Object> map = new HashMap<>(1);
            map.put("id", promoteFormProductSpuVO.getSpuId());
            ProductSpu productSpu = rpcComponent.rpcQueryInfo(map, RpcTypeEnum.GET_ONE_PRODUCT_SPU, ProductSpu.class);
            promoteFormProductSpuVO.setProductSpu(productSpu);
        });
        return promoteFormProductSpuList;
    }


    /**
     * TODO DO转化VO
     *
     * @param pageDO 推广表单产品id
     * @return Page<PromoteFormProductSpuVO>
     * @author lijh
     * @methodName toPageVO
     * @time 2022-10-28 03:09:32
     */
    public Page<PromoteFormProductSpuVO> toPageVO(Page<PromoteFormProductSpu> pageDO) {
        Page<PromoteFormProductSpuVO> pageVO = new Page<PromoteFormProductSpuVO>();
        pageVO.setRecords(GeneralConvertor.convertor(pageDO.getRecords(), PromoteFormProductSpuVO.class));
        pageVO.setCurrent(pageDO.getCurrent());
        pageVO.setPages(pageDO.getPages());
        pageVO.setSize(pageDO.getSize());
        pageVO.setTotal(pageDO.getTotal());
        return pageVO;
    }
}