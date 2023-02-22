package com.example.demo.easyexcel.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.example.demo.easyexcel.pojo.ChromeCSV;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class EasyExcelUtil2 {
    public static void main(String[] args) throws FileNotFoundException {
        List<ChromeCSV> outTestCSVS = chromeCSVList("/Users/wqq/Documents/out_test.csv");
        List<ChromeCSV> chromeCSVS = chromeCSVList("/Users/wqq/Downloads/Chrome 密码.csv");
        List<ChromeCSV> excludList = new ArrayList<>();

        for (ChromeCSV outTestCSV : outTestCSVS) {
            String url = outTestCSV.getUrl();
            String userName = outTestCSV.getUserName();
            String password = outTestCSV.getPassword();
            long count = chromeCSVS.stream().filter(e -> StrUtil.equals(e.getUrl(), url)
                    && StrUtil.equals(e.getPassword(), password)
                    && StrUtil.equals(e.getUserName(), userName)).count();
            if (count == 0) {
                excludList.add(outTestCSV);
            }

        }
        System.out.println(excludList);

    }

    private static List<ChromeCSV> chromeCSVList(String filePath) throws FileNotFoundException {
        //同步读取文件内容
        FileInputStream inputStream = new FileInputStream(filePath);
        List<ChromeCSV> list = EasyExcel.read(inputStream).head(ChromeCSV.class).excelType(ExcelTypeEnum.CSV).sheet().doReadSync();
        return list;

    }


}
