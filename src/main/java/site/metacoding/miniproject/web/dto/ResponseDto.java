package site.metacoding.miniproject.web.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseDto<T> {	   
	private Integer code;
	private String message;
	private T data;
}