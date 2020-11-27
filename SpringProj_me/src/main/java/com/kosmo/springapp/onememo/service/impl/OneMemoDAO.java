package com.kosmo.springapp.onememo.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kosmo.springapp.onememo.service.OneMemoDTO;
import com.kosmo.springapp.onememo.service.OneMemoService;


/*
 * mybatis framework 사용
 * SqlSessionFactory 타입 객체 사용
 * 형변환 불필요(ibatis는 형변환이 필요하다.)
 * -------------------------------------------------------
 * 
 * 프로그래밍 순서(외우기)
 * 
 * 1. SqlSessionFactory 타입의  openSession() method로 SqlSession 타입 얻기
 * 2. SqlSession 타입의 메서드 호출
 * 		1) 쿼리문이 SELECT 
 * 				다중 레코드일때: selectList()
 * 				단일 레코드일때: selectOne()
 * 		2) 쿼리문이 INSERT - insert()
 * 				 DELETE - delete()
 * 				 UPDATE - update() 메서드
 * 				단, I/D/U 일때는 commit() 호출
 * 3. close() 호출
 * 
 */


@Repository
public class OneMemoDAO implements OneMemoService {
	/*
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;
	
	@Resource(name="dataSourceByJNDI")
	private DataSource source;
	*/
	//방법1+2.
	//mybatis-spring을 쓰지 않고 mybatis만 사용했을경우의 코드
	/*
	//Spring이 myBatis관련해서 지원하는 API 미사용
	private static SqlSessionFactory sqlMapper; //mybatis의 클래스
	//SqlSessionFactoryBean과  SqlSessionTemplate는 mybatis-springdml 클래스
	static {
		String resource = "com/kosmo/springapp/onememo/mybatis/configuration.xml";//configuration file 주소 넣기
		try {
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	*/
	
	//방법1+2. 
	//SqlSessionFactory 주입 받기
	//@Resource는 static 필드 지원 안 한다!!!! 주의 문제...
	//@Resource(name = "sqlSessionFactory")
	//private SqlSessionFactory sqlMapper;


	
	//방법3. 
	//mybatis-spring를 이용 (SqlSessionTemplate) -root-context.xml에 저장해둠
	@Resource(name = "template")
	private SqlSessionTemplate sqlMapper;
	
	
	@Override
	public boolean isLogin(Map map) {
		//방법0. mybatis도 사용 안 할때
		/*
		try {
			conn = source.getConnection();
			psmt=conn.prepareStatement("SELECT COUNT(*) FROM member WHERE id=? AND pwd=?");
			psmt.setString(1, map.get("id").toString());
			psmt.setString(2, map.get("pwd").toString());
			rs = psmt.executeQuery();
			rs.next();
			if(rs.getInt(1)==0) return false;
		} 
		catch (SQLException e) {e.printStackTrace();return false;}
		*/
		//방법2.
		/*
		//Spring이 myBatis관련해서 지원하는 API 미사용
		//1) SqlSession 얻기
		SqlSession session = sqlMapper.openSession();
		//2) selectOne() 호출
		int count = session.selectOne("memoIsLogin", map);
		//3) 커넥션 닫아주기
		session.close();
		return count==1 ? true : false;
		*/
		//방법3.
		return (Integer)sqlMapper.selectOne("memoIsLogin", map)==1 ? true : false;
	}

	@Override
	public List<OneMemoDTO> selectList(Map map) {
		//방법2. 
		/*
		//Spring이 myBatis관련해서 지원하는 API 미사용
		//1) SqlSession 얻기
		SqlSession session = sqlMapper.openSession();
		//2) selectList() 호출
		List<OneMemoDTO> list = session.selectList("memoSelectList", map);
		//3) 커넥션 닫아주기
		session.close();
		return list;
		*/
		//방법 3.
		return sqlMapper.selectList("memoSelectList", map);
	}

	@Override
	public int getTotalRecord(Map map) {
		return sqlMapper.selectOne("memoGetTotalRecord", map);
	}

	@Override
	public OneMemoDTO selectOne(Map map) {
		return sqlMapper.selectOne("memoSelectOne",map);
	}

	@Override
	public int insert(Map map) {
		//방법1. 
		/*
		//Spring이 myBatis관련해서 지원하는 API 미사용
		//1) SqlSession 얻기
		SqlSession session = sqlMapper.openSession();
		//2) insert() 호출
		int affected = session.insert("memoInsert", map);
		//2-1) commit() 호출
		session.commit();
		//3) 커넥션 닫아주기
		session.close();
		return affected;
		*/
		System.out.println("여기입니다. "+map.get("content"));
		//방법 3.
		return sqlMapper.insert("memoInsert", map);
	}

	@Override
	public int delete(Map map) {
		/*
		 * 메모 삭제:
		 * 프로그래밍적으로 삭제하거나 
		 * 혹은 부모 삭제시 자동으로 해당 자식도 삭제되도록 FK설정
		 * REFERENCES 부모테이블(PK컬럼) ON DELETE CASCADE
		 */
		//자식삭제
		sqlMapper.delete("commentDeleteByNo",map);
		//부모 삭제--프로그래밍적 삭제
		return sqlMapper.delete("memoDelete",map);
	}

	@Override
	public int update(Map map) {
		return sqlMapper.update("memoUpdate", map);
	}

}
