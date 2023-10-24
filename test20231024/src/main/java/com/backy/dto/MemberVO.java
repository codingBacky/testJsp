package com.backy.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberVO {
	private int custno;
	private String custname;
	private String phone;
	private String address;
	private String joindate;
	private String grade;
	private String city;
}
