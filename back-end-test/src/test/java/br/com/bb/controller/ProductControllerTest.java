package br.com.bb.controller;

import br.com.bb.Application;
import br.com.bb.entity.Product;
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
public class ProductControllerTest extends BaseTestSupport{

	@Before
    public void setup() {
		RestAssured.port = port;
	}

	@Test
    public void listByCategoryAlimentos() {
		Response response = doGetRequest("/product/listByCategory/1", "{}")
				.then()
				.statusCode(HttpStatus.OK.value())
				.extract().response();

		List<Product> products = Arrays.asList(response.as(Product[].class));

		assertThat(products).isNotEmpty();
		assertThat(products).extracting("id", "name")
				.contains(tuple(1L, "Arroz"),
						tuple(2L, "Feijão"));
    }

	@Test
	public void listByCategoryEletrodomesticos() {
		Response response = doGetRequest("/product/listByCategory/2", "{}")
				.then()
				.statusCode(HttpStatus.OK.value())
				.extract().response();

		List<Product> products = Arrays.asList(response.as(Product[].class));

		assertThat(products).isNotEmpty();
		assertThat(products).extracting("id", "name")
				.contains(tuple(3L, "Aspirador de pó"),
						tuple(4L, "Batedeira"),
						tuple(5L, "Liquidificador"));
	}

	@Test
	public void listByCategoryMoveis() {
		Response response = doGetRequest("/product/listByCategory/3", "{}")
				.then()
				.statusCode(HttpStatus.OK.value())
				.extract().response();

		List<Product> products = Arrays.asList(response.as(Product[].class));

		assertThat(products).isNotEmpty();
		assertThat(products).extracting("id", "name")
				.contains(tuple(6L, "Sofá"),
						tuple(7L, "Mesa"),
						tuple(8L, "Estante"));
	}

	@Test
	public void listByInvalidCategory() {
		doGetRequest("/product/listByCategory/99", "{}")
				.then()
				.statusCode(HttpStatus.NOT_FOUND.value());
	}
}
