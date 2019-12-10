package com.etnetera.hr.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.sql.Timestamp;


/**
 * Simple data entity describing basic properties of every JavaScript framework.
 * 
 * @author Etnetera
 *
 */
@Entity
public class JavaScriptFramework {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, length = 30)
	private String name;

	@Column(nullable = false)
	private String version;
	
	@Column()
	private Timestamp deprecationDate;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private HypeLevel hypeLevel;

	public JavaScriptFramework() {
	}

	public JavaScriptFramework(String name, String version, Timestamp deprecationDate, HypeLevel hypeLevel) {
		this.name = name;
		this.version = version;
		this.deprecationDate = deprecationDate;
		this.hypeLevel = hypeLevel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Timestamp getDeprecationDate() {
		return deprecationDate;
	}

	public void setDeprecationDate(Timestamp deprecationDate) {
		this.deprecationDate = deprecationDate;
	}


	public HypeLevel getHypeLevel() {
		return hypeLevel;
	}

	public void setHypeLevel(HypeLevel hypeLevel) {
		this.hypeLevel = hypeLevel;
	}

	@Override
	public String toString() {
		return "JavaScriptFramework [id=" + id + ", name=" + name + "]";
	}
}
