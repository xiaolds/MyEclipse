package com.datasure.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.datasure.shop.Product;
import com.datasure.shop.User;
import com.datasure.sql.ProductDAO;


/**
 * 
 * @ClassName: HandleCookie 
 * @Description: ������Cookie
 * @date: 2016��3��15�� ����10:15:52 
 * @author LiDongSheng
 * @version
 */
public class HandleCookie {
	
	
	/**
	 * 
	 * addCookie:�����Ʒ��Cookie. <br/>
	 *	��Cookie�������Ʒ�����ԭ���ʹ���,
	 *	�޸ģ������ڣ����´���.
	 *	������Ʒ���е��ۡ������������ԣ�����ʹ�� [number,price]
	 *	�ĸ�ʽ�����л��뷴���л�
	 * @author LiDongSheng
	 * @param prdt
	 */
	public static void addCookie(HttpServletRequest req,
			HttpServletResponse resp, final Product prdt){
		//�ж�product�Ƿ������cookie��
		String name = prdt.getName();

		
		Cookie[] cookies = req.getCookies();
		boolean isExist = false;	//����Ƿ��и���Ʒ��Cookie
		if(cookies == null || cookies.length == 0){
			//û��Cookie��ֱ�����
			Cookie cookie = new Cookie(name,Product.serialize(prdt));
			cookie.setMaxAge(2 * 60 * 60 * 1000);
			resp.addCookie(cookie);
		}
		else{
			//����Cookie������
			for(Cookie cookie: cookies){
				if(cookie.getName().equals(name)){
					isExist = true;
					cookie.setValue(Product.serialize(prdt));
					break;
				}
			}
			//�����
			if(!isExist){
				Cookie cookie = new Cookie(name,Product.serialize(prdt));
				cookie.setMaxAge(2 * 60 * 60 * 1000);
				resp.addCookie(cookie);
			}
		}
		
	}
	
	/**
	 * 
	 * clearAllCookies:ɾ������Cookie. <br/>
	 *
	 * @author LiDongSheng
	 * @param req	����
	 * @param resp	�ظ�
	 */
	public static void clearAllCookies(HttpServletRequest req, HttpServletResponse resp){
		//����Ƿ���Cookies
		Cookie[] cookies = req.getCookies();
		
		if(cookies == null || cookies.length == 0){
			return;
		}
		
		for(Cookie c : cookies){
			c.setMaxAge(0); 	//delete
		}
		
	}
	
	/**
	 * 
	 * addPrdtToDB:����Ʒ��Ϣ�־û���DataBase����ɾ������Cookie. <br/>
	 *
	 * @author LiDongSheng
	 * @param req	����
	 * @param resp	�ظ�
	 * @param user	�û�
	 */
	public static void addPrdtToDB(HttpServletRequest req, HttpServletResponse resp, User user){
		//��ȡCookie
		Cookie[] cookies = req.getCookies();
		if(cookies == null || cookies.length == 0){
			return;
		}
		
		String prdtName = null;
		Product prdt = null;
		for(Cookie c: cookies){
			prdtName = c.getName();
			prdt = Product.reSerialize(prdtName, c.getValue());
			if(ProductDAO.isOwnPrdt(user, prdt)){
				//���и���Ʒ
				ProductDAO.updatePrdtFromUser(user, prdt);
			}
			else{
				//�����и���Ʒ
				ProductDAO.addPrdtToUser(user, prdt);
			}
			//ɾ����Cookie
			c.setMaxAge(0);
		}
	}
	
	/**
	 * 
	 * getPrdtFromCookies:��Cookie�ж�ȡ���еĲ�Ʒ. <br/>
	 * @author LiDongSheng
	 * @param req
	 * @param resp
	 * @return
	 */
	public static List<Product> getPrdtFromCookies(HttpServletRequest req, HttpServletResponse resp){
		List<Product> list = new ArrayList<>();
		
		Cookie[] cookies = req.getCookies();
		
		if(cookies == null || cookies.length == 0){
			return null;
		}
		
		String prdtName = null;
		Product prdt = null;
		for(Cookie c: cookies){
			prdtName = c.getName();
			prdt = Product.reSerialize(prdtName, c.getValue());
			list.add(prdt);
		}
		
		return list;
	}
	
}
