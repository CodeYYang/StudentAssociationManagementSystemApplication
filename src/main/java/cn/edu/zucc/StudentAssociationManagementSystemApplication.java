package cn.edu.zucc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@MapperScan("cn.edu.zucc.mapper")
@SpringBootApplication
//开启swaggerUi
@EnableSwagger2
public class StudentAssociationManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentAssociationManagementSystemApplication.class, args);
	}

}
