package com.gb.promoteform.service;

import com.gb.promoteform.entity.query.PromoteFormQuery;
import com.gb.promoteform.entity.vo.PromoteFormVO;
import com.gb.promoteform.entity.bo.PromoteFormBO;
import com.gb.promoteform.entity.PromoteForm;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;


/**
 * TODO 推广表单，Service服务接口层
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormService
 * @time 2022-07-04 10:47:25
 */
public interface PromoteFormService extends IService<PromoteForm> {


    /**
     * TODO 分页
     *
     * @param page
     * @param promoteFormQuery 推广表单
     * @return Page<PromoteFormVO>
     * @author lijh
     * @methodName pageEnhance
     * @time 2022-07-04 10:47:25
     */
    Page<PromoteFormVO> pageEnhance(Page page, PromoteFormQuery promoteFormQuery);


    /**
     * TODO 集合
     *
     * @param promoteFormQuery 推广表单
     * @return List<PromoteFormVO>
     * @author lijh
     * @methodName listEnhance
     * @time 2022-07-04 10:47:25
     */
    List<PromoteFormVO> listEnhance(PromoteFormQuery promoteFormQuery);


    /**
     * TODO 单条
     *
     * @param promoteFormQuery 推广表单
     * @return PromoteFormVO
     * @author lijh
     * @methodName getOneEnhance
     * @time 2022-07-04 10:47:25
     */
    PromoteFormVO getOneEnhance(PromoteFormQuery promoteFormQuery);


    /**
     * TODO 总数
     *
     * @param promoteFormQuery 推广表单
     * @return Long
     * @author lijh
     * @methodName countEnhance
     * @time 2022-07-04 10:47:25
     */
    Long countEnhance(PromoteFormQuery promoteFormQuery);


    /**
     * TODO 新增
     *
     * @param promoteFormBO 推广表单
     * @return String
     * @author lijh
     * @methodName saveEnhance
     * @time 2022-07-04 10:47:25
     */
    String saveEnhance(PromoteFormBO promoteFormBO);


    /**
     * TODO 修改
     *
     * @param promoteFormBO 推广表单
     * @return Boolean
     * @author lijh
     * @methodName updateEnhance
     * @time 2022-07-04 10:47:25
     */
    Boolean updateEnhance(PromoteFormBO promoteFormBO);


    /**
     * TODO 删除
     *
     * @param promoteFormBO 推广表单
     * @return Boolean
     * @author lijh
     * @methodName removeEnhance
     * @time 2022-07-04 10:47:25
     */
    Boolean removeEnhance(PromoteFormBO promoteFormBO);

    /**
     * 经纪人预览表单二维码
     *
     * @param id
     * @param agentUserId
     * @param agentUserName
     * @return
     */
    String previewForm(String id, String agentUserId, String agentUserName);

    /**
     * 预览表单二维码
     *
     * @param qrCodeLinkAddress 地址
     * @return
     */
    String getQrCodeLinkAddress(String qrCodeLinkAddress);
}
