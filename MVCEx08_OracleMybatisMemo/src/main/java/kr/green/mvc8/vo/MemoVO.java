package kr.green.mvc8.vo;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@XmlType(propOrder = {"idx","name","password","content","regDate","ip"})
public class MemoVO {
	private int    idx;
	private String name;
	private String password;
	private String content;
	private Date   regDate;
	private String ip;
}
