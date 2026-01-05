package com.gb.promoteform.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gb.aliyun.oss.OssUtils;
import com.gb.bean.GongBaoConfig;
import com.gb.promoteform.entity.*;
import com.gb.promoteform.entity.bo.PromoteFormBO;
import com.gb.promoteform.entity.enums.PromoteFormTypeEnum;
import com.gb.promoteform.entity.query.PromoteFormQuery;
import com.gb.promoteform.entity.vo.PromoteFormVO;
import com.gb.promoteform.mapper.PromoteFormMapper;
import com.gb.promoteform.service.*;
import com.gb.promoteform.service.query.PromoteFormServiceQuery;
import com.gb.promoteform.service.results.PromoteFormServiceResults;
import com.gb.utils.GeneralConvertor;
import com.gb.utils.constants.UniversalConstant;
import com.gb.utils.exception.BusinessException;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * TODO 推广表单，Service服务实现层
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormServiceImpl
 * @time 2022-07-04 10:47:25
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class PromoteFormServiceImpl extends ServiceImpl<PromoteFormMapper, PromoteForm> implements PromoteFormService {


    /**
     * 推广表单
     */
    private PromoteFormMapper promoteFormMapper;


    /**
     * 推广表单
     */
    private PromoteFormServiceResults promoteFormServiceResults;


    /**
     * 推广表单增强条件
     */
    private PromoteFormServiceQuery promoteFormServiceQuery;
    /**
     * 推广表单微信
     */
    private PromoteFormWechatService promoteFormWechatService;
    /**
     * 推广表单产品
     */
    private PromoteFormProductService promoteFormProductService;
    /**
     * 推广表单按钮
     */
    private PromoteFormButtonService promoteFormButtonService;
    /**
     * 推广表单窗口
     */
    private PromoteFormWindowService promoteFormWindowService;

    /**
     * 推广表单浮框，险种
     */
    private PromoteFormFloatingWindowService promoteFormFloatingWindowService;
    private PromoteFormFloatingWindowDangerPlantedService promoteFormFloatingWindowDangerPlantedService;
    /**
     * 推广表单字段
     */
    private PromoteFormFieldService promoteFormFieldService;
    private PromoteFormFloatingWindowDangerPlantedSpuService promoteFormFloatingWindowDangerPlantedSpuService;

    private PromoteFormProductSpuService promoteFormProductSpuService;

    /**
     * TODO 集合
     *
     * @param promoteFormQuery 推广表单
     * @return List<PromoteFormVO>
     * @author lijh
     * @methodName listEnhance
     * @time 2022-07-04 10:47:25
     */
    @Override
    public List<PromoteFormVO> listEnhance(PromoteFormQuery promoteFormQuery) {
        PromoteForm promoteForm = GeneralConvertor.convertor(promoteFormQuery, PromoteForm.class);
        QueryWrapper<PromoteForm> queryWrapper = new QueryWrapper<>(promoteForm);
        // TODO 自动生成查询，禁止手动写语句
        promoteFormServiceQuery.query(promoteFormQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(promoteFormQuery, queryWrapper);
        // DO数据
        List<PromoteForm> promoteFormDO = promoteFormMapper.selectList(queryWrapper);
        // VO数据
        List<PromoteFormVO> promoteFormVO = GeneralConvertor.convertor(promoteFormDO, PromoteFormVO.class);
        // 判断是否增强
        if (promoteFormQuery.getAssignment() == null) {
            return promoteFormServiceResults.assignment(promoteFormVO);
        } else {
            return promoteFormQuery.getAssignment() ? promoteFormServiceResults.assignment(promoteFormVO) : promoteFormVO;
        }
    }


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
    @Override
    public Page<PromoteFormVO> pageEnhance(Page page, PromoteFormQuery promoteFormQuery) {
        PromoteForm promoteForm = GeneralConvertor.convertor(promoteFormQuery, PromoteForm.class);
        QueryWrapper<PromoteForm> queryWrapper = new QueryWrapper<>(promoteForm);
        // TODO 自动生成查询，禁止手动写语句
        promoteFormServiceQuery.query(promoteFormQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(promoteFormQuery, queryWrapper);
        // DO数据
        Page<PromoteForm> pageDO = promoteFormMapper.selectPage(page, queryWrapper);
        // VO数据
        Page<PromoteFormVO> pageVO = promoteFormServiceResults.toPageVO(pageDO);
        // 判断是否增强
        if (promoteFormQuery.getAssignment() == null) {
            return promoteFormServiceResults.assignment(pageVO);
        } else {
            return promoteFormQuery.getAssignment() ? promoteFormServiceResults.assignment(pageVO) : pageVO;
        }
    }


    /**
     * TODO 单条
     *
     * @param promoteFormQuery 推广表单
     * @return PromoteFormVO
     * @author lijh
     * @methodName getOneEnhance
     * @time 2022-07-04 10:47:25
     */
    @Override
    public PromoteFormVO getOneEnhance(PromoteFormQuery promoteFormQuery) {
        PromoteForm promoteForm = GeneralConvertor.convertor(promoteFormQuery, PromoteForm.class);
        QueryWrapper<PromoteForm> queryWrapper = new QueryWrapper<>(promoteForm);
        // TODO 自动生成查询，禁止手动写语句
        promoteFormServiceQuery.query(promoteFormQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(promoteFormQuery, queryWrapper);
        // DO数据
        PromoteForm promoteFormDO = promoteFormMapper.selectOne(queryWrapper);
        // VO数据
        PromoteFormVO promoteFormVO = GeneralConvertor.convertor(promoteFormDO, PromoteFormVO.class);
        // 判断是否增强
        if (promoteFormQuery.getAssignment() == null) {
            return promoteFormServiceResults.assignment(promoteFormVO);
        } else {
            return promoteFormQuery.getAssignment() ? promoteFormServiceResults.assignment(promoteFormVO) : promoteFormVO;
        }
    }


    /**
     * TODO 总数
     *
     * @param promoteFormQuery 推广表单
     * @return Integer
     * @author lijh
     * @methodName countEnhance
     * @time 2022-07-04 10:47:25
     */
    @Override
    public Long countEnhance(PromoteFormQuery promoteFormQuery) {
        PromoteForm promoteForm = GeneralConvertor.convertor(promoteFormQuery, PromoteForm.class);
        QueryWrapper<PromoteForm> queryWrapper = new QueryWrapper<>(promoteForm);
        // TODO 自动生成查询，禁止手动写语句
        promoteFormServiceQuery.query(promoteFormQuery, queryWrapper);
        // TODO 人工查询条件
        queryArtificial(promoteFormQuery, queryWrapper);
        return promoteFormMapper.selectCount(queryWrapper);
    }


    /**
     * TODO 新增
     *
     * @param promoteFormBO 推广表单
     * @return String
     * @author lijh
     * @methodName saveEnhance
     * @time 2022-07-04 10:47:25
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public String saveEnhance(PromoteFormBO promoteFormBO) {
        log.info("新增推广表单,promoteFormBO={}", promoteFormBO);
        PromoteForm pf = promoteFormMapper.selectOne(new QueryWrapper<PromoteForm>().lambda().eq(PromoteForm::getName, promoteFormBO.getName()));
        if (Objects.nonNull(pf)) {
            throw new BusinessException("表单名称已存在");
        }
        PromoteForm promoteForm = GeneralConvertor.convertor(promoteFormBO, PromoteForm.class);
        //新增表单
        promoteFormMapper.insert(promoteForm);
        //生成二维码链接
        promoteForm.setLinkAddress(GongBaoConfig.frontDomainName + promoteFormBO.getLinkAddress() + "?id=" + promoteForm.getId());
        promoteForm.setLinkAddressCode(generateQrCode(promoteForm.getLinkAddress()));
        promoteFormMapper.updateById(promoteForm);
        String promoteFormId = promoteForm.getId();

        //新增表单的微信信息
        if (Objects.nonNull(promoteFormBO.getPromoteFormWechat())) {
            promoteFormBO.getPromoteFormWechat().setPromoteFormId(promoteFormId);
            promoteFormWechatService.save(promoteFormBO.getPromoteFormWechat());
        }
        //新增表单弹窗
        if (Objects.nonNull(promoteFormBO.getPromoteFormWindow())) {
            promoteFormBO.getPromoteFormWindow().setPromoteFormId(promoteFormId);
            promoteFormWindowService.save(promoteFormBO.getPromoteFormWindow());
        }
        //新增表单的产品信息
        if (CollectionUtils.isNotEmpty(promoteFormBO.getPromoteFormProductList())) {
            promoteFormBO.getPromoteFormProductList().forEach(promoteFormProduct -> promoteFormProduct.setPromoteFormId(promoteFormId));
            List<PromoteFormProduct> promoteFormProductList = promoteFormBO.getPromoteFormProductList();
            promoteFormProductService.saveBatch(promoteFormProductList);
            //表单配置 投保链接类型 选择的产品集合
            for (PromoteFormProduct promoteFormProduct : promoteFormProductList) {
                if (CollectionUtils.isNotEmpty(promoteFormProduct.getPromoteFormProductSpuList())) {
                    for (PromoteFormProductSpu promoteFormProductSpu : promoteFormProduct.getPromoteFormProductSpuList()) {
                        promoteFormProductSpu.setPromoteFormProductId(promoteFormProduct.getId());
                    }
                    promoteFormProductSpuService.saveBatch(promoteFormProduct.getPromoteFormProductSpuList());
                }
            }
        }
        //合并按钮和底部集合
        List<PromoteFormButton> promoteFormButtonList = Stream.of(CollectionUtils.isNotEmpty(promoteFormBO.getPromoteFormButtonList()) ? promoteFormBO.getPromoteFormButtonList() : new ArrayList<PromoteFormButton>(),
                        CollectionUtils.isNotEmpty(promoteFormBO.getPromoteFormBottomList()) ? promoteFormBO.getPromoteFormBottomList() : new ArrayList<PromoteFormButton>(),
                        CollectionUtils.isNotEmpty(promoteFormBO.getPromoteFormFixedBottomList()) ? promoteFormBO.getPromoteFormFixedBottomList() : new ArrayList<PromoteFormButton>()).
                flatMap(Collection::stream).collect(Collectors.toList());
        //新增按钮，底部浮窗
        if (CollectionUtils.isNotEmpty(promoteFormButtonList)) {
            promoteFormButtonList.forEach(promoteFormButton -> promoteFormButton.setPromoteFormId(promoteFormId));
            promoteFormButtonService.saveBatch(promoteFormButtonList);
        }

        //新增表单字段
        if (CollectionUtils.isNotEmpty(promoteFormBO.getPromoteFormFieldList())) {
            promoteFormBO.getPromoteFormFieldList().forEach(field -> field.setPromoteFormId(promoteFormId));
            promoteFormFieldService.saveBatch(promoteFormBO.getPromoteFormFieldList());
        }

        //新增底部浮窗
        if (Objects.nonNull(promoteFormBO.getPromoteFormFloatingWindow()) && CollectionUtils.isNotEmpty(promoteFormBO.getPromoteFormFloatingWindow().getWindowDangerPlantedList())) {
            promoteFormBO.setId(promoteFormId);
            savePromoteFormFloatingWindow(promoteFormBO);
        }

        return promoteFormId;
    }


    /**
     * TODO 修改
     *
     * @param promoteFormBO 推广表单
     * @return Boolean
     * @author lijh
     * @methodName updateEnhance
     * @time 2022-07-04 10:47:25
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean updateEnhance(PromoteFormBO promoteFormBO) {
        log.info("修改推广表单,promoteFormBO={}", promoteFormBO);
        PromoteForm pf = promoteFormMapper.selectOne(new QueryWrapper<PromoteForm>().lambda().ne(PromoteForm::getId, promoteFormBO.getId()).eq(PromoteForm::getName, promoteFormBO.getName()));
        if (Objects.nonNull(pf)) {
            throw new BusinessException("表单名称已存在");
        }
        PromoteForm promoteForm = GeneralConvertor.convertor(promoteFormBO, PromoteForm.class);
        UpdateWrapper<PromoteForm> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", promoteFormBO.getId());
        int i = promoteFormMapper.update(promoteForm, updateWrapper);
        //修改表单的微信信息
        if (Objects.nonNull(promoteFormBO.getPromoteFormWechat())) {
            promoteFormWechatService.updateById(promoteFormBO.getPromoteFormWechat());
        }
        //修改表单弹窗
        if (Objects.nonNull(promoteFormBO.getPromoteFormWindow())) {
            promoteFormWindowService.updateById(promoteFormBO.getPromoteFormWindow());
        }
        //修改产品
        promoteFormProductService.updateEnhanceByForm(promoteFormBO);
        //修改表单的按钮和底部浮窗
        promoteFormButtonService.updateEnhanceByForm(promoteFormBO);
        //修改表单字段
        promoteFormFieldService.updateEnhanceByForm(promoteFormBO);

        //删除之前浮窗信息   重新新增
        LambdaQueryWrapper<PromoteFormFloatingWindow> queryWrapper = new QueryWrapper<PromoteFormFloatingWindow>().lambda().eq(PromoteFormFloatingWindow::getPromoteFormId, promoteFormBO.getId());
        PromoteFormFloatingWindow floatingWindow = promoteFormFloatingWindowService.getOne(queryWrapper);
        promoteFormFloatingWindowService.remove(queryWrapper);
        if (Objects.nonNull(floatingWindow)) {
            promoteFormFloatingWindowDangerPlantedService.remove(new QueryWrapper<PromoteFormFloatingWindowDangerPlanted>().lambda().eq(PromoteFormFloatingWindowDangerPlanted::getPromoteFormFloatingWindowId, floatingWindow.getId()));
        }
        //新增底部浮窗
        if (Objects.nonNull(promoteFormBO.getPromoteFormFloatingWindow()) && CollectionUtils.isNotEmpty(promoteFormBO.getPromoteFormFloatingWindow().getWindowDangerPlantedList())) {
            savePromoteFormFloatingWindow(promoteFormBO);
        }
        return i > 0;
    }


    /**
     * TODO 删除
     *
     * @param promoteFormBO 推广表单
     * @return Boolean
     * @author lijh
     * @methodName removeEnhance
     * @time 2022-07-04 10:47:25
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public Boolean removeEnhance(PromoteFormBO promoteFormBO) {
        PromoteForm promoteForm = GeneralConvertor.convertor(promoteFormBO, PromoteForm.class);
        QueryWrapper<PromoteForm> queryWrapper = new QueryWrapper<>(promoteForm);
        int i = promoteFormMapper.delete(queryWrapper);
        return i > 0;
    }

    @Override
    public String previewForm(String id, String agentUserId, String agentUserName) {
        PromoteForm pf = promoteFormMapper.selectOne(new QueryWrapper<PromoteForm>().lambda().eq(PromoteForm::getId, id));
        if (Objects.isNull(pf)) {
            throw new BusinessException("表单不存在");
        }
        if (StringUtils.isEmpty(agentUserId)) {
            throw new BusinessException("缺少必要参数");
        }
        String linkAddress = pf.getLinkAddress() + "&agentUserName=" + agentUserName + "&agentUserId=" + agentUserId;
        return generateQrCode(linkAddress);
    }

    @Override
    public String getQrCodeLinkAddress(String qrCodeLinkAddress) {
        return generateQrCode(qrCodeLinkAddress);
    }


    /**
     * TODO 人工查询条件
     *
     * @param promoteFormQuery 推广表单
     * @author lijh
     * @methodName queryArtificial
     * @time 2022-07-04 10:47:25
     */
    private void queryArtificial(PromoteFormQuery promoteFormQuery, QueryWrapper<PromoteForm> queryWrapper) {
        //模糊查询 表单名称
        if (StringUtils.isNotBlank(promoteFormQuery.getQuery())) {
            queryWrapper.like("name", promoteFormQuery.getQuery());
        }
        //模糊查询 标题
        if (StringUtils.isNotBlank(promoteFormQuery.getQueryTitle())) {
            queryWrapper.like("title", promoteFormQuery.getQueryTitle());
        }
        //表单类型
        if (Objects.nonNull(promoteFormQuery.getType())) {
            queryWrapper.eq("type", promoteFormQuery.getType());
        }
        //表单状态
        if (Objects.nonNull(promoteFormQuery.getState())) {
            queryWrapper.eq("state", promoteFormQuery.getState());
        }
        //经纪人是否开启
        if (Objects.nonNull(promoteFormQuery.getAgent())) {
            queryWrapper.eq("agent", promoteFormQuery.getAgent());
        }
        //是否启用
        if (Objects.nonNull(promoteFormQuery.getEnable())) {
            queryWrapper.eq("enable", promoteFormQuery.getEnable());
        }
        //搜索类型
        if (Objects.nonNull(promoteFormQuery.getQueryType()) && Objects.nonNull(promoteFormQuery.getQueryField())) {
            switch (promoteFormQuery.getQueryType()) {
                case 1:
                    queryWrapper.like("title", promoteFormQuery.getQueryField());
                    break;
                case 2:
                    queryWrapper.like("name", promoteFormQuery.getQueryField());
                    break;
                case 3:
                    List<Integer> typeList = PromoteFormTypeEnum.vagueGetDesc(promoteFormQuery.getQueryField());
                    Integer[] array = new Integer[typeList.size()];
                    queryWrapper.lambda().in(PromoteForm::getType, CollectionUtils.isNotEmpty(typeList) ? typeList.toArray(array) : new Object[]{99});
                default:
                    break;
            }
        }
        //app 搜索标题  表单类型  表单名称
        if (Objects.nonNull(promoteFormQuery.getQueryNameField())) {
            List<Integer> typeList = PromoteFormTypeEnum.vagueGetDesc(promoteFormQuery.getQueryNameField());
            Integer[] array = new Integer[typeList.size()];
            queryWrapper.and(broker -> broker.like("title", promoteFormQuery.getQueryNameField()).
                    or().
                    like("name", promoteFormQuery.getQueryNameField()).
                    or().lambda().
                    in(PromoteForm::getType, CollectionUtils.isNotEmpty(typeList) ? typeList.toArray(array) : new Object[]{99}));
        }
        //公众号 搜索分享标题  表单类型  表单名称
        if (Objects.nonNull(promoteFormQuery.getQueryThePublicNameField())) {
            List<String> formIds = new ArrayList<>();
            formIds.add("99");
            List<PromoteFormWechat> wechatList = promoteFormWechatService.list(new QueryWrapper<PromoteFormWechat>().lambda().like(PromoteFormWechat::getTitle, promoteFormQuery.getQueryThePublicNameField()));
            if (CollectionUtils.isNotEmpty(wechatList)) {
                formIds = wechatList.stream().map(PromoteFormWechat::getPromoteFormId).collect(Collectors.toList());
            }
            List<Integer> typeList = PromoteFormTypeEnum.vagueGetDesc(promoteFormQuery.getQueryThePublicNameField());
            Integer[] array = new Integer[typeList.size()];
            List<String> finalFormIds = formIds;
            queryWrapper.and(broker -> broker.in("id", finalFormIds).
                    or().
                    like("name", promoteFormQuery.getQueryThePublicNameField()).
                    or().
                    in("type", CollectionUtils.isNotEmpty(typeList) ? typeList.toArray(array) : new Object[]{99}));
        }
    }

    public String generateQrCode(String content) {
        try {
            String nowTime = new DateTime().toString(DatePattern.PURE_DATETIME_PATTERN);
            String fileName = "promote/" + nowTime + RandomUtil.randomNumbers(6) + ".png";
            byte[] bytes = cn.hutool.extra.qrcode.QrCodeUtil.generatePng(content, UniversalConstant.TWO_HUNDRED, UniversalConstant.TWO_HUNDRED);
            return OssUtils.uploadFileReturnUrl(bytes, fileName);
        } catch (Exception e) {
            log.error("生成表单二维码异常" + e);
        }
        return null;
    }

    /**
     * 新增浮窗
     *
     * @param promoteFormBO
     */
    private void savePromoteFormFloatingWindow(PromoteFormBO promoteFormBO) {
        //浮窗险种
        List<PromoteFormFloatingWindowDangerPlanted> windowDangerPlantedList = promoteFormBO.getPromoteFormFloatingWindow().getWindowDangerPlantedList();
        //新增浮窗信息
        PromoteFormFloatingWindow promoteFormFloatingWindow = promoteFormBO.getPromoteFormFloatingWindow();
        promoteFormFloatingWindow.setPromoteFormId(promoteFormBO.getId());
        promoteFormFloatingWindowService.save(promoteFormFloatingWindow);
        //新增浮窗险种
        windowDangerPlantedList.forEach(planted -> planted.setPromoteFormFloatingWindowId(promoteFormFloatingWindow.getId()));
        promoteFormFloatingWindowDangerPlantedService.saveBatch(windowDangerPlantedList);
        for (PromoteFormFloatingWindowDangerPlanted promoteFormFloatingWindowDangerPlanted : windowDangerPlantedList) {
            //险种产品
            if (CollectionUtils.isNotEmpty(promoteFormFloatingWindowDangerPlanted.getWindowDangerPlantedSpuList())) {
                for (PromoteFormFloatingWindowDangerPlantedSpu promoteFormFloatingWindowDangerPlantedSpu : promoteFormFloatingWindowDangerPlanted.getWindowDangerPlantedSpuList()) {
                    promoteFormFloatingWindowDangerPlantedSpu.setPromoteFormFloatingWindowDangerPlantedId(promoteFormFloatingWindowDangerPlanted.getId());
                }
                //新增浮框险种产品
                promoteFormFloatingWindowDangerPlantedSpuService.saveBatch(promoteFormFloatingWindowDangerPlanted.getWindowDangerPlantedSpuList());
            }
        }
    }
}