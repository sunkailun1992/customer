package com.gb.promoteform.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gb.promoteform.entity.PromoteFormProduct;
import com.gb.promoteform.entity.bo.PromoteFormBO;
import com.gb.promoteform.entity.bo.PromoteFormProductBO;
import com.gb.promoteform.entity.query.PromoteFormProductQuery;
import com.gb.promoteform.entity.vo.PromoteFormProductVO;

import java.util.List;


/**
 * TODO 推广表单产品，Service服务接口层
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormProductService
 * @time 2022-07-04 10:49:04
 */
public interface PromoteFormProductService extends IService<PromoteFormProduct> {


    /**
     * TODO 分页
     *
     * @param page
     * @param promoteFormProductQuery 推广表单产品
     * @return Page<PromoteFormProductVO>
     * @author lijh
     * @methodName pageEnhance
     * @time 2022-07-04 10:49:04
     */
    Page<PromoteFormProductVO> pageEnhance(Page page, PromoteFormProductQuery promoteFormProductQuery);


    /**
     * TODO 集合
     *
     * @param promoteFormProductQuery 推广表单产品
     * @return List<PromoteFormProductVO>
     * @author lijh
     * @methodName listEnhance
     * @time 2022-07-04 10:49:04
     */
    List<PromoteFormProductVO> listEnhance(PromoteFormProductQuery promoteFormProductQuery);


    /**
     * TODO 单条
     *
     * @param promoteFormProductQuery 推广表单产品
     * @return PromoteFormProductVO
     * @author lijh
     * @methodName getOneEnhance
     * @time 2022-07-04 10:49:04
     */
    PromoteFormProductVO getOneEnhance(PromoteFormProductQuery promoteFormProductQuery);


    /**
     * TODO 总数
     *
     * @param promoteFormProductQuery 推广表单产品
     * @return Long
     * @author lijh
     * @methodName countEnhance
     * @time 2022-07-04 10:49:04
     */
    Long countEnhance(PromoteFormProductQuery promoteFormProductQuery);


    /**
     * TODO 新增
     *
     * @param promoteFormProductBO 推广表单产品
     * @return String
     * @author lijh
     * @methodName saveEnhance
     * @time 2022-07-04 10:49:04
     */
    String saveEnhance(PromoteFormProductBO promoteFormProductBO);


    /**
     * TODO 修改
     *
     * @param promoteFormProductBO 推广表单产品
     * @return Boolean
     * @author lijh
     * @methodName updateEnhance
     * @time 2022-07-04 10:49:04
     */
    Boolean updateEnhance(PromoteFormProductBO promoteFormProductBO);


    /**
     * TODO 删除
     *
     * @param promoteFormProductBO 推广表单产品
     * @return Boolean
     * @author lijh
     * @methodName removeEnhance
     * @time 2022-07-04 10:49:04
     */
    Boolean removeEnhance(PromoteFormProductBO promoteFormProductBO);


    /**
     * 修改产品信息
     *
     * @param promoteFormBO 表单信息
     */
    void updateEnhanceByForm(PromoteFormBO promoteFormBO);
}
