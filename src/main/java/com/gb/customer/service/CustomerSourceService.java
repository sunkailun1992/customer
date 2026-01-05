package com.gb.customer.service;

import com.gb.customer.entity.query.CustomerSourceQuery;
import com.gb.customer.entity.vo.CustomerSourceVO;
import com.gb.customer.entity.bo.CustomerSourceBO;
import com.gb.customer.entity.CustomerSource;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;


/**
 * TODO 客户来源，Service服务接口层
 * 代码生成器
 *
 * @author lijh
 * @className CustomerSourceService
 * @time 2022-09-01 03:12:09
 */
public interface CustomerSourceService extends IService<CustomerSource> {


    /**
     * TODO 分页
     *
     * @param page
     * @param customerSourceQuery 客户来源
     * @return Page<CustomerSourceVO>
     * @author lijh
     * @methodName pageEnhance
     * @time 2022-09-01 03:12:09
     */
    Page<CustomerSourceVO> pageEnhance(Page page, CustomerSourceQuery customerSourceQuery);


    /**
     * TODO 集合
     *
     * @param customerSourceQuery 客户来源
     * @return List<CustomerSourceVO>
     * @author lijh
     * @methodName listEnhance
     * @time 2022-09-01 03:12:09
     */
    List<CustomerSourceVO> listEnhance(CustomerSourceQuery customerSourceQuery);


    /**
     * TODO 单条
     *
     * @param customerSourceQuery 客户来源
     * @return CustomerSourceVO
     * @author lijh
     * @methodName getOneEnhance
     * @time 2022-09-01 03:12:09
     */
    CustomerSourceVO getOneEnhance(CustomerSourceQuery customerSourceQuery);


    /**
     * TODO 总数
     *
     * @param customerSourceQuery 客户来源
     * @return Long
     * @author lijh
     * @methodName countEnhance
     * @time 2022-09-01 03:12:09
     */
    Long countEnhance(CustomerSourceQuery customerSourceQuery);


    /**
     * TODO 新增
     *
     * @param customerSourceBO 客户来源
     * @return String
     * @author lijh
     * @methodName saveEnhance
     * @time 2022-09-01 03:12:09
     */
    String saveEnhance(CustomerSourceBO customerSourceBO);


    /**
     * TODO 修改
     *
     * @param customerSourceBO 客户来源
     * @return Boolean
     * @author lijh
     * @methodName updateEnhance
     * @time 2022-09-01 03:12:09
     */
    Boolean updateEnhance(CustomerSourceBO customerSourceBO);


    /**
     * TODO 删除
     *
     * @param customerSourceBO 客户来源
     * @return Boolean
     * @author lijh
     * @methodName removeEnhance
     * @time 2022-09-01 03:12:09
     */
    Boolean removeEnhance(CustomerSourceBO customerSourceBO);
}
