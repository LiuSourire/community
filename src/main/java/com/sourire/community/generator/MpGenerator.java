package com.sourire.community.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class MpGenerator {
	public static void main(String[] args) {
		//1.全局配置
		GlobalConfig gc = new GlobalConfig();
		gc.setActiveRecord(true) //是否支持AR模式
		  .setAuthor("sourire")  //作者
		  .setFileOverride(true)   //文件是否覆盖
		  .setOutputDir("F:\\springbootDemo\\community\\src\\main\\java")  //文件输出路径
		  .setBaseResultMap(true)
		  .setBaseColumnList(true)
		  .setIdType(IdType.AUTO)
		  .setServiceName("%sService")
				//设置实体类的date类型为util包下的date
		  .setDateType(DateType.ONLY_DATE);
		//2.数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setDbType(DbType.MYSQL)
		   .setDriverName("com.mysql.cj.jdbc.Driver")
		   .setUrl("jdbc:mysql://localhost:3306/community?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT%2b8")
		   .setUsername("root")
		   .setPassword("root");
		//3.策略配置
		StrategyConfig sc = new StrategyConfig();
		sc.setNaming(NamingStrategy.underline_to_camel)
          .setColumnNaming(NamingStrategy.underline_to_camel)
          //.setSuperEntityClass("com.sourire.BaseEntity")
          .setEntityLombokModel(true)
          .setRestControllerStyle(true)
          //.setSuperControllerClass("com.sourire.BaseController")
          .setSuperEntityColumns("id")
          .setInclude("question")
          .setControllerMappingHyphenStyle(true);
		//4.包名配置
		PackageConfig pc = new PackageConfig();
		pc.setParent("com.sourire.community")
		  .setEntity("entity")
		  .setMapper("mapper")
		  .setService("service")
		  .setController("controller")
		  .setXml("xml");
		//5.整合配置
		AutoGenerator ag = new AutoGenerator();
		ag.setGlobalConfig(gc)
		.setDataSource(dsc)
		.setStrategy(sc)
		.setPackageInfo(pc);
		//6.执行
		ag.execute();
	}
}
