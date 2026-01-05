package com.gb.promoteform.service.results;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gb.promoteform.entity.*;
import com.gb.promoteform.entity.enums.PromoteFormButtonStateEnum;
import com.gb.promoteform.entity.enums.PromoteFormFieldTypeEnum;
import com.gb.promoteform.entity.query.PromoteFormFloatingWindowQuery;
import com.gb.promoteform.entity.vo.PromoteFormFloatingWindowVO;
import com.gb.promoteform.entity.vo.PromoteFormProductSpuVO;
import com.gb.promoteform.entity.vo.PromoteFormVO;
import com.gb.promoteform.service.*;
import com.gb.rpc.entity.enums.RpcTypeEnum;
import com.gb.rpc.impl.RpcComponent;
import com.gb.utils.GeneralConvertor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


/**
 * TODO 推广表单,Service返回实现
 * 代码生成器
 *
 * @author lijh
 * @className PromoteFormServiceResults
 * @time 2022-07-04 10:47:25
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class PromoteFormServiceResults {

    private PromoteFormFieldService promoteFormFieldService;
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

    private RpcComponent rpcComponent;

    private PromoteFormService promoteFormService;

    private PromoteFormProductSpuService promoteFormProductSpuService;


    /**
     * TODO 单条，增强返回参数追加
     *
     * @param promoteFormVO 推广表单
     * @return PromoteFormVO
     * @author lijh
     * @methodName assignment
     * @time 2022-07-04 10:47:25
     */
    public PromoteFormVO assignment(PromoteFormVO promoteFormVO) {
        if (Objects.isNull(promoteFormVO)) {
            return null;
        }
        String promoteFormId = promoteFormVO.getId();
        //微信信息
        PromoteFormWechat promoteFormWechat = promoteFormWechatService.getOne(new QueryWrapper<PromoteFormWechat>().lambda().eq(PromoteFormWechat::getPromoteFormId, promoteFormId));
        promoteFormVO.setPromoteFormWechat(promoteFormWechat);

        //表单弹窗
        PromoteFormWindow promoteFormWindow = promoteFormWindowService.getOne(new QueryWrapper<PromoteFormWindow>().lambda().eq(PromoteFormWindow::getPromoteFormId, promoteFormId));
        promoteFormVO.setPromoteFormWindow(promoteFormWindow);

        //产品信息
        List<PromoteFormProduct> promoteFormProductList = promoteFormProductService.list(new QueryWrapper<PromoteFormProduct>().lambda().eq(PromoteFormProduct::getPromoteFormId, promoteFormId).orderByDesc(PromoteFormProduct::getSorting).orderByDesc(PromoteFormProduct::getCreateDateTime));
        if (CollectionUtils.isNotEmpty(promoteFormProductList)) {
            promoteFormProductList.forEach(formProduct -> {
                //跳转的险种
                formProduct.setDangerPlantedName(getDangerPlantedName(formProduct.getDangerPlantedId()));
                //跳转的表单
                if (StringUtils.isNotBlank(formProduct.getInsuredPromoteFormId())) {
                    PromoteForm promoteForm = promoteFormService.getOne(new QueryWrapper<PromoteForm>().lambda().eq(PromoteForm::getId, formProduct.getInsuredPromoteFormId()));
                    if (Objects.nonNull(promoteForm)) {
                        formProduct.setFormName(promoteForm.getName());
                    }
                }
                //配置的投保链接下的产品
                List<PromoteFormProductSpu> promoteFormProductSpuList = promoteFormProductSpuService.listByFormProductId(formProduct.getId());
                formProduct.setPromoteFormProductSpuList(promoteFormProductSpuList);
            });
        }
        promoteFormVO.setPromoteFormProductList(promoteFormProductList);

        //按钮,浮窗集合
        List<PromoteFormButton> promoteFormList = promoteFormButtonService.list(new QueryWrapper<PromoteFormButton>().lambda().eq(PromoteFormButton::getPromoteFormId, promoteFormId).orderByDesc(PromoteFormButton::getSorting).orderByDesc(PromoteFormButton::getCreateDateTime));

        //拆分集合
        if (CollectionUtils.isNotEmpty(promoteFormList)) {
            promoteFormList.forEach(formButton -> formButton.setDangerPlantedName(getDangerPlantedName(formButton.getDangerPlantedId())));
            //按钮
            List<PromoteFormButton> promoteFormButtonList = promoteFormList.stream().filter(promoteFormButton -> Objects.equals(promoteFormButton.getState(), PromoteFormButtonStateEnum.按钮.getValue())).collect(Collectors.toList());
            //底部浮窗
            List<PromoteFormButton> promoteFormBottomList = promoteFormList.stream().filter(promoteFormButton -> Objects.equals(promoteFormButton.getState(), PromoteFormButtonStateEnum.底部浮窗.getValue())).collect(Collectors.toList());
            //固底浮窗
            List<PromoteFormButton> promoteFormFixedBottomList = promoteFormList.stream().filter(promoteFormButton -> Objects.equals(promoteFormButton.getState(), PromoteFormButtonStateEnum.固底浮窗.getValue())).collect(Collectors.toList());

            promoteFormVO.setPromoteFormButtonList(promoteFormButtonList);
            promoteFormVO.setPromoteFormBottomList(promoteFormBottomList);
            promoteFormVO.setPromoteFormFixedBottomList(promoteFormFixedBottomList);
        }

        //字段集合
        List<PromoteFormField> promoteFormFieldList = promoteFormFieldService.list(new QueryWrapper<PromoteFormField>().lambda().eq(PromoteFormField::getPromoteFormId, promoteFormId).orderByDesc(PromoteFormField::getSorting).orderByDesc(PromoteFormField::getCreateDateTime));
        promoteFormVO.setPromoteFormFieldList(promoteFormFieldList);

        //底部浮框,险种选择样式
        PromoteFormFloatingWindowVO promoteFormFloatingWindowVO = promoteFormFloatingWindowService.getOneEnhance(new PromoteFormFloatingWindowQuery() {{
            setPromoteFormId(promoteFormId);
        }});
        promoteFormVO.setPromoteFormFloatingWindow(promoteFormFloatingWindowVO);
        return promoteFormVO;
    }


    /**
     * TODO 分页，增强返回参数追加
     *
     * @param promoteFormVOList 推广表单
     * @return Page<PromoteFormVO>
     * @author lijh
     * @methodName assignment
     * @time 2022-07-04 10:47:25
     */
    public Page<PromoteFormVO> assignment(Page<PromoteFormVO> promoteFormVOList) {
        promoteFormVOList.getRecords().forEach(promoteFormVO -> {
            List<PromoteFormField> list = promoteFormFieldService.list(new QueryWrapper<PromoteFormField>().lambda().eq(PromoteFormField::getPromoteFormId, promoteFormVO.getId()));
            list.forEach(promoteFormField -> promoteFormField.setTypeName(PromoteFormFieldTypeEnum.getDesc(promoteFormField.getType())));
            //拼接收集字段
            promoteFormVO.setPromoteFormFields(list.stream().map((PromoteFormField::getTypeName)).collect(Collectors.joining("、")));

            //微信信息
            PromoteFormWechat promoteFormWechat = promoteFormWechatService.getOne(new QueryWrapper<PromoteFormWechat>().lambda().eq(PromoteFormWechat::getPromoteFormId, promoteFormVO.getId()));
            promoteFormVO.setPromoteFormWechat(promoteFormWechat);
        });
        return promoteFormVOList;
    }


    /**
     * TODO 集合，增强返回参数追加
     *
     * @param promoteFormVOList 推广表单
     * @return List<PromoteFormVO>
     * @author lijh
     * @methodName assignment
     * @time 2022-07-04 10:47:25
     */
    public List<PromoteFormVO> assignment(List<PromoteFormVO> promoteFormVOList) {
        promoteFormVOList.forEach(promoteFormVO -> {
        });
        return promoteFormVOList;
    }


    /**
     * TODO DO转化VO
     *
     * @param pageDO 推广表单
     * @return Page<PromoteFormVO>
     * @author lijh
     * @methodName toPageVO
     * @time 2022-07-04 10:47:25
     */
    public Page<PromoteFormVO> toPageVO(Page<PromoteForm> pageDO) {
        Page<PromoteFormVO> pageVO = new Page<PromoteFormVO>();
        pageVO.setRecords(GeneralConvertor.convertor(pageDO.getRecords(), PromoteFormVO.class));
        pageVO.setCurrent(pageDO.getCurrent());
        pageVO.setPages(pageDO.getPages());
        pageVO.setSize(pageDO.getSize());
        pageVO.setTotal(pageDO.getTotal());
        return pageVO;
    }

    /**
     * 根据险种id获取险种名称
     *
     * @param dangerPlantedId
     * @return
     */
    public String getDangerPlantedName(String dangerPlantedId) {
        HashMap<String, Object> hashMap = new HashMap<>(1);
        hashMap.put("id", dangerPlantedId);
        LinkedHashMap linkedHashMap = rpcComponent.rpcQueryInfo(hashMap, RpcTypeEnum.GET_ONE_DANGER_PLANTED, LinkedHashMap.class);
        if (CollectionUtils.isEmpty(linkedHashMap)) {
            return StringUtils.EMPTY;
        }
        return linkedHashMap.get("name").toString();
    }
}