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
 * @Description: 负责处理Cookie
 * @date: 2016年3月15日 上午10:15:52 
 * @author LiDongSheng
 * @version
 */
public class HandleCookie {
	
	
	/**
	 * 
	 * addCookie:添加商品的Cookie. <br/>
	 *	向Cookie中添加商品，如果原来就存在,
	 *	修改，不存在，重新创建.
	 *	由于商品含有单价、数量两个属性，所以使用 [number,price]
	 *	的格式来序列化与反序列化
	 * @author LiDongSheng
	 * @param prdt
	 */
	public static void addCookie(HttpServletRequest req,
			HttpServletResponse resp, final Product prdt){
		//判断product是否存在于cookie中
		String name = prdt.getName();

		
		Cookie[] cookies = req.getCookies();
		boolean isExist = false;	//标记是否含有该商品的Cookie
		if(cookies == null || cookies.length == 0){
			//没有Cookie，直接添加
			Cookie cookie = new Cookie(name,Product.serialize(prdt));
			cookie.setMaxAge(2 * 60 * 60 * 1000);
			resp.addCookie(cookie);
		}
		else{
			//含有Cookie，遍历
			for(Cookie cookie: cookies){
				if(cookie.getName().equals(name)){
					isExist = true;
					cookie.setValue(Product.serialize(prdt));
					break;
				}
			}
			//新添加
			if(!isExist){
				Cookie cookie = new Cookie(name,Product.serialize(prdt));
				cookie.setMaxAge(2 * 60 * 60 * 1000);
				resp.addCookie(cookie);
			}
		}
		
	}
	
	/**
	 * 
	 * clearAllCookies:删除所有Cookie. <br/>
	 *
	 * @author LiDongSheng
	 * @param req	请求
	 * @param resp	回复
	 */
	public static void clearAllCookies(HttpServletRequest req, HttpServletResponse resp){
		//检查是否有Cookies
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
	 * addPrdtToDB:将产品信息持久化到DataBase，并删除所有Cookie. <br/>
	 *
	 * @author LiDongSheng
	 * @param req	请求
	 * @param resp	回复
	 * @param user	用户
	 */
	public static void addPrdtToDB(HttpServletRequest req, HttpServletResponse resp, User user){
		//获取Cookie
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
				//含有该商品
				ProductDAO.updatePrdtFromUser(user, prdt);
			}
			else{
				//不含有该商品
				ProductDAO.addPrdtToUser(user, prdt);
			}
			//删除该Cookie
			c.setMaxAge(0);
		}
	}
	
	/**
	 * 
	 * getPrdtFromCookies:从Cookie中读取所有的产品. <br/>
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
