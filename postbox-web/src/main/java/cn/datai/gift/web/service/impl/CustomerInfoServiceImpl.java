package cn.datai.gift.web.service.impl;

import cn.datai.gift.persist.mapper.TBoxInfoMapperExt;
import cn.datai.gift.persist.mapper.TCustomerInfoMapperExt;
import cn.datai.gift.persist.po.*;
import cn.datai.gift.utils.RespResult;
import cn.datai.gift.utils.enums.RespCode;
import cn.datai.gift.utils.exception.BizException;
import cn.datai.gift.web.contants.TokenContants;
import cn.datai.gift.web.plugin.vo.MobileCode;
import cn.datai.gift.web.plugin.vo.UserLoginInfo;
import cn.datai.gift.web.service.BaseInfoService;
import cn.datai.gift.web.service.CustomerInfoService;
import cn.datai.gift.web.utils.MyStringUtil;
import org.bouncycastle.jcajce.provider.symmetric.ARC4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;

import static cn.datai.gift.web.controller.IndexController.checkParams;

/**
 * 客户Service
 * Created by H.CAAHN on 2017/6/19.
 */
@Service
public class CustomerInfoServiceImpl implements CustomerInfoService {
    @Autowired
    private TCustomerInfoMapperExt tCustomerInfoMapperExt;

    @Autowired
    private TBoxInfoMapperExt tBoxInfoMapperExt;

    @Autowired
    private BaseInfoService baseInfoService;

    public TCustomerInfo queryById(Long customerId) {
        return tCustomerInfoMapperExt.selectByPrimaryKey(customerId);
    }

    /**
     * 绑定手机号
     *
     * @param phone
     * @param customerName
     * @param isSpecial
     * @param userWxInfo
     * @param userLoginInfo
     * @param redirecturl
     * @param code
     * @param serverIds
     * @param request
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public RespResult bind(String phone, String customerName, String isSpecial, UserWxInfo userWxInfo, UserLoginInfo userLoginInfo, String redirecturl, String code, String serverIds, HttpServletRequest request) {

        RespResult respResult = checkParams(phone, isSpecial);
        if(!respResult.getCode().equals(RespCode.SUCCESS.getCode())){
            throw new BizException(RespCode.EXCEPTION_BIND_PARAMS);
        }
        //判断验证是否正确是不是超时
        MobileCode mobileCode = userLoginInfo.getMobileCode();
        if(!checkCode(mobileCode,phone,code)){
            throw new BizException(RespCode.EXCEPTION_PHONE_ERROR);
        }

        //绑定手机号操作
        TCustomerInfo tCustomerInfo = this.baseInfoService.queryTCustomerInfo(userLoginInfo.getCustomerInfoId());
        if(null == tCustomerInfo){
            throw new BizException(RespCode.EXCEPTION_NOT_FIND_USER);
        }
        if(!StringUtils.isEmpty(tCustomerInfo.getMobilePhone())){
            throw new BizException(RespCode.EXCEPTION_BIND_PHONE);
        }

        TCustomerInfo tCustomerInfo1 = this.baseInfoService.queryTCustomerInfoIdByPhone(phone);
        if(null != tCustomerInfo1){
            throw new BizException(RespCode.EXCEPTION_BIND_PHONE);
        }

        tCustomerInfo.setRealname(customerName);
        tCustomerInfo.setMobilePhone(phone);

        baseInfoService.updateTCustomerInfo(tCustomerInfo);

        if("true".equalsIgnoreCase(isSpecial)){
            if(MyStringUtil.isBlank(customerName)){
                throw new BizException(RespCode.EXCEPTION_USERNAME);
            }
            String[] imgs = serverIds.split(",");
            if(imgs.length !=2 || imgs[0].equals(imgs[1]) || MyStringUtil.isBlank(imgs[0]) || MyStringUtil.isBlank(imgs[1])){
                throw new BizException(RespCode.EXCEPTION_UPLOAD);
            }

            //快递员信息表中插入数据
            TExpressmanInfo tExpressmanInfo = new TExpressmanInfo();
            tExpressmanInfo.setCustomerInfoId(tCustomerInfo.getCustomerInfoId());
            tExpressmanInfo.setApplytime(new Date());
            tExpressmanInfo.setStatus("NORMAL");
            tExpressmanInfo.setIdcardImgPath("(" + phone + "-"+ customerName + ")" + imgs[0] + ".jpg");
            tExpressmanInfo.setWorkcardImgPath("(" + phone + "-"+ customerName + ")" + imgs[1] + ".jpg");

            Arrays.asList(imgs).stream().forEach(item ->{
                String requestUrl = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
                requestUrl = requestUrl.replace("ACCESS_TOKEN", TokenContants.WEIXIN_TOKEN).replace(
                        "MEDIA_ID", item);
                try {
                    baseInfoService.uploadPhoto( phone +"_" + item,requestUrl);//上传图片
                } catch (Exception e) {
                    throw new BizException(RespCode.EXCEPTION_UPLOAD);
                }
            });
            this.baseInfoService.insertTExpressmanInfo(tExpressmanInfo);//插入快递员信息
        }

        return new RespResult(RespCode.SUCCESS,redirecturl);
    }

    /**
     * 通过箱子code更新箱子当前的手机号
     * @param mobile
     * @param boxCode
     */
    @Transactional
    @Override
    public void updateBoxInfo(String mobile, String boxCode) {
        TBoxInfo boxInfo = new TBoxInfo();
        boxInfo.setMobilePhone(mobile);

        TBoxInfoExample example = new TBoxInfoExample();
        example.createCriteria().andBoxCodeEqualTo(boxCode);

        this.tBoxInfoMapperExt.updateByExampleSelective(boxInfo,example);
    }

    /**
     * 校验手机验证码是否正确是否超时
     * @param mobileCode
     * @param code
     * @return
     */
    private static boolean checkCode(MobileCode mobileCode,String mobile,String code){
        if(null == mobileCode || StringUtils.isEmpty(code) || StringUtils.isEmpty(mobile)){
            return false;
        }
        if(!mobileCode.getCode().equals(code)){
            return false;
        }
        if(!mobileCode.getMobile().equals(mobile)){
            return false;
        }
        Long periodTime = System.currentTimeMillis() - mobileCode.getDateTime();
        if(periodTime > 2 * 60 * 1000){
            return false;
        }

        return true;
    }
}
