package com.lhb.es.estest;

import com.lhb.es.dao.ProductDao;
import com.lhb.es.entity.Product;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description: term查询
 * @Author: waveLee
 * @Date: 2022/2/10 16:16
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDataESSearchTest {


    @Autowired
    private ProductDao productDao;

    /**
     * term 查询
     * search(termQueryBuilder) 调用搜索方法，参数查询构建器对象
     */
    @Test
    public void termQuery() {
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("category", "手机");
        Iterable<Product> products = productDao.search(termQueryBuilder);
        for (Product product : products) {
            System.out.println(product);
        }
    }

    /**
     * term 查询加分页
     */
    @Test
    public void termQueryByPage() {
        int pageNo = 0;
        int pageSize = 5;
        //设置查询分页
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("category", "手机");
        Iterable<Product> products = productDao.search(termQueryBuilder, pageRequest);
        for (Product product : products) {
            System.out.println(product);
        }
    }
}
