package com.springboot.blog.springbootblogrestapi;

import com.springboot.blog.springbootblogrestapi.entity.Role;
import com.springboot.blog.springbootblogrestapi.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class SpringbootBlogRestApiApplication implements CommandLineRunner {


	@Autowired
	private RoleRepository roleRepository;
	@GetMapping("/api/post")
    public String greet(Device device){
        System.out.println("called greet method");
        String deviceType="";
        String platform="";
        if(device.isMobile())
            deviceType="mobile";
        else if(device.isTablet())
            deviceType="tablet";
        else if(device.isNormal())
            deviceType="browser";

        platform=device.getDevicePlatform().name();

        if(platform.equalsIgnoreCase("UNKNOWN"))
            platform="browser";
        return "index";
    }
	public static void main(String[] args)  {
		SpringApplication.run(SpringbootBlogRestApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Role user_role=Role.builder().name("ROLE_USER").build();
		Role admin_role=Role.builder().name("ROLE_ADMIN").build();

		if(!roleRepository.findByName("ROLE_USER").isPresent())
			roleRepository.save(user_role);
		if(!roleRepository.findByName("ROLE_ADMIN").isPresent())
			roleRepository.save(admin_role);
	}
}
