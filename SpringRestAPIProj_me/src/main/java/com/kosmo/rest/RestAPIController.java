package com.kosmo.rest;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kosmo.rest.service.ContactsDAO;
import com.kosmo.rest.service.ContactsDTO;
import com.kosmo.rest.service.UsersDTO;

/*
@RestController:스프링 4.X대에 나온 어노 테이션으로 @Controller와 @ResponseBody어노테이션을 합친 것으로
		                 컨트롤러 메소드의 반환값이 응답바디에 쓰인다.
				즉 반환 타입이 String이면 해당 반환 문자열이 
				그리고 DTO계열이면 JSON 형식({키:값}) List<DTO계열>이면 JSON배열 형식([{},{},...])
				이 응답바디에 보내진다
*/
@RestController
public class RestAPIController {
	/*
	 * GET method: query 파라미터 사용
	 * POSTMAN:
	 * GET : http://localhost:8080/rest/users?name=kosmo
	 * produces = "text/html; charset=UTF-8" //한글깨짐 방지용
	 */
	
	@GetMapping(value="/users", produces = "text/html; charset=UTF-8")
	public String getQueryParameter(@RequestParam String name) {
		return String.format("GET: 쿼리파라미터 사용-%s", name);
	}//////////////getQueryParameter
	
	/*
	 * GET method: URI 파라미터 사용
	 * POSTMAN:
	 * GET : http://localhost:8080/rest/users/kosmo
	 */
	@GetMapping(value="/users/{name}", produces = "text/html; charset=UTF-8")
	public String getURIParameter(@PathVariable String name) {
		return String.format("GET: URI 파라미터 사용-%s", name);
	}//////////////getURIParameter
	
	
	/*
	 * POST method: query 파라미터 사용
	 * POSTMAN:
	 * test1.
	 * POST : http://localhost:8080/rest/users?name=kosmo
	 * test2.
	 * POST : http://localhost:8080/rest/users/kosmo
	 * 위와 같이 POST형식에 url에 정보가 담기게 하지 않는다.(잘 안씀)
	 */
	@PostMapping(value="/users", produces = "text/html; charset=UTF-8")
	public String postQueryParameter(@RequestParam String name) {
		return String.format("POST: 쿼리파라미터 사용-%s", name);
	}//////////////postQueryParameter
	@PostMapping(value="/users/{name}", produces = "text/html; charset=UTF-8")
	public String postURIParameter(@PathVariable String name) {
		return String.format("POST: URI 파라미터 사용-%s", name);
	}//////////////postQueryParameter
	
	
	
	//jackson-databind:DTO계열(혹은 VO)을 JSON으로 혹은 JSON을 DTO계열의 자바 객체로 바인딩 해주는 라이브러리
	//따라서 dto로 보내도 json 배열 형태로 해준다.
	@GetMapping("/json/users")
	public List<UsersDTO> getJsonUsers(){
		List<UsersDTO> users = new Vector<UsersDTO>();
		users.add(new UsersDTO("가길동", "20", "가산동"));
		users.add(new UsersDTO("나길동", "30", "나산동"));
		return users;
	}//////////////////////////getJsonUsers
	
	
	//json하나 리턴하기
	@GetMapping("/json/users/{userid}")//{}사이에는 임의의 변수 집어 넣기
	public UsersDTO getJsonUser(@PathVariable String userid) {//변수명 mapping과 일치시키기
		//id로 검색 로직 추가
		if(userid.equalsIgnoreCase("KIM"))
			return new UsersDTO("김길동", "45", "청담동");
		else if(userid.equalsIgnoreCase("LEE"))
			return new UsersDTO("이길동", "25", "방배동");
		else return new UsersDTO("박길동", "35", "서초동");
	}////////////getJsonUser
	
	
	//json으로 하나의 데이터 받고 그대로 json으로 응답 보내기
	@PostMapping("/json/users/one")
	public UsersDTO postJsonOne(@RequestBody UsersDTO user) {
		System.out.println("클라인언트로 부터 받은 JSON: "+user);
		return user;
	}/////////////////postJsonOne
	
	
	//json으로 여러개 데이터 받고 그대로 json으로 응답 보내기
	@PostMapping("/json/users")
	public List<UsersDTO> postJsonAll(@RequestBody List<UsersDTO> users){
		return users;
	}///////////////postJsonAll
	
	
	
