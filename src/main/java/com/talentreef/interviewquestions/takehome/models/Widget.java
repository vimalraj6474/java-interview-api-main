package com.talentreef.interviewquestions.takehome.models;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder(toBuilder=true)
public class Widget {

    @Id
    @Column(unique = true, nullable = false)
    @NotNull
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters.")
    private String name;

    @Column(nullable = false)
    @NotNull
    @Size(min = 5, max = 1000, message = "Description must be between 5 and 1000 characters.")
    private String description;

    @Column(nullable = false)
    @NotNull
    @DecimalMin(value = "1.00", inclusive = true, message = "Price must be at least 1.")
    @DecimalMax(value = "20000.00", inclusive = true, message = "Price must be no more than 20,000.")
    private BigDecimal price;

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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Widget [name=" + name + ", description=" + description + ", price=" + price + "]";
	}

    // Getters and Setters
    
}
