package com.lhb.es.estest;

import com.lhb.es.dao.ProductDao;
import com.lhb.es.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: es文档操作测试
 * @Author: waveLee
 * @Date: 2022/2/10 15:31
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDataESProductDaoTest {

    @Autowired
    private ProductDao productDao;
    /**
     * 新增
     */
    @Test
    public void save(){
        Product product = new Product();
        product.setId(2L);
        product.setTitle("小米12Pro");
        product.setCategory("手机");
        product.setPrice(5399.00);
        product.setImages("https://www.xiaomi.com/xiaomi12pro.jpg");
        productDao.save(product);
    }


    /**
     * 修改
     */
    @Test
    public void update(){
        Product product = new Product();
        product.setId(2L);
        product.setTitle("小米12Pro");
        product.setCategory("手机");
        product.setPrice(5399.00);
        product.setImages("https://www.xiaomi.com/xiaomi12pro.jpg");
        productDao.save(product);
    }

    //根据 id 查询
    @Test
    public void findById(){
        Product product = productDao.findById(1L).get();
        System.out.println(product);
    }


    @Test
    public void findAll(){
        Iterable<Product> products = productDao.findAll();
        for (Product product : products) {
            System.out.println(product);
        }
    }

    //删除
    @Test
    public void delete(){
        Product product = new Product();
        product.setId(1L);
        productDao.delete(product);
    }

    //全删除
    private void deleteAll(){
        productDao.deleteAll();
    }

    //批量新增
    @Test
    public void saveAll(){
        List<Product> productList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Product product = new Product();
            product.setId((long) i);
            product.setTitle("小米手机["+i+"]");
            product.setCategory("手机");
            product.setPrice(1999.0+i);
            product.setImages("http://www.xiaomi/xm.jpg");
            productList.add(product);
        }
        productDao.saveAll(productList);
    }

    //分页查询
    @Test
    public void findByPageable(){
        //设置排序(排序方式，正序还是倒序，排序的 id)
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        int pageNo=1;//当前页，第一页从 0 开始，1 表示第二页
        int pageSize = 5;//每页显示多少条
        //设置查询分页
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize,sort);
        //分页查询
        Page<Product> productPage = productDao.findAll(pageRequest);
        for (Product Product : productPage.getContent()) {
            System.out.println(Product);
        }
    }

}
