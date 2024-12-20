package com.spring.security.entity;

import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="users")
public class User {
	public static void main(String[] args) {
		Map<String,Integer> x=new HashMap<>();
	}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "username",nullable = false,unique = true)
    private String name;
    @Column(name = "password",nullable = false)
    private String password;
    @Column(name = "role")
    private String role;
}
