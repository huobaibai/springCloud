package com.huobaibai.poi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupDataTest {
	
	@Test
	public void ImportCroupTempletTest() {
		
		new ImportGroupTemplet().execute();		
	}

}
