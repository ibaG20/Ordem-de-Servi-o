package com.gabi.os;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrdemDeServicoApplication{
	
	public static void main(String[] args) {
		SpringApplication.run(OrdemDeServicoApplication.class, args);
	}

}

/*
spring_profiles_active=prod
PROD_DB_URL=mysql://${{ MYSQLUSER }}:${{ MYSQLPASSWORD }}@${{ MYSQLHOST }}:${{ MYSQLPORT }}/${{ MYSQLDATABASE }}
PROD_DB_URL=mysql://${{ root }}:${{ xJQj3f7jHB4ygrotvsgK }}@${{ containers-us-west-193.railway.app }}:${{ 6683 }}/${{ railway }}
PROD_DB_HOST=containers-us-west-193.railway.app
PROD_DB_PORT=6683
PROD_DB_NAME=railway
PROD_DB_PASSWORD=xJQj3f7jHB4ygrotvsgK
PROD_DB_USERNAME=root
*/
