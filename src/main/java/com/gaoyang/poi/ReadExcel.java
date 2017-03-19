/**
 * 
 */
package com.gaoyang.poi;

import com.gaoyang.bean.Taobaoke;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Hongten
 * @created 2014-5-20
 */
public class ReadExcel {
    
    /**
     * read the Excel file
     * @param path the path of the Excel file
     * @return
     * @throws IOException
     */
    public List<Taobaoke> readExcel(String path, String type) throws IOException {
        if (path == null || Common.EMPTY.equals(path)) {
            return null;
        } else {
            return readXls(path, type);
        }
    }

    /**
     * Read the Excel 2003-2007
     * @param path the path of the Excel
     * @return
     * @throws IOException
     */
    public List<Taobaoke> readXls(String path, String type) throws IOException {
        int a = 0; int b = 0; int c = 0;
        if (type.equals("1")) {
            a = 25; b = 20; c = 24;
        } else {
            a = 20; b = 15; c = 19;
        }



        System.out.println(Common.PROCESSING + path);
        InputStream is = new FileInputStream(path);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        List<Taobaoke> list = new ArrayList<>();
        // Read the Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // Read the Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow != null) {
                    Taobaoke taobaoke = new Taobaoke();
                    //商品主图
                    HSSFCell productPic = hssfRow.getCell(2);
                    //产品名称
                    HSSFCell productName = hssfRow.getCell(1);
                    //领券下单地址
                    HSSFCell couponShortUrl = hssfRow.getCell(a);
                    //产品原价
                    HSSFCell origin = hssfRow.getCell(5);
                    //券后价格
                    String discount = "";
                    HSSFCell couponValue = hssfRow.getCell(b);
                    if (getValue(couponValue).equals("无")) {
                        continue;
                    }
                    if (getValue(couponValue).contains("满") && getValue(couponValue).contains("减")) {
                        String cutStr = getValue(couponValue).replace("元", "");
                        int man = cutStr.indexOf("满");
                        int jian = cutStr.indexOf("减");
                        String firstValue = cutStr.substring(man + 1, jian);
                        String secondValue = cutStr.substring(jian + 1, cutStr.length());
                        if (Double.valueOf(getValue(origin)) >= Double.valueOf(firstValue)){
                            discount = String.format("%.2f", Double.valueOf(getValue(origin)) - Double.valueOf(secondValue));
//                            discount = String.valueOf(Double.valueOf(getValue(origin)) - Double.valueOf(secondValue));
                        }
                    } else if (getValue(couponValue).contains("元无条件券")) {
                        String cutStr = getValue(couponValue).replace("元无条件券", "");
                        if (Double.valueOf(getValue(origin)) >= Double.valueOf(cutStr)){
                            discount = String.format("%.2f", Double.valueOf(getValue(origin)) - Double.valueOf(cutStr));
//                            discount = String.valueOf(Double.valueOf(getValue(origin)) - Double.valueOf(cutStr));
                        }
                    } else {
                        System.out.println("无法处理的优惠券类型");
                    }

//                    HSSFCell setCouponLongUrl = hssfRow.getCell(18);
//                    String htmlCouponDetail = HttpUtils.sendTaobaokeGet(getValue(setCouponLongUrl));
//                    System.out.println("htmlCouponDetail:" + htmlCouponDetail);
//                    Document couponDoc = Jsoup.parse(htmlCouponDetail);
//                    String origin = couponDoc.getElementsByClass("origin").text();
//                    String discount = couponDoc.getElementsByClass("sale").text();

//                    HSSFCell productDetailUrl = hssfRow.getCell(3);
//                    String htmlProductDetail = HttpUtils.sendTaobaokeGet(getValue(productDetailUrl));
//                    System.out.println("productDetail:" + htmlProductDetail);
//                    Document detailDoc = Jsoup.parse(htmlProductDetail);
//                    //产品介绍
//                    String intro = detailDoc.getElementsByClass("newp").text();
//                    System.out.println("intro:" + intro);
                    //淘口令
                    HSSFCell couponTaokouling = hssfRow.getCell(c);

                    taobaoke.setProductPic(getValue(productPic));
                    taobaoke.setProductName(getValue(productName));
                    taobaoke.setCouponShortUrl(getValue(couponShortUrl));
                    taobaoke.setOrigin(getValue(origin));
                    taobaoke.setDiscount(discount);
//                    taobaoke.setIntro(intro);
                    taobaoke.setCouponTaokouling(getValue(couponTaokouling));

                    list.add(taobaoke);
                }
            }
        }
        return list;
    }

    @SuppressWarnings("static-access")
    private String getValue(HSSFCell hssfCell) {
        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
            return String.valueOf(hssfCell.getNumericCellValue());
        } else {
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }
}