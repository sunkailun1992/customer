package com.gb.promoteform.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gb.promoteform.entity.PromoteFormLabel;
import com.gb.promoteform.entity.bo.PromoteFormLabelBO;
import com.gb.promoteform.entity.query.PromoteFormLabelQuery;
import com.gb.promoteform.entity.vo.PromoteFormLabelVO;
import com.gb.rpc.entity.UserLabelInfoVO;

import java.util.List;


/**
 * TODO 推广表单标签，Service服务接口层
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormLabelService
 * @time 2022-07-04 10:49:04
 */
public interface PromoteFormLabelService extends IService<PromoteFormLabel> {


    /**
     * TODO 分页
     *
     * @param page
     * @param promoteFormLabelQuery 推广表单标签
     * @return Page<PromoteFormLabelVO>
     * @author lijh
     * @methodName pageEnhance
     * @time 2022-07-04 10:49:04
     */
    Page<PromoteFormLabelVO> pageEnhance(Page page, PromoteFormLabelQuery promoteFormLabelQuery);


    /**
     * TODO 集合
     *
     * @param promoteFormLabelQuery 推广表单标签
     * @return List<PromoteFormLabelVO>
     * @author lijh
     * @methodName listEnhance
     * @time 2022-07-04 10:49:04
     */
    List<PromoteFormLabelVO> listEnhance(PromoteFormLabelQuery promoteFormLabelQuery);


    /**
     * TODO 单条
     *
     * @param promoteFormLabelQuery 推广表单标签
     * @return PromoteFormLabelVO
     * @author lijh
     * @methodName getOneEnhance
     * @time 2022-07-04 10:49:04
     */
    PromoteFormLabelVO getOneEnhance(PromoteFormLabelQuery promoteFormLabelQuery);


    /**
     * TODO 总数
     *
     * @param promoteFormLabelQuery 推广表单标签
     * @return Long
     * @author lijh
     * @methodName countEnhance
     * @time 2022-07-04 10:49:04
     */
    Long countEnhance(PromoteFormLabelQuery promoteFormLabelQuery);


    /**
     * TODO 新增
     *
     * @param promoteFormLabelBO 推广表单标签
     * @return String
     * @author lijh
     * @methodName saveEnhance
     * @time 2022-07-04 10:49:04
     */
    String saveEnhance(PromoteFormLabelBO promoteFormLabelBO);


    /**
     * TODO 修改
     *
     * @param promoteFormLabelBO 推广表单标签
     * @return Boolean
     * @author lijh
     * @methodName updateEnhance
     * @time 2022-07-04 10:49:04
     */
    Boolean updateEnhance(PromoteFormLabelBO promoteFormLabelBO);


    /**
     * TODO 删除
     *
     * @param promoteFormLabelBO 推广表单标签
     * @return Boolean
     * @author lijh
     * @methodName removeEnhance
     * @time 2022-07-04 10:49:04
     */
    Boolean removeEnhance(PromoteFormLabelBO promoteFormLabelBO);

    /**
     * 获取标签列表
     * @param labelName
     * @return
     */
    List<UserLabelInfoVO> getLabelList(String labelName);
}
