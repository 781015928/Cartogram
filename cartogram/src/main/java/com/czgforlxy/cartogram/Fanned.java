package com.czgforlxy.cartogram;

import android.graphics.Color;

/**
 * 类 名 称  ： Fanned.class
 * 作    者 ：  czg
 * 日    期 ：  2016/9/30.
 * 作    用 ： 在这里写一句话描述作用
 */
public  class Fanned{
    int size;
    String text;
    int color;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = Color.parseColor(color);
    }
}