package com.gb.configuration.service;

import com.gb.configuration.entity.query.ProjectTypeQuery;
import com.gb.configuration.entity.vo.ProjectTypeVO;
import com.gb.configuration.entity.bo.ProjectTypeBO;
import com.gb.configuration.entity.ProjectType;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;


/**
 * TODO 项目类型，Service服务接口层
 * 代码生成器
 *
 * @author lijh
 * @className ProjectTypeService
 * @time 2022-05-05 02:45:25
 */
public interface ProjectTypeService extends IService<ProjectType> {


    /**
     * TODO 分页
     *
     * @param page
     * @param projectTypeQuery 项目类型
     * @return Page<ProjectTypeVO>
     * @author {author}
     * @methodName pageEnhance
     * @time 2022-05-05 02:45:25
     */
    Page<ProjectTypeVO> pageEnhance(Page page, ProjectTypeQuery projectTypeQuery);


    /**
     * TODO 集合
     *
     * @param projectTypeQuery 项目类型
     * @return List<ProjectTypeVO>
     * @author lijh
     * @methodName listEnhance
     * @time 2022-05-05 02:45:25
     */
    List<ProjectTypeVO> listEnhance(ProjectTypeQuery projectTypeQuery);


    /**
     * TODO 单条
     *
     * @param projectTypeQuery 项目类型
     * @return ProjectTypeVO
     * @author lijh
     * @methodName getOneEnhance
     * @time 2022-05-05 02:45:25
     */
    ProjectTypeVO getOneEnhance(ProjectTypeQuery projectTypeQuery);


    /**
     * TODO 总数
     *
     * @param projectTypeQuery 项目类型
     * @return Integer
     * @author lijh
     * @methodName countEnhance
     * @time 2022-05-05 02:45:25
     */
    Long countEnhance(ProjectTypeQuery projectTypeQuery);


    /**
     * TODO 新增
     *
     * @param projectTypeBO 项目类型
     * @return String
     * @author lijh
     * @methodName saveEnhance
     * @time 2022-05-05 02:45:25
     */
    String saveEnhance(ProjectTypeBO projectTypeBO);


    /**
     * TODO 修改
     *
     * @param projectTypeBO 项目类型
     * @return Boolean
     * @author lijh
     * @methodName updateEnhance
     * @time 2022-05-05 02:45:25
     */
    Boolean updateEnhance(ProjectTypeBO projectTypeBO);


    /**
     * TODO 删除
     *
     * @param projectTypeBO 项目类型
     * @return Boolean
     * @author lijh
     * @methodName removeEnhance
     * @time 2022-05-05 02:45:25
     */
    Boolean removeEnhance(ProjectTypeBO projectTypeBO);
}
