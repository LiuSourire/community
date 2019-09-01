package com.sourire.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Pagination<T> {
    private Integer current;
    private Integer size;
    private Integer total;
    private Integer totalPage;
    private List<T> questionDTOS;
    private List<Integer> pages = new ArrayList<>();

    private boolean showPrev;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showLastPage;


    public Pagination(Integer current ,Integer size ,Integer total){
        this.total = total;
        this.size = size;

        //计算总页数
        if (total % size == 0) {
            this.totalPage = total / size;
        } else {
            this.totalPage = total / size + 1;
        }

        if(current < 1){
            current = 1;
        }

        if(current > totalPage){
            current = totalPage;
        }

        this.current = current;

        pages.add(current);

        for (int i = 1; i <= 3; i++) {
            if (current - i > 0) {
                pages.add(0, current - i);
            }
            if (current + i <= totalPage) {
                pages.add(current + i);
            }
        }

        if (current == 1) {
            this.showPrev = false;
        } else {
            this.showPrev = true;
        }

        if (current.equals(totalPage)) {
            this.showNext = false;
        } else {
            this.showNext = true;
        }

        if (pages.contains(1)) {
            this.showFirstPage = false;
        } else {
            this.showFirstPage = true;
        }

        if (pages.contains(totalPage)) {
            this.showLastPage = false;
        } else {
            this.showLastPage = true;
        }
    }
}
