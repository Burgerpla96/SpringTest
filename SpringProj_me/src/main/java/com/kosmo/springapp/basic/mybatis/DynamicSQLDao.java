package com.kosmo.springapp.basic.mybatis;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kosmo.springapp.onememo.service.OneMemoDTO;
import com.kosmo.springapp.onememo.service.OneMemoService;

@Repository("dynamic")
public class DynamicSQLDao{
	@Resource(name ="template")
	private SqlSessionTemplate sqlMapper;
	public List if1(Map map) {
		return sqlMapper.selectList("findOneMemoWithTitleLike",map);
	}////////////////
	public List if2(Map map) {
		return sqlMapper.selectList("findOneMemoLike",map);
	}////////////////
	public List choose(Map map) {
		return sqlMapper.selectList("findOneMemoLikeChoose",map);
	}////////////////
	public List where(Map map) {
		return sqlMapper.selectList("findOneMemoLikeWhere",map);
	}////////////////
	public List trim1(Map map) {
		return sqlMapper.selectList("findOneMemoLikeTrim",map);
	}////////////////
	public int trim2(Map map) {
		return sqlMapper.update("updateOneMemoTrim",map);
	}////////////////
	public int set(Map map) {
		return sqlMapper.update("updateOneMemoSet",map);
	}////////////////
	//1.List인 경우
	//public List foreach(List lists) {
	//2.Map인 경우
	public List foreach(Map map) {
		return sqlMapper.selectList("findOneMemoForeach",map);
	}////////////////
}
