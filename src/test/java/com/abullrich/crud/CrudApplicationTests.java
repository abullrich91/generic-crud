package com.abullrich.crud;

import lombok.RequiredArgsConstructor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@JdbcTest
@SpringBootTest
@RequiredArgsConstructor
@RunWith(SpringJUnit4ClassRunner.class)
class CrudApplicationTests {

	private final ApplicationContext context;

	@Test
	public void contextLoads() {
		Assert.assertNotNull(context);
	}

}
