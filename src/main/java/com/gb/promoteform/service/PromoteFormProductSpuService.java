package com.gb.promoteform.service;

import com.gb.promoteform.entity.PromoteFormProduct;
import com.gb.promoteform.entity.bo.PromoteFormBO;
import com.gb.promoteform.entity.query.PromoteFormProductSpuQuery;
import com.gb.promoteform.entity.vo.PromoteFormProductSpuVO;
import com.gb.promoteform.entity.bo.PromoteFormProductSpuBO;
import com.gb.promoteform.entity.PromoteFormProductSpu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;


/**
 * TODO 推广表单产品id，Service服务接口层
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormProductSpuService
 * @time 2022-10-28 03:09:32
 */
public interface PromoteFormProductSpuService extends IService<PromoteFormProductSpu> {


    /**
     * TODO 分页
     *
     * @param page
     * @param promoteFormProductSpuQuery 推广表单产品id
     * @return Page<PromoteFormProductSpuVO>
     * @author lijh
     * @methodName pageEnhance
     * @time 2022-10-28 03:09:32
     */
    Page<PromoteFormProductSpuVO> pageEnhance(Page page, PromoteFormProductSpuQuery promoteFormProductSpuQuery);


    /**
     * TODO 集合
     *
     * @param promoteFormProductSpuQuery 推广表单产品id
     * @return List<PromoteFormProductSpuVO>
     * @author lijh
     * @methodName listEnhance
     * @time 2022-10-28 03:09:32
     */
    List<PromoteFormProductSpuVO> listEnhance(PromoteFormProductSpuQuery promoteFormProductSpuQuery);


    /**
     * TODO 单条
     *
     * @param promoteFormProductSpuQuery 推广表单产品id
     * @return PromoteFormProductSpuVO
     * @author lijh
     * @methodName getOneEnhance
     * @time 2022-10-28 03:09:32
     */
    PromoteFormProductSpuVO getOneEnhance(PromoteFormProductSpuQuery promoteFormProductSpuQuery);


    /**
     * TODO 总数
     *
     * @param promoteFormProductSpuQuery 推广表单产品id
     * @return Long
     * @author lijh
     * @methodName countEnhance
     * @time 2022-10-28 03:09:32
     */
    Long countEnhance(PromoteFormProductSpuQuery promoteFormProductSpuQuery);


    /**
     * TODO 新增
     *
     * @param promoteFormProductSpuBO 推广表单产品id
     * @return String
     * @author lijh
     * @methodName saveEnhance
     * @time 2022-10-28 03:09:32
     */
    String saveEnhance(PromoteFormProductSpuBO promoteFormProductSpuBO);


    /**
     * TODO 修改
     *
     * @param promoteFormProductSpuBO 推广表单产品id
     * @return Boolean
     * @author lijh
     * @methodName updateEnhance
     * @time 2022-10-28 03:09:32
     */
    Boolean updateEnhance(PromoteFormProductSpuBO promoteFormProductSpuBO);


    /**
     * TODO 删除
     *
     * @param promoteFormProductSpuBO 推广表单产品id
     * @return Boolean
     * @author lijh
     * @methodName removeEnhance
     * @time 2022-10-28 03:09:32
     */
    Boolean removeEnhance(PromoteFormProductSpuBO promoteFormProductSpuBO);

    /**
     * 根据推广表单配置的产品表id 获取产品集合
     *
     * @param promoteFormProductId
     * @return
     */
    List<PromoteFormProductSpu> listByFormProductId(String promoteFormProductId);

    /**
     * 根据表单配置的产品信息 修改产品spu
     * @param promoteFormProduct
     */
    void updateEnhanceByFormProduct(PromoteFormProduct promoteFormProduct);
}
