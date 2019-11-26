package com.clyon.dto.es;

import lombok.Data;

@Data
public class SnackDTO {

	private long id;

	private String name;

	private String description;

	private Integer pageNow;

	private Integer pageSize;

}
