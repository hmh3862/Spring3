package kr.green.mvc8.vo;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@XmlRootElement // 루트 태그로 쓰겠다
@XmlAccessorType(XmlAccessType.FIELD) // 타입을 필드에 쓰겠다.
@XmlType(propOrder = {"idx","ref","seq","lev","name","content","regDate","ip","del"}) // 나타나는 순서
public class GuestVO {
	@XmlAttribute // 속성으로 쓰겠다.
	private int idx;
	@XmlAttribute
	private int ref;
	@XmlAttribute
	private int seq;
	@XmlAttribute
	private int lev;
	@XmlElement // 태그로 쓰겠다.
	private String name;
	//@XmlElement
	@XmlTransient // XML저장시 제외하겠다.
	private String password;
	@XmlElement
	private String content;
	@XmlElement
	@XmlJavaTypeAdapter(DateXmlAdapter.class) // 읽기/저장 시 모양 변경
	private Date   regDate;
	@XmlElement
	private String ip;
	@XmlElement
	private String del;
}
