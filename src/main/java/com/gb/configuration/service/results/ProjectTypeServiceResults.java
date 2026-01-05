package com.gb.configuration.service.results;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.gb.configuration.entity.ProjectType;
import com.gb.configuration.entity.query.ProjectTypeValueQuery;
import com.gb.configuration.entity.vo.ProjectTypeVO;
import com.gb.configuration.entity.bo.ProjectTypeBO;
import com.gb.configuration.entity.vo.ProjectTypeValueVO;
import com.gb.configuration.service.ProjectTypeValueService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import com.gb.utils.GeneralConvertor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * TODO 项目类型,Service返回实现
 * 代码生成器
 *
 * @author lijh
 * @className ProjectTypeServiceResults
 * @time 2022-05-05 02:45:25
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class ProjectTypeServiceResults {

    private ProjectTypeValueService projectTypeValueService;


    /**
     * TODO 单条，增强返回参数追加
     *
     * @param projectTypeVO 项目类型
     * @return ProjectTypeVO
     * @author lijh
     * @methodName assignment
     * @time 2022-05-05 02:45:25
     */
    public ProjectTypeVO assignment(ProjectTypeVO projectTypeVO) {
        if (projectTypeVO != null) {
            return projectTypeVO;
        } else {
            return projectTypeVO;
        }
    }


    /**
     * TODO 分页，增强返回参数追加
     *
     * @param projectTypeVOList 项目类型
     * @return Page<ProjectTypeVO>
     * @author lijh
     * @methodName assignment
     * @time 2022-05-05 02:45:25
     */
    public Page<ProjectTypeVO> assignment(Page<ProjectTypeVO> projectTypeVOList) {
        projectTypeVOList.getRecords().forEach(projectTypeVO -> {
            List<ProjectTypeValueVO> projectTypeValueVOList = projectTypeValueService.listEnhance(new ProjectTypeValueQuery() {{
                setProjectTypeId(projectTypeVO.getId());
            }});
            if (CollectionUtils.isNotEmpty(projectTypeValueVOList)) {
                projectTypeVO.setProjectTypeValueList(projectTypeValueVOList);
            }
        });
        return projectTypeVOList;
    }


    /**
     * TODO 集合，增强返回参数追加
     *
     * @param projectTypeVOList 项目类型
     * @return List<ProjectTypeVO>
     * @author lijh
     * @methodName assignment
     * @time 2022-05-05 02:45:25
     */
    public List<ProjectTypeVO> assignment(List<ProjectTypeVO> projectTypeVOList) {
        projectTypeVOList.forEach(projectTypeVO -> {
            List<ProjectTypeValueVO> projectTypeValueVOList = projectTypeValueService.listEnhance(new ProjectTypeValueQuery() {{
                setProjectTypeId(projectTypeVO.getId());
            }});
            if (CollectionUtils.isNotEmpty(projectTypeValueVOList)) {
                projectTypeVO.setProjectTypeValueList(projectTypeValueVOList);
            }
        });
        return projectTypeVOList;
    }


    /**
     * TODO DO转化VO
     *
     * @param pageDO 项目类型
     * @return Page<ProjectTypeVO>
     * @author lijh
     * @methodName toPageVO
     * @time 2022-05-05 02:45:25
     */
    public Page<ProjectTypeVO> toPageVO(Page<ProjectType> pageDO) {
        Page<ProjectTypeVO> pageVO = new Page<ProjectTypeVO>();
        pageVO.setRecords(GeneralConvertor.convertor(pageDO.getRecords(), ProjectTypeVO.class));
        pageVO.setCurrent(pageDO.getCurrent());
        pageVO.setPages(pageDO.getPages());
        pageVO.setSize(pageDO.getSize());
        pageVO.setTotal(pageDO.getTotal());
        return pageVO;
    }
}