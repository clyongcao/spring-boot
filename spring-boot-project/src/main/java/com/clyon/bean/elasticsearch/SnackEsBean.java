package com.clyon.bean.elasticsearch;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "sbdindex", type = "snack")

public class SnackEsBean implements Serializable {
	
	@Id
	@Field(type = FieldType.Long, store = true)
	private long id;

	@Field(type = FieldType.Text, store = true, analyzer = "ik_smart")
	private String name;
	
	@Field(type = FieldType.Text, store = true, analyzer = "ik_smart")
	private String description;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "SnackEsBean [id=" + id + ", name=" + name + ", description=" + description + "]";
	}
	
}
