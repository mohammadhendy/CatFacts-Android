package com.mohammadhendy.catfacts.catfactslist;

import com.mohammadhendy.catfacts.base.mvp.BaseListView;
import com.mohammadhendy.catfacts.model.core.CatFact;

import java.io.File;

/**
 * Created by mohammadhendy on 11/14/17.
 */

public interface CatFactsListView extends BaseListView<CatFact> {

    void share(File file);
}
