package lesson28;

import com.gaoyang.bean.DianPingUser;
import com.gaoyang.util.FileUtil;
import com.gaoyang.util.HttpUtils;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.junit.Test;

import java.net.URLDecoder;
import java.util.Map;

/**
 * Created by ssports on 2016/12/23.
 */
public class TestDianping {
    @Test
    public void action() {
        //是否显示验证码代码
        String tel = "18601162346";
        String sessionId = "";
        Map<String, String> captchaMap = new HashedMap();
        captchaMap.put("captchaChannel", "101");
        captchaMap.put("params", "%7B%22username%22%3A%22" + tel + "%22%7D");
        captchaMap.put("callback", "EasyLoginCallBack1");
        String result1 = HttpUtils.setDianPingLoginRequest("https://m.dianping.com/account/ajax/captchaShow", captchaMap);
        String json_str1 = result1.substring(19, result1.length() - 1);
        JSONObject obj1 = JSONObject.fromObject(json_str1);
        boolean isShow = obj1.getJSONObject("msg").getBoolean("isShow");

        if (isShow) {
            //获取验证码代码
            Map<String, String> picMap = new HashedMap();
            picMap.put("callback", "EasyLoginCallBack2");

            String result2 = HttpUtils.setDianPingLoginRequest("https://www.dianping.com/account/ajax/getCaptcha", picMap);
            String json_str2 = result2.substring(19, result2.length() - 1);
            //获取图片
            JSONObject obj2 = JSONObject.fromObject(json_str2);
            String signature = obj2.getJSONObject("msg").getString("signature");
            System.out.println("signature:" + signature);
            String sha = obj2.getJSONObject("msg").getString("url");
            System.out.println("sha:" + sha);
            String picUrl = sha;
            System.out.println("picUrl = " + picUrl);
            try {
                DianPingUser user = new DianPingUser();
                user.setUserName(tel);
                FileUtil.download(picUrl, "output_" + tel + ".tif", "c:\\image\\", user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void code() {
        String sessionId = "urnd";
        String code = "ywhn";
        String signature = "b01vWLbU-izByQVTnVOe07OfikyH54CfvZTa7iujb2B1vK-NZK0yJrS-xrXSFkia3laeJawdH_Cbli_82CrHJMIuA**";
        Map<String, String> picMap = new HashedMap();
        picMap.put("vcode", code);
        picMap.put("signature", signature);
        picMap.put("callback", "EasyLoginCallBack3");

        String result1 = HttpUtils.setDianPingLoginRequest("https://www.dianping.com/account/ajax/checkCaptcha", picMap);
        System.out.println("" + "验证码：" + " 结果：" + result1);
    }

    @Test
    public void decode() {
        try {
            String str = "http%3A%2F%2Fshcsec.api.qcloud.com%2Fgetcap%3Fappid%3D1251301791%26uid%3D0%26buid%3D1%26sceneid%3D1%26captype%3D1%26sig%3Db01P7ajXidK4LGnFccNaxi1SVhWp_q4NveOMVehNdET2rpMaN5vZKtI7_poHJNJkH10jZnNFygV1dTHAmBuLbmWQg**";
            String s = URLDecoder.decode(str, "utf-8");
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}