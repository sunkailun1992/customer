package com.gb.promoteform.service;

import com.gb.promoteform.entity.query.PromoteFormFloatingWindowDangerPlantedQuery;
import com.gb.promoteform.entity.vo.PromoteFormFloatingWindowDangerPlantedVO;
import com.gb.promoteform.entity.bo.PromoteFormFloatingWindowDangerPlantedBO;
import com.gb.promoteform.entity.PromoteFormFloatingWindowDangerPlanted;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;


/**
 * TODO 推广表单浮框险种，Service服务接口层
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormFloatingWindowDangerPlantedService
 * @time 2022-07-04 10:49:04
 */
public interface PromoteFormFloatingWindowDangerPlantedService extends IService<PromoteFormFloatingWindowDangerPlanted> {


    /**
     * TODO 分页
     *
     * @param page
     * @param promoteFormFloatingWindowDangerPlantedQuery 推广表单浮框险种
     * @return Page<PromoteFormFloatingWindowDangerPlantedVO>
     * @author lijh
     * @methodName pageEnhance
     * @time 2022-07-04 10:49:04
     */
    Page<PromoteFormFloatingWindowDangerPlantedVO> pageEnhance(Page page, PromoteFormFloatingWindowDangerPlantedQuery promoteFormFloatingWindowDangerPlantedQuery);


    /**
     * TODO 集合
     *
     * @param promoteFormFloatingWindowDangerPlantedQuery 推广表单浮框险种
     * @return List<PromoteFormFloatingWindowDangerPlantedVO>
     * @author lijh
     * @methodName listEnhance
     * @time 2022-07-04 10:49:04
     */
    List<PromoteFormFloatingWindowDangerPlantedVO> listEnhance(PromoteFormFloatingWindowDangerPlantedQuery promoteFormFloatingWindowDangerPlantedQuery);


    /**
     * TODO 单条
     *
     * @param promoteFormFloatingWindowDangerPlantedQuery 推广表单浮框险种
     * @return PromoteFormFloatingWindowDangerPlantedVO
     * @author lijh
     * @methodName getOneEnhance
     * @time 2022-07-04 10:49:04
     */
    PromoteFormFloatingWindowDangerPlantedVO getOneEnhance(PromoteFormFloatingWindowDangerPlantedQuery promoteFormFloatingWindowDangerPlantedQuery);


    /**
     * TODO 总数
     *
     * @param promoteFormFloatingWindowDangerPlantedQuery 推广表单浮框险种
     * @return Long
     * @author lijh
     * @methodName countEnhance
     * @time 2022-07-04 10:49:04
     */
    Long countEnhance(PromoteFormFloatingWindowDangerPlantedQuery promoteFormFloatingWindowDangerPlantedQuery);


    /**
     * TODO 新增
     *
     * @param promoteFormFloatingWindowDangerPlantedBO 推广表单浮框险种
     * @return String
     * @author lijh
     * @methodName saveEnhance
     * @time 2022-07-04 10:49:04
     */
    String saveEnhance(PromoteFormFloatingWindowDangerPlantedBO promoteFormFloatingWindowDangerPlantedBO);


    /**
     * TODO 修改
     *
     * @param promoteFormFloatingWindowDangerPlantedBO 推广表单浮框险种
     * @return Boolean
     * @author lijh
     * @methodName updateEnhance
     * @time 2022-07-04 10:49:04
     */
    Boolean updateEnhance(PromoteFormFloatingWindowDangerPlantedBO promoteFormFloatingWindowDangerPlantedBO);


    /**
     * TODO 删除
     *
     * @param promoteFormFloatingWindowDangerPlantedBO 推广表单浮框险种
     * @return Boolean
     * @author lijh
     * @methodName removeEnhance
     * @time 2022-07-04 10:49:04
     */
    Boolean removeEnhance(PromoteFormFloatingWindowDangerPlantedBO promoteFormFloatingWindowDangerPlantedBO);
}
