package com.pinyougou.page.service.impl;

import com.pinyougou.page.service.ItemPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
@Component
public class PageListener implements MessageListener {
   @Autowired
   private ItemPageService itemPageService;
    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            String text = textMessage.getText();
            System.out.println("goodsId是"+text);
            System.out.println("接受消息："+text);
            boolean b = itemPageService.genItemHtml(Long.parseLong(text));
            System.out.println(b);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
