package jp.co.axa.apidemo.model;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class EmployeeDetailsDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5773924646623757088L;

	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	private String name;

	@Getter
	@Setter
	private Integer salary;

	@Getter
	@Setter
	private String department;

	@Override
	public String toString() {
		return "EmployeeDetailsDTO [id=" + id + ", name=" + name + ", salary=" + salary + ", department=" + department
				+ "]";
	}
}
