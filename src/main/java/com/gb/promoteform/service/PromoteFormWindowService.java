package com.gb.promoteform.service;

import com.gb.promoteform.entity.query.PromoteFormWindowQuery;
import com.gb.promoteform.entity.vo.PromoteFormWindowVO;
import com.gb.promoteform.entity.bo.PromoteFormWindowBO;
import com.gb.promoteform.entity.PromoteFormWindow;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;


/**
 * TODO 推广表单窗口，Service服务接口层
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormWindowService
 * @time 2022-07-04 10:49:05
 */
public interface PromoteFormWindowService extends IService<PromoteFormWindow> {


    /**
     * TODO 分页
     *
     * @param page
     * @param promoteFormWindowQuery 推广表单窗口
     * @return Page<PromoteFormWindowVO>
     * @author lijh
     * @methodName pageEnhance
     * @time 2022-07-04 10:49:05
     */
    Page<PromoteFormWindowVO> pageEnhance(Page page, PromoteFormWindowQuery promoteFormWindowQuery);


    /**
     * TODO 集合
     *
     * @param promoteFormWindowQuery 推广表单窗口
     * @return List<PromoteFormWindowVO>
     * @author lijh
     * @methodName listEnhance
     * @time 2022-07-04 10:49:05
     */
    List<PromoteFormWindowVO> listEnhance(PromoteFormWindowQuery promoteFormWindowQuery);


    /**
     * TODO 单条
     *
     * @param promoteFormWindowQuery 推广表单窗口
     * @return PromoteFormWindowVO
     * @author lijh
     * @methodName getOneEnhance
     * @time 2022-07-04 10:49:05
     */
    PromoteFormWindowVO getOneEnhance(PromoteFormWindowQuery promoteFormWindowQuery);


    /**
     * TODO 总数
     *
     * @param promoteFormWindowQuery 推广表单窗口
     * @return Long
     * @author lijh
     * @methodName countEnhance
     * @time 2022-07-04 10:49:05
     */
    Long countEnhance(PromoteFormWindowQuery promoteFormWindowQuery);


    /**
     * TODO 新增
     *
     * @param promoteFormWindowBO 推广表单窗口
     * @return String
     * @author lijh
     * @methodName saveEnhance
     * @time 2022-07-04 10:49:05
     */
    String saveEnhance(PromoteFormWindowBO promoteFormWindowBO);


    /**
     * TODO 修改
     *
     * @param promoteFormWindowBO 推广表单窗口
     * @return Boolean
     * @author lijh
     * @methodName updateEnhance
     * @time 2022-07-04 10:49:05
     */
    Boolean updateEnhance(PromoteFormWindowBO promoteFormWindowBO);


    /**
     * TODO 删除
     *
     * @param promoteFormWindowBO 推广表单窗口
     * @return Boolean
     * @author lijh
     * @methodName removeEnhance
     * @time 2022-07-04 10:49:05
     */
    Boolean removeEnhance(PromoteFormWindowBO promoteFormWindowBO);
}
