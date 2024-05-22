package com.Api.Pojo.Address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddAddress_output_Pojo {

	private int status;
	private String message;
	private int address_id;
}
