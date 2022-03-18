package kr.green.mvc11;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/uploadForm")
	public String uploadForm() {
		return "uploadForm";
	}
	// 업로드를 처리할 메서드
	@RequestMapping(value = "/uploadOk")
	public String uploadOk(HttpServletRequest request, MultipartFile file, Model model) {
		logger.info("uploadOk 호출!!!!");
		// 업로드될 위치의 절대 경로 구하기
		@SuppressWarnings("deprecation")
		String realPath = request.getRealPath("upload");
		// 내용 받기
		String content = request.getParameter("content");
		
		// 파일 받기
		if(file!=null && file.getSize()>0) { // 파일이 존재 한다면
			try {
				// 이름 중복 처리를 하기위해 UUID로 중복되지않는 문자열을 생성하고 뒤에 원본이름을 붙여준다.
				String saveName = UUID.randomUUID() + "_" + file.getOriginalFilename();
				// 저장할 파일 객체 생성
				File   saveFile = new File(realPath, saveName);
				// 파일을 복사
				FileCopyUtils.copy(file.getBytes(), saveFile);
				
				model.addAttribute("saveName", saveName);
				model.addAttribute("originalName", file.getOriginalFilename());
				model.addAttribute("contentType", file.getContentType());
				model.addAttribute("fileSize", file.getSize());
				model.addAttribute("folder", realPath);
				model.addAttribute("content", content);
				
				return "uploadOk";
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "redirect:/uploadForm";
	}
	
	@RequestMapping(value = "/multiUploadForm")
	public String multiUploadForm() {
		return "multiUploadForm";
	}
	@RequestMapping(value = "/multiUploadOk")
	public String multiUploadOk(MultipartHttpServletRequest request, Model model) {
		logger.info("multiUploadOk 호출!!!!");
		// 업로드될 위치의 절대 경로 구하기
		@SuppressWarnings("deprecation")
		String realPath = request.getRealPath("upload");
		// 내용 받기
		String content = request.getParameter("content");
		
		// 모든 파일을 리스트로 받기
		List<MultipartFile> list = request.getFiles("files");
		// 받은 내용을 저장할 스트링 버퍼 선언
		StringBuffer sb = new StringBuffer();
		sb.append("받은 내용 : " + content + "<br>"); 
		sb.append("저장 폴더 : " + realPath + "<br><hr>"); 
		if(list!=null && list.size()>0) { // 리스트에 내용이 있다면
			for(MultipartFile file : list) { // 반복
				// 파일 받기
				if(file!=null && file.getSize()>0) { // 파일이 존재 한다면
					try {
						// 이름 중복 처리를 하기위해 UUID로 중복되지않는 문자열을 생성하고 뒤에 원본이름을 붙여준다.
						String saveName = UUID.randomUUID() + "_" + file.getOriginalFilename();
						// 저장할 파일 객체 생성
						File   saveFile = new File(realPath, saveName);
						// 파일을 복사
						FileCopyUtils.copy(file.getBytes(), saveFile);
						
						sb.append("saveName : " + saveName + "<br>");
						sb.append("originalName : " + file.getOriginalFilename() + "<br>");
						sb.append("contentType : " + file.getContentType() + "<br>");
						sb.append("fileSize : " + file.getSize() + "<br>");
						sb.append("<button onclick=\"fileDown('" + file.getOriginalFilename() + "','"+saveName+"')\">다운로드</button><br><hr>");
						
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			model.addAttribute("result", sb.toString());
			return "multiUploadOk";
		}
		return "redirect:/multiUploadForm";
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
}
