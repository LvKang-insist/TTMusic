package com.car.lib_image_loader.app;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RemoteViews;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.NotificationTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.car.lib_image_loader.image.Utils;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @author 345 QQ:1831712732
 * @name TTMusic
 * @class name：com.car.lib_image_loader.app
 * @time 2019/12/10 21:52
 * @description 图片加载类，支持为各种 view ，notification ，appwidget ，ViewGroup 加载图片
 */
public class ImageLoaderManager {


    private ImageLoaderManager() {
    }


    private static class SignletonHolder {
        private static final ImageLoaderManager INSTANCE = new ImageLoaderManager();
    }

    public static ImageLoaderManager getInstance() {
        return SignletonHolder.INSTANCE;
    }


    /**
     * 为 ImageView 加载图片
     */
    public void displayImageForView(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .asBitmap()
                .load(url)
                .apply(initCommonRequestOption())
                .transition(BitmapTransitionOptions.withCrossFade())
                .into(imageView);
    }

    /**
     * 为 ImageView 加载圆形图片
     *
     * @param imageView
     * @param url
     */
    public void displayImageForCircle(final ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .asBitmap()
                .load(url)
                .apply(initCommonRequestOption())
                .into(new BitmapImageViewTarget(imageView) {
                    //将 imageView 包装成 target
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(imageView.getResources(), resource);
                        //设置圆形 drawable
                        drawable.setCircular(true);
                        imageView.setImageDrawable(drawable);
                    }
                });
    }

    /**
     * 为 ViewGroup 设置背景，并模糊处理
     *
     * @param group
     * @param ulr
     */
    public void displayImageForViewGroup(final ViewGroup group, String ulr) {
        Glide.with(group.getContext())
                .asBitmap()
                .load(ulr)
                .apply(initCommonRequestOption())
                .into(new SimpleTarget<Bitmap>() {
                    @SuppressLint("CheckResult")
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        final Bitmap res = resource;
                        Observable.just(resource)
                                .map(new Function<Bitmap, Drawable>() {
                                    @Override
                                    public Drawable apply(Bitmap bitmap) {
                                        Drawable drawable = new BitmapDrawable(
                                                Utils.doBlur(res, 100, true)
                                        );
                                        return drawable;
                                    }
                                })
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Consumer<Drawable>() {
                                    @Override
                                    public void accept(Drawable drawable) throws Exception {
                                        group.setBackground(drawable);
                                    }
                                });
                    }
                });
    }


    /**
     * 为notification加载图
     */
    public void displayImageForNotification(Context context, RemoteViews rv, int id,
                                            Notification notification, int notificationId, String url) {
        this.displayImageForTarget(context,
                initNotificationTarget(context, id, rv, notification, notificationId), url);
    }

    private Target initNotificationTarget(Context context, int id, RemoteViews rv, Notification notification, int notificationid) {
        return new NotificationTarget(context, id, rv, notification, notificationid);
    }


    private void displayImageForTarget(Context context, Target target, String url) {
        Glide.with(context)
                .asBitmap()
                .load(url)
                .apply(initCommonRequestOption())
                .transition(BitmapTransitionOptions.withCrossFade())
                .fitCenter()
                .into(target);
    }

    @SuppressLint("CheckResult")
    private RequestOptions initCommonRequestOption() {
        RequestOptions options = new RequestOptions();
        options.placeholder(android.R.mipmap.sym_def_app_icon)
                .error(android.R.mipmap.sym_def_app_icon)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .skipMemoryCache(false)
                .priority(Priority.NORMAL);
        return options;
    }

}
