package com.gb.promoteform.service;

import com.gb.promoteform.entity.query.PromoteFormWechatQuery;
import com.gb.promoteform.entity.vo.PromoteFormWechatVO;
import com.gb.promoteform.entity.bo.PromoteFormWechatBO;
import com.gb.promoteform.entity.PromoteFormWechat;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;


/**
 * TODO 推广表单微信，Service服务接口层
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormWechatService
 * @time 2022-07-04 10:49:05
 */
public interface PromoteFormWechatService extends IService<PromoteFormWechat> {


    /**
     * TODO 分页
     *
     * @param page
     * @param promoteFormWechatQuery 推广表单微信
     * @return Page<PromoteFormWechatVO>
     * @author lijh
     * @methodName pageEnhance
     * @time 2022-07-04 10:49:05
     */
    Page<PromoteFormWechatVO> pageEnhance(Page page, PromoteFormWechatQuery promoteFormWechatQuery);


    /**
     * TODO 集合
     *
     * @param promoteFormWechatQuery 推广表单微信
     * @return List<PromoteFormWechatVO>
     * @author lijh
     * @methodName listEnhance
     * @time 2022-07-04 10:49:05
     */
    List<PromoteFormWechatVO> listEnhance(PromoteFormWechatQuery promoteFormWechatQuery);


    /**
     * TODO 单条
     *
     * @param promoteFormWechatQuery 推广表单微信
     * @return PromoteFormWechatVO
     * @author lijh
     * @methodName getOneEnhance
     * @time 2022-07-04 10:49:05
     */
    PromoteFormWechatVO getOneEnhance(PromoteFormWechatQuery promoteFormWechatQuery);


    /**
     * TODO 总数
     *
     * @param promoteFormWechatQuery 推广表单微信
     * @return Long
     * @author lijh
     * @methodName countEnhance
     * @time 2022-07-04 10:49:05
     */
    Long countEnhance(PromoteFormWechatQuery promoteFormWechatQuery);


    /**
     * TODO 新增
     *
     * @param promoteFormWechatBO 推广表单微信
     * @return String
     * @author lijh
     * @methodName saveEnhance
     * @time 2022-07-04 10:49:05
     */
    String saveEnhance(PromoteFormWechatBO promoteFormWechatBO);


    /**
     * TODO 修改
     *
     * @param promoteFormWechatBO 推广表单微信
     * @return Boolean
     * @author lijh
     * @methodName updateEnhance
     * @time 2022-07-04 10:49:05
     */
    Boolean updateEnhance(PromoteFormWechatBO promoteFormWechatBO);


    /**
     * TODO 删除
     *
     * @param promoteFormWechatBO 推广表单微信
     * @return Boolean
     * @author lijh
     * @methodName removeEnhance
     * @time 2022-07-04 10:49:05
     */
    Boolean removeEnhance(PromoteFormWechatBO promoteFormWechatBO);
}
