package com.example.demo.ppt;

import org.apache.poi.xslf.usermodel.*;

import java.io.*;
import java.util.List;

/**
 * @author Jin
 */

public class PowerPointGenerator {


    public static void main(String[] args) throws IOException {
        // 打开模板PPTX文件
        XMLSlideShow pptx = new XMLSlideShow(new FileInputStream("template.pptx"));
        // 获取幻灯片数组
        List<XSLFSlide> slides = pptx.getSlides();
        // 获取每个幻灯片
        for (XSLFSlide slide : slides) {
            // 获取幻灯片上的形状
            for (XSLFShape shape : slide.getShapes()) {
                // 查找表格形状
                if (shape instanceof XSLFTable) {
                    XSLFTable table = (XSLFTable) shape;
                    // 填充表格
                    table.getCell(0, 0).setText("Name");
                    table.getCell(1, 0).setText("Role");
                    table.getCell(2, 0).setText("Vehicle Info");
                    table.getCell(0, 1).setText("John");
                    table.getCell(1, 1).setText("User");
                    table.getCell(2, 1).setText("Hatchback, Blue, License Plate #ABC123");
                }
            }
        }
        // 保存新的PPTX文件
        FileOutputStream out = new FileOutputStream("newfile.pptx");
        pptx.write(out);
        out.close();
        pptx.close();
    }
}