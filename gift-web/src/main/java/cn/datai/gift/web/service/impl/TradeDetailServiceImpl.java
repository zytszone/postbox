package cn.datai.gift.web.service.impl;

import cn.datai.gift.persist.mapper.TradeOrderMapperExt;
import cn.datai.gift.persist.mapper.UserBizBriefMapperExt;
import cn.datai.gift.persist.po.TradeOrder;
import cn.datai.gift.persist.po.UserBizBrief;
import cn.datai.gift.persist.po.UserBizBriefExample;
import cn.datai.gift.persist.vo.commodity.CommodityVo;
import cn.datai.gift.utils.DateUtil;
import cn.datai.gift.web.service.CommodityService;
import cn.datai.gift.web.service.TradeDetailService;
import cn.datai.gift.web.vo.tradeDetail.TradeDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/4/27.
 */
@Service
public class TradeDetailServiceImpl implements TradeDetailService {

    @Autowired
    private UserBizBriefMapperExt userBizBriefMapperExt;

    @Autowired
    private CommodityService commodityService;

    @Autowired
    private TradeOrderMapperExt tradeOrderMapperExt;

    /**
     * 查询用户交易明细
     *
     * @param bizType
     * @param commodityTypeId
     * @param date
     * @param userInfoId
     * @return
     */
    @Override
    public List<TradeDetailVo> queryRecords(String bizType, Long commodityTypeId, String date, Long userInfoId) throws IOException {
        List<TradeDetailVo> list = new ArrayList<>();
        Date beginDateTime = DateUtil.monthStart(date);
        Date endDateTime = DateUtil.monthEnd(date);

        UserBizBriefExample userBizBriefExample = new UserBizBriefExample();
        userBizBriefExample.setOrderByClause("CREATE_TIME DESC");
        UserBizBriefExample.Criteria criteria = userBizBriefExample.createCriteria();
        criteria.andUserInfoIdEqualTo(userInfoId).andCreateTimeBetween(beginDateTime,endDateTime);
        if(!StringUtils.isEmpty(bizType)){
            criteria.andBizTypeEqualTo(bizType);
        }
        if(commodityTypeId>0){
            criteria.andCommodityTypeIdEqualTo(commodityTypeId);
        }
        List<UserBizBrief> userBizBriefs = userBizBriefMapperExt.selectByExample(userBizBriefExample);

        for(UserBizBrief userBizBrief :userBizBriefs){
            TradeDetailVo tradeDetailVo = new TradeDetailVo();
            tradeDetailVo.setBizId(userBizBrief.getBizId());
            tradeDetailVo.setBizType(userBizBrief.getBizType());
            tradeDetailVo.setCommodityContractId(userBizBrief.getCommodityContractId());
            tradeDetailVo.setCreateTime(userBizBrief.getCreateTime());
            tradeDetailVo.setOpCommodityUnitQuantity(userBizBrief.getOpCommodityUnitQuantity());
//            tradeDetailVo.setAttach(userBizBrief.getAttach());
            CommodityVo commodityVo = commodityService.findById(userBizBrief.getCommodityContractId());
            tradeDetailVo.setCommodityName(commodityVo.getCommodityName());
            tradeDetailVo.setUnitPrice(commodityVo.getUnitPrice());
            tradeDetailVo.setUnit(commodityVo.getUnit());
            tradeDetailVo.setUnitEn(commodityVo.getUnitEn());
            tradeDetailVo.setCommodityTypeId(commodityVo.getCommodityTypeId());
            tradeDetailVo.setSubjectMatter(commodityVo.getSubjectMatter());
            tradeDetailVo.setBizStatus(userBizBrief.getBizStatus());
            tradeDetailVo.setTypeName(commodityVo.getTypeName());
            list.add(tradeDetailVo);
        }
        return list;
    }

    /**
     * 买详细信息
     * @param tradeOrderId
     * @return
     */
    @Override
    public TradeOrder queryTradeOrder(Long tradeOrderId) {
        return tradeOrderMapperExt.selectByPrimaryKey(tradeOrderId);
    }

    /**
     * 通过业务类型和业务ID查询业务概况
     *
     * @param bizId
     * @param bizType
     * @return
     */
    @Override
    public List<UserBizBrief> queryUserBizBriefList(Long bizId, String bizType) {

        UserBizBriefExample userBizBriefExample = new UserBizBriefExample();
        userBizBriefExample.createCriteria().andBizIdEqualTo(bizId.toString()).andBizTypeEqualTo(bizType);
        return userBizBriefMapperExt.selectByExample(userBizBriefExample);
    }
}
