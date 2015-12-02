package com.thoughtworks.fixed.assets.api;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class TestUtil {
    public static void reset(){
        String resource = "db/mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {

            sqlSession.delete("Reset.asset");
            sqlSession.delete("Reset.income");
            sqlSession.delete("Reset.assetCategory");

            sqlSession.insert("Reset.newAsset");
            sqlSession.commit();
//        sqlSessionFactory.openSession().delete("delete from income");
        }finally {
            sqlSession.close();
        }
    }
}
