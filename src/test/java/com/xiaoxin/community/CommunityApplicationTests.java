package com.xiaoxin.community;

import com.xiaoxin.community.dao.AlphaDAO;
import com.xiaoxin.community.service.AlphaService;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.text.SimpleDateFormat;

@SpringBootTest
class CommunityApplicationTests implements ApplicationContextAware {

	private ApplicationContext applicationContext;
	@Autowired
	private SimpleDateFormat simpleDateFormat;
	@Autowired
	@Qualifier("alphaHibernate")
	private AlphaDAO alphaDAO;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Test
	public void testApplicationContext() {
		System.out.println(applicationContext);
		AlphaDAO alphaDAO = applicationContext.getBean("alphaHibernate",AlphaDAO.class);
		System.out.println(alphaDAO.select());
	}

	@Test
	public void testBeanManagement() {
		 AlphaService alphaService =  applicationContext.getBean(AlphaService.class);
		 System.out.println(alphaService);
		alphaService =  applicationContext.getBean(AlphaService.class);
		System.out.println(alphaService);
	}

	@Test
	//测试simpleDateFormat
	public void testSimpleDateFormat() {
		SimpleDateFormat simpleDateFormat =  applicationContext.getBean(SimpleDateFormat.class);
		System.out.println(simpleDateFormat.format(System.currentTimeMillis()));
	}

	@Test
	public void testDI() {
		System.out.println(simpleDateFormat);
		System.out.println(alphaDAO);
	}
}
