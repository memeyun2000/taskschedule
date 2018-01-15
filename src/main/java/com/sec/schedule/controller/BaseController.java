package com.sec.schedule.controller;



import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import com.sec.schedule.model.PageInfo;

public class BaseController{
    
    public Pageable getPageable(PageInfo _pageInfo) {
        int size = 15;
        int page = 0;
        Sort sort = null;
        if( _pageInfo.getPageNum() != 0) {
            page = _pageInfo.getPageNum();
        } else {
            _pageInfo.setPageNum(page);
        }

        if( _pageInfo.getPageSize() != 0) {
            size = _pageInfo.getPageSize();
        } else {
            _pageInfo.setPageSize(size);
        }

        if( _pageInfo.getSort() != null) {
            sort = _pageInfo.getSort(); 
            return new PageRequest(page, size,_pageInfo.getSort());
        } else {
            return new PageRequest(page, size);
        }
        
    }
}