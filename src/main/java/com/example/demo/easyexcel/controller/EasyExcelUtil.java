package com.example.demo.easyexcel.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.example.demo.easyexcel.pojo.AppleCSV;
import com.example.demo.easyexcel.pojo.ChromeCSV;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class EasyExcelUtil {
    public static void main(String[] args) throws FileNotFoundException {
        List<ChromeCSV> chromeCSVS = chromeCSVList();
        List<AppleCSV> appleCSVS = appleCSVList();
        List<ChromeCSV> secretList = new ArrayList<>(chromeCSVS);
        for (AppleCSV appleCSV : appleCSVS) {
            String url = appleCSV.getUrl();
            String userName = appleCSV.getUserName();
            String password = appleCSV.getPassword();
            String title = appleCSV.getTitle();

            long count = secretList.stream().filter(e -> StrUtil.equals(e.getUrl(), url)
                    && StrUtil.equals(e.getPassword(), password)
                    && StrUtil.equals(e.getUserName(), userName)).count();
            if (count == 0) {
                ChromeCSV chromeCSV = new ChromeCSV();
                chromeCSV.setName(title);
                chromeCSV.setUrl(url);
                chromeCSV.setUserName(userName);
                chromeCSV.setPassword(password);
                secretList.add(chromeCSV);
            }

        }

        String outPath = "/Users/wqq/Documents/out_test.csv";
        EasyExcel.write(outPath, ChromeCSV.class).sheet("密码表").doWrite(secretList);
    }

    private static List<AppleCSV> appleCSVList() throws FileNotFoundException {
        //同步读取文件内容
        FileInputStream inputStream = new FileInputStream(new File("/Users/wqq/Documents/密码.csv"));
        List<AppleCSV> list = EasyExcel.read(inputStream).head(AppleCSV.class).excelType(ExcelTypeEnum.CSV).sheet().doReadSync();
        return list;

    }

    private static List<ChromeCSV> chromeCSVList() throws FileNotFoundException {
        //同步读取文件内容
        FileInputStream inputStream = new FileInputStream(new File("/Users/wqq/Documents/Chrome 密码.csv"));
        List<ChromeCSV> list = EasyExcel.read(inputStream).head(ChromeCSV.class).excelType(ExcelTypeEnum.CSV).sheet().doReadSync();
        return list;

    }


}
