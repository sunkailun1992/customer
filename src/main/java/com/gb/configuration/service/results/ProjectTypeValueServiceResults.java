package com.gb.configuration.service.results;

import com.gb.configuration.entity.ProjectTypeValue;
import com.gb.configuration.entity.vo.ProjectTypeValueVO;
import com.gb.configuration.entity.bo.ProjectTypeValueBO;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import com.gb.utils.GeneralConvertor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * TODO 项目类型值,Service返回实现
 * 代码生成器
 *
 * @author lijh
 * @className ProjectTypeValueServiceResults
 * @time 2022-05-05 02:45:25
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class ProjectTypeValueServiceResults {


    /**
     * TODO 单条，增强返回参数追加
     *
     * @param projectTypeValueVO 项目类型值
     * @return ProjectTypeValueVO
     * @author lijh
     * @methodName assignment
     * @time 2022-05-05 02:45:25
     */
    public ProjectTypeValueVO assignment(ProjectTypeValueVO projectTypeValueVO) {
        if (projectTypeValueVO != null) {
            return projectTypeValueVO;
        } else {
            return projectTypeValueVO;
        }
    }


    /**
     * TODO 分页，增强返回参数追加
     *
     * @param projectTypeValueVOList 项目类型值
     * @return Page<ProjectTypeValueVO>
     * @author lijh
     * @methodName assignment
     * @time 2022-05-05 02:45:25
     */
    public Page<ProjectTypeValueVO> assignment(Page<ProjectTypeValueVO> projectTypeValueVOList) {
        projectTypeValueVOList.getRecords().forEach(projectTypeValueVO -> {
        });
        return projectTypeValueVOList;
    }


    /**
     * TODO 集合，增强返回参数追加
     *
     * @param projectTypeValueVOList 项目类型值
     * @return List<ProjectTypeValueVO>
     * @author lijh
     * @methodName assignment
     * @time 2022-05-05 02:45:25
     */
    public List<ProjectTypeValueVO> assignment(List<ProjectTypeValueVO> projectTypeValueVOList) {
        projectTypeValueVOList.forEach(projectTypeValueVO -> {
        });
        return projectTypeValueVOList;
    }


    /**
     * TODO DO转化VO
     *
     * @param pageDO 项目类型值
     * @return Page<ProjectTypeValueVO>
     * @author lijh
     * @methodName toPageVO
     * @time 2022-05-05 02:45:25
     */
    public Page<ProjectTypeValueVO> toPageVO(Page<ProjectTypeValue> pageDO) {
        Page<ProjectTypeValueVO> pageVO = new Page<ProjectTypeValueVO>();
        pageVO.setRecords(GeneralConvertor.convertor(pageDO.getRecords(), ProjectTypeValueVO.class));
        pageVO.setCurrent(pageDO.getCurrent());
        pageVO.setPages(pageDO.getPages());
        pageVO.setSize(pageDO.getSize());
        pageVO.setTotal(pageDO.getTotal());
        return pageVO;
    }
}