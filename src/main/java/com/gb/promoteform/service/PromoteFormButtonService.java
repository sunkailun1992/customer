package com.gb.promoteform.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gb.promoteform.entity.PromoteFormButton;
import com.gb.promoteform.entity.bo.PromoteFormBO;
import com.gb.promoteform.entity.bo.PromoteFormButtonBO;
import com.gb.promoteform.entity.query.PromoteFormButtonQuery;
import com.gb.promoteform.entity.vo.PromoteFormButtonVO;

import java.util.List;


/**
 * TODO 推广表单按钮，Service服务接口层
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormButtonService
 * @time 2022-07-04 10:49:04
 */
public interface PromoteFormButtonService extends IService<PromoteFormButton> {


    /**
     * TODO 分页
     *
     * @param page
     * @param promoteFormButtonQuery 推广表单按钮
     * @return Page<PromoteFormButtonVO>
     * @author lijh
     * @methodName pageEnhance
     * @time 2022-07-04 10:49:04
     */
    Page<PromoteFormButtonVO> pageEnhance(Page page, PromoteFormButtonQuery promoteFormButtonQuery);


    /**
     * TODO 集合
     *
     * @param promoteFormButtonQuery 推广表单按钮
     * @return List<PromoteFormButtonVO>
     * @author lijh
     * @methodName listEnhance
     * @time 2022-07-04 10:49:04
     */
    List<PromoteFormButtonVO> listEnhance(PromoteFormButtonQuery promoteFormButtonQuery);


    /**
     * TODO 单条
     *
     * @param promoteFormButtonQuery 推广表单按钮
     * @return PromoteFormButtonVO
     * @author lijh
     * @methodName getOneEnhance
     * @time 2022-07-04 10:49:04
     */
    PromoteFormButtonVO getOneEnhance(PromoteFormButtonQuery promoteFormButtonQuery);


    /**
     * TODO 总数
     *
     * @param promoteFormButtonQuery 推广表单按钮
     * @return Long
     * @author lijh
     * @methodName countEnhance
     * @time 2022-07-04 10:49:04
     */
    Long countEnhance(PromoteFormButtonQuery promoteFormButtonQuery);


    /**
     * TODO 新增
     *
     * @param promoteFormButtonBO 推广表单按钮
     * @return String
     * @author lijh
     * @methodName saveEnhance
     * @time 2022-07-04 10:49:04
     */
    String saveEnhance(PromoteFormButtonBO promoteFormButtonBO);


    /**
     * TODO 修改
     *
     * @param promoteFormButtonBO 推广表单按钮
     * @return Boolean
     * @author lijh
     * @methodName updateEnhance
     * @time 2022-07-04 10:49:04
     */
    Boolean updateEnhance(PromoteFormButtonBO promoteFormButtonBO);


    /**
     * TODO 删除
     *
     * @param promoteFormButtonBO 推广表单按钮
     * @return Boolean
     * @author lijh
     * @methodName removeEnhance
     * @time 2022-07-04 10:49:04
     */
    Boolean removeEnhance(PromoteFormButtonBO promoteFormButtonBO);

    /**
     * 修改产品信息
     *
     * @param promoteFormBO 表单信息
     */
    void updateEnhanceByForm(PromoteFormBO promoteFormBO);
}
