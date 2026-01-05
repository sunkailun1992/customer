package com.gb.promoteform.service;

import com.gb.promoteform.entity.query.PromoteFormFloatingWindowQuery;
import com.gb.promoteform.entity.vo.PromoteFormFloatingWindowVO;
import com.gb.promoteform.entity.bo.PromoteFormFloatingWindowBO;
import com.gb.promoteform.entity.PromoteFormFloatingWindow;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;


/**
 * TODO 推广表单浮框，Service服务接口层
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormFloatingWindowService
 * @time 2022-07-04 10:49:04
 */
public interface PromoteFormFloatingWindowService extends IService<PromoteFormFloatingWindow> {


    /**
     * TODO 分页
     *
     * @param page
     * @param promoteFormFloatingWindowQuery 推广表单浮框
     * @return Page<PromoteFormFloatingWindowVO>
     * @author lijh
     * @methodName pageEnhance
     * @time 2022-07-04 10:49:04
     */
    Page<PromoteFormFloatingWindowVO> pageEnhance(Page page, PromoteFormFloatingWindowQuery promoteFormFloatingWindowQuery);


    /**
     * TODO 集合
     *
     * @param promoteFormFloatingWindowQuery 推广表单浮框
     * @return List<PromoteFormFloatingWindowVO>
     * @author lijh
     * @methodName listEnhance
     * @time 2022-07-04 10:49:04
     */
    List<PromoteFormFloatingWindowVO> listEnhance(PromoteFormFloatingWindowQuery promoteFormFloatingWindowQuery);


    /**
     * TODO 单条
     *
     * @param promoteFormFloatingWindowQuery 推广表单浮框
     * @return PromoteFormFloatingWindowVO
     * @author lijh
     * @methodName getOneEnhance
     * @time 2022-07-04 10:49:04
     */
    PromoteFormFloatingWindowVO getOneEnhance(PromoteFormFloatingWindowQuery promoteFormFloatingWindowQuery);


    /**
     * TODO 总数
     *
     * @param promoteFormFloatingWindowQuery 推广表单浮框
     * @return Long
     * @author lijh
     * @methodName countEnhance
     * @time 2022-07-04 10:49:04
     */
    Long countEnhance(PromoteFormFloatingWindowQuery promoteFormFloatingWindowQuery);


    /**
     * TODO 新增
     *
     * @param promoteFormFloatingWindowBO 推广表单浮框
     * @return String
     * @author lijh
     * @methodName saveEnhance
     * @time 2022-07-04 10:49:04
     */
    String saveEnhance(PromoteFormFloatingWindowBO promoteFormFloatingWindowBO);


    /**
     * TODO 修改
     *
     * @param promoteFormFloatingWindowBO 推广表单浮框
     * @return Boolean
     * @author lijh
     * @methodName updateEnhance
     * @time 2022-07-04 10:49:04
     */
    Boolean updateEnhance(PromoteFormFloatingWindowBO promoteFormFloatingWindowBO);


    /**
     * TODO 删除
     *
     * @param promoteFormFloatingWindowBO 推广表单浮框
     * @return Boolean
     * @author lijh
     * @methodName removeEnhance
     * @time 2022-07-04 10:49:04
     */
    Boolean removeEnhance(PromoteFormFloatingWindowBO promoteFormFloatingWindowBO);
}
