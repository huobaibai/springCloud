package com.huobaibai.Service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.huobaibai.conf.ComputeClientHystrix;

@FeignClient(value="compute-service",fallback = ComputeClientHystrix.class )
public interface ComputeClient {
  //通过Spring MVC的注解 服务"compute-service" 下的具体实现
	@RequestMapping(method = RequestMethod.GET, value = "/add")
    Integer add(@RequestParam(value = "a") Integer a, @RequestParam(value = "b") Integer b);
	
}
