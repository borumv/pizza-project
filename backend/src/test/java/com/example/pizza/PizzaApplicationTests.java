package com.example.pizza;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@ActiveProfiles("test")
@SpringBootTest
@Sql(value = {"/sql/init.sql", "/sql/data.sql"})
class PizzaApplicationTests {

	@Test
	void contextLoads() {
	}

}
