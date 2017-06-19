package cn.datai.gift.web.service.impl;

import cn.datai.gift.persist.mapper.TBoxInfoMapperExt;
import cn.datai.gift.persist.po.TCustomerInfo;
import cn.datai.gift.web.service.BoxInfoService;
import cn.datai.gift.web.utils.sec.AESCoder;
import cn.datai.gift.web.utils.sec.HexUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by H.CAAHN on 2017/5/16.
 */
@Service
public class BoxInfoServiceImpl implements BoxInfoService {
    @Autowired
    private TBoxInfoMapperExt tBoxInfoMapperExt;

    private static final Logger logger = LoggerFactory.getLogger(BoxInfoServiceImpl.class);

    public String updateForDecode(Long boxId, String mkey, TCustomerInfo tCustomerInfo) {
//        TBoxInfo box = this.tBoxInfoMapperExt.selectByPrimaryKey(boxId);
//        if (box == null) {
//            return "没有找到对应的箱子";
//        }
//
//        boolean open = false;
//        // 箱子是空的
//        if ("E".equalsIgnoreCase(box.getMstatus())) {
//            // 快递员，允许打开箱子
//            if ("true".equalsIgnoreCase(userInfo.getIsSpecial())) {
//                open = true;
//                if (logger.isDebugEnabled()) {
//                    logger.debug("箱子为空，快递员打开箱子");
//                }
//            }
//            // 普通用户，且箱子和用户的手机号相同，则判断上次打开时间是不是在10分钟之内
//            else if (StringUtils.equals(userInfo.getMobilePhone(), box.getMobileno())) {
//                Calendar calendar = Calendar.getInstance();
//                long nowtime = calendar.getTimeInMillis();
//
//                Date opentime = box.getOpentime();
//                if (opentime == null) {
//                    open = true;
//                }
//                else {
//                    calendar.setTime(opentime);
//                    open = (nowtime - calendar.getTimeInMillis()) < (1000 * 60 * 10);
//                }
//
//                if (logger.isDebugEnabled()) {
//                    logger.debug("箱子为空，客户重新打开{}", open ? "成功" : "失败");
//                }
//            }
//            else if (logger.isDebugEnabled()) {
//                logger.debug("箱子为空，但手机号不匹配: {}, {}", userInfo.getMobilePhone(), box.getMobileno());
//            }
//        }
//        // 箱子里有货物，且箱子和用户的手机号相同
//        else if (StringUtils.equals(userInfo.getMobilePhone(), box.getMobileno())) {
//            open = true;
//            box.setMstatus("E");
//            box.setOpentime(new Date());
//            this.tBoxInfoMapperExt.updateByPrimaryKeySelective(box);
//            if (logger.isDebugEnabled()) {
//                logger.debug("箱子有货物，且箱子和用户手机号相同，允许打开");
//            }
//        }
//        else if (logger.isDebugEnabled()) {
//            logger.debug("箱子有货物，但手机号不匹配: {}, {}", userInfo.getMobilePhone(), box.getMobileno());
//        }
//
//        if (open) {
//            if (logger.isDebugEnabled()) {
//                logger.debug("客户/快递员: {} 成功打开箱子: {}, 状态: {}", userInfo.getMobilePhone(), boxId, box.getMstatus());
//            }
//
//            String decode = "";
//            try {
//                byte[] bytes = AESCoder.ecbDec(HexUtil.hexToBytes(mkey), HexUtil.hexToBytes(box.getSkey()));
////                decode = new String(bytes).substring(0, 6);
//                for (int i = 0; i < bytes.length && i < 6; i++) {
//                    decode += bytes[i];
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return decode;
//        }
//        else if (logger.isDebugEnabled()) {
//            logger.debug("客户/快递员: {} 无权开启箱子: {}, 状态: {}", userInfo.getMobilePhone(), boxId, box.getMstatus());
//        }
//
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
    public TBoxInfo queryById(Long id) {
        return this.tBoxInfoMapperExt.selectByPrimaryKey(id);
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
