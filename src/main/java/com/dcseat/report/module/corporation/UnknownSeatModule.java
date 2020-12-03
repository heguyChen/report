package com.dcseat.report.module.corporation;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcseat.report.base.CharacterInfo;
import com.dcseat.report.dao.seat.Users;
import com.dcseat.report.module.Alliance;
import com.dcseat.report.module.alliance.AllianceTemplate;
import com.dcseat.report.util.SpringContextUtil;
import com.dcseat.report.util.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class UnknownSeatModule extends CorporationTemplate implements Alliance {
    private static final Logger log = LoggerFactory.getLogger(UnknownSeatModule.class);

    private final Users users = SpringContextUtil.getBean("users");

    private String name = "名单";

    private String corp = "军团";

    /**
     * 构造方法 指定联盟名称
     *
     * @param corporation 父类对象
     */
    public UnknownSeatModule(CorporationTemplate corporation) {
        super();
        this.corps = corporation.corps;
        this.initData();
    }

    @Override
    public int printExcelTitle(Sheet sheet, int row, int col) {
        try {
            // 设置标题
            setCellStyle(sheet.getRow(row + 1).createCell(col++)).setCellValue(name);
            setCellStyle(sheet.getRow(row + 1).createCell(col++)).setCellValue(corp);
        } catch (Exception e) {
            log.error("设置单元格title出错,{}", e.getMessage());
        }
        return col;
    }

    @Override
    public int printExcelValue(Sheet sheet, int row, int col) {
        int temp_col = col;
        for (CharacterInfo c : chars) {
            col = temp_col;
            Row row_ = sheet.createRow(row++);
//            if (row_ == null) row_ = sheet.createRow(row);
            row_.createCell(col++).setCellValue(c.getName());
            row_.createCell(col++).setCellValue(c.getCorpName());
        }
        return col;
    }

    @Override
    public void initData() {
        String firstUrl = "https://esi.evetech.net/latest/characters/";
        //https://esi.evetech.net/latest/characters/2113925305/?datasource=tranquility
        List<CharacterInfo> chars = users.getUsers(corps, StringUtils.getSqlDate());

        BufferedReader in = null;


        for (CharacterInfo c :chars) {
            try {
                String result = "";
                Integer id = c.getId();
                URL url = new URL(firstUrl + id + "/?datasource=tranquility");
                URLConnection urlConnection = url.openConnection();
                // 设置通用的请求属性
                urlConnection.setRequestProperty("accept", "*/*");
                urlConnection.setRequestProperty("connection", "Keep-Alive");
                urlConnection.setRequestProperty("user-agent",
                        "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
                // 建立实际的连接
                urlConnection.connect();
                // 定义 BufferedReader输入流来读取URL的响应
                in = new BufferedReader(new InputStreamReader(
                        urlConnection.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    result += line;
                }
                System.out.print(chars.indexOf(c));
                System.out.print(",");
                System.out.println(chars.size());
                JSONObject parse = (JSONObject) JSON.parse(result);
                c.setName((String) parse.get("name"));
            } catch (Exception e) {

            }finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        this.chars = chars;
    }
}
