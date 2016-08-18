package cn.xiaocool.hongyunschool.utils;

import android.content.Context;

import cn.xiaocool.hongyunschool.view.CustomDialog;


public class LoadingViewManager {

	private CustomDialog dialog;

	private static class PopupWindowManagerHolder {
		public static final LoadingViewManager instance = new LoadingViewManager();
	}

	public static LoadingViewManager getInstance() {
		return PopupWindowManagerHolder.instance;
	}
		

	public void showWindowToLoading(Context context) {
		if(dialog == null){
			dialog = new CustomDialog(context);
			dialog.show();
		}else{
			dismissWindowToLoading();
			showWindowToLoading(context);
		}
	}

	public void dismissWindowToLoading() {
		if(dialog !=null){
			dialog.cancel();
			dialog =null;
		}
	}
	
}
