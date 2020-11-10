package com.service;

import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.pojo.TbBrand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext-dao.xml")
public class ServiceText {
    @Autowired
    TbBrandMapper tbBrandMapper;
    @Test
    public void text01(){
        List<TbBrand> tbBrands = tbBrandMapper.selectByExample(null);
        for (TbBrand tbBrand : tbBrands) {
            System.out.println(tbBrand);
        }

    }
}
