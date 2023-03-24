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
            for (int i = 0; i < slide.getShapes().size(); i++) {
                XSLFShape shape = slide.getShapes().get(i);
                // 查找表格形状
                if (shape instanceof XSLFTable && i == 0) {
                    XSLFTable table = (XSLFTable) shape;
                    // 填充表格
                    table.getCell(0, 0).setText("Name");
                    table.getCell(1, 0).setText("Role");
                    table.getCell(2, 0).setText("Vehicle Info");
                    table.getCell(0, 1).setText("John");
                    table.getCell(1, 1).setText("User");
                    table.getCell(2, 1).setText("Hatchback, Blue, License Plate #ABC123");
                } else if (shape instanceof XSLFTable && i == 1) {
                    XSLFTable table = (XSLFTable) shape;
                    // 填充表格
                    table.getCell(0, 0).setText("Rank");
                    table.getCell(1, 0).setText("第二行");
                    table.getCell(2, 0).setText("第三行");
                    table.getCell(0, 1).setText("C");
                    table.getCell(1, 1).setText("第二行value");
                    table.getCell(2, 1).setText("第三行答案");
                    table.getCell(3, 0).setText("第四行");
                    table.getCell(3, 1).setText("第四行答案");
                } else {
                    XSLFTable table = (XSLFTable) shape;
                    // 填充表格
                    table.getCell(0, 0).setText("Others");
                    table.getCell(1, 0).setText("第二行");
                    table.getCell(2, 0).setText("第三行");
                    table.getCell(0, 1).setText("");
                    table.getCell(1, 1).setText("第二行value");
                    table.getCell(2, 1).setText("第三行答案");
                    table.getCell(3, 0).setText("第四行");
                    table.getCell(3, 1).setText("第四行答案");
                    table.getCell(4, 0).setText("第五行");
                    table.getCell(4, 1).setText("第五行答案");
                    table.getCell(5, 0).setText("第六行");
                    table.getCell(5, 1).setText("第六行答案");
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