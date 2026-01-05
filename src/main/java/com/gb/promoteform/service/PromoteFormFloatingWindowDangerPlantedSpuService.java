package com.gb.promoteform.service;

import com.gb.promoteform.entity.query.PromoteFormFloatingWindowDangerPlantedSpuQuery;
import com.gb.promoteform.entity.vo.PromoteFormFloatingWindowDangerPlantedSpuVO;
import com.gb.promoteform.entity.bo.PromoteFormFloatingWindowDangerPlantedSpuBO;
import com.gb.promoteform.entity.PromoteFormFloatingWindowDangerPlantedSpu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;


/**
 * TODO 推广表单浮框险种产品，Service服务接口层
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormFloatingWindowDangerPlantedSpuService
 * @time 2022-10-28 03:09:31
 */
public interface PromoteFormFloatingWindowDangerPlantedSpuService extends IService<PromoteFormFloatingWindowDangerPlantedSpu> {


    /**
     * TODO 分页
     *
     * @param page
     * @param promoteFormFloatingWindowDangerPlantedSpuQuery 推广表单浮框险种产品
     * @return Page<PromoteFormFloatingWindowDangerPlantedSpuVO>
     * @author lijh
     * @methodName pageEnhance
     * @time 2022-10-28 03:09:31
     */
    Page<PromoteFormFloatingWindowDangerPlantedSpuVO> pageEnhance(Page page, PromoteFormFloatingWindowDangerPlantedSpuQuery promoteFormFloatingWindowDangerPlantedSpuQuery);


    /**
     * TODO 集合
     *
     * @param promoteFormFloatingWindowDangerPlantedSpuQuery 推广表单浮框险种产品
     * @return List<PromoteFormFloatingWindowDangerPlantedSpuVO>
     * @author lijh
     * @methodName listEnhance
     * @time 2022-10-28 03:09:31
     */
    List<PromoteFormFloatingWindowDangerPlantedSpuVO> listEnhance(PromoteFormFloatingWindowDangerPlantedSpuQuery promoteFormFloatingWindowDangerPlantedSpuQuery);


    /**
     * TODO 单条
     *
     * @param promoteFormFloatingWindowDangerPlantedSpuQuery 推广表单浮框险种产品
     * @return PromoteFormFloatingWindowDangerPlantedSpuVO
     * @author lijh
     * @methodName getOneEnhance
     * @time 2022-10-28 03:09:31
     */
    PromoteFormFloatingWindowDangerPlantedSpuVO getOneEnhance(PromoteFormFloatingWindowDangerPlantedSpuQuery promoteFormFloatingWindowDangerPlantedSpuQuery);


    /**
     * TODO 总数
     *
     * @param promoteFormFloatingWindowDangerPlantedSpuQuery 推广表单浮框险种产品
     * @return Long
     * @author lijh
     * @methodName countEnhance
     * @time 2022-10-28 03:09:31
     */
    Long countEnhance(PromoteFormFloatingWindowDangerPlantedSpuQuery promoteFormFloatingWindowDangerPlantedSpuQuery);


    /**
     * TODO 新增
     *
     * @param promoteFormFloatingWindowDangerPlantedSpuBO 推广表单浮框险种产品
     * @return String
     * @author lijh
     * @methodName saveEnhance
     * @time 2022-10-28 03:09:31
     */
    String saveEnhance(PromoteFormFloatingWindowDangerPlantedSpuBO promoteFormFloatingWindowDangerPlantedSpuBO);


    /**
     * TODO 修改
     *
     * @param promoteFormFloatingWindowDangerPlantedSpuBO 推广表单浮框险种产品
     * @return Boolean
     * @author lijh
     * @methodName updateEnhance
     * @time 2022-10-28 03:09:31
     */
    Boolean updateEnhance(PromoteFormFloatingWindowDangerPlantedSpuBO promoteFormFloatingWindowDangerPlantedSpuBO);


    /**
     * TODO 删除
     *
     * @param promoteFormFloatingWindowDangerPlantedSpuBO 推广表单浮框险种产品
     * @return Boolean
     * @author lijh
     * @methodName removeEnhance
     * @time 2022-10-28 03:09:31
     */
    Boolean removeEnhance(PromoteFormFloatingWindowDangerPlantedSpuBO promoteFormFloatingWindowDangerPlantedSpuBO);
}
