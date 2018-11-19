package com.quintype.androidoxygen.models.storypresenter;

import android.view.View;

import com.quintype.androidoxygen.models.story.StoryElement;

public interface StoryElementBinder {
    void bind(StoryElement var1);

    View getView();

    boolean recreate();
}
