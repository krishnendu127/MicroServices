package com.KB.Inventory;

import org.springframework.boot.SpringApplication;

public class TestInventoryApplication {

	public static void main(String[] args) {
		SpringApplication.from(InventoryApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
