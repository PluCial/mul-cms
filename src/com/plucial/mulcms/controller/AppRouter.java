package com.plucial.mulcms.controller;

import org.slim3.controller.router.RouterImpl;
import org.slim3.util.RequestUtil;

/**
 * 公開部分ルーティング
 * addRoutingメソッドの順番変更は禁止！
 * @author takahara
 *
 */
public class AppRouter extends RouterImpl {

	public AppRouter() {
	    addRouting(
            "/mulcms/",
            "/mulcms/index");
	    addRouting(
            "/{lang}/",
            "/front?path=/index.html&lang={lang}");
        addRouting(
            "/{lang}/{p1}.html",
            "/front?path=/{p1}.html&lang={lang}");
        addRouting(
            "/{lang}/{p1}/{p2}.html",
            "/front?path=/{p1}/{p2}.html&lang={lang}");
        addRouting(
            "/{lang}/{p1}/{p2}/{p3}.html",
            "/front?path=/{p1}/{p2}/{p3}.html&lang={lang}");
        addRouting(
            "/{lang}/{p1}/{p2}/{p3}/{p4}.html",
            "/front?path=/{p1}/{p2}/{p3}/{p4}.html&lang={lang}");
        addRouting(
            "/{lang}/{p1}/{p2}/{p3}/{p4}/{p5}.html",
            "/front?path=/{p1}/{p2}/{p3}/{p4}/{p5}.html&lang={lang}");
        
	}
	
	@Override
	   public boolean isStatic(String path) throws NullPointerException {
	       boolean isStatic = super.isStatic(path);

	       if("html".equals(RequestUtil.getExtension(path))) {
	           return false;
	       } else {
	           return isStatic;
	       }
	   }


}
