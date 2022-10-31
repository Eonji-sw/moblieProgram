package com.example.mp_20212979;

public class singleItem {
    String name;
    String author;
    int resld;

    public singleItem(String name, String author, int resld) {
        this.name = name;
        this.author = author;
        this.resld = resld;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor() {
        this.author = author;
    }

    public int getResld() {
        return resld;
    }

    public void setResld(int resld) {
        this.resld = resld;
    }

    @Override
    public String toString() {
        return "SingleItem{" + "name:'" + name + '\'' + ", author:'" + author + '\'' + '}';
    }

}
