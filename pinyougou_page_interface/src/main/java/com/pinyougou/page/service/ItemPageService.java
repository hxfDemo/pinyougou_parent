package com.pinyougou.page.service;

import java.io.IOException;

public interface ItemPageService {
    public boolean genItemHtml(Long goodsId) throws Exception;
public boolean deleteItemHtml(Long[] goodsIds);
}
