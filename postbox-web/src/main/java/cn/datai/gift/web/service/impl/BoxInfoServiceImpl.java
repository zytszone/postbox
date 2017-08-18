package cn.datai.gift.web.service.impl;

import cn.datai.gift.persist.mapper.TBoxInfoMapperExt;
import cn.datai.gift.persist.po.*;
import cn.datai.gift.utils.RespResult;
import cn.datai.gift.utils.enums.RespCode;
import cn.datai.gift.utils.exception.BizException;
import cn.datai.gift.web.enums.BoxExpressStatus;
import cn.datai.gift.web.service.BoxInfoService;
import cn.datai.gift.web.service.CustomerInfoService;
import cn.datai.gift.web.service.SmsSenderService;
import cn.datai.gift.web.utils.MyStringUtil;
import cn.datai.gift.web.utils.sec.AESCoder;
import cn.datai.gift.web.utils.sec.HexUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by H.CAAHN on 2017/5/16.
 */
@Service
public class BoxInfoServiceImpl implements BoxInfoService {
    @Autowired
    private TBoxInfoMapperExt tBoxInfoMapperExt;

    @Autowired
    private SmsSenderService smsSenderService;

    @Autowired
    private CustomerInfoService customerInfoService;

    private static final Logger logger = LoggerFactory.getLogger(BoxInfoServiceImpl.class);

    private static final boolean debugEnable = logger.isDebugEnabled();

    @Override
    public TBoxInfo queryById(Long id) {
        return this.tBoxInfoMapperExt.selectByPrimaryKey(id);
    }

    @Override
    public List<TBoxInfo> queryTBoxInfoByMobileOrproxyCustomerInfoId(String mobilePhone,String proxyCustomerInfoId) {
        TBoxInfoExample example = new TBoxInfoExample();
        TBoxInfoExample.Criteria criteria = example.createCriteria();
        criteria.andExpressStatusEqualTo(BoxExpressStatus.FULL.name());
        criteria.andMobilePhoneEqualTo(mobilePhone);
        criteria.andProxyCustomerInfoIdEqualTo(proxyCustomerInfoId);

        return this.tBoxInfoMapperExt.selectByExample(example);
    }

    @Override
    public String updateAsNormalUserForDecode(TBoxInfo boxInfo, String mkey, TCustomerInfo customerInfo) {
        boolean open = false;
        // 箱子里无包裹
        if (BoxExpressStatus.EMPTY.name().equalsIgnoreCase(boxInfo.getExpressStatus())) {
            // 箱子和用户的手机号相同，则判断上次打开时间是不是在10分钟之内，如是，则允许打开
            if (StringUtils.equals(customerInfo.getMobilePhone(), boxInfo.getMobilePhone())) {
                Calendar calendar = Calendar.getInstance();
                long nowtime = calendar.getTimeInMillis();
                Date opentime = boxInfo.getOpentime();
                if (opentime == null) {
                    open = true;
                }
                else {
                    calendar.setTime(opentime);
                    open = (nowtime - calendar.getTimeInMillis()) < (1000 * 60 * 10);
                }

                if (debugEnable) {
                    logger.debug("箱子为空，客户重新打开{}", open ? "成功" : "失败");
                }
            }
            else if (debugEnable) {
                logger.debug("箱子为空，但手机号不匹配: {}, {}", customerInfo.getMobilePhone(), boxInfo.getMobilePhone());
            }
        }
        else if (BoxExpressStatus.FULL.name().equalsIgnoreCase(boxInfo.getExpressStatus())) {
            if (StringUtils.equals(customerInfo.getMobilePhone(), boxInfo.getMobilePhone())) {
                boxInfo.setOpentime(new Date());
                boxInfo.setExpressStatus(BoxExpressStatus.EMPTY.name());
                this.tBoxInfoMapperExt.updateByPrimaryKeySelective(boxInfo);
                open = true;
                if (debugEnable) {
                    logger.debug("箱子非空，且箱子和用户手机号相同，允许打开");
                }
            }
            else if (debugEnable) {
                logger.debug("箱子非空，手机号不匹配: {}, {}", customerInfo.getMobilePhone(), boxInfo.getMobilePhone());
            }
        }
        else {
            logger.error("箱子快件状态值不正确：{}", boxInfo.getExpressStatus());
        }

        if (open) {
            if (debugEnable) {
                logger.debug("客户: {} 成功打开箱子: {}", customerInfo.getMobilePhone(), boxInfo.getBoxInfoId());
            }

            String decode = this.decode(mkey, boxInfo.getSecKey());
            return decode;
        }
        else if (debugEnable) {
            logger.debug("客户: {} 无权开启箱子: {}, 状态: {}", customerInfo.getMobilePhone(), boxInfo.getBoxInfoId());
        }
        return "";
    }

