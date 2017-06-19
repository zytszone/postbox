package cn.datai.gift.web.service.impl;

import cn.datai.gift.persist.mapper.TBoxInfoMapperExt;
import cn.datai.gift.persist.po.TBoxInfo;
import cn.datai.gift.persist.po.TBoxInfoExample;
import cn.datai.gift.persist.po.TCustomerInfo;
import cn.datai.gift.persist.po.TExpressmanInfo;
import cn.datai.gift.utils.SecurityUtils;
import cn.datai.gift.utils.Strings;
import cn.datai.gift.web.enums.BoxExpressStatus;
import cn.datai.gift.web.service.BoxInfoService;
import cn.datai.gift.web.utils.DESUtil;
import cn.datai.gift.web.utils.sec.AESCoder;
import cn.datai.gift.web.utils.sec.HexUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by H.CAAHN on 2017/5/16.
 */
@Service
public class BoxInfoServiceImpl implements BoxInfoService {
    @Autowired
    private TBoxInfoMapperExt tBoxInfoMapperExt;

    private static final Logger logger = LoggerFactory.getLogger(BoxInfoServiceImpl.class);

    private static final boolean debugEnable = logger.isDebugEnabled();

    @Override
    public TBoxInfo queryById(Long id) {
        return this.tBoxInfoMapperExt.selectByPrimaryKey(id);
    }

    @Override
    public List<TBoxInfo> queryByMobilePhone(String mobilePhone) {
        TBoxInfoExample example = new TBoxInfoExample();
        TBoxInfoExample.Criteria criteria = example.createCriteria();
        criteria.andExpressStatusEqualTo(BoxExpressStatus.FULL.name());
        criteria.andMobilePhoneEqualTo(mobilePhone);

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
        if (BoxExpressStatus.FULL.name().equalsIgnoreCase(boxInfo.getExpressStatus())) {
            if (debugEnable) {
                logger.debug("快递员：{} 成功打开箱子：{}", expressman.getCustomerInfoId(), boxInfo.getBoxInfoId());
            }
            decode = this.decode(mkey, boxInfo.getSecKey());
        }
        return decode;
    }

    @Override
    public void updateBoxMobilePhone(Long boxId, String mobile) {
        TBoxInfo record = new TBoxInfo();
        record.setBoxInfoId(boxId);
        record.setMobilePhone(mobile);
        record.setExpressStatus(BoxExpressStatus.FULL.name());
        this.tBoxInfoMapperExt.updateByPrimaryKeySelective(record);
    }

    private String decode(String code, String seckey) {
        String decode = "";
        try {
            byte[] bytes = AESCoder.ecbDec(HexUtil.hexToBytes(code), HexUtil.hexToBytes(seckey));
            for (int i = 0; i < bytes.length && i < 6; i++) {
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
}
