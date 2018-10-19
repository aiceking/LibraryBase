# LibraryBase
[![](https://jitpack.io/v/NoEndToLF/LibraryBase.svg)](https://jitpack.io/#NoEndToLF/LibraryBase)  

Step 1. Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        compile 'com.github.NoEndToLF:LibraryBase:1.6.4'
	}
# 使用：在Application的onCreate中初始化
``` java
BaseLibraryManager<NetApiService> baseLibraryInitHelp= BaseLibraryManager.getInstance();
        //1--4顺序不能变
        //1,设置context
        baseLibraryInitHelp.setContext(context);
        //2,设置baseurl和证书
        baseLibraryInitHelp.setBaseUrl("测试站BaseUrl","正式站BaseUrl");
//        baseLibraryInitHelp.setHttpsCer(false);/**是否有证书,默认为false*/
        baseLibraryInitHelp.setHttpsCer(true);
        /**测试站和生产站证书，放在assets目录下*/
        baseLibraryInitHelp.setCerNames(new String[]{"**.cer","**.cer"});
        //3,设置log开关
        baseLibraryInitHelp.setLogger(BuildConfig.isLog);
        //4,设置测试站还是生产站(可随时切换)
        baseLibraryInitHelp.setDebug(false);
        //其他设置
        baseLibraryInitHelp.setUpLoadImgService();/**图片上传*/
        baseLibraryInitHelp.setApiService(NetApiService.class);/**初始化Api*/
```
