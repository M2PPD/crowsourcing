package com.ppd.crowdsourcing;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.microsoft.sqlserver.jdbc.*;
import com.ppd.crowdsourcing.entity.Ligne;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrowdsourcingApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrowdsourcingApplication.class, args);
	}
}
