package cn.nurasoft.zero.php;


import android.app.Activity;

import android.os.Bundle;
import android.util.Log;

import com.github.barteksc.pdfviewer.PDFView;

import net.sqlcipher.database.SQLiteDatabase;



/*
 * Created by Administrator on 22/02/2018.
 */

public class Tutorial_Activity extends Activity {

    PDFView pdfView;
    DatabaseHelperClass helperClass;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(cn.nurasoft.zero.php.R.layout.tutorial_view);

        pdfView = findViewById(cn.nurasoft.zero.php.R.id.pdfview);

        helperClass = new DatabaseHelperClass(Tutorial_Activity.this);

        db = helperClass.getReadableDatabase("ILOVEYOU");

        helperClass.openDataBase();

        Bundle bundle=getIntent().getExtras();

        String title=bundle.getString("title");

        Log.e("title",title);

        //pdfView.fromAsset("pdf/vb.pdf").load();
        pdfView.fromBytes(helperClass.getPDF(bundle.getString("title")))
                //.pages(0) // all pages are displayed by default
           //     .enableSwipe(true) // allows to block changing pages using swipe
           //     .swipeHorizontal(false)
           //     .enableDoubletap(true)
            //    .defaultPage(0)
                // allows to draw something on the current page, usually visible in the middle of the screen
                // .onDraw(onDrawListener)
                // allows to draw something on all pages, separately for every page. Called only for visible pages
                // .onDrawAll(onDrawListener)
                // .onLoad(onLoadCompleteListener) // called after document is loaded and starts to be rendered
                // .onPageChange(onPageChangeListener)
                // .onPageScroll(onPageScrollListener)
                // .onError(onErrorListener)
                // .onPageError(onPageErrorListener)
                // .onRender(onRenderListener) // called after document is rendered for the first time
                // called on single tap, return true if handled, false to toggle scroll handle visibility
                //  .onTap(onTapListener)
           //     .enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
             //   .password(null)
             //   .scrollHandle(null)
             //   .enableAntialiasing(true) // improve rendering a little bit on low-res screens
                // spacing between pages in dp. To define spacing color, set view background
             //   .spacing(0)
                //  .linkHandler(DefaultLinkHandler)
            //    .pageFitPolicy(FitPolicy.WIDTH)
                .load();
        helperClass.close();

    }


}

