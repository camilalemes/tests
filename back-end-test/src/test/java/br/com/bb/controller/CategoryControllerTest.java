package br.com.bb.controller;

import br.com.bb.Application;
import br.com.bb.entity.Category;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
public class CategoryControllerTest extends BaseTestSupport{

	@Before
    public void setup() {
		RestAssured.port = port;
	}

	@Test
    public void listAll() {
		Response response = doGetRequest("/category/listAll", "{}")
				.then()
				.statusCode(HttpStatus.OK.value())
				.extract().response();

		List<Category> categories = Arrays.asList(response.as(Category[].class));

		assertThat(categories).isNotEmpty();
		assertThat(categories).hasSize(3);
		assertThat(categories).extracting("id", "name")
				.contains(tuple(1L, "Alimentos"),
						tuple(2L, "Eletrodomesticos"),
						tuple(3L, "Móveis"));
    }
}
