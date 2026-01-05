package com.gb.configuration.service;

import com.gb.configuration.entity.query.ProjectTypeValueQuery;
import com.gb.configuration.entity.vo.ProjectTypeValueVO;
import com.gb.configuration.entity.bo.ProjectTypeValueBO;
import com.gb.configuration.entity.ProjectTypeValue;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;


/**
 * TODO 项目类型值，Service服务接口层
 * 代码生成器
 *
 * @author lijh
 * @className ProjectTypeValueService
 * @time 2022-05-05 02:45:25
 */
public interface ProjectTypeValueService extends IService<ProjectTypeValue> {


    /**
     * TODO 分页
     *
     * @param page
     * @param projectTypeValueQuery 项目类型值
     * @return Page<ProjectTypeValueVO>
     * @author {author}
     * @methodName pageEnhance
     * @time 2022-05-05 02:45:25
     */
    Page<ProjectTypeValueVO> pageEnhance(Page page, ProjectTypeValueQuery projectTypeValueQuery);


    /**
     * TODO 集合
     *
     * @param projectTypeValueQuery 项目类型值
     * @return List<ProjectTypeValueVO>
     * @author lijh
     * @methodName listEnhance
     * @time 2022-05-05 02:45:25
     */
    List<ProjectTypeValueVO> listEnhance(ProjectTypeValueQuery projectTypeValueQuery);


    /**
     * TODO 单条
     *
     * @param projectTypeValueQuery 项目类型值
     * @return ProjectTypeValueVO
     * @author lijh
     * @methodName getOneEnhance
     * @time 2022-05-05 02:45:25
     */
    ProjectTypeValueVO getOneEnhance(ProjectTypeValueQuery projectTypeValueQuery);


    /**
     * TODO 总数
     *
     * @param projectTypeValueQuery 项目类型值
     * @return Integer
     * @author lijh
     * @methodName countEnhance
     * @time 2022-05-05 02:45:25
     */
    Long countEnhance(ProjectTypeValueQuery projectTypeValueQuery);


    /**
     * TODO 新增
     *
     * @param projectTypeValueBO 项目类型值
     * @return String
     * @author lijh
     * @methodName saveEnhance
     * @time 2022-05-05 02:45:25
     */
    String saveEnhance(ProjectTypeValueBO projectTypeValueBO);


    /**
     * TODO 修改
     *
     * @param projectTypeValueBO 项目类型值
     * @return Boolean
     * @author lijh
     * @methodName updateEnhance
     * @time 2022-05-05 02:45:25
     */
    Boolean updateEnhance(ProjectTypeValueBO projectTypeValueBO);


    /**
     * TODO 删除
     *
     * @param projectTypeValueBO 项目类型值
     * @return Boolean
     * @author lijh
     * @methodName removeEnhance
     * @time 2022-05-05 02:45:25
     */
    Boolean removeEnhance(ProjectTypeValueBO projectTypeValueBO);
}