	//PUT메소드:아이디 받아서 수정후 수정 데이타 보내기
	@PutMapping("/json/users/{userid}")
	public UsersDTO updateUser(@RequestBody UsersDTO user, @PathVariable String userid) {
		//실제 테이블에서 아이디로 수정후 json으로 보내기
		//가상으로 수정하는 코딩 추가
		if(userid.equalsIgnoreCase("KIM")) {
			user.setAddr("워싱턴");
			user.setAge("100");
			user.setName("가이드");
		}
		return user;
	}/////////////updateUser
	
	
	@DeleteMapping("/json/users/{userid}")
	public UsersDTO deleteUser(@RequestBody UsersDTO user, @PathVariable String userid) {
		//실제 테이블에서 아이디로 삭제후, json으로 삭제한 사용자 보내기
		return user;
	}/////////////////deleteUser
	
	
	
	
	//파일 업로드
	@PostMapping("/users/upload")
	public UsersDTO fileUpload(@RequestParam Map map, @RequestPart MultipartFile upload, HttpServletRequest req) throws IllegalStateException, IOException {
		//1)서버의 물리적 경로 얻기
		String path = req.getSession().getServletContext().getRealPath("/upload");
		//2) 파일 객체 생성
		File file = new File(path+File.separator+upload.getOriginalFilename());
		//3) 업로드 처리
		upload.transferTo(file);
		return new UsersDTO(map.get("name").toString(), map.get("age").toString(), map.get("addr").toString());
	}///////////////////fileUpload
	
	
	
	
	
	//실제 데이터 베이스와 CRUD
	@Resource(name="contactsDAO")
	private ContactsDAO contacts;
	
	//등록하기
	//UI 없이 POSTMAN으로 확인
	@CrossOrigin
	@PostMapping(value = "/contact", produces = "text/html; charset=UTF-8")
	public String create(@RequestParam Map map, @RequestPart MultipartFile photo, HttpServletRequest req) throws IllegalStateException, IOException {
		//1)서버의 물리적 경로 얻기
		String path = req.getSession().getServletContext().getRealPath("/upload");
		//2) 파일 객체 생성
		File file = new File(path+File.separator+photo.getOriginalFilename());
		//3) 업로드 처리
		photo.transferTo(file);
		//DB에 입력처리
		//@RequestParam Map map에는 type="file"이 설정이 안된다.
		map.put("photo", photo.getOriginalFilename());
		return contacts.insert(map)==1? "입력성공":"입력실패";
	}////////////////create
	
	
	//모든 레코드 조회
	@CrossOrigin
	@GetMapping("/contacts")
	public List<ContactsDTO> selectList(){
		List<ContactsDTO> contacts = this.contacts.selectList();
		return contacts;
	}////////////selectAll
	
	
	//레코드 하나 조회
	@CrossOrigin
	@GetMapping("/contacts/{contact_id}")
	public ContactsDTO selectOne(@PathVariable String contact_id){
		return contacts.selectOne(contact_id);
	}////////////selectOne
	
	
	//레코드 수정하기
	//PutMapping해야하는데 안되서 Post로 함
	@CrossOrigin
	@PostMapping(value = "/contacts/{contact_id}", produces = "text/html; charset=UTF-8")
	public String update(@PathVariable String contact_id, 
			@RequestParam Map map, 
			@RequestPart MultipartFile photo, 
			HttpServletRequest req) throws IllegalStateException, IOException {
		//1)서버의 물리적 경로 얻기
		String path = req.getSession().getServletContext().getRealPath("/upload");
		//2) 파일 객체 생성
		File file = new File(path+File.separator+photo.getOriginalFilename());
		//3) 업로드 처리
		photo.transferTo(file);
		//DB에 입력처리
		//@RequestParam Map map에는 type="file"이 설정이 안된다.
		map.put("photo", photo.getOriginalFilename());
		map.put("contact_id", contact_id);
		return contacts.update(map)==1?"수정성공":"수정실패";
	}////////////////update
	
	
	//레코드 삭제하기
	@CrossOrigin//모든 도메인을 허용한다.
	@DeleteMapping(value = "/contacts/{contact_id}", produces = "text/html; charset=UTF-8")
	public String delete(@PathVariable String contact_id) {
		return contacts.delete(contact_id)==1?"삭제성공":"삭제실패";
	}
	
	
	
	
	
}
