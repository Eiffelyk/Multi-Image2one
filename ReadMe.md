一个简单的 将多个image中的图片抓取 合成同一张图bitmap

主要实现思路  将imageView的图片缓冲开启，之后从中获取bitmap 加入到canvas上之后关闭缓冲区，然后将canvas保存成bitmap，如果你后续需要File保存到本地文件，或者是post到web都根据bitmap进行操作，但是一定要注意内存

private Bitmap SaveBitmap(ArrayList<ImageView> arrayList) {
        if (arrayList==null|arrayList.size()<=0) return null;
        Bitmap bmOverlay = Bitmap.createBitmap(arrayList.get(0).getWidth(), arrayList.get(0).getHeight(), Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(bmOverlay);
        for (ImageView imageView :arrayList){
            imageView.setDrawingCacheEnabled(true);
            canvas.drawBitmap(imageView.getDrawingCache(), 0, 0, null);
            imageView.setDrawingCacheEnabled(false);
        }
        canvas.save(Canvas.ALL_SAVE_FLAG);// 保存
        canvas.restore();// 存储
        return bmOverlay;
    }


需要注意
1. imageView.setDrawingCacheEnabled(true); 允许调用imageView的图片缓冲区 之后必须调用 setDrawingCacheEnabled(false)清空画图缓冲区，否则，下一次用getDrawingCache()方法回去图像时，还是原来的图像
2.由于是多个图片合成需要根据图片的多少、大小具体情况  确定是否需要开启独立线程去进行这个耗时操作o 后续会加入到此项目中
3.由于多图操作，可能造成内存溢出，需要注意回收内存，这个后续会加入到此项目中