package kr.green.mvc8.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@XmlRootElement(name = "guestbooks")
@XmlAccessorType(XmlAccessType.FIELD)
public class GuestbooktList {
	@XmlElement(name="guest")
	private List<GuestVO> guestList;
}
