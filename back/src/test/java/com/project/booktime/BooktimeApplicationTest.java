package com.project.booktime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.project.booktime.controllers.ExempleController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ExempleController.class)
class BooktimeApplicationTest {

	@Test
	void contextLoads() {}
}
