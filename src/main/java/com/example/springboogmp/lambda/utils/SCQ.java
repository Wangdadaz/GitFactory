package com.example.springboogmp.lambda.utils;//wangDD

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

//2022-05-2022/5/1-20:29
public class SCQ {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/jxl?rewriteBatchedStatements=true&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC&allowMultiQueries=true", "root", "root")
                .globalConfig(builder -> {
                    builder.author("baomidou") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("C:\\Users\\86182\\eclipse-workspace\\springboog-mp"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("src.main.java.com.example.springboogmp") // 设置父包名
                            .moduleName("mybatis") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "C:\\Users\\86182\\eclipse-workspace\\springboog-mp\\src\\main\\resources\\mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("menu","emp","dining") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_","m_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }
}
