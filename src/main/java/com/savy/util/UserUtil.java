package com.savy.util;



/**
 * 
 * 获取用户id
 * @author chenqi
 *
 */
public class UserUtil {

	/**
	 * 获取用户id
	 * @param tokenId
	 * @return
	 * @throws Exception
	 */
	public static int getUserIdByTokenId(String tokenId) throws Exception{
		int userId = 0;
		try {
			userId = Integer.parseInt(LinkEncrypt.getUserIdByToken(tokenId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userId;
	}
	
	public static void main(String[] args) {
		try {
			
			String tokenId = LinkEncrypt.generateToken("3");
			System.out.println(tokenId);
			System.out.println(UserUtil.getUserIdByTokenId("Q29vbGFkaW5nLVNFQ1RFVDoxNDM0MTc1MTkwOjI4"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}