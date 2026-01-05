package com.gb.customer.service;

import com.gb.customer.entity.query.AssistantAssociatedQuery;
import com.gb.customer.entity.vo.AssistantAssociatedVO;
import com.gb.customer.entity.bo.AssistantAssociatedBO;
import com.gb.customer.entity.AssistantAssociated;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * @author:     	lijh
 * @since:   	    2021-11-02 10:03:07
 * @description:	TODO  助理关联，Service服务接口层
 * @source:  	    代码生成器
 */
public interface AssistantAssociatedService extends IService<AssistantAssociated> {


    /**
     * 集合条件查询
     * @author  lijh
     * @since   2021-11-02 10:03:07
     * @param   assistantAssociatedQuery:
     * @return  java.util.List<com.entity.AssistantAssociatedVO>
     */
    List<AssistantAssociatedVO> listEnhance(AssistantAssociatedQuery assistantAssociatedQuery);


    /**
     * 分页条件查询
     * @author  lijh
     * @since   2021-11-02 10:03:07
     * @param   page:
     * @param   assistantAssociatedQuery:
     * @return  com.baomidou.mybatisplus.extension.plugins.pagination.Page
     */
    Page<AssistantAssociatedVO> pageEnhance(Page page, AssistantAssociatedQuery assistantAssociatedQuery);


    /**
     * 单条条件查询
     * @author  lijh
     * @since   2021-11-02 10:03:07
     * @param   assistantAssociatedQuery:
     * @return  java.util.List<com.entity.AssistantAssociatedVO>
     */
    AssistantAssociatedVO getOneEnhance(AssistantAssociatedQuery assistantAssociatedQuery);


    /**
     * 总数
     * @author  lijh
     * @since   2021-11-02 10:03:07
     * @param   assistantAssociatedQuery:
     * @return  java.lang.Integer
     */
    Long countEnhance(AssistantAssociatedQuery assistantAssociatedQuery);


    /**
     * 新增
     * @author  lijh
     * @since   2021-11-02 10:03:07
     * @param   assistantAssociatedBO:
     * @return  java.lang.String
     */
     String saveEnhance(AssistantAssociatedBO assistantAssociatedBO);


    /**
     * 修改
     * @author  lijh
     * @since   2021-11-02 10:03:07
     * @param   assistantAssociatedBO:
     * @return  java.lang.Boolean
     */
    Boolean updateEnhance(AssistantAssociatedBO assistantAssociatedBO);


    /**
     * 删除
     * @author  lijh
     * @since   2021-11-02 10:03:07
     * @param   assistantAssociatedBO:
     * @return  java.lang.Boolean
     */
    Boolean removeEnhance(AssistantAssociatedBO assistantAssociatedBO);
}
