package com.example.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookManager {

	//定义一个内部类，作为系统的业务对象
	public static class Book {
		public Integer id;
		public String title;
		public String desc;

		public Book(Integer id, String title, String desc) {
			this.id = id;
			this.title = title;
			this.desc = desc;
		}

		@Override
		public String toString() {
			return title;
		}
	}

	//使用List集合记录系统所包含的Book对象
	public static List<Book> ITEMS = new ArrayList<>();
	//使用Map集合记录系统所包含的Book对象
	public static Map<Integer, Book> ITEM_MAP = new HashMap<>();

	static {
		//使用静态初始化代码，将Book对象添加到List集合、Map集合中
		addItem(new Book(1, "Head First Java", "一本基础、入门的Java学习书籍，深受读者喜欢。"));
		addItem(new Book(2, "Head First Android", "一本基础、入门的Android学习书籍，深受读者喜欢。"));
		addItem(new Book(3, "Thinking in Java", "一本全面、深入的Java学习书籍，深受读者喜欢。"));
	}

	private static void addItem(Book book) {
		ITEMS.add(book);
		ITEM_MAP.put(book.id, book);
	}
}