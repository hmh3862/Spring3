package kr.green.mvc6.vo;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@XmlRootElement(name = "student")
@XmlType(propOrder = {"idx","name","section"})
public class StudentVO {
	private int idx;
	private String name;
	private String section;
}
