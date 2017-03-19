package com.gaoyang.util;

import com.gaoyang.bean.DianPingUser;
import com.gaoyang.bean.User;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by ssports on 2016/12/22.
 */
public class FileUtil {

    public static void download(String urlString, String filename, String savePath, DianPingUser user) throws Exception {
        // 构造URL
        URL url = new URL(urlString);
        // 打开连接
        URLConnection con = url.openConnection();
        con.setRequestProperty("Accept","*/*");
        con.setRequestProperty("Accept-Encoding","gzip, deflate, br");
        con.setRequestProperty("Accept-Language","zh-CN,zh;q=0.8");
        con.setRequestProperty("Connection","keep-alive");
        con.setRequestProperty("Cookie", "PHOENIX_ID=0a010879-15920812b3d-bf48d4; _hc.v=\"\\\"4a37d7ba-3da6-4987-a747-570c4b462e4f.1482309053\\\"\"; JSESSIONID=A58952430CACFB54534233A2475E748E; aburl=1; cy=1; cye=shanghai; ua=" + user.getUserName() + "; msource=default; default_ab=my%3AA%3A1; pvhistory=\"5oiR55qEPjo8L215Pjo8MTQ4MjMxNDI5MDEyN11fWw==\"; m_flash2=1");
        con.setRequestProperty("Host","www.dianping.com");
        con.setRequestProperty("Referer","https://mlogin.dianping.com/login/password?redir=https%3A%2F%2Fm.dianping.com%2Fmy");
        con.setRequestProperty("User-Agent","Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.76 Mobile Safari/537.36");

        //设置请求超时为5s
        con.setConnectTimeout(5 * 1000);
        // 输入流
        InputStream is = con.getInputStream();

        // 1K的数据缓冲
        byte[] bs = new byte[1024];
        // 读取到的数据长度
        int len;
        // 输出的文件流
        File sf = new File(savePath);
        if (!sf.exists()) {
            sf.mkdirs();
        }
        OutputStream os = new FileOutputStream(savePath + filename);
        // 开始读取
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        // 完毕，关闭所有链接
        os.close();
        is.close();
    }
}
