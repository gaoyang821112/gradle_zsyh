package com.gaoyang.controller;

import com.gaoyang.util.DateUtil;
import com.gaoyang.util.MailUtil;
import com.gaoyang.util.PropertiesHolder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by gaoyang on 2016/4/16.
 */
@Controller
@RequestMapping("/card")
public class CardWorldController {

    public static String CARD_IDS = (String) PropertiesHolder.getContextProperty("cardIds");

    @RequestMapping("/start")
    public void start() throws Exception{
        String mailStr = "";
        for (String cardId : CARD_IDS.split(",")) {
            Document doc = Jsoup.connect("http://www.kazuworld.com.cn/market/item/" + cardId).get();
            if (doc != null) {
                long hour = DateUtil.getDiffHour(this.getEndTimeStr(doc));
                long min = DateUtil.getDiffMin(this.getEndTimeStr(doc));
                if (hour == 0 && (min == 30 || min == 10 || min == 3)) {
                    String contentStr = this.getCardStr(doc, cardId, true);
                    mailStr += contentStr + "</br>";
                }
            }
        }
        if (!mailStr.equals("")) {
            MailUtil.sendMail("球星卡到时提示", mailStr.toString());
        }
    }

    @RequestMapping("/everyday")
    public void everyday() throws Exception{
        String mailStr = "";
        for (String cardId : CARD_IDS.split(",")) {
            Document doc = Jsoup.connect("http://www.kazuworld.com.cn/market/item/" + cardId).get();
            if (doc != null) {
                String endDateStr = this.getEndTimeStr(doc);
                long day = DateUtil.getDiffDay(endDateStr);
                long hour = DateUtil.getDiffHour(endDateStr);
                System.out.println("cardId=" + cardId);
                if (hour <= 24 && (day == 0 || day ==1)) {
                    String contentStr = this.getCardStr(doc, cardId, false);
                    System.out.println(contentStr);
                    mailStr += contentStr;
                }
            }
        }
        if (!mailStr.equals("")) {
            MailUtil.sendMail("球星卡整点提示", mailStr.toString());
        }
    }

    private String getCardStr(Document doc, String cardId, boolean isShowBid) throws Exception{
        //标题
        String titleText = doc.getElementsByClass("blueTitle").get(0).text();
        //卖家
        Element sellerDiv = doc.getElementsByClass("messageBoxThumbnail").get(0).parent();
        String sellerText = sellerDiv.nextElementSibling().child(0).child(0).text();
        //结束时间
        String endTimeText = getEndTimeStr(doc);
        //当前价
        String currentPriceText = doc.getElementsByAttributeValue("id","currentPrice").get(0).text();
        //出价次数
        String priceTimes = doc.getElementsByAttributeValue("id","currentPrice").get(0).nextElementSibling().child(0).text();
        //邮费
//        Element postPriceSpan = doc.getElementsMatchingOwnText("邮费").get(0);
//        Element postPriceTd = postPriceSpan.parent().nextElementSibling().nextElementSibling();
//        String postPriceText = postPriceTd.text();
        //宝贝图片
        Element picSpan = doc.getElementsMatchingOwnText("宝贝图片").get(0);
        Elements picDivs = picSpan.parent().nextElementSibling().children();
        StringBuilder picStr = new StringBuilder();
        for (Element pic : picDivs) {
            Element picImg = pic.child(0);
            picImg.attr("src", "http://www.kazuworld.com.cn" + pic.child(0).attr("src"));
            picStr.append(picImg.toString()).append("</br>");
        }

        StringBuilder contentStr = new StringBuilder();
        contentStr
                .append("链接:http://www.kazuworld.com.cn/market/item/" + cardId).append("</br>")
                .append("物品名称:" + titleText).append("</br>")
                .append("卖家名称:" + sellerText).append("</br>")
                .append("结束时间:" + endTimeText).append("</br>")
                .append("当前价格:" + currentPriceText).append("</br>")
                .append("出价次数:" + priceTimes + "次出价").append("</br>")
//                .append("邮费:" + postPriceText).append("</br>")
                .append("图片:" + picStr.toString());

        if (isShowBid) {
            Document bidrecords_doc = Jsoup.connect("http://www.kazuworld.com.cn/market/bidrecords/" + cardId).get();
            String bidTableStr = bidrecords_doc.getElementsByClass("table-bordered").get(0).toString();
            contentStr.append("出价记录:" + bidTableStr).append("</br>");
        }

        return contentStr.toString();
    }

    private String getEndTimeStr(Document doc) {
        Element endTimeSpan = doc.getElementsMatchingOwnText("结束时间").get(0);
        Element endTimeTd = endTimeSpan.parent().nextElementSibling().nextElementSibling();
        return endTimeTd.child(0).text();
    }

}
