package com.huobaibai.conf;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.huobaibai.Service.ComputeClient;
@Component
public class ComputeClientHystrix implements ComputeClient  {

	@Override
	public Integer add( @RequestParam(value = "a") Integer a, @RequestParam(value = "b")Integer b) {
		// TODO Auto-generated method stub
		return -9999;
	}
	
	

}
