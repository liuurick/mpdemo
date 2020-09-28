package com.liuurick.mpdemo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liuurick.mpdemo.entity.User;
import com.liuurick.mpdemo.mapper.UserMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class MpdemoApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() {
    }

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(6, userList.size());
        userList.forEach(System.out::println);

//        List<User> userList = userMapper.selectList(null);
//        for(User user :userList){
//            System.out.println(user);
//        }
    }

    @Test
    public void testInsert() {
        User user = new User();
        user.setName("lucyyyyyy");
        user.setAge(22);
        user.setEmail("liuurick@gmail.com");
        int result = userMapper.insert(user);
        System.out.println(result);
        System.out.println(user);
    }


    @Test
    public void testUpdateById() {
        User user = new User();
        user.setId(1L);
        user.setName("qqqqqqqqqq");
        userMapper.updateById(user);
    }

    //测试乐观锁
    @Test
    public void testOptimisticLocker() {
        //查询
        User user = userMapper.selectById(1261893570772623361L);
        //修改数据
        user.setName("Helen Yao");
        user.setEmail("helen@qq.com");
        //执行更新
        userMapper.updateById(user);
    }


    //分页查询
    @Test
    public void testPage() {
        Page<User> userPage = new Page<>();
        userMapper.selectPage(userPage, null);
        System.out.println(userPage.getCurrent());
        System.out.println(userPage.getRecords());
        System.out.println(userPage.getSize());//每页显示记录数
        System.out.println(userPage.getTotal());
        System.out.println(userPage.getPages());//总页数
    }

    //逻辑删除
    @Test
    public void testDelete() {
        int result = userMapper.deleteById(1261933109507534850L);
        System.out.println(result);
    }
}
