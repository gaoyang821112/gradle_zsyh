package com.gaoyang.util;

public class API_URL {

	public final static String[][] userArray = new String[][] {
			{
					"高洋",
					"?p0=a&p1=35&p2=12c9be35016244e99ba2fd8e1bb749ce&p3=522e29c023924b379e53df1bf555587c8&p4=08a20b452bc4446c99f23f290632acec&p5=17A8AF7157CD46FBAC97188626CA08A0&p6=477786267&p7=928817042e334b46b5322d21277da205&p8=aa7b2c7de485494cb7fb895377e530e9&p9=null&p10=d7c36fcce8074b8ca94553929ea2190d",
					"17A8AF7157CD46FBAC97188626CA08A0" },
			{
					"荣佳丽",
					"?p0=a&p1=31&p2=13ed11a11b2040eda7aad3b15218ae0f&p3=FE73ED0E3803F5C41A8A573EFA114AF7&p4=4079cc4296164fa3a33535eb9dc99efe&p5=9861c5e60f3c48fd98579918c4c44a1f&p6=471677430&p7=967ecdf77abf4b1b8da34ae8a12b58b9&p8=7e10a746a41e4f9782be29420e6eb518&p9=null&p10=36c7dfb8db35402d9d05da0f6582751b",
					"9861c5e60f3c48fd98579918c4c44a1f" },
			{
					"王志强",
					"?p0=a&p1=31&p2=78faedc5eb27401980b6734a316f8be2&p3=D9D850D6908A54E77EAFAD2A4D3BE30C&p4=e69d447e339a4966955f26c4cd03a6bb&p5=c9d2cf6c8b3c4f519695ab59c35f00cb&p6=471678020&p7=4a47b3639a364d85a92088b79c4a6c0c&p8=f13d454eabcb4b3a98b8660f3e61188b&p9=null&p10=a943ef6af7814a4486ceebcab49d731f",
					"c9d2cf6c8b3c4f519695ab59c35f00cb" },
			{
					"王悦",
					"?p0=a&p1=31&p2=deb54f0fe5314204a8c04e3dc06d8168&p3=751DEB5D047AFF136BBDA42FBD99B4EC&p4=b2ff0501b28f418f9cacd6f377fd791d&p5=7c521c86114e4f8aa2c901a91d5d25c5&p6=471679289&p7=47155f6109224699a029e7ad6b32c302&p8=2570bbe079cc41a49e877ffb2bdc1274&p9=null&p10=9080f5dfea0a47ceaf03a06a0e33c56c",
					"23e645508dde487c92069a231ff99d9f" },
			{
					"李秀龙",
					"?p0=a&p1=35&p2=f59b4efef5454bb18561c4dde7974c67&p3=522e29c023924b379e53df1bf555587c8&p4=8013b5f1ae704ce3a20075dcea7ded58&p5=17A8AF7157CD46FBAC97188626CA08A0&p6=477725532&p7=928817042e334b46b5322d21277da205&p8=bfcff26b5b294170b6bfa7cf31f2369b&p9=09642418d500463a9fd4f2f9eab03cbf&p10=018744f4a94c435099095ba6132d02b4",
					"8D5BD870E2AC45D4A96A0EF97D8D841E" },
			{
					"李秀龙",
					"?p0=a&p1=35&p2=f59b4efef5454bb18561c4dde7974c67&p3=522e29c023924b379e53df1bf555587c8&p4=8013b5f1ae704ce3a20075dcea7ded58&p5=17A8AF7157CD46FBAC97188626CA08A0&p6=477725532&p7=928817042e334b46b5322d21277da205&p8=bfcff26b5b294170b6bfa7cf31f2369b&p9=09642418d500463a9fd4f2f9eab03cbf&p10=018744f4a94c435099095ba6132d02b4",
					"51aa1808222149e0ac9ea4d32d991bb8" }
			};

	public final static String ZSYH_ADDRESS1 = "http://mlife.cmbchina.com/NeptuneApp/";
	public final static String ZSYH_ADDRESS2 = "http://piao.cmbchina.com/";
	public final static String ZSYH_ADDRESS3 = "http://mlife.cmbchina.com/";
	public final static String CITY = "10";


	public static class ZSYH {
		public static final String PRODUCT_9POINT_LIST_URL = "queryProductsGMV5.json";
		public static final String PRODUCT_3HOUR_LIST_URL = "queryProductList.json";


		/**
		 * 获取抢购产品列表
		 */
		public static final String PRODUCT_LIST_URL =  "queryProductList.json";

		/**
		 * 创建订单
		 */
		public static final String CREATE_ORDER_URL =  "Cashier/receiveMerchantOrder.json";

		/**
		 * 创建订单2
		 */
		public static final String PRODUCT_9POINT_LIST_URL2 = "yummy-portal/JSONServer/execute.do";

		/**
		 * 创建订单3
		 */
		public static final String PRODUCT_9POINT_LIST_URL3 = "createOrderV5.json";

		public static final String LAGANXIANG = "Campaign/panicBuyV2.json";

		public static final String TENSTORM = "TenStorm/receivePrize.json";
	}

	public static class BAOJIE {

		public static final String BAOJIE_9_URL = "http://shenghuojia.campaign.socialjia.com/index.php?token=85c34e5833eaf3738f439e26a59b761f&a=NineLife&m=immediatelyBuy";

		public static final String BAOJIE_9_LIST_URL1 = "http://shenghuojia.campaign.socialjia.com/index.php?a=ScoreExchange&m=index&brandid=1&apiKey=8a674fcd013ccbc5e0fc206d3b1771c4&timestamp=1451443756&sig=8e4b7a7e3877904d8ac7a89550a8c885&openid=";
		public static final String BAOJIE_9_LIST_URL2 = "http://shenghuojia.campaign.socialjia.com/index.php?a=NineLife&m=index";
		public static final String BAOJIE_9_LIST_URL3 = "http://shenghuojia.campaign.socialjia.com/index.php?a=NineLife&m=immediatelyBuy&token=";
	}

	public static class SHIHUI {
		public static final String SHIHUI_LIST_URL = "http://api.hiwemeet.com/sh/welfare/list";
		public static final String SHIHUI_LOTTERY_URL = "http://api.hiwemeet.com/sh/welfare/lottery";
		public static final String SHIHUI_JOIN_URL = "http://api.hiwemeet.com/sh/welfare/join";
	}


}