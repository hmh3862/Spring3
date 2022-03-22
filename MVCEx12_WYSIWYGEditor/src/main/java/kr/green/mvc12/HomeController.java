package kr.green.mvc12;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		return "home";
	}
	
	// 다운로드가 가능하게 하자!!!!
	@RequestMapping(value = "/download")
	public ModelAndView download(@RequestParam HashMap<Object, Object> params, ModelAndView mv) {
		String ofileName = (String) params.get("of");
		String sfileName = (String) params.get("sf");
		mv.setViewName("downloadView");
		mv.addObject("ofileName",ofileName);
		mv.addObject("sfileName",sfileName);
		return mv;
	}
	// 써머노트
	@RequestMapping(value = "summernote")
	public String summernote() {
		return "summernote";
	}
	
	@RequestMapping(value = "result")
	public String result() {
		return "result";
	}

	// 섬머노트의 큰 이미지를 처리하기위한 주소 1개를 생성해 주어야 한다.
	@RequestMapping(value = "/imageUpload", method = RequestMethod.POST)
	@ResponseBody
	public String imageUpload(MultipartHttpServletRequest request) {
		String saveName="";
		@SuppressWarnings("deprecation")
		String realPath = request.getRealPath("upload");
		
		// 모든 파일을 리스트로 받기
		List<MultipartFile> list = request.getFiles("file");
		
		if(list!=null && list.size()>0) { // 리스트에 내용이 있다면
			for(MultipartFile file : list) { // 반복
				// 파일 받기
				if(file!=null && file.getSize()>0) { // 파일이 존재 한다면
					try {
						// 이름 중복 처리를 하기위해 UUID로 중복되지않는 문자열을 생성하고 뒤에 원본이름을 붙여준다.
						saveName = UUID.randomUUID() + "_" + file.getOriginalFilename();
						// 저장할 파일 객체 생성
						File   saveFile = new File(realPath, saveName);
						// 파일을 복사
						FileCopyUtils.copy(file.getBytes(), saveFile);
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		System.out.println(request.getContextPath() + "/upload/" + saveName);
		return request.getContextPath() + "/upload/" + saveName; // 실제 그림이 저장된 경로를 리턴해주게 만든다.
	}
	
	
	// CkEditor
	@RequestMapping(value = "ckeditor")
	public String ckeditor() {
		return "ckeditor";
	}
	
	@RequestMapping(value = "result2")
	public String result2() {
		return "result2";
	}
	
	@RequestMapping(value = "/fileupload", produces = "application/json;charset=UTF-8" )
	@ResponseBody
	public JsonObject fileupload(MultipartHttpServletRequest request) {
		String saveName="";
		String originalFileName="";
		@SuppressWarnings("deprecation")
		String realPath = request.getRealPath("upload");
		
		// 모든 파일을 리스트로 받기
		List<MultipartFile> list = request.getFiles("upload");
		
		if(list!=null && list.size()>0) { // 리스트에 내용이 있다면
			for(MultipartFile file : list) { // 반복
				// 파일 받기
				if(file!=null && file.getSize()>0) { // 파일이 존재 한다면
					try {
						// 이름 중복 처리를 하기위해 UUID로 중복되지않는 문자열을 생성하고 뒤에 원본이름을 붙여준다.
						saveName = UUID.randomUUID() + "_" + file.getOriginalFilename();
						originalFileName = file.getOriginalFilename();
						// 저장할 파일 객체 생성
						File   saveFile = new File(realPath, saveName);
						// 파일을 복사
						FileCopyUtils.copy(file.getBytes(), saveFile);
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		// 리턴할 데이터를 만들어 주어야 한다.
        // {"uploaded" : 1, "fileName" : "test.jpg", "url" : "/img/test.jpg"}
		JsonObject json = new JsonObject();
        json.addProperty("uploaded", 1);
        json.addProperty("fileName", originalFileName);
        json.addProperty("url", request.getContextPath()+"/upload/" + saveName);
        System.out.println("[" + json.toString() + "]");
		return json;
	}
}