    @Override
    public String updateAsExpressmanForDecode(TBoxInfo boxInfo, String mkey, TExpressmanInfo expressman) {
        String decode = "";
        if (BoxExpressStatus.EMPTY.name().equalsIgnoreCase(boxInfo.getExpressStatus())) {
            boxInfo.setOpentime(new Date());
            this.tBoxInfoMapperExt.updateByPrimaryKeySelective(boxInfo);
            if (debugEnable) {
                logger.debug("快递员：{} 成功打开箱子：{}", expressman.getCustomerInfoId(), boxInfo.getBoxInfoId());
            }
            decode = this.decode(mkey, boxInfo.getSecKey());
        }
        return decode;
    }

    @Override
    public void updateBoxMobilePhone(Long boxId, String mobile) {
        TBoxInfo box = this.tBoxInfoMapperExt.selectByPrimaryKey(boxId);
        if (box != null) {
            TBoxInfo record = new TBoxInfo();
            record.setBoxInfoId(boxId);
            record.setMobilePhone(mobile);
            record.setExpressStatus(BoxExpressStatus.FULL.name());
            this.tBoxInfoMapperExt.updateByPrimaryKeySelective(record);

            // 发送短信通知
            this.smsSenderService.sendReached(mobile, box.getBoxName(), box.getBoxCode());

            // 发送微信消息通知
        }
    }

    /**
     * 更新手机号（代领人）
     * @param customerInfoId
     * @param mobile
     * @param boxCode
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public RespResult updateForMeLead(Long customerInfoId,String mobile, String boxCode) {
        boolean mobileNO = isMobileNO(mobile);
        if(!mobileNO){
            throw new BizException(RespCode.ERROR_MOBILE);
        }
        boolean checkBoxCode = this.checkBoxCode(boxCode);
        if(!checkBoxCode){
            throw new BizException(RespCode.ERROR_BOX_CODE);
        }
        TBoxInfo tboxInfo = this.tBoxInfoMapperExt.findAndLockTboxInfo(boxCode);
        if(tboxInfo == null){
            throw new BizException(RespCode.ERROR_BOX_CODE);
        }
        if(MyStringUtil.isBlank(tboxInfo.getMobilePhone()) || tboxInfo.getMobilePhone().equals(mobile)){
            throw new BizException(RespCode.NO_EXPRESS_OR_SELF);
        }

        TCustomerInfo tCustomerInfo = this.customerInfoService.queryById(customerInfoId);
        if(tCustomerInfo == null || MyStringUtil.isBlank(tCustomerInfo.getMobilePhone()) || !tboxInfo.getMobilePhone().equals(tCustomerInfo.getMobilePhone())){
            throw new BizException(RespCode.NO_EXPRESS_OR_SELF);
        }

        TCustomerInfo customerInfo = this.customerInfoService.queryTCustomerInfoByMobile(mobile);
        if(null == customerInfo){
            throw new BizException(RespCode.NOT_FIND_USERINFO);
        }
        tboxInfo.setProxyCustomerInfoId(customerInfo.getCustomerInfoId().toString());
        this.tBoxInfoMapperExt.updateByPrimaryKeySelective(tboxInfo);
        // TODO: 2017/8/16 发送短信给代领人
        return new RespResult(RespCode.SUCCESS,"设置代领人成功");
    }

    private String decode(String code, String seckey) {
        String decode = "";
        try {
            byte[] bytes = AESCoder.ecbDec(HexUtil.hexToBytes(code), HexUtil.hexToBytes(seckey));
            for (int i = 0; i < bytes.length && i < 4; i++) {
                decode += bytes[i];
            }
        } catch (Exception e) {
            logger.error("客户/快递员打开箱子失败【解码失败】", e);
        }
        return decode;
    }

    public static void main(String[] args) {
        String decode = null;
        try {
            byte[] bytes = AESCoder.ecbDec(HexUtil.hexToBytes("59248B5CFA28F540229567DC33CA203A"), HexUtil.hexToBytes("32B043A0D66210377B2CA2A0B10DB974"));
            String str = "";
            for (int i = 0; i < bytes.length && i < 6; i++) {
                str += bytes[i];
            }
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 验证手机号
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles){
        if(MyStringUtil.isBlank(mobiles)){
            return false;
        }
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9])|(17[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 验证code
     * @param boxCode
     * @return
     */
    public static boolean checkBoxCode(String boxCode){
        if(MyStringUtil.isBlank(boxCode)){
            return false;
        }
        Pattern p = Pattern.compile("^[A-Za-z0-9]+$");
        Matcher m = p.matcher(boxCode);
        return m.matches();
    }
}
