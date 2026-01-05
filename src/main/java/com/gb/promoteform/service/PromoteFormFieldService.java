package com.gb.promoteform.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gb.promoteform.entity.PromoteFormField;
import com.gb.promoteform.entity.bo.PromoteFormBO;
import com.gb.promoteform.entity.bo.PromoteFormFieldBO;
import com.gb.promoteform.entity.query.PromoteFormFieldQuery;
import com.gb.promoteform.entity.vo.PromoteFormFieldVO;

import java.util.List;


/**
 * TODO 推广表单字段，Service服务接口层
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormFieldService
 * @time 2022-07-04 10:49:04
 */
public interface PromoteFormFieldService extends IService<PromoteFormField> {


    /**
     * TODO 分页
     *
     * @param page
     * @param promoteFormFieldQuery 推广表单字段
     * @return Page<PromoteFormFieldVO>
     * @author lijh
     * @methodName pageEnhance
     * @time 2022-07-04 10:49:04
     */
    Page<PromoteFormFieldVO> pageEnhance(Page page, PromoteFormFieldQuery promoteFormFieldQuery);


    /**
     * TODO 集合
     *
     * @param promoteFormFieldQuery 推广表单字段
     * @return List<PromoteFormFieldVO>
     * @author lijh
     * @methodName listEnhance
     * @time 2022-07-04 10:49:04
     */
    List<PromoteFormFieldVO> listEnhance(PromoteFormFieldQuery promoteFormFieldQuery);


    /**
     * TODO 单条
     *
     * @param promoteFormFieldQuery 推广表单字段
     * @return PromoteFormFieldVO
     * @author lijh
     * @methodName getOneEnhance
     * @time 2022-07-04 10:49:04
     */
    PromoteFormFieldVO getOneEnhance(PromoteFormFieldQuery promoteFormFieldQuery);


    /**
     * TODO 总数
     *
     * @param promoteFormFieldQuery 推广表单字段
     * @return Long
     * @author lijh
     * @methodName countEnhance
     * @time 2022-07-04 10:49:04
     */
    Long countEnhance(PromoteFormFieldQuery promoteFormFieldQuery);


    /**
     * TODO 新增
     *
     * @param promoteFormFieldBO 推广表单字段
     * @return String
     * @author lijh
     * @methodName saveEnhance
     * @time 2022-07-04 10:49:04
     */
    String saveEnhance(PromoteFormFieldBO promoteFormFieldBO);


    /**
     * TODO 修改
     *
     * @param promoteFormFieldBO 推广表单字段
     * @return Boolean
     * @author lijh
     * @methodName updateEnhance
     * @time 2022-07-04 10:49:04
     */
    Boolean updateEnhance(PromoteFormFieldBO promoteFormFieldBO);


    /**
     * TODO 删除
     *
     * @param promoteFormFieldBO 推广表单字段
     * @return Boolean
     * @author lijh
     * @methodName removeEnhance
     * @time 2022-07-04 10:49:04
     */
    Boolean removeEnhance(PromoteFormFieldBO promoteFormFieldBO);

    /**
     * 修改表单字段
     *
     * @param promoteFormBO 表单信息
     */
    void updateEnhanceByForm(PromoteFormBO promoteFormBO);
}
