package com.example.demo.easyexcel.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class AppleCSV {
    @ExcelProperty(value = "Title")
    private String title;
    @ExcelProperty(value = "URL")
    private String url;
    @ExcelProperty(value = "Username")
    private String userName;
    @ExcelProperty(value = "Password")
    private String password;
}
