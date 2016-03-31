package com.datasure.shop;


/**
 * 
 * @ClassName: Production 
 * @Description: 产品信息的封装
 * @date: 2016年3月9日 下午4:33:10 
 * @author LiDongSheng
 * @version
 */
public class Product {
	
	private String name;
	private int numbers;
	private int price;
	
	public Product(String name, int numbers, int price) {
		this.name = name;
		this.numbers = numbers;
		this.price = price;
	}
	
	public Product(String name){
		this.name = name;
		numbers = 0;	//默认值
		price = 0;	//默认值
	}
	
	/*****getter and setter******/
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumbers() {
		return numbers;
	}

	public void setNumbers(int numbers) {
		this.numbers = numbers;
	}
	
	
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "[" + name + "," + numbers + "," + price +"]";
	}
	
	/**
	 * 
	 * serialize:序列化操作. <br/>
	 * 序列化单价与数量，便于存储
	 * @author LiDongSheng
	 * @param prdt	商品
	 * @return 返回格式 [number,price]
	 */
	public static String serialize(Product prdt){
		return "[" + prdt.getNumbers() + "," + prdt.getPrice() + "]";
	}
	
	/**
	 * 
	 * reSerialize:反序列化. <br/>
	 * @author LiDongSheng
	 * @param name	商品名
	 * @param seri	序列化后的数据
	 * @return	Product
	 */
	public static Product reSerialize(String name, String seri){
		Product prdt = null;
		int number = 0;
		int price = 0;
		
		String[] s = seri.split(",");
		number = Integer.valueOf(s[0].substring(1, s[0].length()));
		price = Integer.valueOf(s[1].substring(0, s[1].length() - 1));
		
		prdt = new Product(name, number, price);
		return prdt;
	}
	

	
}
