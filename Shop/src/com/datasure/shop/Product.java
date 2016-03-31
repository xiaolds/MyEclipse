package com.datasure.shop;


/**
 * 
 * @ClassName: Production 
 * @Description: ��Ʒ��Ϣ�ķ�װ
 * @date: 2016��3��9�� ����4:33:10 
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
		numbers = 0;	//Ĭ��ֵ
		price = 0;	//Ĭ��ֵ
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
	 * serialize:���л�����. <br/>
	 * ���л����������������ڴ洢
	 * @author LiDongSheng
	 * @param prdt	��Ʒ
	 * @return ���ظ�ʽ [number,price]
	 */
	public static String serialize(Product prdt){
		return "[" + prdt.getNumbers() + "," + prdt.getPrice() + "]";
	}
	
	/**
	 * 
	 * reSerialize:�����л�. <br/>
	 * @author LiDongSheng
	 * @param name	��Ʒ��
	 * @param seri	���л��������
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
